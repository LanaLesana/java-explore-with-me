package ru.practicum.explore.request.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
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
