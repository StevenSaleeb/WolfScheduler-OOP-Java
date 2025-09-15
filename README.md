## WolfScheduler
    WolfScheduler is a Java-based GUI application designed to help students manage their course schedules. 
    It allows users to view a catalog of available courses, add courses and events to a personal schedule, remove activities, and export the finalized schedule.
## Features
    View Course Catalog: Browse all available courses loaded from a file.
    Manage Student Schedule: Add and remove courses or custom events from the student schedule.
    Set Schedule Title: Customize the title of your schedule.
    Event Scheduling: Add non-course events with specific days, start and end times, and details.
    Display and Export Schedule: View the full schedule with detailed information and export it to a file.
    Interactive GUI: User-friendly interface with tables, forms, buttons, and tooltips for easy navigation.
## Usage
    When the application starts, a file chooser will prompt you to select the course catalog file.
    Browse the Course Catalog to view all available courses.
    Add a Course: Select a course from the catalog and click "Add Course."
    Remove a Course/Event: Select the course or event from your schedule and click "Remove Activity."
    Reset Schedule: Clear all scheduled activities.
    Set Schedule Title: Enter a new title in the text box and click "Set Title."
    Add Event: Enter event details, select meeting days and time, then click "Add Event."
    Display Final Schedule: Click "Display Final Schedule" to view the full schedule.
    Export Schedule: Click "Export Schedule" to save the schedule to a file.
    Revise Schedule: Return to the previous screen to make changes.
## Project Structure
    edu.ncsu.csc216.wolf_scheduler/
    ├── course/                  # Contains Course, Event, and Activity classes
    ├── io/                      # Input/output utilities (CourseRecordIO, ActivityRecordIO)
    ├── scheduler/               # WolfScheduler class (manages catalog and schedule)
    └── ui/                      # GUI classes (WolfSchedulerGUI)
    WolfScheduler.java: Manages the course catalog and student schedule, handles adding/removing courses and events, and exports the schedule.
    WolfSchedulerGUI.java: Provides a graphical interface for interacting with the catalog and schedule. 