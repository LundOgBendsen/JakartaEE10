package com.recipes.model;

import java.security.InvalidParameterException;

public enum MeasureUnit {
	KG("Kilo"), L("Liter"), Gr("Gram"), Dl("Deci liter"), TableSpoon("Table spoon"), TeaSpoon("Tea Spoon"), PCS("Pieces");
	
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
