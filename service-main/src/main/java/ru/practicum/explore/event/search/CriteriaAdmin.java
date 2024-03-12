package ru.practicum.explore.event.search;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.practicum.explore.enums.EventState;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CriteriaAdmin {
     List<Long> users;
     List<EventState> states;
     List<Long> categories;
     LocalDateTime rangeStart;
     LocalDateTime rangeEnd;
}
