package dk.lundogbendsen.batch.recipes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import dk.lundogbendsen.batch.recipes.validation.DecimalValid;

@Entity
@NamedQueries({
		@NamedQuery(name = "Ingredient.findAll", query = "SELECT i FROM Ingredient i"),
		@NamedQuery(name = "Ingredient.findByName", query = "SELECT i FROM Ingredient i WHERE i.name = :name") })
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(unique = true, length = 60)
	@NotNull
	@Size(max=60, min=3)
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
