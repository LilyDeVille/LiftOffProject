package org.launchcode.LiftOffProject.Repository;


import org.launchcode.LiftOffProject.models.Ingredient;
import org.launchcode.LiftOffProject.models.Recipe;
import org.launchcode.LiftOffProject.models.Step;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StepRepository extends CrudRepository<Step, Integer> {

    List<Step> findByRecipe(Recipe recipe);


}
