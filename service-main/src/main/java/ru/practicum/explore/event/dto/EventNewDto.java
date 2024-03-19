package ru.practicum.explore.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.Getter;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.practicum.explore.location.LocationDto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventNewDto {
    Long id;

    @NotBlank
    @Size(min = 3, max = 120)
    String title;

    @NotBlank
    @Size(min = 20, max = 7000)
    String description;

    @NotBlank
    @Size(min = 20, max = 2000)
    String annotation;

    @NotNull
    Long category;

    @NotNull
    LocationDto location;

    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;

    Long participantLimit;

    Boolean requestModeration;

    Boolean paid;

    public Long getParticipantLimit() {
        if (participantLimit == null) {
            participantLimit = 0L;
        }
        return participantLimit;
    }

    public Boolean getRequestModeration() {
        if (requestModeration == null) {
            requestModeration = true;
        }
        return requestModeration;
    }

    public Boolean getPaid() {
        if (paid == null) {
            paid = false;
        }
        return paid;
    }
}
