package week2;

public class Student extends Person {
	
	private double GPA;
	
	public Student(String firstName, String lastName, int age, double GPA) {
		super(firstName, lastName, age);
		this.GPA = GPA;
	}
	
	public double getGPA() {
		return GPA;
	}
	
	public void setGPA(double GPA) {
		this.GPA = GPA;
	}
	
	public String toString() {
		return super.toString() + " They have a GPA of " + GPA;
	}
	
	
	public static void main(String[] args) {
		Person john = new Person("John", "Smith", 24);
		john.setAge(25);
		System.out.println(john);
		
		Student joe = new Student("Joe", "Schmoo", 27, 3.9);
		System.out.println(joe);
		
		Undergrad jimmy = new Undergrad("Jimmy", "John", 20, 3.7, 2);
		System.out.println(jimmy);
	}

}
