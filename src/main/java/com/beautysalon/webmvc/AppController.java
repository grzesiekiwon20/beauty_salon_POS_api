package com.beautysalon.webmvc;


import com.beautysalon.user.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.unbescape.html.HtmlEscape;

import java.util.HashSet;


@SessionAttributes("merge")
@Controller
@RequiredArgsConstructor
public class AppController {

    private final UserServiceImpl userServiceImpl;

    @RequestMapping("/")
    public String mainPage() {
        return "home";
    }

    @RequestMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/user/login";
    }

    @GetMapping("/login-error")
    public String login(Model model) {
        model.addAttribute("errorMessage", "Wrong credentials try again");
        return "/user/login";
    }

    @GetMapping("/account")
    public String accountPage( Authentication authentication, Model model, RedirectAttributes redirectAttributes) {
        if (authentication == null || !authentication.isAuthenticated()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in to access your account.");
            return "redirect:/user/login";
        }
        model.addAttribute("userDetails", userServiceImpl.getLoggedInUserDetails(authentication));
        return "/account/account";
    }

    @RequestMapping("/simulateError")
    public void simulateError() {
        throw new UsernameNotFoundException("This is a simulated error message");
    }

    @RequestMapping("/error.html")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }
}
