package ru.practicum.explore.comments.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.practicum.explore.comments.dto.CommentDto;
import ru.practicum.explore.comments.dto.CommentShortDto;
import ru.practicum.explore.comments.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("/users/{userId}/events/{eventId}/comments")
public class PrivateCommentController {

    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommentShortDto saveComment(@PathVariable Long userId,
                                       @PathVariable Long eventId,
                                       @Valid @RequestBody CommentDto commentDto) {
        log.info("Получен POST-запрос users/{userId}/events/{eventId}/comments на добавление комментария {}" +
                " от пользователя (id): {} к событию (id): {}", commentDto, userId, eventId);
        log.info(String.format("Получен POST-запрос users/{userId}/events/{eventId}/comments на добавление комментария %s от пользователя (id): %s к событию (id): %s", commentDto, userId, eventId));
        return commentService.saveComment(userId, eventId, commentDto);
    }


    @PatchMapping("/{commentId}")
    public CommentShortDto updateComment(@PathVariable Long userId,
                                         @PathVariable Long eventId,
                                         @PathVariable Long commentId,
                                         @Valid @RequestBody CommentDto commentDto) {
        log.info(String.format("Получен PATCH- запрос users/{userId}/events/{eventId}/comments/{commentId} на обновление комментария (id): %s от пользователя (id): %s к событию (id): %s, комментарий: %s", commentId, userId, eventId, commentDto));
        return commentService.updateComment(userId, eventId, commentId, commentDto);
    }

    @GetMapping("/{commentId}")
    public CommentShortDto getCommentByIdForEvent(@PathVariable Long userId,
                                                  @PathVariable Long eventId,
                                                  @PathVariable Long commentId) {
        log.info(String.format("Получен GET- запрос /users/{userId}/events/{eventId}/comment/{commentId} (Private). Просмотр комментария (id): %s fк событию (id): %s, от пользователя (id): %s", userId, eventId, commentId));
        return commentService.getCommentByIdForEvent(userId, eventId, commentId);
    }

    @GetMapping
    public List<CommentShortDto> getCommentsForEvent(@PathVariable Long userId,
                                                     @PathVariable Long eventId,
                                                     @RequestParam(required = false, defaultValue = "0") int from,
                                                     @RequestParam(required = false, defaultValue = "10") int size) {
        log.info(String.format("Получен GET- запрос /users/{userId}/events/{eventId}/comment/ (Private). Просмотр опубликованного комментариев (dto) к событию (id): %s, from: %s to: %s, от пользователя (id): %s", eventId, from, size, userId));
        return commentService.getCommentsForEvent(userId, eventId, size, from);
    }

    @DeleteMapping("/{commentId}")
    public void deletedComment(@PathVariable Long userId,
                               @PathVariable Long eventId,
                               @PathVariable Long commentId) {
        log.info(String.format("Получен DELETE-запрос /users/{userId}/events/{eventId}/comment/{commentId} (Private). Удаление комментария(id): %s к событию (id): %s, от пользователя (id): %s", userId, eventId, commentId));
        commentService.deleteCommentByUser(userId, eventId, commentId);
    }
}
