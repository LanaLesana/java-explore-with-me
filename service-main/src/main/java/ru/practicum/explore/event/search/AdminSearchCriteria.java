package ru.practicum.explore.event.search;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.explore.enums.EventState;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminSearchCriteria {
     List<Long> users;
     List<EventState> states;
     List<Long> categories;
     LocalDateTime rangeStart;
     LocalDateTime rangeEnd;
     int from;
     int size;
}
