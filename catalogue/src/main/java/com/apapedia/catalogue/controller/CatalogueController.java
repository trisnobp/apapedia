package com.apapedia.catalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.apapedia.catalogue.DTO.CatalogueMapper;
import com.apapedia.catalogue.DTO.request.CreateCatalogueRequestDTO;
import com.apapedia.catalogue.service.CatalogueService;
import com.apapedia.catalogue.service.CategoryService;

import jakarta.validation.Valid;

@Controller
public class CatalogueController {

    @Autowired
    CatalogueService catalogueService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CatalogueMapper catalogueMapper;

    @GetMapping("/catalogue/create")
    public String formCreateCatalogue(Model model){
        var catalogueDTO = new CreateCatalogueRequestDTO();

        model.addAttribute("catalogueDTO", catalogueDTO);

        var listCategory = categoryService.getAllCategory();
        model.addAttribute("listCategory", listCategory);

        return "form-create-catalogue";
    }

    @PostMapping("/catalogue/create")
    public String addCatalogue(@Valid @ModelAttribute CreateCatalogueRequestDTO catalogueDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "error-view";
        }

        var catalogue = catalogueMapper.createCatalogueRequestDTOToCatalogue(catalogueDTO);

        catalogueService.createCatalogue(catalogue);

        model.addAttribute("id", catalogue.getId());

        model.addAttribute("nama", catalogue.getName());

        model.addAttribute("image", catalogue.getImage());

        model.addAttribute("price", catalogue.getPrice());

        model.addAttribute("nama_produk", catalogue.getProductName());

        model.addAttribute("desc_produk", catalogue.getProductDesc());

        model.addAttribute("stok", catalogue.getStock());

        return "catalogue";
    }

    
    
}
