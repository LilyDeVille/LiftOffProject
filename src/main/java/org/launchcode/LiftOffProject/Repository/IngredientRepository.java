package org.launchcode.LiftOffProject.Repository;

import org.launchcode.LiftOffProject.models.Ingredient;
import org.launchcode.LiftOffProject.models.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

List<Ingredient> findByRecipe(Recipe recipe);

}
