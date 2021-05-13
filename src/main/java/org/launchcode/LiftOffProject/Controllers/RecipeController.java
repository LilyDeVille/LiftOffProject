package org.launchcode.LiftOffProject.Controllers;


import org.launchcode.LiftOffProject.Repository.IngredientRepository;
import org.launchcode.LiftOffProject.Repository.RecipeRepository;
import org.launchcode.LiftOffProject.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;


    @GetMapping("add")
    public String displayAddRecipeForm(Model model) {
        model.addAttribute(new Recipe());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "add";

    }

    @PostMapping("add")
    public String processRecipeForm(@ModelAttribute Recipe newRecipe) {
        recipeRepository.save(newRecipe);
        return "redirect";
    }

}
