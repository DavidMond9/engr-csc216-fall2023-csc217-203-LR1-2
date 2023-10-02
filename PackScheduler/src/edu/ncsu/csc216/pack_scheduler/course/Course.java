/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * A course for NCSU
 * 
 */
public class Course extends Activity implements Comparable<Course> {
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/**Minimum length of a course's name (EX: CSC 216 is 7 characters)*/
	final static int MIN_NAME_LENGTH = 5;
	/**Maximum length of a course's name*/
	final static int MAX_NAME_LENGTH = 8;
	/**Minimum length of a course's prefix (EX: CSC is 3 characters)*/
	final static int MIN_LETTER_COUNT = 1;
	/**Maximum length of a course's prefix*/
	final static int MAX_LETTER_COUNT = 4;
	/**Exact amount of digits each course must have (EX: CSC 216 has 3 digits)*/
	final static int DIGIT_COUNT = 3;
	/**Exact amount of digits the section string can be*/
	final static int SECTION_LENGTH = 3;
	/**Minimum amount of credits a course can be*/
	final static int MIN_CREDITS = 1;
	/** Maximum amount of credits a course can be*/
	final static int MAX_CREDITS = 5;
	/**
	 * Constructs a Course object with all fields
	 * @param name name of a course
	 * @param title title of a course
	 * @param section section of a course
	 * @param credits credit hrs of a course
	 * @param instructorId instructor's Unity ID
	 * @param meetingDays days course meets
	 * @param startTime When the course starts
	 * @param endTime When the course ends
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
	    super(title, meetingDays, startTime, endTime);
		setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	}

	
	/**
	 * Constructs a Course object with unknown startTime and unknown endTime
	 * @param name name of a course
	 * @param title title of a course
	 * @param section the section of a course
	 * @param credits the credit hours of a course
	 * @param instructorId instructor's Unity ID
	 * @param meetingDays days course meets
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}


	/**
	 * Returns the Course's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the Course's name.  If the name is null, has a length less than 5 or more than 8,
	 * does not contain a space between letter characters and number characters, has less than 1
	 * or more than 4 letter characters, and not exactly three trailing digit characters, an
	 * IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) throws IllegalArgumentException{
	    //Throw exception if the name is null
	    if (name == null) {
	    	throw new IllegalArgumentException("Invalid course name.");
	    }
	    //Throw exception if the name is an empty string
	    //Throw exception if the name contains less than 5 character or greater than 8 characters
	    if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
	    	throw new IllegalArgumentException("Invalid course name.");
	    }
//	    //Check for pattern of L[LLL] NNN
//	    initialize counter for number of letters
	    int numberOfLetters = 0;
//	    initialize counter for number of digits
	    int numberOfDigits = 0;
//	    initialize boolean flag for finding a space to false
	    boolean spaceFound = false;
//	    for each character in name
	    for (int nameIterator = 0; nameIterator < name.length(); nameIterator++) {
//	        if a space has not yet been found
	    	if (!spaceFound) {
//	            if the character at nameIterator is a letter
	    		if (Character.isLetter(name.charAt(nameIterator))){
//	                increment the letter counter
	    			numberOfLetters++;
	    		}
//	            else if the character at i is a space
	    		else if (name.charAt(nameIterator) == ' ') {
//	                space flag should be set to true
	    			spaceFound = true;
	    		}
//	            otherwise
	    		else {
	    			// Course name is invalid
	    			throw new IllegalArgumentException("Invalid course name.");
	    		}           
	    	}
//	        else if a space is found
	    	else if(spaceFound) {
//            if the character is a digit
	    		if (Character.isDigit(name.charAt(nameIterator))){
//                increment the digit counter
	    			numberOfDigits++;
	    		}
//            otherwise
	    		else {
	    			// Course name is invalid
	    			throw new IllegalArgumentException("Invalid course name.");
	    		}
	    	}
	    }
	    //Check that the number of letters is correct
//	    if letter counter is less than one or more than 4
	    if (numberOfLetters < MIN_LETTER_COUNT || numberOfLetters > MAX_LETTER_COUNT) {
	    	// Course name is invalid
	    	throw new IllegalArgumentException("Invalid course name.");
	    }
	    //Check that the number of digits is correct
//	    if digit counter is not 3
	    if (numberOfDigits != DIGIT_COUNT) {
	        // Course name is invalid
	    	throw new IllegalArgumentException("Invalid course name.");
	    }
	    //set this.name (field) to name (parameter)
	    this.name = name;
	}
	/**
	 * Returns the Course's section.
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section.
	 * Fails if:
	 * 	> the section number is NOT exactly three digits
	 * @param section the section to set
	 */
	
