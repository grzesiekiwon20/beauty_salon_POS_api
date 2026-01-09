package com.beautysalon.webmvc;

import com.beautysalon.category.CategoryServiceImpl;
import com.beautysalon.category.dto.CategoryResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CategoryFormatter implements Formatter<CategoryResponse> {


    @Autowired
    private CategoryServiceImpl categoryService;

    @Override
    public CategoryResponse parse(@NonNull String text,@NonNull Locale locale) throws ParseException {
        final Long categoryId = Long.parseLong(text);
        return this.categoryService.findCategoryResponseById(categoryId);
    }

    @Override
    @NonNull
    public String print(CategoryResponse object,@NonNull Locale locale) {
        return (object != null? object.categoryId().toString() : "");

    }
}
