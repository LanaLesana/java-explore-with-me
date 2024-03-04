package ru.practicum.explore.useDto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HitDto {

     String app;
     String uri;
     String ip;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     LocalDateTime timestamp;
}