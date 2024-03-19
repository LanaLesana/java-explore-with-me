package ru.practicum.explore.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.user.dto.UserDto;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventShortDto {
    Long id;

    String title;

    String description;

    String annotation;

    CategoryDto category;

    UserDto initiator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;

    Long confirmedRequests;

    Boolean paid;

    Long views;
}
