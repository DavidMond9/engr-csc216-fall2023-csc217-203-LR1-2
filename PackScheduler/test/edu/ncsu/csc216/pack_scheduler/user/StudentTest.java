package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentTest {
	
	/**
	 * Test First Name
	 */
	private static final String FIRST_NAME = "Bob";
	/**
	 * Test Last Name
	 */
	private static final String LAST_NAME = "Builder";
	/**
	 * Test ID
	 */
	private static final String ID = "bbuilder";
	/**
	 * Test email
	 */
	private static final String EMAIL = "bbuilder@ncsu.edu";
	/**
	 * Test Password
	 */
	private static final String PASSWORD = "building";
	/**
	 * Test max credits
	 */
	private static final int MAX_CREDITS = 16;
	
	/**
	 * Tests constructing a Student with no specific credit amount given
	 */
	@Test
	void testStudentNoCredits() {
		//Tests a valid construction
		Student s = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS),
				"Should not throw exception");
		
		assertAll("Course", 
				() -> assertEquals(FIRST_NAME, s.getFirstName(), "incorrect name"), 
				() -> assertEquals(LAST_NAME, s.getLastName(), "incorrect title"),
				() -> assertEquals(ID, s.getId(), "incorrect section"), 
				() -> assertEquals(EMAIL, s.getEmail(), "incorrect credits"),
				() -> assertEquals(PASSWORD, s.getPassword(), "incorrect instructor id"),
				() -> assertEquals(MAX_CREDITS, s.getMaxCredits(), "incorrect meeting days"));		
	}
	
	@Test
	void testStudentWithCredits() {
		//Tests a valid construction
				Student s = assertDoesNotThrow(
						() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD),
						"Should not throw exception");
				
				assertAll("Course", 
						() -> assertEquals(FIRST_NAME, s.getFirstName(), "incorrect name"), 
						() -> assertEquals(LAST_NAME, s.getLastName(), "incorrect title"),
						() -> assertEquals(ID, s.getId(), "incorrect section"), 
						() -> assertEquals(EMAIL, s.getEmail(), "incorrect credits"),
						() -> assertEquals(PASSWORD, s.getPassword(), "incorrect instructor id"),
						() -> assertEquals(18, s.getMaxCredits(), "incorrect meeting days"));	
	}


	@Test
	void testSetFirstName() {
		fail("Not yet implemented");
	}

	@Test
	void testSetLastName() {
		fail("Not yet implemented");
	}

	@Test
	void testSetEmail() {
		fail("Not yet implemented");
	}

	@Test
	void testSetPassword() {
		fail("Not yet implemented");
	}

	@Test
	void testSetMaxCredits() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testEqualsObject() {
		fail("Not yet implemented");
	}
	
	@Test
	void testHashCode() {
		fail("Not yet implemented");
	}


}
