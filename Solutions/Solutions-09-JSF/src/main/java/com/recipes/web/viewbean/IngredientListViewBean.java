package com.recipes.web.viewbean;


import com.recipes.cdi.CustomResource;
import com.recipes.ejb.IngredientDao;
import com.recipes.model.Ingredient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@jakarta.inject.Named
@jakarta.faces.view.ViewScoped
public class IngredientListViewBean implements Serializable {
    @Inject
    @CustomResource
    IngredientDao dao;


    List<Ingredient> ingredients = null;


    @PostConstruct
    void init() {
        ingredients = dao.findAll();
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }


    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
