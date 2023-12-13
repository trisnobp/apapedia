package com.apapedia.frontend.controller;

import com.apapedia.frontend.DTO.request.LoginRequestDTO;
import com.apapedia.frontend.security.xml.Attributes;
import com.apapedia.frontend.security.xml.ServiceResponse;
import com.apapedia.frontend.service.user.UserService;
import com.apapedia.frontend.setting.Setting;
import io.netty.channel.ChannelOption;
import lombok.extern.java.Log;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import reactor.netty.http.client.HttpClient;

import java.security.Principal;
import java.time.Duration;
import java.util.NoSuchElementException;

@Controller
public class PageController {

    @Autowired
    private UserService userRestService;

    private WebClient webClient = WebClient.builder()
            .codecs(configurer -> configurer.defaultCodecs()
                    .jaxb2Decoder(new Jaxb2XmlDecoder()))
            .build();

    @GetMapping("/validate-ticket")
    public ModelAndView adminLoginSSO(
            @RequestParam(value = "ticket", required = false) String ticket,
            HttpServletRequest request
    ) {

        System.out.println("Berhasil 1");

        var url = String.format(
                Setting.SERVER_VALIDATE_TICKET,
                ticket,
                Setting.CLIENT_LOGIN
        );
        var temp = this.webClient.get().uri(
                url
        ).retrieve().bodyToMono(ServiceResponse.class);


        var serviceResponse = temp.block();

        Attributes attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
        String username = serviceResponse.getAuthenticationSuccess().getUser();

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "webadmin", null);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        var dto = new LoginRequestDTO();
        dto.setUsernameOrEmail(username);
        dto.setPassword("SSOPassword");

        try {
            var token = userRestService.login(dto).getToken();
            if (token == null) { // If account couldn't be found
                return new ModelAndView("redirect:/logout-sso");
            }

            HttpSession httpSession = request.getSession(true);
            httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
            httpSession.setAttribute("token", token);
            return new ModelAndView("redirect:/");
        } catch (NoSuchElementException e) {
            return new ModelAndView("redirect:/logout-sso");
        }

    }

    @GetMapping("/login-sso")
    public ModelAndView loginSSO() {
        return new ModelAndView("redirect:" + Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
    }

    @GetMapping("/logout-sso")
    public ModelAndView logoutSSO(Principal principal, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("token");
        session.invalidate();

        return new ModelAndView("redirect:" + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
    }
}