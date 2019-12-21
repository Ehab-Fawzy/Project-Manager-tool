import java.util.ArrayList;
import java.util.Date;

public class Project {
	 String name ,startDay;
	 int cost;
	 Date StartingDate, DueDates ;
	 int hoursPerDay, ActualProjectHours;
	 ArrayList<Task> tasks;
	
	 public Project() {
		 tasks = new ArrayList<Task>();
	 }
	 
	 public Project ( String name, int cost, Date StartingDate, Date DueDates , String startDay, int hoursPerDay, int ActualProjectHourst) {
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
	public Project load(String name) {
		String Projectname = null; //"SELECT Name FROM project WHERE name = name " ;
		int cost = 0; // SELECT cost FROM project WHERE name = name "
		Date Start = null ; // SELECT startingday FROM project WHERE name = name "
		Date DueDates = null ; // SELECT DueDates FROM project WHERE name = name "
		String StartDay = null ; // SELECT StartDay FROM project WHERE name = name "
		int hoursPerDay = 0 ;// // SELECT StartDay FROM project WHERE name = name "
		int ActualProjectHours = 0 ;// // SELECT StartDay FROM project WHERE name = name "
		
		
		this.name = Projectname;
		this.cost = cost;
		this.StartingDate = Start;
		this.DueDates = DueDates;
		this.startDay = StartDay;
		this.hoursPerDay = hoursPerDay;
		this.ActualProjectHours = ActualProjectHours;
		
		return this;
	}
	public void add ()
	{
		//INSERT INTO Project (name, cost,StartingDate ,DueDates ,startDay,hoursPerDay,ActualProjectHours,)
		//VALUES (this.name, this.cost, this.StartingDate, this.DueDates,this.startDay,this.hoursPerDay,this.ActualProjectHours,);
	}
	
}
