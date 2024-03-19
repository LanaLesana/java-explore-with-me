package ru.practicum.explore.event.search;

import lombok.Builder;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CriteriaUser {
     String text;
     List<Long> categories;
     Boolean paid;
     LocalDateTime rangeStart;
     LocalDateTime rangeEnd;
     Boolean onlyAvailable;
}
