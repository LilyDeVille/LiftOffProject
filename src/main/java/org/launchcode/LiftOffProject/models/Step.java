package org.launchcode.LiftOffProject.models;


import javax.persistence.*;

@Entity
public class Step {

    @ManyToOne
    private Recipe recipe;
    private String description;
    private int stepOrder;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Step() {

    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(int stepOrder) {
        this.stepOrder = stepOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
