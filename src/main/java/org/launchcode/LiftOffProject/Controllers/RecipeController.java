package org.launchcode.LiftOffProject.Controllers;


import org.launchcode.LiftOffProject.Repository.*;
import org.launchcode.LiftOffProject.Utilities.FileUploadUtil;
import org.launchcode.LiftOffProject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private RecipeViewRepository recipeViewRepository;


    @GetMapping("add")
    public String displayAddRecipeForm(Model model) {
        model.addAttribute(new Recipe());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("steps", stepRepository.findAll());
        return "add";

    }

    @PostMapping("add")
    public String processRecipeForm(@ModelAttribute Recipe newRecipe, @ModelAttribute MultipartFile image, HttpServletRequest request) throws IOException {
        int userID = (int)request.getSession().getAttribute("user");
        User user = userRepository.findById(userID).get();
        newRecipe.setUser(user);
        recipeRepository.save(newRecipe);

        String uploadDir = "recipe-photos/" + newRecipe.getId();

        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        newRecipe.setPhotos(fileName);

        FileUploadUtil.saveFile(uploadDir, fileName, image);

        recipeRepository.save(newRecipe);


        return "redirect:ingredients/add/"+ newRecipe.getId();
    }

    @GetMapping("view/{recipeId}")
    public String viewRecipe(Model model, @PathVariable int recipeId, HttpServletRequest request) {

        Recipe recipe = recipeRepository.findById(recipeId).get();
        List<Ingredient> ingredients = ingredientRepository.findByRecipe(recipe);
        List<Step> steps = stepRepository.findByRecipe(recipe);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("steps", steps);
        model.addAttribute("recipe", recipe);
        int userID = (int)request.getSession().getAttribute("user");
        User user = userRepository.findById(userID).get();
        RecipeView recipeView = new RecipeView();
        recipeView.setUser(user);
        recipeView.setDatetime(LocalDateTime.now());
        recipeView.setRecipe(recipe);
        recipeViewRepository.save(recipeView);


        return "view";

    };

    @GetMapping("edit/{recipeId}")
    public String editRecipe(Model model, @PathVariable int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).get();
        model.addAttribute("recipe", recipe);

        return "edit";
    }

    @PostMapping("edit/{recipeId}")
    public String saveEditRecipe(Model model, @PathVariable int recipeId, @ModelAttribute Recipe editedRecipe, @RequestParam(value="action") String action) {
       saveEditedChanges(recipeId, editedRecipe);
       String route = "";
       if(action.equals("Save and Finish")){
           route = "redirect:../view/" + recipeId;
       }
       else if(action.equals("Save and Edit Ingredients")){
           route = "redirect:../ingredients/view/"+ recipeId;
       }
       else{
           route = "redirect:../steps/view/"+ recipeId;
       }
        return route;
    }

    private void saveEditedChanges (int recipeId, Recipe editedRecipe) {
        Recipe recipe = recipeRepository.findById(recipeId).get();
        recipe.setName(editedRecipe.getName());
        recipe.setDescription(editedRecipe.getDescription());
        recipeRepository.save(recipe);
    }

}

