package org.launchcode.LiftOffProject.Controllers;


import org.launchcode.LiftOffProject.Repository.IngredientRepository;
import org.launchcode.LiftOffProject.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("add")
    public String displayAddIngredientForm(Model model) {
        model.addAttribute(new Ingredient());
        return "ingredients/add";
    }

    @PostMapping("add")
    public String processAddIngredientForm(@ModelAttribute Ingredient newIngredient, Model model) {

        ingredientRepository.save(newIngredient);
        return "redirect:../add";
    }



}
