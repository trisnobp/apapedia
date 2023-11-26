package com.apapedia.frontend.controller;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apapedia.frontend.DTO.request.GetCatalogueDetailDTO;

import com.apapedia.frontend.service.CatalogueServiceClient;

@Controller
// @RestController
@RequestMapping("/frontend")
public class controller {

    @Autowired
    CatalogueServiceClient catalogueServiceClient;
    @GetMapping("/")
    public String homePage() {

        return "home";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @GetMapping("catalogue/{idProduk}")
    public String retrieveCatalogue(@PathVariable("idProduk") UUID idProduk,   Model model){

        ResponseEntity<GetCatalogueDetailDTO> responseEntity = catalogueServiceClient.retrieveCatalogue(idProduk);

        try {
           GetCatalogueDetailDTO getProdukDetailDTO = responseEntity.getBody();

            //    return getProdukDetailDTO;
            model.addAttribute("catalogDetail", getProdukDetailDTO);
            return "catalog-detail";
        } catch (NoSuchElementException e){
            throw new  ResponseStatusException(
            responseEntity.getStatusCode(), "Produk not found");
        }
    
    }
}
