package com.beautysalon.category;


import com.beautysalon.category.dto.CategoryRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/addNew")
    public String addNewCategory(Model model){
        CategoryRequest categoryRequest = new CategoryRequest();
        model.addAttribute("category", categoryRequest);
        return "/category/categorymng";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") CategoryRequest categoryRequest){
        service.save(categoryRequest);
        return "redirect:/";
    }


    @GetMapping("/{categoryId}")
    public String getCategoriesByCategoryId(
            @PathVariable Long categoryId,
            Model model
    ){
        model.addAttribute("categoryId" , service.findCategoryResponseById(categoryId));
        return "main";
    }


}
