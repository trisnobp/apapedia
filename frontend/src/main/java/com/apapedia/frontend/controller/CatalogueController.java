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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Controller
public class CatalogueController {

    @Autowired
    CatalogueServiceClient catalogueServiceClient;

    @Autowired
    UserService userService;

    @GetMapping("/search")
    public String filterProductByName(
            @RequestParam(name = "productName", required = false) String nama,
            Model model
    ) {
        List<CatalogueDetailDTO> listOfCatalogue = catalogueServiceClient.getCatalogsByName(nama).getBody();
        if (listOfCatalogue.isEmpty()) { // If filter by Product Name
            model.addAttribute("searchMode", true);
            model.addAttribute("isLoggedIn", false);
            model.addAttribute("isEmpty", true);
        }
        model.addAttribute("searchMode", true);
        model.addAttribute("isLoggedIn", false);
        model.addAttribute("allCatalogue", listOfCatalogue);
        model.addAttribute("isEmpty", false);

        return "home";
    }

    @GetMapping("/searchByPrice")
    public String filterByPriceRange(
            @RequestParam(name = "startPrice", required = false) BigDecimal startPrice,
            @RequestParam(name = "endPrice", required = false) BigDecimal endPrice,
            Model model
    ) {
        List<CatalogueDetailDTO> listOfCatalogue = catalogueServiceClient.getCatalogsByPriceRange(startPrice, endPrice).getBody();
        if (listOfCatalogue.isEmpty()) {
            model.addAttribute("searchMode", true);
            model.addAttribute("isLoggedIn", false);
            model.addAttribute("isEmpty", true);
        }

        model.addAttribute("searchMode", true);
        model.addAttribute("isLoggedIn", false);
        model.addAttribute("allCatalogue", listOfCatalogue);
        model.addAttribute("isEmpty", false);

        return "home";
    }

    @GetMapping("/sortProducts")
    public String filterBySorting(
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "order", required = false) String order,
            Model model
    ) {
        List<CatalogueDetailDTO> listOfCatalogue = catalogueServiceClient.getSortedCatalog(sortBy, order).getBody();
        if (listOfCatalogue.isEmpty()) {
            model.addAttribute("searchMode", true);
            model.addAttribute("isLoggedIn", false);
            model.addAttribute("isEmpty", true);
        }

        model.addAttribute("searchMode", true);
        model.addAttribute("isLoggedIn", false);
        model.addAttribute("allCatalogue", listOfCatalogue);
        model.addAttribute("isEmpty", false);

        return "home";
    }

    @GetMapping("/catalogue/add")
    public String addCatalogueForm(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session == null || (String) session.getAttribute("token") == null) {
            return "redirect:/login-sso";
        }

        String token = (String) session.getAttribute("token");
        if (!userService.checkTokenValidity(token)) {
            return "redirect:/logout-sso";
        }

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
            if (!userService.checkTokenValidity(token)) {
                return "redirect:/logout-sso";
            }

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
            return "redirect:/login-sso";
        }
    }

    @GetMapping("/catalogue/{id}/update")
    public String updateCatalogueForm(@PathVariable("id") UUID id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session == null || (String) session.getAttribute("token") == null) {
            return "redirect:/login-sso";
        }

        String token = (String) session.getAttribute("token");
        if (!userService.checkTokenValidity(token)) {
            return "redirect:/logout-sso";
        }

        CatalogueDetailDTO catalogueDTO = catalogueServiceClient.retrieveCatalogue(token, id).getBody();
        model.addAttribute("listCategory", catalogueServiceClient.getAllCategories(token).getBody());
        model.addAttribute("catalogueDTO", catalogueDTO);
        return "form-update-catalogue";
    }

    @PostMapping("/catalogue/{id}/update")
    public String updateCatalogue(@ModelAttribute CatalogueDetailDTO catalogueDTO, @PathVariable("id") UUID id, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        try {
            HttpSession session = request.getSession();
            String token = (String) session.getAttribute("token");
            if (!userService.checkTokenValidity(token)) {
                return "redirect:/logout-sso";
            }

            // Ubah format image ke byte, supaya bisa ditampilin
            if (!(catalogueDTO.getFile().getOriginalFilename() == null)) {
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
            return "redirect:/login-sso";
        }
    }
}
