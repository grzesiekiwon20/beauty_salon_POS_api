package com.beautysalon.serviceentity;


import com.beautysalon.category.CategoryServiceImpl;
import com.beautysalon.category.SubCategory;
import com.beautysalon.product.dto.ProductResponse;
import com.beautysalon.serviceentity.dto.ServiceEntityRequest;
import com.beautysalon.serviceentity.dto.ServiceEntityResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceEntityController {

    private final ServiceEntityServiceImpl serviceEntityServiceImpl;
    private final CategoryServiceImpl categoryService;


    @GetMapping("/addNew")
    public String addNewServiceEntity(Model model
    ) {
        ServiceEntityRequest serviceEntityRequest = new ServiceEntityRequest();
        model.addAttribute("serviceEntity", serviceEntityRequest);
        model.addAttribute("categories", categoryService.findCategoriesBySubcategory(SubCategory.Services));
        return "/services/servicesmng";
    }
    @PostMapping("/save")
    public String saveServiceEntity(
            @ModelAttribute("serviceEntity") @Valid ServiceEntityRequest request,
            @RequestParam("file") MultipartFile file
    ){
        serviceEntityServiceImpl.save(request, file);
        return "redirect:/";
    }

    @GetMapping("/byCategory/{categoryId}")
    public String viewServicesByCategoryId(
            @PathVariable Long categoryId,
            Model model
    ) {
        model.addAttribute("allServices", serviceEntityServiceImpl.findServicesByCategoryId(categoryId));
        return "/services/services";
    }

    @GetMapping("/all")
    public String getAllServices(
            Model model
    ){
        model.addAttribute("allServices", serviceEntityServiceImpl.findAllServices());
        return "/services/services";
    }

    @GetMapping("byId/{id}")
    public String getServiceById(
            @PathVariable Long id,
            Model model
    ) {
        model.addAttribute("serviceDetails", serviceEntityServiceImpl.findById(id));
        return "/services/services";
    }


    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getServiceEntityImage(@PathVariable Long id) {
        ServiceEntityResponse serviceEntityResponse = serviceEntityServiceImpl.findById(id);

        if (serviceEntityResponse.image() == null || serviceEntityResponse.image().length == 0) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(serviceEntityResponse.image(), headers, HttpStatus.OK);
    }

    @GetMapping("/manageServices")
    public String servicesManagement(
            Model model
    ){
        model.addAttribute("servicesList", serviceEntityServiceImpl.findAllServices());
        return "/services/services_list_admin";
    }

}
