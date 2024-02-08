package com.recipes.web.viewbean;

import com.recipes.cdi.CustomResource;
import com.recipes.ejb.IngredientDao;
import com.recipes.model.Ingredient;
import com.recipes.model.MeasureUnit;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@jakarta.inject.Named
@jakarta.faces.view.ViewScoped
public class IngredientListViewBean implements Serializable{
	@Inject
	@CustomResource
	IngredientDao dao;

	List<Ingredient> ingredients = null;
	Ingredient current=new Ingredient();

	@PostConstruct
	void init() {
		ingredients = dao.findAll();
	}
	
	public void create(ActionEvent ae) {
		dao.create(current);
		FacesMessage msg = new FacesMessage("Created Ingredient", getCurrent().getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		this.current=new Ingredient();
		init();
	}
	
	public MeasureUnit[] getUnits() {
        return MeasureUnit.values();
    }


	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Ingredient getCurrent() {
		return current;
	}

	public void setCurrent(Ingredient current) {
		this.current = current;
	}
}