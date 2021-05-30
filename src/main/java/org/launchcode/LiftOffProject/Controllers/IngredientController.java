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
        model.addAttribute("recipeId", recipeId);
        model.addAttribute("recipeIngredients", recipeIngredients);
        model.addAttribute(new Ingredient());
        return "ingredients/add";
    }

    @PostMapping("add/{recipeId}")
    public String processAddIngredientForm(@ModelAttribute Ingredient newIngredient, Model model, @PathVariable int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).get();
        newIngredient.setRecipe(recipe);
        ingredientRepository.save(newIngredient);
        return "redirect:../../ingredients/add/"+ recipe.getId();
    }

    @GetMapping("view/{recipeId}")
    public String viewIngredients(Model model, @PathVariable int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).get();
        List<Ingredient> recipeIngredients = ingredientRepository.findByRecipe(recipe);
        model.addAttribute("recipeId", recipeId);
        model.addAttribute("recipeIngredients", recipeIngredients);

        return "ingredients/view";
    }

    @PostMapping("view/{recipeId}")
    public String processViewIngredients(@RequestParam("ingredientId") int id) {
        return "redirect:../../ingredients/edit/" + id;
    }


    @GetMapping("edit/{ingredientId}")
    public String editIngredients(Model model, @PathVariable int ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId).get();
        model.addAttribute("ingredient", ingredient);
        return "ingredients/edit";
    }

    @PostMapping("edit/{ingredientId}")
    public String processEditIngredients(Model model, @PathVariable int ingredientId, @ModelAttribute Ingredient editedIngredient) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId).get();
        ingredient.setName(editedIngredient.getName());
        ingredient.setQuantity(editedIngredient.getQuantity());
        ingredient.setUnitOfMeasurement(editedIngredient.getUnitOfMeasurement());
        ingredientRepository.save(ingredient);

        return "redirect:../../ingredients/view/" + ingredient.getRecipe().getId();

    }


}
