package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Represents an Event in the WolfScheduler, an Event has a title, meeting days, 
 * start and end times, and details about the event.
 * @author Steven Saleeb
 */
public class Event extends Activity {
	
	/** the Details about the event */
	private String eventDetails;
	
	/**
	 * Gets the details of the event.
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}
	
	/**
	 * Constructs a new Event with a title, meeting days, start time, end time, and details.
	 * @param title the title of the event
	 * @param meetingDays the meeting days of the event
	 * @param startTime the start time of the event
	 * @param endTime the end time of the event
	 * @param eventDetails the details of the event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
        super(title, meetingDays, startTime, endTime);
        setEventDetails(eventDetails);
    }

	/**
	 * Sets the details of the event
	 * @param eventDetails the eventDetails to set
	 * @throws IllegalArgumentException if eventDetails is null
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}

	/**
	 * Sets the meeting days and times for the event, checks that the meeting days are valid and do not repeat,
	 * then sets the start and end times using the superclass method
	 * @param meetingDays the meeting days of the event
	 * @param startTime the start time of the event
	 * @param endTime the end time of the event
	 * @throws IllegalArgumentException if the days or times are invalid
	 * 
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		 if (meetingDays == null || "".equals(meetingDays)) {
		        throw new IllegalArgumentException("Invalid meeting days and times.");
		    } else {
		
			int countM = 0;
	        int countT = 0;
	        int countW = 0;
	        int countH = 0;
	        int countF = 0;
	        int countS = 0;
	        int countU = 0;
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
	            case 'S':
	                countS++;
	                break;
	            case 'U':
	                countU++;
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid meeting days and times.");
	            }
	        
	        if (countM > 1 || countT > 1 || countW > 1 || countH > 1 || countF > 1 || countS > 1 || countU > 1) {
	            throw new IllegalArgumentException("Invalid meeting days and times.");
	        }
	       }
		   super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	    }
		 
	}
	
	/**
	 * Returns an array for short display of the event.
     * @return shortDisplay the string array with the title, and the meeting string
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[4];
		shortDisplay[0] = "";
		shortDisplay[1] = "";
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		
		return shortDisplay;
	}

	/**
	 * Returns an array for long display of the event.
     * @return longDisplay the string array with the title, the meeting string, and the event details
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		longDisplay[0] = "";
		longDisplay[1] = "";
		longDisplay[2] = getTitle();
		longDisplay[3] = "";
		longDisplay[4] = "";
		longDisplay[5] = getMeetingString();
		longDisplay[6] = getEventDetails();
		
		return longDisplay;
	}
	
	/**
     * Returns a string that represents the event,
     * the string have the title, meeting days, start and end times, and event details
     * @return a string of the event informations
     */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + 
				getStartTime() + "," + getEndTime() + "," + getEventDetails();
	}
	
	/**
     * Checks if another activity is a duplicate of this event, two events are duplicates if they have the same title
     * @param activity the activity to compare
     * @return true if the activity is a duplicate, false if not
     */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Event) {
			Event c = (Event) activity;
			return this.getTitle().equals(c.getTitle());
		}
		return false;
	}


}
