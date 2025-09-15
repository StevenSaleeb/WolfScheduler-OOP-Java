/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This class represent a course in the wolfScheduler. 
 * The course has a name, a title, a section, how many credits, instructor, meeting days, start time, and end time.
 * This class checks that everything is valid.
 * @author Steven Saleeb
 */
public class Course extends Activity {

	/** Course's name. */
	private String name;

	/** Course's section. */
	private String section;

	/** Course's credit hours */
	private int credits;

	/** Course's instructor */
	private String instructorId;

	/** Minimum name length */
	private static final int MIN_NAME_LENGTH = 5;

	/** Maximum name length */
	private static final int MAX_NAME_LENGTH = 8;

	/** Minimum number of letter in the name */
	private static final int MIN_LETTER_COUNT = 1;

	/** Maximum number of letters in the name */
	private static final int MAX_LETTER_COUNT = 4;

	/** Number of digit in the name */
	private static final int DIGIT_COUNT = 3;

	/** Length of the section */
	private static final int SECTION_LENGTH = 3;

	/** Maximum credits allowed */
	private static final int MAX_CREDITS = 5;

	/** Minimum credits allowed */
	private static final int MIN_CREDITS = 1;

	/**
	 * Constructs a Course object with values for all fields
	 * @param name course name
	 * @param title course title
	 * @param section course section
	 * @param credits number of credits
	 * @param instructorId instructor id
	 * @param meetingDays the days the course requires to meet
	 * @param startTime  the start time of the course
	 * @param endTime the end time of the course
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
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * @param name course name
	 * @param title course title
	 * @param section course section
	 * @param credits number of credits
	 * @param instructorId instructor id
	 * @param meetingDays the days the course requires to meet
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Gets the course name.
	 * @return name the of the course
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more
	 * than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {

		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		int counterOfLetters = 0;
		int counterOfDigits = 0;
		boolean findingSpace = false;

		for (int i = 0; i < name.length(); i++) {
			char character = name.charAt(i);
			if (!findingSpace) {
				
				if (Character.isLetter(character)) {
					counterOfLetters++;
				} else if (character == ' ') {
					findingSpace = true;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			} else {
				if (Character.isDigit(character)) {
					counterOfDigits++;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}
		}
		if (counterOfLetters < MIN_LETTER_COUNT || counterOfLetters > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if (counterOfDigits != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		this.name = name;
	}

	/**
	 * Gets the course section.
	 * @return the course section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the course section and make sure it is valid
	 * @param section the section to set
	 * @throws IllegalArgumentException if section is invalid
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		
		for (int i = 0; i < SECTION_LENGTH; i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}

		this.section = section;
	}

	/**
	 * Gets the number of credits
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the number of credits
	 * @param credits the number of credits
	 * @throws IllegalArgumentException if number of credits is invalid
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}

	/**
	 * Gets the instructor Id
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the instructor Id
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructor id is null or empty
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}
	
	/**
	 * Sets the meeting days and times for the course, checks for valid characters in meetingDays and makes sure of the arrangement
	 * Calls super class method to set the time
	 * @param meetingDays the meeting days
     * @param startTime the start time
     * @param endTime the end time
     * @throws IllegalArgumentException if meetingDays or times are invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// Handle arranged
	    	if(meetingDays == null || "".equals(meetingDays)) {
	    		throw new IllegalArgumentException("Invalid meeting days and times.");
	    	}
	    	
	    	for( int i = 0; i < meetingDays.length(); i++) {
	    		if ("MTWHFA".indexOf(meetingDays.charAt(i)) == -1) {
	    			throw new IllegalArgumentException("Invalid meeting days and times.");
	    		}
	    	}
	    	
	    	if ("A".equals(meetingDays)) {
		    	if (startTime != 0 || endTime != 0) {
		    		throw new IllegalArgumentException("Invalid meeting days and times.");
		    	}
	    
	    	
	    } else {
	        // Check for valid characters in meeting days
	        int countM = 0;
	        int countT = 0;
	        int countW = 0;
	        int countH = 0;
	        int countF = 0;
	        for (int i = 0; i < meetingDays.length(); i++) {
	            char c = meetingDays.charAt(i);
	            switch (c) {
	            case 'M':
	                countM++;
	                break;
	            case 'T':
	                countT++;
	                break;
	            case 'W':
	                countW++;
	                break;
	            case 'H':
	                countH++;
	                break;
	            case 'F':
	                countF++;
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid meeting days and times.");
	            }
	        }
	
	        if (countM > 1 || countT > 1 || countW > 1 || countH > 1 || countF > 1) {
	            throw new IllegalArgumentException("Invalid meeting days and times.");
	        }
	}
		   super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	    
	}

	
	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}

	/**
	 * Returns a hash code for the course which is based on the course name, the course section, the credits, the instructor ID
	 * @return the hash code of the course
	 */
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

	/**
	 * Compares this course to another object, two courses are equal if they have the same name, section, instructor ID.
	 * @param obj the object to compare 
	 * @return true if they are equal and false if not
	 */
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
	 * Returns an array for short display of the course.
     * @return shortDisplay the string array with the name, section,title, and meeting string
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[4];
		shortDisplay[0] = getName();
		shortDisplay[1] = getSection();
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		
		return shortDisplay;
	}
	
	/**
     * Returns an array for long display of the course.
     * @return longDisplay the string array with the name, section, title, credits, instructorId, meeting string, and empty string
     */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		longDisplay[0] = getName();
		longDisplay[1] = getSection();
		longDisplay[2] = getTitle();
		longDisplay[3] = Integer.toString(getCredits());
		longDisplay[4] = getInstructorId();
		longDisplay[5] = getMeetingString();
		longDisplay[6] = "";

		return longDisplay;
	}
	
	/**
	 * Checks if the activity is a duplicate if they have the same name
	 * @param activity the activity to compare 
	 * @return true if it is a duplicate, false if it is not
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course c = (Course) activity;
			return this.getName().equals(c.getName());
		}
		return false;
		
	}

}
