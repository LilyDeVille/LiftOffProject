package org.launchcode.LiftOffProject.Controllers;


import org.launchcode.LiftOffProject.Repository.RecipeRepository;
import org.launchcode.LiftOffProject.models.Recipe;
import org.launchcode.LiftOffProject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    RecipeRepository recipeRepository;

    @RequestMapping(value="")
    public String search(Model model) {
        return "search";
    }


    @RequestMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Recipe> recipes = new ArrayList<>();
        if (searchType.equals("author")) {
            List<Recipe> allRecipes = (List<Recipe>) recipeRepository.findAll();
            for (Recipe recipe : allRecipes) {
                User user = recipe.getUser();
                if (user.getUsername().toLowerCase().contains(searchTerm.toLowerCase())) {
                    recipes.add(recipe);
                }
            }
        } else if (searchType.equals("recipe")) {
            List<Recipe> allRecipes = (List<Recipe>) recipeRepository.findAll();
            for (Recipe recipe : allRecipes) {
                if (recipe.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                    recipes.add(recipe);
                }
            }
            }

        model.addAttribute("recipes", recipes);
        return "search";
    }





}
