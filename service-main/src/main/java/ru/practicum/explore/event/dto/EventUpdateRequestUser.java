package ru.practicum.explore.event.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import ru.practicum.explore.enums.StateActionUser;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventUpdateRequestUser extends EventUpdateDto {
    private StateActionUser stateAction;
}
