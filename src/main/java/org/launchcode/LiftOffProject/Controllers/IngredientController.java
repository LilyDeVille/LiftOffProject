package org.launchcode.LiftOffProject.Controllers;


import org.launchcode.LiftOffProject.Repository.IngredientRepository;
import org.launchcode.LiftOffProject.Repository.RecipeRepository;
import org.launchcode.LiftOffProject.models.Ingredient;
import org.launchcode.LiftOffProject.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("add/{recipeId}")
    public String displayAddIngredientForm(Model model, @PathVariable int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).get();
        List<Ingredient> recipeIngredients = ingredientRepository.findByRecipe(recipe);
        model.addAttribute("recipeIngredients", recipeIngredients);
        model.addAttribute(new Ingredient());
        return "ingredients/add";
    }

    @PostMapping("add/{recipeId}")
    public String processAddIngredientForm(@ModelAttribute Ingredient newIngredient, Model model, @PathVariable int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).get();
        newIngredient.setRecipe(recipe);
        ingredientRepository.save(newIngredient);
        return "redirect:ingredients/add/"+ recipe.getId();
    }



}
