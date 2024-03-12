package ru.practicum.explore.event.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import ru.practicum.explore.enums.StateActionAdmin;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class EventUpdateRequestAdmin extends EventUpdateDto {
    private StateActionAdmin stateAction;
}
