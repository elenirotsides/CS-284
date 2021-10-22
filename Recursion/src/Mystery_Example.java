
public class Mystery_Example {
	@SuppressWarnings("deprecation")
	public int mystery(int a, int b) {
		if(b==0)
			return 0;
		if(b%2==0) 
			return mystery(a+a, new Integer(b/2));
		return mystery(a+a, new Integer(b/2))+a; 
	}
	
	public static void main(String[] args) {
		Mystery_Example m = new Mystery_Example();
		System.out.println("The result of mystery(2,4) = "+m.mystery(2, 4));
		System.out.println("The result of mystery(3,5) = "+m.mystery(3, 5));
		System.out.println("The result of mystery(6,7) = "+m.mystery(6, 7));
	}
}
