package ru.practicum.explore.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;


@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModelStats {
    @Column(name = "app", nullable = false)
     String app;
    @Column(name = "uri", nullable = false)
     String uri;
    @Column(name = "countIp", nullable = false)
     Long hits;
}
