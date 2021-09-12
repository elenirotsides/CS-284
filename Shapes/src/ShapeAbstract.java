
public abstract class ShapeAbstract {
	//Data fields
	private String color;
	
	//Constructor
	
	public ShapeAbstract(String color) {
		this.color = color;
	}
	//Methods
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public abstract double area();
	
	public String toString() {
		return "I am a Shape. My color is "+color;
	}
}
