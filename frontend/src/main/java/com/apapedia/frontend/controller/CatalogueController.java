package com.apapedia.frontend.controller;

import com.apapedia.frontend.DTO.request.CatalogueDetailDTO;
import com.apapedia.frontend.DTO.request.CreateCatalogueRequestDTO;
import com.apapedia.frontend.service.catalogue.CatalogueServiceClient;
import com.apapedia.frontend.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Base64;
import java.util.UUID;

@Controller
public class CatalogueController {

    @Autowired
    CatalogueServiceClient catalogueServiceClient;

    @Autowired
    UserService userService;

    @GetMapping("/catalogue/add")
    public String addCatalogueForm(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session == null || (String) session.getAttribute("token") == null) {
            return "redirect:/login";
        }

        String token = (String) session.getAttribute("token");

        CreateCatalogueRequestDTO catalogueDTO = new CreateCatalogueRequestDTO();
        catalogueDTO.setSellerId(userService.getUserData(token).getId());

        model.addAttribute("listCategory", catalogueServiceClient.getAllCategories(token).getBody());
        model.addAttribute("catalogueDTO", catalogueDTO);
        return "form-add-catalogue";
    }

    @PostMapping("/catalogue/add")
    public String addCatalogue(@ModelAttribute CreateCatalogueRequestDTO catalogueDTO, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            String token = (String) session.getAttribute("token");

            // Ubah format image ke byte, supaya bisa ditampilin
            String fileName = StringUtils.cleanPath(catalogueDTO.getFile().getOriginalFilename());
            byte[] fileBytes = catalogueDTO.getFile().getBytes();
            catalogueDTO.setImage(Base64.getEncoder().encodeToString(fileBytes));
            catalogueDTO.setFile(null);

            var createCatalogueResponse = catalogueServiceClient.addCatalogue(token, catalogueDTO);

            if (createCatalogueResponse.getStatusCode() == HttpStatusCode.valueOf(400)) {
                redirectAttributes.addFlashAttribute("error", "Something is wrong.");
                return "redirect:/catalogue/add";
            } else {
                redirectAttributes.addFlashAttribute("success", "Product with id " + createCatalogueResponse.getBody().getId() + " is successfully added.");
                return "redirect:/";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Please check your credentials again.");
            return "redirect:/login";
        }
    }

    @GetMapping("/catalogue/{id}/update")
    public String updateCatalogueForm(@PathVariable("id") UUID id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session == null || (String) session.getAttribute("token") == null) {
            return "redirect:/login";
        }

        String token = (String) session.getAttribute("token");

        CatalogueDetailDTO catalogueDTO = catalogueServiceClient.retrieveCatalogue(token, id).getBody();
        System.out.println(catalogueDTO.getImage());
        model.addAttribute("listCategory", catalogueServiceClient.getAllCategories(token).getBody());
        model.addAttribute("catalogueDTO", catalogueDTO);
        return "form-update-catalogue";
    }

    @PostMapping("/catalogue/{id}/update")
    public String updateCatalogue(@ModelAttribute CatalogueDetailDTO catalogueDTO, @PathVariable("id") UUID id, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        try {
            System.out.println(catalogueDTO.getImage());
            HttpSession session = request.getSession();
            String token = (String) session.getAttribute("token");

            if (catalogueDTO.getFile() != null) {
                // Ubah format image ke byte, supaya bisa ditampilin
                String fileName = StringUtils.cleanPath(catalogueDTO.getFile().getOriginalFilename());
                byte[] fileBytes = catalogueDTO.getFile().getBytes();
                catalogueDTO.setImage(Base64.getEncoder().encodeToString(fileBytes));
                catalogueDTO.setFile(null);
            }

            var createCatalogueResponse = catalogueServiceClient.updateCatalogue(token, id, catalogueDTO);

            if (createCatalogueResponse.getStatusCode() == HttpStatusCode.valueOf(400)) {
                redirectAttributes.addFlashAttribute("error", "Something is wrong.");
                return "redirect:/catalogue/" + id + "/update";
            } else {
                redirectAttributes.addFlashAttribute("success", "Product with id " + createCatalogueResponse.getBody().getId() + " is successfully updated.");
                return "redirect:/";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Please check your credentials again.");
            return "redirect:/login";
        }
    }

//    @GetMapping("/catalogue/{idProduk}")
//    public String retrieveCatalogue(HttpServletRequest request, @PathVariable("idProduk") UUID idProduk, Model model){
//        HttpSession session = request.getSession(false);
//        if (session == null || (String) session.getAttribute("token") == null) {
//            return "redirect:/login";
//        }
//
//        String token = (String) session.getAttribute("token");
//        ResponseEntity<CatalogueDetailDTO> responseEntity = catalogueServiceClient.retrieveCatalogue(token, idProduk);
//        try {
//            CatalogueDetailDTO getProdukDetailDTO = responseEntity.getBody();
//
//            //    return getProdukDetailDTO;
//            model.addAttribute("catalogDetail", getProdukDetailDTO);
//            return "catalog-detail";
//        } catch (NoSuchElementException e){
//            throw new ResponseStatusException(
//                    responseEntity.getStatusCode(), "Produk not found");
//        }
//
//    }
}
