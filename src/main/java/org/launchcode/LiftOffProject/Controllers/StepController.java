package org.launchcode.LiftOffProject.Controllers;


import org.launchcode.LiftOffProject.Repository.RecipeRepository;
import org.launchcode.LiftOffProject.Repository.StepRepository;
import org.launchcode.LiftOffProject.models.Ingredient;
import org.launchcode.LiftOffProject.models.Recipe;
import org.launchcode.LiftOffProject.models.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("steps")
public class StepController {

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("add/{recipeId}")
    public String displayAddStepForm(Model model, @PathVariable int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).get();
        List<Step> recipeSteps = stepRepository.findByRecipe(recipe);
        model.addAttribute("recipeSteps", recipeSteps);
        Step newStep = new Step();
        model.addAttribute(newStep);
        return "steps/add";
    }

    @PostMapping("add/{recipeId}")
    public String processAddStepForm(@ModelAttribute Step newStep, Model model, @PathVariable int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).get();
        newStep.setRecipe(recipe);
        List<Step> recipeSteps = stepRepository.findByRecipe(recipe);
        newStep.setStepOrder(recipeSteps.size() + 1);
        stepRepository.save(newStep);
        return "redirect:../../steps/add/"+ recipe.getId();

    }



}
