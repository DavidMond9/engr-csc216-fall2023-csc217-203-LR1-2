/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests for StudentRecordIO Object
 * 
 * @author Sean Van Acker
 * @author 
 */
class StudentRecordIOTest {

	/** Valid student records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Invalid student records */
	private final String invalidTestFile = "test-files/invalid_student_records.txt";
	
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";

	private String [] validStudents = {validStudent0, validStudent1, validStudent2, validStudent3, validStudent4, validStudent5,
	        validStudent6, validStudent7, validStudent8, validStudent9};

	private String hashPW;
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = Base64.getEncoder().encodeToString(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}

	/**
	 * Helper method to compare two files for the same contents
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
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.io.StudentRecordIO#readStudentRecords(java.lang.String)}.
	 */
	@Test
	void testReadStudentRecords() {
		try {
			ArrayList<Student> s = StudentRecordIO.readStudentRecords(validTestFile);
			assertEquals(10, s.size());
			
			for (int i = 0; i < validStudents.length; i++) {
				assertEquals(validStudents[i], s.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}

	
	/**
	 * Tests readStudentRecords() with invalid test file
	 */
	@Test
	public void testReadInvalidStudentRecords() {
		ArrayList<Student> s;
		try {
			s = StudentRecordIO.readStudentRecords(invalidTestFile);
			assertEquals(0, s.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}
	
//	/**
//	 * Tests readStudentRecords() for a file that does not exist.
//	 */
//	@Test
//	public void testReadRecordsFileDoesNotExist() {
//		ArrayList<Student> s;
//		Exception e = assertThrows(FileNotFoundException.class,
//					() -> (s = StudentRecordIO.readStudentRecords("test-files/not_a_real_file.txt")));
//	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.io.StudentRecordIO#writeStudentRecords(java.lang.String, java.util.ArrayList)}.
	 */
	@Test
	void testWriteStudentRecords() {
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		students.add(new Student("Cassandra","Schwartz","cschwartz","semper@imperdietornare.co.uk",hashPW,4));
		students.add(new Student("Shannon","Hansen","shansen","convallis.est.vitae@arcu.ca",hashPW,14));
		students.add(new Student("Demetrius","Austin","daustin","Curabitur.egestas.nunc@placeratorcilacus.co.uk",hashPW,18));
		students.add(new Student("Raymond","Brennan","rbrennan","litora.torquent@pellentesquemassalobortis.ca",hashPW,12));
		students.add(new Student("Emerald","Frost","efrost","adipiscing@acipsumPhasellus.edu",hashPW,3));
		students.add(new Student("Lane","Berg","lberg","sociis@non.org",hashPW,14));
		students.add(new Student("Griffith","Stone","gstone","porta@magnamalesuadavel.net",hashPW,17));
		students.add(new Student("Althea","Hicks","ahicks","Phasellus.dapibus@luctusfelis.com",hashPW,11));
		students.add(new Student("Dylan","Nolan","dnolan","placerat.Cras.dictum@dictum.net",hashPW,5));
		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", students);
		} catch (IOException e) {
			fail("Cannot write to student records file");
		}
		
		checkFiles("test-files/expected_full_student_records.txt", "test-files/actual_student_records.txt");
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.io.StudentRecordIO#writeStudentRecords(java.lang.String, java.util.ArrayList)}.
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));

		
		Exception exception = assertThrows(IOException.class, 
				() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
		assertEquals("/home/sesmith5/actual_student_records.txt (No such file or directory)", exception.getMessage());
	}

}
