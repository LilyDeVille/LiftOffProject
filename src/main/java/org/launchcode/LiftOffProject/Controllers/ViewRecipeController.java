package org.launchcode.LiftOffProject.Controllers;


import org.launchcode.LiftOffProject.Repository.RecipeRepository;
import org.launchcode.LiftOffProject.Repository.UserRepository;
import org.launchcode.LiftOffProject.models.Recipe;
import org.launchcode.LiftOffProject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping(value = "view")
public class ViewRecipeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecipeRepository recipeRepository;


    @GetMapping("user-recipe")
    public String listUserRecipes(Model model, HttpServletRequest request) {
        List<Recipe> recipes = new ArrayList<>();


        int userID = (int)request.getSession().getAttribute("user");
        User user = userRepository.findById(userID).get();
        recipes = recipeRepository.findByUser(user);

        model.addAttribute("recipes", recipes);

        return "viewrecipe";

    }

}
