package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Student class has many fields defining attributes about the student. It has setter and
 * getter methods for the name, id, and more. It has two constructors, one that uses
 * encapsulation and allows user to set maxCredits, and one that uses the default maxCredits.

/**
 * Object that emulates a student.
 */
public class Student implements Comparable<Student> {
	/**
	 * Default maxCredits value.
	 */
	public static final int MAX_CREDITS = 18;
	/**
	 * First name for the student.
	 */
	private String firstName;
	/**
	 * Last name for the student.
	 */
	private String lastName;
	/**
	 * Student's id number.
	 */
	private String id;
	/**
	 * Student's email address.
	 */
	private String email;
	/**
	 * Password for the student.
	 */
	private String password;
	/**
	 * Change the maxCredits default value to store into this state.
	 */
	private int maxCredits;
	
	
	/**
	 * Creates a student object. Uses encapsulation and setter methods to define all attributes.
	 * Can alter the maxCredits value.
	 * @param firstName Student's first name.
	 * @param lastName Student's last name.
	 * @param id Student's id number.
	 * @param email Student's email address.
	 * @param password Student's password.
	 * @param maxCredits Student's max credits.
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
		setMaxCredits(maxCredits);
		
	}
	/**
	 * Creates a Student object. Same as previous Student constructor except this uses the
	 * default value for maxCredits of 18.
	 * @param firstName Student's first name.
	 * @param lastName Student's last name.
	 * @param id Student's id number.
	 * @param email Student's email address.
	 * @param password Student's password.
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
		setMaxCredits(MAX_CREDITS);
	}
	
	
	/**
	 * Gets student's first name.
	 * @return Student's first name.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Get's student's last name.
	 * @return Student's last name.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Get's student's id.
	 * @return Student's id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * Get's student's email.
	 * @return Student's email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Get's student's password.
	 * @return Student's password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Get's student's max credits.
	 * @return Student's max credits.
	 */
	public int getMaxCredits() {
		return maxCredits;
	}
	
	
	/**
	 * Set's the student's first name.
	 * @param name Represents the student's first name.
	 */
	public void setFirstName(String name) {
		if("".equals(name) || name == null) {
			throw new IllegalArgumentException("Invalid first name");
		}
		firstName = name;
	}

	/**
	 * Set's the student's last name.
	 * @param name Represents the student's last name.
	 */
	public void setLastName(String name) {
		if("".equals(name) || name == null) {
			throw new IllegalArgumentException("Invalid last name");
		}
		lastName = name;
	}
	/**
	 * Set's the student's id name.
	 * @param id Represents the student's id.
	 */
	private void setId(String id) {
		if("".equals(id) || id == null) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}
	/**
	 * Set's the student's email.
	 * @param email Represents the student's email.
	 */
	public void setEmail(String email) {
		if("".equals(email) || email == null) {
			throw new IllegalArgumentException("Invalid email");
		}
		if(!email.contains("@") || !email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
		int idx1 = email.indexOf("@");
//		for(int i = 0; i < email.length(); i++) {
//			//if current index is equal to i, and index of . is before index of @, throw exception
//			if(email.indexOf(".") == i && i < idx1) {
//				throw new IllegalArgumentException("Invalid email.");
//			}
//		}
		int indexOfLastDot = email.lastIndexOf(".");
		if(indexOfLastDot < idx1) {
			throw new IllegalArgumentException("Invalid email");
		}

		this.email = email;
	}
	/**
	 * Set's the student's password.
	 * @param password Represents the student's password.
	 */
	public void setPassword(String password) {
		if("".equals(password) || password == null) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}
	/**
	 * Set's the student's credits.
	 * @param credits Represents the student's credits.
	 */
	public void setMaxCredits(int credits) {
		if(credits < 3 || credits > 18) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		maxCredits = credits;
	}
	/**
	 * Converts Student to a string, with variables in order of:
	 * firstName,lastName,id,email,hashedPassword,maxCredits
	 * @return String of all student variables.
	 */
	@Override
	public String toString() {
		return this.firstName + "," + this.lastName + "," + this.id + "," + this.email + "," + this.password + "," + this.maxCredits;
	}
	/**
	 * Gets the hash code.
	 * @return Returns the hash code.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + email.hashCode();
		result = prime * result + firstName.hashCode();
		result = prime * result + id.hashCode();
		result = prime * result + lastName.hashCode();
		result = prime * result + maxCredits;
		result = prime * result + password.hashCode();
		return result;
	}
	/**
	 * Checks if an object is equal to another object.
	 * @return Returns true if equal, false if not equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (!email.equals(other.email))
			return false;
		if (!firstName.equals(other.firstName))
			return false;
		if (!id.equals(other.id))
			return false;
		if (!lastName.equals(other.lastName))
			return false;
		if (maxCredits != other.maxCredits)
			return false;
	    return password.equals(other.password);
	}
	@Override
	public int compareTo(Student o) {
		if (this.getLastName().compareTo(o.getLastName()) != 0) {
			return this.getLastName().compareTo(o.getLastName());
		}
		if (this.getFirstName().compareTo(o.getFirstName()) != 0) {
			return this.getFirstName().compareTo(o.getFirstName());
		}
		if (this.getId().compareTo(o.getId()) != 0) {
			return this.getId().compareTo(o.getId());
		}
		return 0;
	}
}
