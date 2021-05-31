package org.launchcode.LiftOffProject.Controllers;

import org.launchcode.LiftOffProject.Repository.RecipeRepository;
import org.launchcode.LiftOffProject.Repository.RecipeViewRepository;
import org.launchcode.LiftOffProject.Repository.UserRepository;
import org.launchcode.LiftOffProject.models.Recipe;
import org.launchcode.LiftOffProject.models.RecipeView;
import org.launchcode.LiftOffProject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
public class HomeController {

    public class SortByDate implements Comparator<RecipeView> {
        @Override
        public int compare(RecipeView a, RecipeView b) {
            return a.getDatetime().compareTo(b.getDatetime());
        }
    }

    public class SortByViewCount implements Comparator<Recipe> {
        @Override
        public int compare(Recipe a, Recipe b) {
            List<RecipeView> aViews = recipeViewRepository.findByRecipe(a);
            List<RecipeView> bViews = recipeViewRepository.findByRecipe(b);
            if (aViews.size() == bViews.size()) {
                return 0;
            } else if(aViews.size() < bViews.size()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecipeViewRepository recipeViewRepository;
    
    @Autowired
    RecipeRepository recipeRepository;

    @RequestMapping("")
    public String index(Model model, HttpServletRequest request) {
        String route = request.getSession().getAttribute("user") == null ? "redirect:/login" : "index";
        if (route.equals("index")) {
            int userID = (int)request.getSession().getAttribute("user");
            User user = userRepository.findById(userID).get();


            model.addAttribute("recentlyViewedRecipes", getMostRecentlyViewed(user));
            model.addAttribute("featuredRecipe", getFeaturedRecipe());
            model.addAttribute("mostPopular", getMostPopularRecipe());

        }

        return route;
    }

    private List<Recipe> getMostRecentlyViewed(User user) {
        List<RecipeView> recipeViews = recipeViewRepository.findByUser(user);
        Collections.sort(recipeViews, new SortByDate());
        Collections.reverse(recipeViews);
        List<RecipeView> recentlyViewedRecipes = new ArrayList<>();
        if (recipeViews.size() >= 5) {
            recentlyViewedRecipes = recipeViews.subList(0, 5);
        } else {
            recentlyViewedRecipes = recipeViews.subList(0, recipeViews.size());
        }
        List<Recipe> recipes = new ArrayList<>();
        for (RecipeView recipeView:recentlyViewedRecipes) {
            recipes.add(recipeView.getRecipe());
            //recipes.add(recipeRepository.findByRecipeView(recipeView));

        }
        return recipes;
    }

    private Recipe getFeaturedRecipe() {
        List<Recipe> allRecipes = new ArrayList<>();
        allRecipes = (List<Recipe>) recipeRepository.findAll();
        Random rand = new Random();
        int upperbound = allRecipes.size();
        int randomNumber = rand.nextInt(upperbound);
        Recipe featuredRecipe = allRecipes.get(randomNumber);

        return featuredRecipe;
    }

    private List<Recipe> getMostPopularRecipe() {
        List<Recipe> allRecipes = new ArrayList<>();
        allRecipes = (List<Recipe>) recipeRepository.findAll();
        Collections.sort(allRecipes, new SortByViewCount());
        Collections.reverse(allRecipes);
        List<Recipe> mostPopularRecipes = new ArrayList<>();
        if (allRecipes.size() >= 5) {
            mostPopularRecipes = allRecipes.subList(0, 5);
        } else {
            mostPopularRecipes = allRecipes.subList(0, allRecipes.size());
        }



        return mostPopularRecipes;

    }




}
