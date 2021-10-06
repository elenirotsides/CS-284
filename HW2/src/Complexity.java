/**
 * 
 * @author Eleni Rotsides
 * @section A
 * I pledge my honor that I have abided by the Stevens honor system.
 */

public class Complexity {
	
	public static void method1(int n) {
		//O(n^2)
		if(n < 0) {
			throw new IllegalArgumentException("n must be greater than or equal to 0");
		}
		int counter = 1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}
	
	public static void method2(int n) {
		//O(n^3)
		if(n < 0) {
			throw new IllegalArgumentException("n must be greater than or equal to 0");
		}
		int counter = 1;
		for(int i = 1; i < n + 1; i++) {
			for(int j = 1; j < n + 1; j++) {
				for(int k = 1; k < n + 1; k++) {
					System.out.println("Operation " + counter);
					counter++;
				}
			}
		}
	}

	public static void method3(int n) {
		//O(log n)
		if(n < 0) {
			throw new IllegalArgumentException("n must be greater than or equal to 0");
		}
		int counter = 1;
		for(int i = 1; i < n; i*=2) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}

	public static void method4(int n) {
		//O(n log n)
		if(n < 0) {
			throw new IllegalArgumentException("n must be greater than or equal to 0");
		}
		int counter = 1;
		for(int i = 1; i < n; i*=2) {
			for(int j = 0; j < n; j++) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}

	public static void method5(int n) {
		//O(log log n)
		if(n < 0) {
			throw new IllegalArgumentException("n must be greater than or equal to 0");
		}
		int counter = 1;
		for(int i = n; i > 2; i/=Math.sqrt(i)) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}
	
	public static int method6(int n) {
		//O(2^n) - Optional
		if(n < 0) {
			throw new IllegalArgumentException("n must be greater than or equal to 0");
		}
		int timesToRun = (int) Math.pow(2,  n);
		
		class innerMethod6 {
			//Data field
			int counter = 1;
			
			//Recursive method that will be called and returned later
			public int internalMethod(int num) {
				if(num == 1) {
					System.out.println("Operation " + counter);
				} else {
					System.out.println("Operation " + counter);
					counter++;
					internalMethod(num - 1);
				}
				return 0;
			}
		}
		innerMethod6 m6 = new innerMethod6();
		int result = m6.internalMethod(timesToRun);
		return result;
	}
	
	public static void main(String args[]) {
//		method1(5);
//		method2(5);
//		method3(5);
//		method4(5);
//		method5(256*256);
//		method6(5);
	}
}
