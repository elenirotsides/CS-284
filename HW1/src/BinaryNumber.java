import java.lang.Character;
import java.util.Arrays;

/**
 * 
 * @author Eleni Rotsides
 * I pledge my honor that I have abided by the Stevens honor system.
 */
public class BinaryNumber {
	
	//Data fields
	private int data[];
	private int length;
	
	//Constructors
	public BinaryNumber(int length) {
		if(length <= 0) {
			throw new IllegalArgumentException("Length must be greater than zero.");
		}
		
		int[] dataArray = new int[length];
		
		for(int i = 0; i < length; i++) {
			dataArray[i] = 0;
		}
		
		data = dataArray;
		this.length = dataArray.length;
	}
	
	public BinaryNumber(String str) {
		
		if(str == "") {
			throw new IllegalArgumentException("Str cannot be empty");
		}
		
		int[] dataArray = new int[str.length()];
		
		for(int i = 0; i < str.length(); i++) {
			if(Character.getNumericValue(str.charAt(i)) == 0) {
				dataArray[i] = 0;
			}else if(Character.getNumericValue(str.charAt(i)) == 1) {
				dataArray[i] = 1;
			}else {
				throw new IllegalArgumentException("Str can only be made up of ones and zeros");
			}
		}
		
		data = dataArray;
		length = str.length();
		
		
	}
	
	//Methods
	public int getLength() {
		return length;
	}
	
	public int[] getInnerArray() {
		return data;
	}
	
	public int getDigit(int index) {
		if(index < 0 || index > (length - 1)) {
			throw new IndexOutOfBoundsException("Index cannot be less than 0 or greater than the length of its digits");
		}
		
		return data[index];
		
	}
	
	public int toDecimal() {
		//TODO
		int[] copy = data.clone();
		int decimalNumber = 0;
		int counter = 0;
		
		for(int i = length - 1; i >= 0; i--) {
			decimalNumber += (copy[i] * Math.pow(2, counter));
			counter++;
		}
		
		return decimalNumber;
	
	}
	
	public void bitShift(int direction, int amount) {
		if(direction == 1) {
			if(amount < 0) {
				throw new IllegalArgumentException("amount must be greater than 0");
			}
			if(amount >= data.length) {
				throw new IllegalArgumentException("amount is larger or equal to than the length of the data array. please select a smaller number.");
			}
			int[] newData = new int[length - amount];
			
			for(int i = 0; i < newData.length; i++) {
				newData[i] = data[i];
			}
			
			data = newData;
			length = newData.length;
			
		} else if(direction == -1) {
			if(amount < 0) {
				throw new IllegalArgumentException("amount must be greater than 0");
			}
			int[] newData = new int[length + amount];
			
			for(int i = 0; i < data.length; i++) {
				newData[i] = data[i];
			}
			
			data = newData;
			length = newData.length;
		} else {
			throw new IllegalArgumentException("direction must be -1 or 1");
		}
		
		
		
	}
	
