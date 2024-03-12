package ru.practicum.explore.useDto.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatsDto {
     String app;
     String uri;
     Long hits;
}
