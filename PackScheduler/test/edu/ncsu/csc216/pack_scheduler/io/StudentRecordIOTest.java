/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import java.io.FileInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for StudentRecordIO Object
 * 
 * @author Sean Van Acker
 * @author 
 */
class StudentRecordIOTest {

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
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.io.StudentRecordIO#writeStudentRecords(java.lang.String, java.util.ArrayList)}.
	 */
	@Test
	void testWriteStudentRecords() {
		fail("Not yet implemented");
	}

}
