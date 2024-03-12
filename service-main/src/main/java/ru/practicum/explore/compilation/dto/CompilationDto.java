package ru.practicum.explore.compilation.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.explore.event.dto.EventShortDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationDto {
    Long id;
    @NotBlank
    @Size(max = 50, min = 1)
    String title;
    Boolean pinned;
    List<EventShortDto> events;

    public Boolean getPinned() {
        if (pinned == null) {
            pinned = false;
        }
        return pinned;
    }
}
