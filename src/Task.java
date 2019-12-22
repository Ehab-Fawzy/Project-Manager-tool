import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class Task {
	
	String Taskname ; 
	int workinghours, ActualWorkingHours, TaskID; 
	String delivaerable;
	Date startDate, DueDate;
	ArrayList<Task> subtasks;
	private static String SQL ;
	private static Connection connection;

	
	Task() throws ClassNotFoundException, SQLException{	
		subtasks = new ArrayList<Task>();
		 SQL =  "jdbc:sqlserver://localhost:1433;databaseName=projectManagerTool;integratedSecurity=true";
	     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	     connection = DriverManager.getConnection(SQL);

	}
	
	Task(String Taskname, int workinghours, Date StartDate, Date DueDates , String deliverables,int ActualWorkingHours) throws ClassNotFoundException, SQLException{
		this.Taskname = Taskname;
		this.startDate = StartDate;
		this.DueDate = DueDates;
		this.delivaerable = deliverables;
		this.ActualWorkingHours= ActualWorkingHours ;
		this.workinghours = workinghours ;
		
		subtasks = new ArrayList<Task>();
		 SQL =  "jdbc:sqlserver://localhost:1433;databaseName=projectManagerTool;integratedSecurity=true";
	     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	     connection = DriverManager.getConnection(SQL);
	}
	public static void main(String[] args) throws Throwable {
		Task t = new Task ("Develop", 50 , new Date(15-11-1999) , new Date(20/11/1999) , "No" , 20 ) ;
		t.addTask(2);
		//Project p = new Project() ;
		//p.load("Hatem") ;
		//System.out.println("Name: "  + p.name + "Cost: "+ p.cost + " Date: " + p.StartingDate + " ID:" +p.projectId) ;
	}
	public static Vector<Task> load(int projectId) throws Throwable
	{
		Vector<Task> v = new Vector<Task>() ;
        String sql = "SELECT* FROM Task INNER JOIN Project ON Task.projectID = Project.projectID; " ;
        Statement statement = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
		
		 while (rs.next()) {   
		    Task t = new Task();   
		    t.Taskname = rs.getString("Taskname") ;
		    t.workinghours = rs.getInt("WorkingHours") ;
		    t.delivaerable = rs.getString("TaskDilevrable") ;
		    t.startDate = rs.getDate("TaskStartDate") ;
		    t.DueDate = rs.getDate("TaskDueDate") ;
		    t.ActualWorkingHours = rs.getInt("ActualWorkingHours");
		    v.add(t) ;
		                                   
		}
		return v;
	}
	public void addTask (int ProjectId) throws Throwable {
        String sql = "INSERT INTO Task(ProjectID,Taskname,WorkingHours,TaskDilevrable,TaskStartDate,TaskDueDate,ActualWorkingHours) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql) ;
        pstmt.setInt(1, ProjectId);
        pstmt.setString(2, this.Taskname);
        pstmt.setInt(3, this.workinghours);
        pstmt.setString(4, this.delivaerable);
        pstmt.setDate(5, this.startDate);
        pstmt.setDate(6, this.DueDate);
        pstmt.setInt(7, this.ActualWorkingHours);
        pstmt.executeUpdate();

		
	}

}
