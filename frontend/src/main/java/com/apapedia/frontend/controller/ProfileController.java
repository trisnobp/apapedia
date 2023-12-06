package com.apapedia.frontend.controller;

import com.apapedia.frontend.DTO.response.UserDTO;
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
import java.util.UUID;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public String getProfile(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session == null || (String) session.getAttribute("token") == null) {
            return "redirect:/login-sso";
        }
        String token = (String) session.getAttribute("token");
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
        var userData = userService.getUserData(token);
        model.addAttribute("userData", userData);
        return "form-update-user-data";
    }

    @PostMapping("/profile/update")
    public String editProfile(@ModelAttribute UserDTO userDTO, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        try {
            HttpSession session = request.getSession();
            String token = (String) session.getAttribute("token");

            var updateUserDataResponse = userService.updateUserData(userDTO, token);

            if (!updateUserDataResponse.isStatus()) {
                redirectAttributes.addFlashAttribute("error", "Something is wrong.");
                return "redirect:/profile/" + userDTO.getId() + "/update";
            } else {
                redirectAttributes.addFlashAttribute("success", updateUserDataResponse.getMessage());
                return "redirect:/profile";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Please check your credentials again.");
            return "redirect:/login-sso";
        }
    }

    @GetMapping("/profile/apapay/withdraw")
    public String getWithdrawPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session == null || (String) session.getAttribute("token") == null) {
            return "redirect:/login-sso";
        }
        String token = (String) session.getAttribute("token");
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
            redirectAttributes.addFlashAttribute("error", "Please check your credentials again.");
            return "redirect:/login-sso";
        }
    }

}
