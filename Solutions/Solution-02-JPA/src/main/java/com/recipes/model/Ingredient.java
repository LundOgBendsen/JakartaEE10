package com.recipes.model;

import jakarta.persistence.*;

@Entity
public class Ingredient {
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true, length = 60)
	private String name;
	private MeasureUnit unit;
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
