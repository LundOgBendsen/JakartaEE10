package com.recipes.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name="Recipe.findAllRecipesWithIngredients", query="select r from Recipe r join fetch r.ingredients")	
})
public class Recipe {
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true, length = 60)
	private String name;
	@Lob
	@Column(length=4096)
	private String description;
	private int preparationTime;
	private Date creationDate = new Date();
	private Category category;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] photo;
	
	private int servings;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="recipe", fetch=FetchType.EAGER)
	private Set<RecipeIngredient> ingredients = new HashSet<RecipeIngredient>();

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

	public int getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(int preparationTime) {
		this.preparationTime = preparationTime;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	public Long getId() {
		return id;
	}

	public Set<RecipeIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<RecipeIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", description="
				+ description + ", preparationTime=" + preparationTime
				+ ", creationDate=" + creationDate + ", category=" + category
				+ ", servings=" + servings + "]";
	}

}
