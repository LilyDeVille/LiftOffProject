package org.launchcode.LiftOffProject.Controllers;


import org.launchcode.LiftOffProject.Repository.UserRepository;
import org.launchcode.LiftOffProject.models.DTO.Login;
import org.launchcode.LiftOffProject.models.DTO.Register;
import org.launchcode.LiftOffProject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";


    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute("register", new Register());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute Register register, Model model, HttpServletRequest request) {
        model.addAttribute("register", register);

        //if (errors.hasErrors()) {
          //  model.addAttribute("title", "Register");
          //  return "register";
       // }

        User existingUser = userRepository.findByUsername(register.getUsername());

        if (existingUser != null) {
            //errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        String password = register.getPassword();
        String verifyPassword = register.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            //errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }

        User newUser = new User(register.getUsername(), register.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);


        return "register";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute Login login, Model model, HttpServletRequest request) {
        model.addAttribute("login", login);

        //if (errors.hasErrors()) {
         //  model.addAttribute("title", "Log In");
          //  return "login";
       // }

        User theUser = userRepository.findByUsername(login.getUsername());

        if (theUser == null) {
            //errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        String password = login.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            //errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:";
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

}
