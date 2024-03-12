package ru.practicum.explore.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;



import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "hits", schema = "public")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModelHit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
    @Column(name = "app", nullable = false)
     String app;
    @Column(name = "uri", nullable = false)
     String uri;
    @Column(name = "ip", nullable = false)
     String ip;
    @Column(name = "date_time", nullable = false)
     LocalDateTime timestamp;

}
