package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Something that people do. Has a name, start and endtime, as well as days of the week
 */
public abstract class Activity implements Conflict {


	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;
	/** Upper hour a class can be (24 hours in a day)*/
	private final static int UPPER_HOUR = 24;
	/** Upper minute a class can be (60 minutes in an hour)*/
	private final static int UPPER_MINUTE = 60;
	
	/**
	 * Constructs an activity
	 * @param title Name of the activity
	 * @param meetingDays Days the activity happens
	 * @param startTime Time of day the activity happens
	 * @param endTime Time the activity ends 
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
	    setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns the Activity's title.
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title.
	 * Fails if: 
	 * > the activity title is null or an empty string
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		if (title == null || "".equals(title)) {
			//The title is invalid
			throw new IllegalArgumentException("Invalid title.");
		}
		else {
			//If the title isn't empty or null, then accept it. 
			this.title = title;
		}
	}

	/**
	 * Returns the Activity's meetingDays.
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Activity's startTime.
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's endTime.
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Activity's meeting days and start and end time.
	 * Fails if:
	 * > meeting days consist of any characters other than ‘M’, ‘T’, ‘W’, ‘H’, ‘F’, or ‘A’
	 * > meeting days have a duplicate character
	 * > if ‘A’ is in the meeting days list, it must be the only character
	 * > the start time is not between 0000 and 2359 an invalid military time
	 * > the end time is not between 0000 and 2359 or an invalid military time
	 * > the end time is less than the start time (i.e., no overnight classes)
	 * > a start time and/or end time is listed when meeting days is ‘A’
	 * @param startTime the time the activity starts
	 * @param meetingDays the days the activity meets
	 * @param endTime the time the activity ends
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		//Check if the times are invalid
		//First start if the start is before the end
		if (startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		//Next, check if the hours and minutes given are actually legal
		//startTime/100 gives startTime hour
		//startTime hour can't be at or above the UPPER_HOUR or below 0
		if (startTime / 100 >= UPPER_HOUR || startTime / 100 < 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		//startTime%100 gives startTime minute
		//startTime minute can't be at or above the UPPER_MINUTE, or below 0
		if (startTime % 100 >= UPPER_MINUTE || startTime % 100 < 0){
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		//Below the same is done for endTime
		if (endTime / 100 >= UPPER_HOUR || endTime / 100 < 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (endTime % 100 >= UPPER_MINUTE || endTime % 100 < 0){
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//Everything should be valid, accept the inputs
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Sets the Activity's meeting days and start and end time.
	 * @return scheduleString The days of the week in letter form, start and end time in 12 hour format
	 *  Returns "Arranged" the meeting days are arranged
	 */
	public String getMeetingString() {
		//Check if it's arranged first
		if ("A".equals(this.meetingDays)) {
			//If it is, return "Arranged"
			return "Arranged";
		}
		//startHour stores the hour of startTime
		int startHour = this.startTime / 100;
		//startMinute stores the minute of startTime
		int startMinute = this.startTime % 100;
		//endHour stores the hour of endTime
		int endHour = this.endTime / 100;
		//endMinute stores the minute of endTime
		int endMinute = this.endTime % 100;
		//Make booleans that hold whether startTime or endTime are AM (False) or PM (True)
		//Assume it's AM first
		boolean startPM = false;
		boolean endPM = false;
		//Checks if startTime is before noon
		if (this.startTime < 1200) {
			//If it's before noon, then check if startHour is 0
			if (startHour == 0) {
				//If startHour is 0, then make it 12 (It's 12 AM)
				startHour = 12;
			}
		}
		//This must mean that startTime is after noon
		else {
			//Check to ensure it's not noon
			if (startHour != 12) {
				//If it is noon, then subtract 12 to the hour
				startHour -= 12;
			}
			//Change startPM to true (It's PM)
			startPM = true;
		}
		//Do the same for endTime
		//Checks if endTime is before noon
		if (this.endTime < 1200) {
			//If it's before noon, then check if endHour is 0
			if (endHour == 0) {
				//If endHour is 0, then make it 12 (It's 12 AM)
				endHour = 12;
			}
		}
		//This must mean that endTime is after noon
		else {
			//Check to ensure it's not noon
			if (endHour != 12) {
				//If it is noon, then subtract 12 to the hour
				endHour -= 12;
			}
			//Change startPM to true (It's PM)
			endPM = true;
		}
		//Now the string construction can start
		//scheduleString holds the output
		String scheduleString = "";
		//Add the meetingDays first (and a space)
		scheduleString += meetingDays + " ";
		//Now add the startHour (and ":")
		scheduleString += String.valueOf(startHour) + ":";
		//Have to check if startMinute < 10
		if (startMinute < 10) {
			//Have to add 0 before start minute, to keep a XX:XX form for time
			scheduleString += "0" + String.valueOf(startMinute);
		}
		else {
			//If startMinute isn't < 10, then simply add the time 
			scheduleString += String.valueOf(startMinute);
		}
		//Now add AM or PM
		if (startPM) {
			//If it is in the PM, then add PM-
			scheduleString += "PM-";
		}
		else{
			//If it isn't in the PM, then add AM-
			scheduleString += "AM-";
		}
		//Now add the endHour (and ":")
		scheduleString += String.valueOf(endHour) + ":";
		//Have to check if endMinute < 10
		if (endMinute < 10) {
			//Have to add 0 before end minute, to keep a XX:XX form for time
			scheduleString += "0" + String.valueOf(endMinute);
		}
		else {
			//If endMinute isn't < 10, then simply add the time 
			scheduleString += String.valueOf(endMinute);
		}
		//Now add AM or PM
		if (endPM) {
			//If it is in the PM, then add PM
			scheduleString += "PM";
		}
		else {
			//If it isn't in the PM, then add AM
			scheduleString += "AM";
		}
		//Should be free to return now
		return scheduleString;
	}
	/**
	 * Gives limited info to the GUI
	 * @return Short version of the array of info for the GUI
	 */
	public abstract String[] getShortDisplayArray();
	/**
	 * Gives more info to the GUI
	 * @return Long version of the array of info for the GUI
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Helps determine whether or not two Activities are duplicates
	 * @param activity The activity to compare
	 * @return true if duplicates
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	/**
	 * Compares this Activity to a given Activity, to see if they conflict
	 * Two Activity objects are in conflict if:
	 * 	 	There is at least one day with an overlapping time. 
	 * 		A time is overlapping if the minute is the same.
	 * @param possibleConflictingActivity The Activity to compare this to
	 * @throws ConflictException if there is a conflict between the Activities
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		//Check for overlapping days first
		String thisDays = this.getMeetingDays();
		String otherDays = possibleConflictingActivity.getMeetingDays();
		//Don't have to check if the days are arranged
		if (!"A".equals(otherDays)) {
			for(char thisDayLetter : thisDays.toCharArray()) {
				if (otherDays.contains(Character.toString(thisDayLetter))) {
					//Now check for overlapping times
					int thisStart = this.getStartTime();
					int otherStart = possibleConflictingActivity.getStartTime();
					int thisEnd = this.getEndTime();
					int otherEnd = possibleConflictingActivity.getEndTime();
					//Check if this start is between other start and end
					if (otherStart <= thisStart && otherEnd >= thisStart) {
						throw new ConflictException();
					}
					//Now check if this end is between other start and end
					else if(otherStart <= thisEnd && otherEnd >= thisEnd) {
						throw new ConflictException();
					}
					//Now do the other way around
					if (thisStart <= otherStart && thisEnd >= otherStart) {
						throw new ConflictException();
					}
					else if(thisStart <= otherEnd && thisEnd >= otherEnd) {
						throw new ConflictException();
					}
				}
			}
		}
	}

}