
public class BasicMath {
	
	public int i;
	public int j;
	
	public BasicMath(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public int add() {
		return i + j;
	}
	
	public int multiply() {
		return i * j;
	}
	
	public int divide() {
		if(j == 0) {
			throw new ArithmeticException("j cannot be 0");
		}
		return i / j;
	}
	
	public String toString() {
		return "Basic(i = " + i + ", j = " + j + ")";
	}
	
}
