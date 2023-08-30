package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Object that emulates a student.
 */
public class Student {
	
	public static final int MAX_CREDITS = 18;
	private String firstName;
	private String lastName;
	private String id;
	private String email;
	private String password;
	private int maxCredits;
	
	

	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		// TODO Auto-generated constructor stub
	}
	
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		// TODO Auto-generated constructor stub
	}
	
	public String getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLastName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Converts Student to a string, with variables in order of:
	 * firstName,lastName,id,email,hashedPassword,maxCredits
	 */
	@Override
	public String toString() {
		return this.firstName + "," + this.lastName + "," + this.id + "," + this.email + "," + this.password + "," + this.maxCredits;
	}
	
}
