package com.apapedia.frontend.controller;

import com.apapedia.frontend.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @Autowired
    UserService userService;

    @GetMapping("/order")
    public String getListOrderPage(HttpServletRequest request, Model model) {
        // Check session
        HttpSession session = request.getSession();
        if (session == null || (String) session.getAttribute("token") == null) {
            return "redirect:/login-sso";
        }

        // If logged in, check validity
        String token = (String) session.getAttribute("token");
        if (!userService.checkTokenValidity(token)) {
            return "redirect:/logout-sso";
        }


        return "order-page";
    }

}
