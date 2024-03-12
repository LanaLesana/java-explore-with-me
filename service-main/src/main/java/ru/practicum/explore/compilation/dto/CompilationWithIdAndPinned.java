package ru.practicum.explore.compilation.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.explore.event.dto.EventShortDto;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationWithIdAndPinned {
    Long id;
    String title;
    Boolean pinned;
    List<EventShortDto> events;
}
