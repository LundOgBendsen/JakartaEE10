package com.recipes.model;

import com.recipes.validation.DecimalValid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Ingredient {
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true, length = 60)
	@Size(min = 2, max = 60)
	@NotNull
	private String name;
	@NotNull
	private MeasureUnit unit;
	@DecimalValid(delta=0.01, min=0.0, max=100_000.0, message="Calories out of range")
	private double calories;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MeasureUnit getUnit() {
		return unit;
	}

	public void setUnit(MeasureUnit unit) {
		this.unit = unit;
	}

	public Long getId() {
		return id;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", unit=" + unit
				+ ", calories=" + calories + "]";
	}

	
	
	
}
