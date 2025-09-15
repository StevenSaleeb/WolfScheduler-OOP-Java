
package edu.ncsu.csc216.wolf_scheduler.scheduler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;


/**
 * This class is responsible to manage the course catalog and the student schedule, 
 * it allows the student to add and remove courses, also it allows the student to export the schedule.
 * There is a file that the catalog is read from and stored as a list and  the schedule is a separate list that
 * holds the courses the student added
 * @author Steven Saleeb
 */
public class WolfScheduler {
	
	/** A list of all the available courses in the catalog */
	private ArrayList<Course> catalog;
	
	/** List of all the activities in the student schedule */
	private ArrayList<Activity> schedule;
	
	/** Title of the schedule */
	private String title;

	/**
	 * Loads the course catalog from the file and makes an empty schedule and sets a default title for the schedule
	 * @param filename the name of the file that will read the course catalog from
	 * @throws IllegalArgumentException if the file can not be read
	 */
	public WolfScheduler(String filename) {
		schedule = new ArrayList<Activity>();
		title = "My Schedule";
		try {
			catalog = CourseRecordIO.readCourseRecords(filename);
		} catch  (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/**
	 * Returns the course catalog as a 2D array and each row contains 
	 * the course name, the section, the title and the meeting informations
	 * @return a 2D array of the course catalog
	 */
	public String[][] getCourseCatalog() {
		String [][] catalogArray = new String[catalog.size()][4];
		for (int i = 0; i < catalog.size(); i++) {
			Course course = catalog.get(i);
			catalogArray[i] = course.getShortDisplayArray();
		}
		return catalogArray;
	}

	/**
	 * Returns the student schedule as a 2D array and each row contains 
	 * the course name, the section and the title, and the meeting informations.
	 * @return a 2D array of the scheduled activities
	 */
	public String[][] getScheduledActivities() {
		String[][] scheduleArray = new String[schedule.size()][4];
		for (int i = 0; i < schedule.size(); i++) {
			Activity activity = schedule.get(i);
			scheduleArray[i] = activity.getShortDisplayArray();
		}
		return scheduleArray;
	}
	
	/**
	 * Returns the student's full schedule as a 2D array with all the details 
	 * each row contains the course name, the section, the title, the number of credits,
	 * the instructor Id, the meeting days and the meeting times.
	 * @return a 2D array of the full schedule
	 */
	public String[][] getFullScheduledActivities() {
		String[][] fullSchedule = new String[schedule.size()][7];
		for (int i = 0; i < schedule.size(); i++) {
			
			Activity activity = schedule.get(i);
			fullSchedule[i] = activity.getLongDisplayArray();
			
		}
		return fullSchedule;
	}
	
	
	
	/**
	 * Gets a course from the catalog by the name and the section and 
	 * it returns the course object if found or returns null if it doesn't exist
	 * @param name the name of the course
	 * @param section the section of the course 
	 * @return the course object if found or null if it doesn't exist
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (Course course : catalog) {
			String x = course.getName();
			String y = course.getSection();
	        if (x.equals(name) && y.equals(section)) {
	            return course;
	        }
	    }
	    return null;
	}

	/**
	 * Adds a course to the schedule by the name and section.
	 * Returns true if the course was added, false if it does not exist in the catalog.
	 * @param name the name of the course 
	 * @param section the section of the course
	 * @return true if the course is added, false if it doesn't exist
	 * @throws IllegalArgumentException if the course is already in the schedule
	 */
	public boolean addCourseToSchedule(String name, String section) {
		Course course = getCourseFromCatalog(name, section);
		if (course == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) { 
			Activity activites = schedule.get(i);
			if (activites.isDuplicate(course)) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}
		schedule.add(course);
		return true;
	}

	/**
	 * Removes an activity from the schedule by index.
	 * Returns true if the activity was removed, false if it was invalid
	 * @param idx the index of the activity to remove
	 * @return true if the activity was removed, false if not 
	 */
	public boolean removeActivityFromSchedule(int idx) {
		try {
			schedule.remove(idx);
			return true;
		} catch (IndexOutOfBoundsException e){
			return false;
		}
	}
	
	/**
	 * Adds an event to the student's schedule.
	 * @param eventTitle the event title 
	 * @param eventMeetingDays the meeting days of the event
	 * @param eventStartTime the start time of the event
	 * @param eventEndTime the end time of the event
	 * @param eventDetails the details of the event
	 * @throws IllegalArgumentException if an event with the same title already exists
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		Activity newEvent = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		for (int i = 0; i < schedule.size(); i++) {
			Activity activities = schedule.get(i);
			if(activities.isDuplicate(newEvent)) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
		}
		schedule.add(newEvent);
	}

	/**
	 * reset the schedule and creates a new empty schedule for the student
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Activity>();
	}

	
	/**
	 * Sets the title of the schedule
	 * @param title the new title for the schedule
	 * @throws IllegalArgumentException if the title is null
	 */
	public void setScheduleTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}

	/**
	 * Returns the title of the schedule
	 * @return the schedule title
	 */
	public String getScheduleTitle() {
		return title;
	}
	
	/**
	 * Exports the student's schedule into a file using the ActivityRecordIO class
	 * @param filename the name of the file to save the schedule
	 * @throws IllegalArgumentException if the file can't be written
	 */
	public void exportSchedule(String filename) {
		try {
			ActivityRecordIO.writeActivityRecords(filename, schedule);
			
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}

}
