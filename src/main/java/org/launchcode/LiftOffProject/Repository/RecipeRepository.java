package org.launchcode.LiftOffProject.Repository;

import org.launchcode.LiftOffProject.models.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    //List<Recipe> findbyName(String name);

    //Recipe findById(int id);
}