import java.util.ArrayList;
import java.util.Date;

public class Task {
	//TaskID, ID(project),
	String Taskname ; 
	int workinghours; //maybe days
	String delivaerable;
	Date startDate, DueDate;
	ArrayList<Task> subtasks;

	
	Task(){	
		subtasks = new ArrayList<Task>();
	}
	
	Task(String Taskname, int workinghours, Date StartDate, Date DueDates , String deliverables){
		this.Taskname = Taskname;
		this.startDate = StartDate;
		this.DueDate = DueDates;
		this.delivaerable = deliverables;
		subtasks = new ArrayList<Task>();
	}
	
	public void saveToDatabase() {
		
	}
	public void loadFromDatabase() {
		
	}

}