	public void setSection(String section) {
		//Check if section is null
		if (section == null) {
			throw new IllegalArgumentException("Invalid section.");
		}
		//If the section String doesn't have SECTION_LENGTH characters
		if (section.length() != SECTION_LENGTH) {
			//The section is invalid
			throw new IllegalArgumentException("Invalid section.");
		}
		//Check if the section String is only digits
		for (int sectionIterator = 0; sectionIterator < SECTION_LENGTH; sectionIterator++) {
			//Check if current position in section isn't a digit
			if (!Character.isDigit(section.charAt(sectionIterator))){
				//If it isn't a digit, then the section is invalid
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		// Assume that section is valid, accept the section 
		this.section = section;
	}

	/**
	 * Returns the Course's credits.
	 * @return the credits
	 */
	
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Sets the Course's credits.
	 * Fails if:
	 * 	> the credit hours are not a number
	 *	> the credit hours are less than MIN or greater than MAX
	 * @param credits the credits to set
	 */
	
	public void setCredits(int credits) {
		//Check if credits is in between MIN and MAX credits allowed
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		//Assume credits is valid, accept it
		this.credits = credits;
	}

	/**
	 * Returns the Course's instructorID.
	 * @return the instructorID
	 */
	
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructorID.
	 * Fails if:
	 * 	> the instructor’s id is null or an empty string
	 * @param instructorId the instructorID to set
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}
	
	/**
	 * Overrides setMeetingDaysAndTime. Checks for multiple uses of the same day. 
	 */

	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			//If meetingDays doesn't have days, it's invalid
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		//Check if A is the only character
		if ("A".equals(meetingDays)) {
			//Check if there's either a start or end time given 
			if (startTime != 0 || endTime != 0) {
				//Fails, as Arranged classes don't have a start or end time
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			// meetingDays is correct, and startTime and endTime should be set as 0
			// The method should end after this
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
		else {
			//weekCounter counts the number times each day is referenced. 
			//0 is Monday, 1, is Tuesday... 4 is Friday
			int[] weekCounter = new int[5];
			//Iterate through meetingDays, and add to weekCounter depending on the day. 
			for(int meetingDaysIterator = 0; meetingDaysIterator < meetingDays.length(); meetingDaysIterator++) {
				//Switch case here
				switch(meetingDays.charAt(meetingDaysIterator)) {
					//M for Monday
					case 'M':
						weekCounter[0]++;
						break;
					//T for Tuesday
					case 'T':
						weekCounter[1]++;
						break;
					//W for Wednesday
					case 'W':
						weekCounter[2]++;
						break;
					//H for tHursday
					case 'H':
						weekCounter[3]++;
						break;
					//F for Friday
					case 'F':
						weekCounter[4]++;
						break;
					//The default case is for when something is wrong
					//If the char at the current position isn't one of the allowed ones (Ex: 'A' or 'Z')
					//Then something is wrong, and the argument is thrown.
					default:
						throw new IllegalArgumentException("Invalid meeting days and times.");
					
				}
			}
			//Iterates through weekCounter, dayCount stores the number of times a single day was submitted
			for(int dayCount : weekCounter) {
				//If the dayCount > 1, then the argument is thrown.
				if (dayCount > 1) {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			//Everything should be valid, accept the inputs
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}

	/**
	 * Returns true if the given Activity is a Course with the same name
	 * Returns false if not a Course, or if it is a Course, the name isn't the same
	 * @param activity the Activity to compare to current Course
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course confirmedCourse = (Course) activity;
			return this.getName().equals(confirmedCourse.getName()) && this.getSection().equals(confirmedCourse.getSection());
		}
		return false;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    if ("A".equals(getMeetingDays())) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}


	/**
	 * TODO
	 * @param args nothing for now
	 */
	public static void main(String[] args) {
//		Course test = new Course("what", "I", "Don't", 1, "Care", "at", 1, 2);
//		test.setName(null);
	}


	@Override
	public String[] getShortDisplayArray() {
		String[] shortArray = {getName(), getSection(), getTitle(), getMeetingString()};
		return shortArray;
	}


	@Override
	public String[] getLongDisplayArray() {
		String[] longArray = {getName(), getSection(), getTitle(), String.valueOf(getCredits()), getInstructorId(), getMeetingString(), ""};
		return longArray;
	}
	/**
	 * Compares two Courses together 
	 * @return 0 if names are equal, >0 if this.name is greater, <0 if less
	 */
	@Override
	public int compareTo(Course o) {
		return this.getName().compareTo(o.getName());
	}

}

