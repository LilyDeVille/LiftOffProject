package org.launchcode.LiftOffProject.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping("")
    public String index(Model model, HttpServletRequest request) {
        String route = request.getSession().getAttribute("user") == null ? "redirect:/login" : "index";
        return route;
    }


}
