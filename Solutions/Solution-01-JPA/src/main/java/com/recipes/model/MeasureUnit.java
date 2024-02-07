package com.recipes.model;

public enum MeasureUnit {
	KG("Kilo"), L("Liter"), Gr("Gram"), Dl("Deci liter"), TableSpoon("Table spoon"), TeaSpoon("Tea Spoon"), Pcs("Pieces");

	private String unitName;

	private MeasureUnit(String name) {
		this.unitName=name;
	}

	public String getUnitName() {
		return unitName;
	}
}
