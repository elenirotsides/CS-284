package test;

import java.util.Calendar;

public class TestPerson {

	public static void main(String[] args) {
		Person p1 = new Person("Sam", "Jones", "1234", 1930);
		Person p2 = new Person("Sue", "Jones", "5678", 1990);
		
		Calendar cal = Calendar.getInstance();
		
		System.out.println(cal.get(Calendar.YEAR));
		
		System.out.println("-Age of " +p1.getGivenName()+
				" is "+p1.age(cal.get(Calendar.YEAR)));
	
		
		if (p1.isSenior(cal.get(Calendar.YEAR)))
			System.out.println(p1.getGivenName()
			 +" can ride the subway for free");
		else
			System.out.println(p1.getGivenName()
			+" must pay to ride the subway");
		
		if (p2.canVote(cal.get(Calendar.YEAR)))
			System.out.println(p2.getGivenName()+" can vote");
		else
			System.out.println(p2.getGivenName()+" can’t vote");
		
		
		System.out.println("-Is p1 and p2 objects are same person?");
		if (p1.equals(p2))
			System.out.println("Yes, p1 and p2 are the same person!");
		else
			System.out.println("No, p1 and p2 are two different persons!");
		

	}

}
