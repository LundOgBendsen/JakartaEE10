package dk.lundogbendsen.recipes.model;

public enum MeasureUnit {
	KG("Kilo"), L("Liter"), Gr("Gram"), Dl("Deci liter"), TableSpoon("Table spoon"), TeaSpoon("Tea Spoon"), PCS("Pieces");
	
	private String unitName;
	
	private MeasureUnit(String name) {
		this.unitName=name;
	}

	public String getUnitName() {
		return unitName;
	}

}
