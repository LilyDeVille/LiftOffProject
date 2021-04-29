package org.launchcode.LiftOffProject.Controllers;


import org.launchcode.LiftOffProject.models.Login;
import org.launchcode.LiftOffProject.models.Register;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController {

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute("register", new Register());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute Register register, Model model) {
        model.addAttribute("register", register);
        return "register";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute Login login, Model model) {
        model.addAttribute("login", login);
        return "login";
    }

}
