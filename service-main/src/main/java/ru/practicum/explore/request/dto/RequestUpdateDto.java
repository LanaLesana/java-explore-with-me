package ru.practicum.explore.request.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.explore.enums.RequestStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestUpdateDto {
    List<Long> requestIds;

    RequestStatus status;
}
