import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class Project {
	 String name ,startDay;
	 int cost;
	 Date StartingDate;
	 Date DueDates ;
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
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String SQL = "jdbc:sqlserver://LAPTOP-M7JGO3P2:1433;databaseName=Library;integratedSecurity=true";
		Project p = new Project ("Hatem",50,new Date(15-11-1999),new Date(20/11/1999),"Friday",50,20) ;
		p.add();
		

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
	public void add () throws SQLException, ClassNotFoundException
	{
		String SQL = "jdbc:sqlserver://LAPTOP-M7JGO3P2:1433;databaseName=Library;integratedSecurity=true";
        //String sql = "INSERT INTO Project (name, cost,StartingDate ,DueDates ,startDay,hoursPerDay,ActualProjectHours,) VALUES (this.name, this.cost, this.StartingDate, this.DueDates,this.startDay,this.hoursPerDay,this.ActualProjectHours,)";
        String sql = "INSERT INTO Project(ProjectName,ProjectCost,ProjectStartDate,ProjectDueDate,StartDayOfWeek,HoursPerDay,ActualProjectHours) VALUES(?,?,?,?,?,?,?)";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection(SQL);
        
        PreparedStatement pstmt = connection.prepareStatement(sql) ;
        pstmt.setString(1, this.name);
        pstmt.setInt(2, this.cost);
        pstmt.setDate(3, (java.sql.Date) this.StartingDate);
        pstmt.setDate(4, (java.sql.Date) this.DueDates);
        pstmt.setString(5, this.startDay);
        pstmt.setInt(2, this.hoursPerDay);
        pstmt.setInt(2, this.ActualProjectHours);
        pstmt.executeUpdate();



        

        
        
        
        pstmt.executeUpdate();
       
    
	}
	
}
