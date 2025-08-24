package creationalDesignPattern;

class GiJoe implements Cloneable {
	String hairColor;
	String skinColor;
	String country;
	int height;

	public GiJoe(String hairColor, String skinColor, String country, int height) {
		super();
		this.hairColor = hairColor;
		this.skinColor = skinColor;
		this.country = country;
		this.height = height;
	}

	@Override
	public GiJoe clone() {
		try {
			return (GiJoe) super.clone(); // Shallow copy
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public String getSkinColor() {
		return skinColor;
	}

	public void setSkinColor(String skinColor) {
		this.skinColor = skinColor;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}

class GiJoeFactory {
	private GiJoe prototype;

	public GiJoeFactory() {
		prototype = new GiJoe("Black", "Brown", "India", 5);
	}

	public GiJoe getWithColor(String color) {
		GiJoe clone = prototype.clone();
		clone.setSkinColor(color);
		return clone;
	}

	public GiJoe getWithCountry(String country) {
		GiJoe clone = prototype.clone();
		clone.setCountry(country);
		return clone;
	}

}

public class PrototypeDemo {

}
