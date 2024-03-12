package ru.practicum.explore.category.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto addCategory(@Valid @RequestBody CategoryDto categoryDto) {
       log.info(String.format("Получен POST- запрос: /admin/categories (Admin). Добавление новой категории (dto): %s.", categoryDto));
        return categoryService.addCategory(categoryDto);
    }

    @PatchMapping("/{catId}")
    public CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto,
                                      @PathVariable Long catId) {
        log.info(String.format("Получен PATCH- запрос: /admin/categories/{catId} (Admin). Категория (id) %s обновлена  (dto): %s", catId, categoryDto));
        return categoryService.updateCategory(categoryDto, catId);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable long catId) {
        log.info("Delete category with id {}", catId);
        log.info(String.format("Удалить категорию с идентификатором {}: %s.", catId));
        categoryService.deleteCategory(catId);
    }


}
