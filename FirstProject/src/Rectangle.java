public class Rectangle {

	private double width; 
	private double height;
	private static int numberOfRectangles = 0;

	//Constructor, no return type for constructors, its an initializer 
	public Rectangle(double w, double h) {
		width = w;
		height = h;
	}

	//Method
	public double area() {
		return width * height;
	}

	public static int getNumberOfRectangles() {
		return numberOfRectangles;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rectangle rect1 = new Rectangle(3.5, 2.6);
		Rectangle rect2 = new Rectangle(7.2, 8.4);

		double ar;
		ar = rect1.area();
		System.out.println(ar);
		ar = rect2.area();
		System.out.println(ar);

	}

}