
public class Hello {
	
	public static boolean isPrime(int num) {
		// i < the square root of num for optimization
		
		if(num <= 1) {
			return false;
		}
		
		
		for(int i = 2; i < num; i++) {
			if(num % i == 0) {
				return false;
			} 
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Number 3
		System.out.println("Hello World!");
		
		// Number 4
		System.out.println(isPrime(25));
	}

}
