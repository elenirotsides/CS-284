package week2;

public class Undergrad extends Student {
	
	private int year; // [2]/4 for example

	public Undergrad(String firstName, String lastName, int age, double GPA, int year) {
		super(firstName, lastName, age, GPA);
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String toString() {
		return super.toString() + " and they're year " + year + "/4";
	}
}
