package org.launchcode.LiftOffProject.Repository;

import org.launchcode.LiftOffProject.models.Recipe;
import org.launchcode.LiftOffProject.models.RecipeView;
import org.launchcode.LiftOffProject.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    //List<Recipe> findbyName(String name);

    //Recipe findById(int id);

    List<Recipe> findByUser(User user);

//    Recipe findByRecipeView(RecipeView recipeView);
}