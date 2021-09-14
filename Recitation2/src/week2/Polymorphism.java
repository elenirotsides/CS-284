package week2;

class Bird{
	public void Sing() {
		System.out.println("tweet");
	}
	
	public void Wings() {
		System.out.println("2");
	}
}

class Robin extends Bird{
	public void Sing() {
		System.out.println("eeeeeeeeeeeee");
	}
	public void Wings() {
		System.out.println("8");
	}
}

public class Polymorphism {

	public static void main(String[] args) {
		Bird a = new Bird();
		a.Sing();
		a.Wings();
		System.out.print("\n");
		Bird b = new Robin();
		b.Sing();
		b.Wings();
		System.out.print("\n");
		Robin c = new Robin();
		c.Sing();
		c.Wings();
		
	}

}
