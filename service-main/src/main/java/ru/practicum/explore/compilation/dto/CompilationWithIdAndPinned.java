package ru.practicum.explore.compilation.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.Getter;
import lombok.AccessLevel;
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
