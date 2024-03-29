package ru.practicum.explore.event;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import ru.practicum.explore.category.Category;
import ru.practicum.explore.enums.EventState;
import ru.practicum.explore.location.Location;
import ru.practicum.explore.request.Request;
import ru.practicum.explore.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "events")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;
    @Length(min = 3, max = 120)
    @Column(name = "title", nullable = false)
    String title;
    @NotBlank
    @NotNull
    @Length(min = 20, max = 7000)
    @Column(name = "description", nullable = false)
    String description;
    @NotBlank
    @NotNull
    @Length(min = 20, max = 2000)
    @Column(name = "annotation", nullable = false)
    String annotation;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "initiator_id", nullable = false)
    User initiator;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    Location location;

    @Column(name = "event_date")
    LocalDateTime eventDate;

    @Column(name = "created_on")
    LocalDateTime createdOn;

    @Column(name = "published_on")
    LocalDateTime publishedOn;

    @Column(name = "participant_limit")
    Long participantLimit;

    @OneToMany
    @JoinColumn(name = "event_id")
    List<Request> allRequests;

    @Column(name = "request_moderation")
    Boolean requestModeration;


    @Column(name = "paid")
    Boolean paid;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    EventState state;

    @Column(name = "views")
    Long views;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Objects.equals(getEventDate(), event.getEventDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEventDate());
    }

}
