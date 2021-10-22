
public class FactorialExample {
	
	public int factorial(int n) {
		if(n < 0) {
			throw new IllegalArgumentException("Cannot find factorial of negative numbers.");
		}
		
		if (n == 0) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}
	}

	public static void main(String[] args) {
		FactorialExample fe = new FactorialExample();
		System.out.println(fe.factorial(4));
		System.out.println(fe.factorial(-4));

	}

}
