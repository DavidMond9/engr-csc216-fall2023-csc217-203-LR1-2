/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Tests the CourseCatalog class.
 * 
 * @author Sarah Heckman
 * @author Sean Van Acker
 */
public class CourseCatalogTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	
	/** Course name */
	private static final String NAME = "CSC 216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	/** Event title */
	private static final String EVENT_TITLE = "Exercise";
	/** Event meeting days */
	private static final String EVENT_MEETING_DAYS = "MTWHF";
	/** Event start time */
	private static final int EVENT_START_TIME = 800;
	/** Event end time */
	private static final int EVENT_END_TIME = 900;
	/** Event details */
	private static final String EVENT_DETAILS = "Cardio Time!";

	/**
	 * Resets course_records.txt for use in other tests.
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	/**
	 * Test CourseCatalog.getCourseFromCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog ws = new CourseCatalog(validTestFile);
		
		//Attempt to get a course that doesn't exist
		assertNull(ws.getCourseFromCatalog("CSC 492", "001"));
		
		//Attempt to get a course that does exist
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(c, ws.getCourseFromCatalog("CSC 216", "001"));
	}
	
	/**
	 * Test CourseCatalog.addCourse().
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog ws = new CourseCatalog(validTestFile);
		
		//Attempt to add a course that doesn't exist
		assertFalse(ws.addCourseToSchedule("CSC 492", "001"));
		assertEquals(0, ws.getScheduledActivities().length);
		assertEquals(0, ws.getFullScheduledActivities().length);
		
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		
		//Attempt to add a course that does exist
		assertTrue(ws.addCourseToSchedule(NAME, SECTION));
		assertEquals(1, ws.getScheduledActivities().length);
		assertEquals(1, ws.getFullScheduledActivities().length);
		String [] course = ws.getFullScheduledActivities()[0];
		assertEquals(NAME, course[0]);
		assertEquals(SECTION, course[1]);
		assertEquals(TITLE, course[2]);
		assertEquals("" + CREDITS, course[3]);
		assertEquals(INSTRUCTOR_ID, course[4]);
		assertEquals(c.getMeetingString(), course[5]);
		assertEquals("", course[6]);
		
		//Attempt to add a course that already exists, even if different section
		try {
			ws.addCourseToSchedule(NAME, "002");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("You are already enrolled in CSC 216", e.getMessage());
		}
	}
	
	/**
	 * Test CourseCatalog.addEvent().
	 */
	@Test
	public void testAddEventToSchedule() {
		CourseCatalog ws = new CourseCatalog(validTestFile);
		
		ws.addEventToSchedule(EVENT_TITLE, EVENT_MEETING_DAYS, EVENT_START_TIME, EVENT_END_TIME, EVENT_DETAILS);
	
		assertEquals(1, ws.getScheduledActivities().length);
		assertEquals(1, ws.getFullScheduledActivities().length);
		String [] course = ws.getFullScheduledActivities()[0];
		assertEquals("", course[0]);
		assertEquals("", course[1]);
		assertEquals(EVENT_TITLE, course[2]);
		assertEquals("", course[3]);
		assertEquals("", course[4]);
		assertEquals("MTWHF 8:00AM-9:00AM", course[5]);
		assertEquals(EVENT_DETAILS, course[6]);
		
		//Attempt to add an event with the same title
		try {
			ws.addEventToSchedule(EVENT_TITLE, EVENT_MEETING_DAYS, 1200, 1500, EVENT_DETAILS);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("You have already created an event called Exercise", e.getMessage());
		}
	}
	
	/**
	 * Test CourseCatalog.removeCourse().
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		CourseCatalog ws = new CourseCatalog(validTestFile);
		
		//Attempt to remove from empty schedule
		assertFalse(ws.removeCourseFromSchedule(0));
		
		//Add some courses and remove them
		assertTrue(ws.addCourseToSchedule(NAME, SECTION));
		assertTrue(ws.addCourseToSchedule("CSC 226", "001"));
		ws.addEventToSchedule(EVENT_TITLE, EVENT_MEETING_DAYS, EVENT_START_TIME, EVENT_END_TIME, EVENT_DETAILS);
		assertTrue(ws.addCourseToSchedule("CSC 116", "002"));
		assertEquals(4, ws.getScheduledActivities().length);
		assertEquals(4, ws.getFullScheduledActivities().length);
		
		//Check that removing a course that doesn't exist when there are 
		//scheduled courses doesn't break anything
		assertFalse(ws.removeCourseFromSchedule(5));
		assertEquals(4, ws.getScheduledActivities().length);
		assertEquals(4, ws.getFullScheduledActivities().length);
		
		//Remove Exercise
		assertTrue(ws.removeCourseFromSchedule(1));
		assertEquals(3, ws.getScheduledActivities().length);
		assertEquals(3, ws.getFullScheduledActivities().length);
		
		//Remove CSC226
		assertTrue(ws.removeCourseFromSchedule(1));
		assertEquals(2, ws.getScheduledActivities().length);
		assertEquals(2, ws.getFullScheduledActivities().length);
		
		//Remove CSC116
		assertTrue(ws.removeCourseFromSchedule(1));
		assertEquals(1, ws.getScheduledActivities().length);
		assertEquals(1, ws.getFullScheduledActivities().length);
		
		//Remove CSC216
		assertTrue(ws.removeCourseFromSchedule(0));
		assertEquals(0, ws.getScheduledActivities().length);
		assertEquals(0, ws.getFullScheduledActivities().length);
		
		//Check that removing all doesn't break future adds
		assertTrue(ws.addCourseToSchedule("CSC 230", "001"));
		assertEquals(1, ws.getScheduledActivities().length);
		assertEquals(1, ws.getFullScheduledActivities().length);
	}
	
	/**
	 * Test CourseCatalog.resetSchedule()
	 */
	@Test
	public void testResetSchedule() {
		CourseCatalog ws = new CourseCatalog(validTestFile);
		
		//Add some courses and reset schedule
		assertTrue(ws.addCourseToSchedule(NAME, SECTION));
		assertTrue(ws.addCourseToSchedule("CSC 226", "001"));
		assertTrue(ws.addCourseToSchedule("CSC 116", "002"));
		assertEquals(3, ws.getScheduledActivities().length);
		assertEquals(3, ws.getFullScheduledActivities().length);
		
		ws.resetSchedule();
		assertEquals(0, ws.getScheduledActivities().length);
		assertEquals(0, ws.getFullScheduledActivities().length);
		
		//Check that resetting doesn't break future adds
		assertTrue(ws.addCourseToSchedule("CSC 230", "001"));
		assertEquals(1, ws.getScheduledActivities().length);
		assertEquals(1, ws.getFullScheduledActivities().length);
	}
	
	/**
	 * Test CourseCatalog.getCourseCatalog().
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog ws = new CourseCatalog(validTestFile);
		
		//Get the catalog and make sure contents are correct
		//Name, section, title
		String [][] catalog = ws.getCourseCatalog();
		//Row 0
		assertEquals("CSC 116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		//Row 1
		assertEquals("CSC 116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		//Row 2
		assertEquals("CSC 116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		//Row 3
		assertEquals("CSC 216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Software Development Fundamentals", catalog[3][2]);
		//Row 4
		assertEquals("CSC 216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Software Development Fundamentals", catalog[4][2]);
		//Row 5
		assertEquals("CSC 216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Software Development Fundamentals", catalog[5][2]);
		//Row 6
		assertEquals("CSC 217", catalog[6][0]);
		assertEquals("202", catalog[6][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[6][2]);
		//Row 7
		assertEquals("CSC 217", catalog[7][0]);
		assertEquals("211", catalog[7][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[7][2]);
		//Row 8
		assertEquals("CSC 217", catalog[8][0]);
		assertEquals("223", catalog[8][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[8][2]);
		//Row 9
		assertEquals("CSC 217", catalog[9][0]);
		assertEquals("601", catalog[9][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[9][2]);
		//Row 10
		assertEquals("CSC 226", catalog[10][0]);
		assertEquals("001", catalog[10][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[10][2]);
		//Row 11
		assertEquals("CSC 230", catalog[11][0]);
		assertEquals("001", catalog[11][1]);
		assertEquals("C and Software Tools", catalog[11][2]);
		//Row 12
		assertEquals("CSC 316", catalog[12][0]);
		assertEquals("001", catalog[12][1]);
		assertEquals("Data Structures and Algorithms", catalog[12][2]);
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}

