package ru.practicum.explore.compilation.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.dto.CompilationNewDto;
import ru.practicum.explore.compilation.dto.CompilationUpdateDto;
import ru.practicum.explore.compilation.service.CompilationService;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
@RequestMapping("/admin/compilations")
public class AdminCompilationController {

    private final CompilationService compilationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto addCompilation(@Valid @RequestBody CompilationNewDto compilationDto) {
        log.info(String.format("Получен POST- запрос: /admin/compilations (Admin). Добавлена подборка событий (dto): %s.", compilationDto));
        return compilationService.addCompilation(compilationDto);
    }

    @PatchMapping("/{compId}")
    public CompilationDto updateCompilation(@PathVariable Long compId,
                                            @Valid @RequestBody CompilationUpdateDto compilationDto) {
        log.info(String.format("Получен PATCH- запрос: /admin/compilations{compId} (Admin). Подборка событий (id) %s обновлена (dto): %s", compId, compilationDto));
        return compilationService.updateCompilation(compId, compilationDto);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCompilation(@PathVariable Long compId) {
        log.info(String.format("Получен DELETE- запрос: /admin/compilations{compId} (Admin). Удалена подборка событий (id): %s.", compId));
        compilationService.deleteCompilation(compId);
    }
}

