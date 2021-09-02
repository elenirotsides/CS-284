package test;

public class Person {
	
	private String givenName;
	private String familyName;
	private String IDNumber;
	private int birthyear = 1900;
	
	//constants, we know this bc of the final keyword and the all caps variable convention
	public static final int VOTE_AGE = 18;
	public static final int SENIOR_AGE = 65;
	
	public Person(String first, String last, String id, int birth) {
		givenName = first;
		familyName = last;
		IDNumber = id;
		birthyear = birth;
	}
	
	public Person(String id) {
		IDNumber = id;
	}
	
	// Accessor Methods
	public String getGivenName() {
		return givenName;
	}
	
	public String getFamilyName() {
		return familyName;
	}
	
	public String getIDNumber() {
		return IDNumber;
	}
	
	public int getBirthyear() {
		return birthyear;
	}

	// Mutator Methods
	public void setGivenName(String givenname) {
		this.givenName = givenname;
	}

	public void setFamilyName(String familyname) {
		this.familyName = familyname;
	}

	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	public void setBirthyear(int birthyear) {
		this.birthyear = birthyear;
	}

	/**
	 * Calculates the person's age w.r.t. current year.
	 * @param year The current year
	 * @return the year minus birth year
	 */
	public int age(int year) {
		return year - birthyear;
	}
	
	/**
	 * Checks if two Person objects are equal
	 * @param p A Person object
	 * @return true if two Person objects are equal
	 */
	public boolean equals(Person p) {
		if (p == null)
			return false;
		return this.givenName.equalsIgnoreCase(p.givenName) &&
				this.familyName.equalsIgnoreCase(p.familyName) &&
				this.IDNumber.equals(p.IDNumber) && this.birthyear == p.birthyear;			
	}
	
	/**
	 * Finds if the person can vote.
	 * @param year The current year
	 * @return true if the Person is eligible to vote.
	 */
	public boolean canVote(int year) {
		return age(year) >= VOTE_AGE;
			
	}
	
	/**
	 * Finds if the person is senior.
	 * @param year The current year
	 * @return true if the Person is in the senior age.
	 */
	public boolean isSenior(int year) {
		if (this.age(year) >= SENIOR_AGE)
			return true;
		return false;
	}
	
	/**
	 * Displays the information in a Person object.
	 * @return the object state as a string
	 */
	public String toString() {
		return "Given name: "+ givenName +"\n"
				+"Family name: "+ familyName +"\n"
				+"ID number: "+ IDNumber +"\n"
				+"Year of birth: "+ birthyear +"\n";
		
	}

}
