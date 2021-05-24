package org.launchcode.LiftOffProject.models;


import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe<string> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    @OneToMany
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();
    @OneToMany
    private List<Step> steps = new ArrayList<>();
    @Column(nullable = true, length =4000)
    private String photos;
    @ManyToOne
    private User user;


    public Recipe(int id, String name, String description, List<Ingredient> ingredients, List<Step> steps, String photos, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.steps = steps;
        this.photos = photos;
        this.user = user;
    }

    @Transient
    public String getPhotosImagePath() {
        if (photos == null) return null;

        return "/recipe-photos/" + id + "/" + photos;
    }

    public Recipe() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
