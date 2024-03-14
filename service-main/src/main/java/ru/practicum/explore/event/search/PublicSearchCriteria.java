package ru.practicum.explore.event.search;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublicSearchCriteria {
     String text;
     List<Long> categories;
     Boolean paid;
     LocalDateTime rangeStart;
     LocalDateTime rangeEnd;
     Boolean onlyAvailable;
     String sort;
     int from;
     int size;
     HttpServletRequest request;
}
