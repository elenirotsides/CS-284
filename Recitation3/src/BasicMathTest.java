import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BasicMathTest {

	@Test
	void test() {
		BasicMath bm = new BasicMath(3,0);
		
		assertThrows(ArithmeticException.class, () -> { bm.divide(); });
	}
	
	@Test
	void test2() {
		BasicMath bm = new BasicMath(3,4);
		
		assertEquals(bm.multiply(), 12);
	}

}
