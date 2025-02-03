package dev.kielblock.movieflix.mapper;

import dev.kielblock.movieflix.controller.request.CategoryRequest;
import dev.kielblock.movieflix.controller.response.CategoryResponse;
import dev.kielblock.movieflix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest) {
        return Category
                .builder()
                .name(categoryRequest.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
