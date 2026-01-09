package com.beautysalon.webmvc;


import com.beautysalon.category.CategoryService;
import com.beautysalon.category.SubCategory;
import com.beautysalon.category.dto.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAdvice {


    private final CategoryService categoryService;


    @ModelAttribute("shopCategories")
    public List<CategoryResponse> populateShopCategories() {
        return categoryService.findCategoriesBySubcategory(SubCategory.Shop);
    }

    @ModelAttribute("servicesCategories")
    public List<CategoryResponse> populateServiceCategories() {
        return categoryService.findCategoriesBySubcategory(SubCategory.Services);
    }

    @ModelAttribute("authenticated")
    public boolean getAuthentication(Authentication authentication) {
        if (authentication == null) {
            return false;
        } else {
            return authentication.isAuthenticated();
        }
    }


}
