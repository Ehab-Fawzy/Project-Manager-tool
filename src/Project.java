import java.util.ArrayList;
import java.util.Date;

public class Project {
	 String name;
	 int cost;
	 Date StartingDate, DueDates , startDay;
	 int hoursPerDay, ActualProjectHours;
	 ArrayList<Task> tasks;
	
	 public Project() {
		 tasks = new ArrayList<Task>();
	 }
	 
	 public Project ( String name, int cost, Date StartingDate, Date DueDates , Date startDay, int hoursPerDay, int ActualProjectHourst) {
		this.name = name;
		this.cost = cost;
		this.StartingDate = StartingDate;
		this.DueDates = DueDates;
		this.startDay = startDay;
		this.hoursPerDay = hoursPerDay;
		this.ActualProjectHours = ActualProjectHourst;
		tasks = new ArrayList<Task>();
	 }
	
	public void saveToDatabase() {
		
	}
	public void loadFromDatabase() {
		
	}
}
