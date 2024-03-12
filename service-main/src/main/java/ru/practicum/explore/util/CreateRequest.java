package ru.practicum.explore.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import ru.practicum.explore.exceptions.ParameterException;

@UtilityClass
public class CreateRequest {
    public static PageRequest createRequest(int from, int size) {
        if (from < 0) {
            throw new ParameterException(String.format("Параметр from должен быть больше нуля.. from = %s", from));
        }
        if (size <= 0) {
            throw new ParameterException(String.format("Размер параметра size должен быть больше или равен нулю. size = %s", size));
        }
        return PageRequest.of(from, size);
    }
}
