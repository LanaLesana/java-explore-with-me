package ru.practicum.explore.comments.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.explore.comments.Comment;
import ru.practicum.explore.comments.CommentRepository;
import ru.practicum.explore.comments.dto.CommentDto;
import ru.practicum.explore.comments.dto.CommentMapper;
import ru.practicum.explore.comments.dto.CommentShortDto;
import ru.practicum.explore.enums.EventState;
import ru.practicum.explore.enums.RequestStatus;
import ru.practicum.explore.event.Event;
import ru.practicum.explore.event.repositories.EventRepository;
import ru.practicum.explore.exceptions.NotFoundException;
import ru.practicum.explore.exceptions.ParameterException;
import ru.practicum.explore.request.RequestRepository;
import ru.practicum.explore.user.User;
import ru.practicum.explore.user.UserRepository;
import ru.practicum.explore.util.CreateRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final EventRepository eventRepository;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CommentShortDto saveComment(Long userId, Long eventId, CommentDto commentDto) {
        User user = getUser(userId);
        Event event = getEvent(eventId);
        if (event.getState() == null) {
            throw new ParameterException("Состояние события не может быть пустым");
        }
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ParameterException("Комментарии доступны только к опубликованным событиям");
        }
        if (commentDto.getCreated().isAfter(event.getEventDate()) && (event.getRequestModeration() ||
                event.getParticipantLimit() != 0)) {
            boolean rq = requestRepository.existsByRequesterIdAndEventIdAndStatus(userId, eventId,
                    RequestStatus.CONFIRMED);
            if (!rq || !userId.equals(event.getInitiator().getId())) {
                throw new ParameterException("Только подтвержденный участник или инициатор события " +
                        "может оставлять комментарии");
            }
        }
        Comment after = commentRepository.save(CommentMapper.toComment(user, event, commentDto));
        log.info("Добавлен комментарий");
        return CommentMapper.toCommentShortDto(after);
    }

    @Override
    @Transactional
    public CommentShortDto updateComment(Long userId, Long eventId, Long commentId, CommentDto commentDto) {
        getUser(userId);
        checkEvent(eventId);

        Comment comment = getComment(commentId);

        if (!userId.equals(comment.getAuthor().getId())) {
            throw new ParameterException("Только автор может изменить комментарий");
        }

        if (comment.getText().equals(commentDto.getText())) {
            throw new ParameterException("Комментарий не изменён");
        }

        comment.setText(commentDto.getText());
        Comment after = commentRepository.save(comment);
        log.info("Комментарий обновлён");
        return CommentMapper.toCommentShortDto(after);
    }

    @Override
    @Transactional
    public void deleteCommentByUser(Long userId, Long eventId, Long commentId) {
        getUser(userId);
        Event event = getEvent(eventId);
        Comment comment = getComment(commentId);

        if (userId.equals(event.getInitiator().getId()) || userId.equals(comment.getAuthor().getId())) {
            commentRepository.deleteById(commentId);
            log.info("Комментарий {} удалён", commentId);
        } else {
            throw new ParameterException(String.format("Только автор %s или инициатор %s события может удалить комментарий", userId, eventId));
        }
    }

    @Override
    @Transactional
    public void deleteCommentByAdmin(Long commentId) {
        log.info("Комментарий удалён администратором");
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    public CommentShortDto getCommentByIdForEvent(Long userId, Long eventId, Long commentId) {
        checkExistUserById(userId);
        checkEvent(eventId);

        Comment commentById = commentRepository.findByIdForEvent(EventState.PUBLISHED.toString(), eventId, commentId)
                .orElseThrow(() -> new NotFoundException(String.format("Комментарий с id = %s не найден", commentId)));
        log.info(String.format("Комментарий (id): %s от пользователя (id): %s к событию (id): %s :", commentId, userId, eventId));
        return CommentMapper.toCommentShortDto(commentById);
    }

    @Override
    @Transactional
    public List<CommentShortDto> getCommentsForEvent(Long eventId, int size, int from) {
        checkEvent(eventId);

        PageRequest pageRequest = CreateRequest.createRequest(from, size);
        List<Comment> pageAllComments = commentRepository.findAllByStateAndEventId(EventState.PUBLISHED.toString(),
                eventId, pageRequest);
        log.info(String.format("Список комментариев к событию (id): %s", eventId));
        return CommentMapper.listToCommentShortDto(pageAllComments);
    }

    @Override
    @Transactional
    public List<CommentShortDto> getCommentsForEvent(Long userId, Long eventId, int size, int from) {
        getUser(userId);
        checkEvent(eventId);

        PageRequest pageRequest = CreateRequest.createRequest(from, size);
        List<Comment> pageAllCommentsForEvent = commentRepository.findAllByStateAndEventId(
                EventState.PUBLISHED.toString(), eventId, pageRequest);
        log.info(String.format("Список комментариев к событию (id): %s от пользователя (id) %s: ", eventId, userId));
        return CommentMapper.listToCommentShortDto(pageAllCommentsForEvent);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь не найден id: %s", userId)));
    }

    private Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(String.format("Комментарий не найден id: %s", commentId)));
    }

    private Event getEvent(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(String.format("Событие не найдено id: %s", eventId)));
    }

    private void checkEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new NotFoundException(String.format("Событие не найдено id: %s", eventId));
        }
    }

    private void checkExistUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(String.format("Пользователь не найден id: %s", userId));
        }
    }

}
