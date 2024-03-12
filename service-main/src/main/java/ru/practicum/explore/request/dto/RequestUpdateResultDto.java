package ru.practicum.explore.request.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestUpdateResultDto {

    List<RequestDto> confirmedRequests = new ArrayList<>();

    List<RequestDto> rejectedRequests = new ArrayList<>();
}
