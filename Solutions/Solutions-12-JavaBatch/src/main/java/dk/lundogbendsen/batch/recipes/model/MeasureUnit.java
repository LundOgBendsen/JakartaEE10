package dk.lundogbendsen.batch.recipes.model;

import java.security.InvalidParameterException;

public enum MeasureUnit {
	KG("Kilo"), // 0
	L("Liter"), // 1
	Gr("Gram"), // 2
	Dl("Deci liter"), // 3
	TableSpoon("Table spoon"), // 4
	TeaSpoon("Tea Spoon"), // 5
	PCS("Pieces"); // 6
	
	private String unitName;
	
	private MeasureUnit(String name) {
		this.unitName=name;
	}

	public String getUnitName() {
		return unitName;
	}

	static public MeasureUnit getMeasureUnitByInt(int value) throws InvalidParameterException {
		if(value < 0 || value > 6)
			throw new InvalidParameterException("");
		return MeasureUnit.values()[value];
	}
}
