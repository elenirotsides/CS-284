
public class Multiplication_Example {
	public int multiply(int a, int count) {
		if (count==1)
			return a;
		else
			return a+multiply(a, count-1);
	}
	
	public static void main(String[] args) {
		Multiplication_Example m = new Multiplication_Example();
		System.out.println("The result of multiply(2,4) = "+m.multiply(2, 4));
		System.out.println("The result of multiply(3,5) = "+m.multiply(3, 5));
		System.out.println("The result of multiply(6,7) = "+m.multiply(6, 7));
	
	}
}
