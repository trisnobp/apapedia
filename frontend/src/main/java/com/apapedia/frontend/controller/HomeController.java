package com.apapedia.frontend.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.apapedia.frontend.DTO.request.LoginRequestDTO;
import com.apapedia.frontend.DTO.request.RegisterRequestDTO;
import com.apapedia.frontend.DTO.response.UserDTO;
import com.apapedia.frontend.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.apapedia.frontend.DTO.request.CatalogueDetailDTO;

import com.apapedia.frontend.service.catalogue.CatalogueServiceClient;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    CatalogueServiceClient catalogueServiceClient;

    @GetMapping("/")
    public String homePage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        // If there's no session or even token, we show all catalogues
        if (session == null || (String) session.getAttribute("token") == null) {
            var responseEntityData = catalogueServiceClient.retrieveAllCatalogue().getBody();
            if (responseEntityData.isEmpty()) {
                model.addAttribute("isEmpty", true);
            } else {
                model.addAttribute("isEmpty", false);
                model.addAttribute("allCatalogue", responseEntityData);
            }
            model.addAttribute("isLoggedIn", false);

        } else {
            // Get seller data via token that we store in session
            String token = (String) session.getAttribute("token");
            UserDTO sellerData = userService.getUserData(token);
            // Get the list of catalogues that the seller has
            try {
                var responseEntityData = catalogueServiceClient.retrieveSellerCatalogue(token, sellerData.getId());
                List<CatalogueDetailDTO> listOfCatalogues = responseEntityData.getBody();

                if (listOfCatalogues.isEmpty()) {
                    model.addAttribute("isEmpty", true);
                } else {
                    model.addAttribute("isEmpty", false);
                    model.addAttribute("sellerCatalogue", listOfCatalogues);
                }
                model.addAttribute("name", sellerData.getName());
                model.addAttribute("isLoggedIn", true);
            } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
            }
        }
        return "home";
    }

    @GetMapping("/register")
    public String showRegisterForm(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        if (session != null) {
            // Check jwt token validity
            String jwtToken = (String) session.getAttribute("token");
            if (jwtToken != null) {
                return "redirect:/";
            }
        }
        model.addAttribute("registerDTO", registerRequestDTO);
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequestDTO registerRequestDTO, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            var registerResponse = userService.register(registerRequestDTO);
            if (registerResponse.isStatus()) {
                redirectAttributes.addFlashAttribute("success", registerResponse.getMessage());
                return "redirect:/login-sso";
            } else {
                redirectAttributes.addFlashAttribute("error", registerResponse.getMessage());
                return "redirect:/register";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Please check your credentials again.");
            return "redirect:/register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("token");
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginForm(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        if (session != null) {
            // Check jwt token validity
            String jwtToken = (String) session.getAttribute("token");
            if (jwtToken != null) {
                return "redirect:/";
            }
        }
        model.addAttribute("loginDTO", loginRequestDTO);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDTO loginRequestDTO, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            var loginResponse = userService.login(loginRequestDTO);
            if (loginResponse.isStatus()) {
                HttpSession session = request.getSession();
                // Store data in the session
                session.setAttribute("token", loginResponse.getToken());

                return "redirect:/";
            } else {
                redirectAttributes.addFlashAttribute("error", loginResponse.getMessage());
                return "redirect:/login";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Please check your credentials again.");
            return "redirect:/login";
        }
    }
}