	public void add(BinaryNumber aBinaryNumber) {
		if(length < aBinaryNumber.getLength()) {
			prepend(aBinaryNumber.getLength() - length);
		} else if(length > aBinaryNumber.getLength()) {
			aBinaryNumber.prepend(length - aBinaryNumber.getLength());
		}
		
		int[] bn1 = data;
		int[] bn2 = aBinaryNumber.getInnerArray();
		int[] result = new int[length];
		int carry = 0;
		
		for(int i = length - 1; i >= 0; i--) {
			if(bn1[i] == 0 && bn2[i] == 0) {
				if(carry == 1) {
					result[i] = 1;
					carry = 0;
				} else {
					result[i] = 0;
					carry = 0;
				}
			} 
			else if(bn1[i] == 0 && bn2[i] == 1) {
				if(carry == 1) {
					result[i] = 0;
				} else {
					result[i] = 1;
					carry = 0;
				}
			}
			else if(bn1[i] == 1 && bn2[i] == 0) {
				if(carry == 1) {
					result[i] = 0;
				} else {
					result[i] = 1;
					carry = 0;
				}
			} else {
				if(carry == 1) {
					result[i] = 1;
					carry = 1;
				} else {
					result[i] = 0;
					carry = 1;	
				}
				
			}
		}
		
		data = result;
		length = result.length;
		
		if(carry == 1) {
			int[] newResult = new int[length + 1];
			
			newResult[0] = 1;
			
			for(int i = 1; i < newResult.length; i++) {
				newResult[i] = result[i - 1];
			}
			data = newResult;
			length = newResult.length;
		}
	}
	
	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn3) {
		if(bn1.getLength() != bn3.getLength()) {
			throw new UnsupportedOperationException("Both inputs must be the same length");
		}
		
		int[] bn1Array = bn1.getInnerArray();
		int[] bn3Array = bn3.getInnerArray();
		int[] bworArray = new int [bn1Array.length];
		
		for(int i = 0; i < bn1Array.length; i++) {
			bworArray[i] = bn1Array[i] | bn3Array[i];
		}
		
		return bworArray;
	}
	
	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn3) {
		if(bn1.getLength() != bn3.getLength()) {
			throw new UnsupportedOperationException("Both inputs must be the same length");
		}
		
		int[] bn1Array = bn1.getInnerArray();
		int[] bn3Array = bn3.getInnerArray();
		int[] bwandArray = new int [bn1Array.length];
		
		for(int i = 0; i < bn1Array.length; i++) {
			bwandArray[i] = bn1Array[i] & bn3Array[i];
		}
		
		return bwandArray;
	}
	
	public void prepend(int amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("amount must be a nonnegative number");
		}
		
		int[] copy = data.clone();
		int[] newArray = new int[amount + copy.length];
		
		for(int i = amount; i < newArray.length; i++) {
			newArray[i] = copy[i - amount];
		}
		
		data = newArray;
		length = amount + copy.length;
	}
	
	public String toString() {
		String dataString = "";
		int[] dataArray = data.clone();
		
		for(int i = 0; i < length; i++) {
			dataString += Integer.toString(dataArray[i]);
		}
		
		return dataString;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryNumber b1 = new BinaryNumber(4);
		BinaryNumber b2 = new BinaryNumber("1011");
		
		System.out.println("Starting to test b1...");
		System.out.println();
		System.out.println(b1.getLength());
		System.out.println(Arrays.toString(b1.getInnerArray()));
		System.out.println(b1.toDecimal());
		b1.prepend(2);
		System.out.println(Arrays.toString(b1.getInnerArray()));
		System.out.println();
		
		
		System.out.println("Starting to test b2...");
		System.out.println();
		System.out.println(Arrays.toString(b2.getInnerArray()));
		System.out.println(b2.toString());
		b2.prepend(3);
		System.out.println(Arrays.toString(b2.getInnerArray()));
		System.out.println(b2.toString());
		System.out.println(b2.toDecimal());
		System.out.println();
		
		System.out.println("Bitshift..");
		BinaryNumber shiftNum = new BinaryNumber("10101");
		shiftNum.bitShift(1, 2);
		System.out.println(shiftNum);
		BinaryNumber shiftNum2 = new BinaryNumber("10101");
		shiftNum2.bitShift(-1, 2);
		System.out.println(shiftNum2);
		System.out.println();
		
		System.out.println("Testing add...");
		System.out.println();
		BinaryNumber b21 = new BinaryNumber("11");
		BinaryNumber b3 = new BinaryNumber("1011");
		b21.add(b3);
		System.out.println(Arrays.toString(b21.getInnerArray()));
		
		BinaryNumber b6 = new BinaryNumber("1010101011111");
		BinaryNumber b7 = new BinaryNumber("0");
		b6.add(b7);
		System.out.println(Arrays.toString(b6.getInnerArray()));
		System.out.println();
		
		System.out.println("Testing bwor and bwand...");
		System.out.println();
		BinaryNumber b4 = new BinaryNumber("1011011");
		BinaryNumber b5 = new BinaryNumber("1010101");
		
		int[] answer = bwor(b4, b5);
		System.out.println(Arrays.toString(answer));
		
		int[] answer2 = bwand(b4, b5);
		System.out.println(Arrays.toString(answer2));
	}

}
