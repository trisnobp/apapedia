package com.apapedia.frontend.controller;

import com.apapedia.frontend.DTO.request.RegisterRequestDTO;
import com.apapedia.frontend.DTO.request.UpdateOrderStatusRequestDTO;
import com.apapedia.frontend.DTO.response.OrderDTO;
import com.apapedia.frontend.DTO.response.UpdateOrderStatusResponseDTO;
import com.apapedia.frontend.service.order.OrderService;
import com.apapedia.frontend.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class OrderController {

    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @GetMapping("/order")
    public String listOrderPage(HttpServletRequest request, Model model) {
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

        // Get the order data
        var userData = userService.getUserData(token);
        var listOfOrders = orderService.getOrderBySellerId(userData.getId());

        for (var order: listOfOrders) {
            var customerData = userService.getUserById(token, order.getCustomer());
            order.setCustomerName(customerData.getName());
        }

        model.addAttribute("listOfOrders", listOfOrders);
        return "order-history-page";
    }

    @GetMapping("/order/{idOrder}")
    public String detailOrderPage(
            @PathVariable("idOrder") UUID idOrder,
            HttpServletRequest request,
            Model model
    ) {
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

        // Get the detail of certain order
        var userData = userService.getUserData(token);
        var listOfOrders = orderService.getOrderBySellerId(userData.getId());

        OrderDTO orderDetail = null;

        for (var order: listOfOrders) {
            if (order.getIdOrder().equals(idOrder))
            {
                var customerData = userService.getUserById(token, order.getCustomer());
                order.setCustomerName(customerData.getName());
                orderDetail = order;
                break;
            }
        }

        model.addAttribute("orderDTO", orderDetail);
        return "order-detail-page";
    }

    @GetMapping("/order/{idOrder}/update")
    public String updateOrderStatusPage(
            @PathVariable("idOrder") UUID idOrder,
            HttpServletRequest request,
            Model model
    ) {
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

        // Get the detail of certain order
        var userData = userService.getUserData(token);
        var listOfOrders = orderService.getOrderBySellerId(userData.getId());

        OrderDTO orderDetail = null;

        for (var order: listOfOrders) {
            if (order.getIdOrder().equals(idOrder))
            {
                orderDetail = order;
                break;
            }
        }

        model.addAttribute("orderDTO", orderDetail);
        return "form-update-order-status";
    }

    @PostMapping("/order/{idOrder}/update")
    public String updateOrderStatus(@ModelAttribute OrderDTO orderDTO, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            // Get Token
            HttpSession session = request.getSession();
            String token = (String) session.getAttribute("token");
            if (!userService.checkTokenValidity(token)) {
                return "redirect:/logout-sso";
            }

            UpdateOrderStatusRequestDTO updateDTO = new UpdateOrderStatusRequestDTO();
            updateDTO.setOrderId(orderDTO.getIdOrder());
            updateDTO.setStatus(orderDTO.getStatus());

            // Send the data to the service
            UpdateOrderStatusResponseDTO responseDTO = orderService.updateOrderStatus(token, updateDTO);
            if (!responseDTO.isStatus()) {
                redirectAttributes.addFlashAttribute("error", responseDTO.getMessage());
                return "redirect:/order/" + orderDTO.getIdOrder() + "/update";
            } else {
                redirectAttributes.addFlashAttribute("success", responseDTO.getMessage());
                return "redirect:/order/" + orderDTO.getIdOrder();
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Something is wrong.");
            return "redirect:/";
        }
    }


}
