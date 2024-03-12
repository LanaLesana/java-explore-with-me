package ru.practicum.explore.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.explore.location.LocationDto;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventUpdateDto {
    @Size(min = 3, max = 120)
    String title;

    @Size(min = 20, max = 7000)
    String description;

    @Size(min = 20, max = 2000)
    String annotation;

    Long category;

    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;

    LocationDto location;

    Long participantLimit;

    Boolean requestModeration;

    Boolean paid;
}
