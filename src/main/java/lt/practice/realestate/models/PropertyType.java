package lt.practice.realestate.models;

public enum PropertyType {
	
	APARTMENT(0.025),
	HOUSE(0.035),
	INDUSTRIAL(0.055);
	
	private final double value;
	
	PropertyType(final double newValue) {
		value = newValue;
	}
	
	public double getValue() {
		return value;
	}

}
