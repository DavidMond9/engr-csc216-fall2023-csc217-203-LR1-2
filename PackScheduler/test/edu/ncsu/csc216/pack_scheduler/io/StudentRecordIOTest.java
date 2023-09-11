/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for StudentRecordIO Object
 * 
 * @author Sean Van Acker
 * @author David Mond
 */
class StudentRecordIOTest {
	
	
	/**
	 * Helper method to compare two files for the same contents. Catches IOException error.
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
			 Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
			
			while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act, "Expected: " + exp + " Actual: " + act); 
				//The third argument helps with debugging!
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

	/** setUp helper method
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/** testReadStudentRecords helper method
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.io.StudentRecordIO#readStudentRecords(java.lang.String)}.
	 */
	@Test
	void testReadStudentRecords() {
		fail("Not yet implemented");
	}

	/** testWriteStudentRecords helper method
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.io.StudentRecordIO#writeStudentRecords(java.lang.String, java.util.ArrayList)}.
	 */
	@Test
	void testWriteStudentRecords() {
		fail("Not yet implemented");
	}

}
