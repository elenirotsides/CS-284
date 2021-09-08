
public class PlayingWithShapes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Rectangle r1 = new Rectangle(1.7, 2.7);
		Circle c1 = new Circle(3.4, "Blue");
		
		//automatically calls toString method
		System.out.println(r1);
		System.out.println(c1);
		
		System.out.println(r1.area());
		System.out.println(c1.area());
	}

}
