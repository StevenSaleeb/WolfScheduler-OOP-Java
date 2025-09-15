package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Abstract activity class that represent the activities like course or event, 
 * it stores the title, meeting days, the start time and the end time.
 * @author Steven Saleeb
 */
public abstract class Activity {

	/** Upper possible hour */
	private static final int UPPER_HOUR = 24;
	/** Upper possible minute */
	private static final int UPPER_MINUTE = 60;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Creates a new activity with a title, meeting days, start time, and end time.
	 * It is responsible to set the title and the meeting information.
	 * @param title the title of the activity
	 * @param meetingDays the meeting days of the activity
	 * @param startTime the start time of the activity
	 * @param endTime the end time of the activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
        super();
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

	/**
	 * Returns the Course's title
	 * 
	 * @return the title
	 */
	public String getTitle() {
	    return title;
	}

	/**
	 * Sets the Course's title
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title is null or empty.
	 */
	public void setTitle(String title) {
	    if (title == null || "".equals(title)) {
	        throw new IllegalArgumentException("Invalid title.");
	    }
	
	    this.title = title;
	}

	/**
	 * Returns the Course's meeting days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
	    return meetingDays;
	}

	/**
	 * Returns the Course's start time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
	    return startTime;
	}

	/**
	 * Returns the Course's end time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
	    return endTime;
	}

	/**
	 * Sets the Course's meeting days
	 * 
	 * @param meetingDays the meetingDays to set
	 * @param startTime   start time of course
	 * @param endTime     end time of course
	 * @throws IllegalArgumentException if any value is invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
	    // Check for null or empty string
	    if (meetingDays == null || "".equals(meetingDays)) {
	        throw new IllegalArgumentException("Invalid meeting days and times.");
	    }
	
	        // Check for valid meeting times
	        int startHour = startTime / 100;
	        int startMin = startTime % 100;
	        int endHour = endTime / 100;
	        int endMin = endTime % 100;
	
	        if (startHour < 0 || startHour >= UPPER_HOUR) {
	            throw new IllegalArgumentException("Invalid meeting days and times.");
	        }
	        if (startMin < 0 || startMin >= UPPER_MINUTE) {
	            throw new IllegalArgumentException("Invalid meeting days and times.");
	        }
	        if (endHour < 0 || endHour >= UPPER_HOUR) {
	            throw new IllegalArgumentException("Invalid meeting days and times.");
	        }
	        if (endMin < 0 || endMin >= UPPER_MINUTE) {
	            throw new IllegalArgumentException("Invalid meeting days and times.");
	        }
	        if (endTime < startTime) {
	            throw new IllegalArgumentException("Invalid meeting days and times.");
	        }
	
	        this.meetingDays = meetingDays;
	        this.startTime = startTime;
	        this.endTime = endTime;
	    }
	
	
	/**
	 * Returns a short display array of activity details
	 * @return a short array of strings which represent activities
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Returns a short display array of activity details
	 * @return a long array of strings which represent activities
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * checks if there is a duplication in the activities
	 * @param activity the activity to compare 
	 * @return true if it is duplicate and false if it is not
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Returns a string representation of the Course's meeting days and times.
	 * 
	 * @return Course's meeting days and times.
	 */
	public String getMeetingString() {
	    if ("A".equals(meetingDays)) {
	        return "Arranged";
	    }
	
	    return meetingDays + " " + getTimeString(startTime) + "-" + getTimeString(endTime);
	}
	
	

	/**
	 * Returns the time in AM/PM format.
	 * 
	 * @param time as an integer
	 * @return time as a string
	 */
	private String getTimeString(int time) {
	    int hour = time / 100;
	    int min = time % 100;
	    boolean morning = true;
	
	    if (hour >= 12) {
	        hour -= 12;
	        morning = false;
	    }
	    if (hour == 0) {
	        hour = 12;
	    }
	
	    String minS = "" + min;
	    if (min < 10) {
	        minS = "0" + minS;
	    }
	
	    String end = morning ? "AM" : "PM";
	
	    return hour + ":" + minS + end;
	}

	/**
	 * Generates a hash code based on title, meeting days, start time and end time
	 * @return the hash code of the activity
	 */
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

	/**
	 * Checks if this activity is equal to another object
	 * @param obj the object to compare
	 * @return true if equal and false if not
	 */
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

}