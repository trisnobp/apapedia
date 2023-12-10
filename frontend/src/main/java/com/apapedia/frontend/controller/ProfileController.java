package com.apapedia.frontend.controller;

import com.apapedia.frontend.DTO.response.UserDTO;
import com.apapedia.frontend.service.catalogue.CatalogueServiceClient;
import com.apapedia.frontend.service.order.OrderService;
import com.apapedia.frontend.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    CatalogueServiceClient catalogueServiceClient;

    @GetMapping("/profile")
    public String getProfile(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session == null || (String) session.getAttribute("token") == null) {
            return "redirect:/login-sso";
        }


        String token = (String) session.getAttribute("token");
        if (!userService.checkTokenValidity(token)) {
            return "redirect:/logout-sso";
        }

        var userData = userService.getUserData(token);
        model.addAttribute("userData", userData);
        return "profile-page";
    }

    @GetMapping("/profile/update")
    public String getEditProfileForm(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session == null || (String) session.getAttribute("token") == null) {
            return "redirect:/login-sso";
        }

        String token = (String) session.getAttribute("token");
        if (!userService.checkTokenValidity(token)) {
            return "redirect:/logout-sso";
        }

        var userData = userService.getUserData(token);
        model.addAttribute("userData", userData);
        return "form-update-user-data";
    }

    @PostMapping("/profile/update")
    public String editProfile(@ModelAttribute UserDTO userDTO, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        try {
            HttpSession session = request.getSession();
            String token = (String) session.getAttribute("token");
            if (!userService.checkTokenValidity(token)) {
                return "redirect:/logout-sso";
            }

            var updateUserDataResponse = userService.updateUserData(userDTO, token);

            if (!updateUserDataResponse.isStatus()) {
                redirectAttributes.addFlashAttribute("error", "Something is wrong.");
                return "redirect:/profile/" + userDTO.getId() + "/update";
            } else {
                redirectAttributes.addFlashAttribute("success", updateUserDataResponse.getMessage());
                return "redirect:/profile";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Something went wrong.");
            return "redirect:/profile/" + userDTO.getId() + "/update";
        }
    }

    @GetMapping("/profile/apapay/withdraw")
    public String getWithdrawPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session == null || (String) session.getAttribute("token") == null) {
            return "redirect:/login-sso";
        }

        String token = (String) session.getAttribute("token");
        if (!userService.checkTokenValidity(token)) {
            return "redirect:/logout-sso";
        }

        var userData = userService.getUserData(token);
        model.addAttribute("userData", userData);
        return "withdraw-page";
    }

    @PostMapping("/profile/apapay/withdraw")
    public String getWithdrawPage(
            @RequestParam(name = "withdraw", required = false) Long saldo,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        try {
            HttpSession session = request.getSession();
            String token = (String) session.getAttribute("token");
            if (!userService.checkTokenValidity(token)) {
                return "redirect:/logout-sso";
            }

            var userData = userService.getUserData(token);
            var updateUserDataResponse = userService.updateUserBalance(saldo, token ,userData.getId());

            if (!updateUserDataResponse.isStatus()) {
                redirectAttributes.addFlashAttribute("error", updateUserDataResponse.getMessage());
                return "redirect:/profile/apapay/withdraw";
            } else {
                redirectAttributes.addFlashAttribute("success", updateUserDataResponse.getMessage());
                return "redirect:/profile";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Something went wrong.");
            return "redirect:/profile/apapay/withdraw";
        }
    }

    @GetMapping("/profile/delete")
    public String softDeleteAccount(
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        HttpSession session = request.getSession();
        if (session == null || (String) session.getAttribute("token") == null) {
            return "redirect:/login-sso";
        }

        String token = (String) session.getAttribute("token");
        if (!userService.checkTokenValidity(token)) {
            return "redirect:/logout-sso";
        }

        // Proses Delete Account
        var userData = userService.getUserData(token);

        // 1. Soft Delete semua Order punya si seller
        var sellerOrder = orderService.getOrderBySellerId(userData.getId());
        if (!sellerOrder.isEmpty()) {
            for (var item: sellerOrder) {
                orderService.deleteSellerOrder(item.getIdOrder());
            }
        }

        // 2. Soft Delete semua Catalog punya si seller (seller)
        var sellerCatalogue = catalogueServiceClient.retrieveSellerCatalogue(token, userData.getId());
        if (!sellerCatalogue.getBody().isEmpty()) {
            for (var item: sellerCatalogue.getBody()) {
                catalogueServiceClient.deleteCatalogue(item.getId());
            }
        }

        // 3. Soft Delete akun seller
        userService.deleteSellerAccount(userData.getId(), token);

        // 4. Logout akun
        return "redirect:/logout-sso";
    }
}