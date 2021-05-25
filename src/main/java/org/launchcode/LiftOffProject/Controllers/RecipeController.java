package org.launchcode.LiftOffProject.Controllers;


import org.launchcode.LiftOffProject.Repository.IngredientRepository;
import org.launchcode.LiftOffProject.Repository.RecipeRepository;
import org.launchcode.LiftOffProject.Repository.StepRepository;
import org.launchcode.LiftOffProject.Repository.UserRepository;
import org.launchcode.LiftOffProject.Utilities.FileUploadUtil;
import org.launchcode.LiftOffProject.models.Recipe;
import org.launchcode.LiftOffProject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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


    @GetMapping("add")
    public String displayAddRecipeForm(Model model) {
        model.addAttribute(new Recipe());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("steps", stepRepository.findAll());
        return "add";

    }

    @PostMapping("add")
    public String processRecipeForm(@ModelAttribute Recipe newRecipe, @ModelAttribute MultipartFile image, HttpServletRequest request) throws IOException {
        String uploadDir = "recipe-photos/" + newRecipe.getId();

        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        newRecipe.setPhotos(fileName);

        FileUploadUtil.saveFile(uploadDir, fileName, image);

        int userID = (int)request.getSession().getAttribute("user");
        User user = userRepository.findById(userID).get();
        newRecipe.setUser(user);
        recipeRepository.save(newRecipe);
        return "redirect:ingredients/add/"+ newRecipe.getId();
    }

}

