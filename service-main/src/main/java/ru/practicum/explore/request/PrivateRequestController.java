package ru.practicum.explore.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.request.dto.RequestDto;
import ru.practicum.explore.request.service.RequestService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("/users/{userId}/requests")
public class PrivateRequestController {

    private final RequestService requestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequestDto saveRequest(@PathVariable Long userId,
                                  @RequestParam Long eventId) {
        log.info(String.format("Получен POST- запрос: users/{userId}/requests на создание запроса участия пользователя (id) %s в событии: (id) %s", userId, eventId));
        return requestService.addRequest(userId, eventId);
    }

    @GetMapping
    public List<RequestDto> getAllRequestsParticipationInOtherPeoplesEvents(@PathVariable Long userId) {
        log.info(String.format("Получен GET-запрос users/{userId}/requests на получение информации о заявках текущего пользователя (id) %s на участие в чужих событиях ", userId));
        return requestService.getAllRequestsParticipationInOtherPeoplesEvents(userId);
    }

    @PatchMapping("{requestId}/cancel")
    public RequestDto cancelRequest(@PathVariable Long userId,
                                    @PathVariable Long requestId) {
        log.info(String.format("Получен PATCH- запрос users/{userId}/requests/{requestId}/cancel об отмене своего запроса (id) %s на участие в событии от пользователя (id) %s ", requestId, userId));
        return requestService.cancelRequest(userId, requestId);
    }
}
