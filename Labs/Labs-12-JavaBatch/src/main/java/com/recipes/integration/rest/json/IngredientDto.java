package com.recipes.integration.rest.json;

import java.time.LocalDate;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbProperty;

import com.recipes.model.Ingredient;

public class IngredientDto {

	@JsonbProperty("id")
	private Long id;

	@JsonbProperty("name")
	private String name;

	@JsonbProperty("measure-unit")
	private String unit;

	@JsonbProperty("number-of-calories")
	private double calories;

	@JsonbDateFormat("dd-MM-yyyy")
	LocalDate timestamp = LocalDate.now();

	public IngredientDto(Ingredient ingredient) {
		id = ingredient.getId();
		name = ingredient.getName();
		unit = ingredient.getUnit().getUnitName();
		calories = ingredient.getCalories();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}

}
