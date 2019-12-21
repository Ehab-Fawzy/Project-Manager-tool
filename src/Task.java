import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

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
	public Vector<Task> load(String Projectname)
	{
		Vector<Task> v = null ;
		String SQL = "jdbc:sqlserver://LAPTOP-M7JGO3P2:1433;databaseName=Library;integratedSecurity=true";
		int projectID = 0 ;////"SELECT ID FROM project WHERE name = Projectname "
	    /*	
        String sql = "SELECT* FROM Task INNER JOIN Project ON Task.projectID = Project.projectID; "
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(SQL);
        Statement statement = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery(sql);
		*/
		
		/*
		 while (rs.next()) {   
		    Task t = new Task ;   
		    Task.Taskname = rs.getString("Taskname") ;
		    Task.workinghours = rs.getString("workinghours") ;
		    Task.delivaerable = rs.getString("workinghours") ;
		    Task.startDate = rs.getString("workinghours") ;
		    Task.DueDate = rs.getString("workinghours") ;
		    v.add(t) ;
		                                   
		}
		*/
		return v;
	}

}
