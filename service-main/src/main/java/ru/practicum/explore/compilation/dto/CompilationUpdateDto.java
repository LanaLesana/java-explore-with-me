package ru.practicum.explore.compilation.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationUpdateDto {

    @Size(max = 50, min = 1)
    String title;
    Boolean pinned;
    List<Long> events;
}
