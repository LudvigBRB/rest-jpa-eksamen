/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Ludvig
 */
@Entity
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;
    private List<String> ingredients = new ArrayList();
    private String cookingTime;
    private List<String> preparation;

    public Recipe() {
    }

    public Recipe(Long id, String description, String cookingTime, List<String> preparation) {
        this.id = id;
        this.description = description;
        this.cookingTime = cookingTime;
        this.preparation = preparation;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStepList(List<String> steps) {
        this.preparation = steps;
    }

    public void addStep(String step) {
        ingredients.add(step);
    }

    public void setIngredientList(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entities.Recipe[ id=" + id + " ]";
    }

}
