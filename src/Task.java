import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class Task {
	
	String Taskname ; 
	int workinghours, ActualWorkingHours, TaskID; 
	String delivaerable;
	Date startDate, DueDate;
	public static Vector<Member> Members;
	public static Vector<Task> subtasks;
	public static Vector<Integer> predecessor;
	private static String SQL ;
	private static Connection connection;

	
	Task() throws ClassNotFoundException, SQLException{	
		subtasks = new Vector<Task>();
		 SQL =  "jdbc:sqlserver://localhost:1433;databaseName=projectManagerTool;integratedSecurity=true";
	     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	     connection = DriverManager.getConnection(SQL);
			Members = new Vector<Task.Member>();


	}
	
	Task(String Taskname, int workinghours, Date StartDate, Date DueDates , String deliverables,int ActualWorkingHours) throws ClassNotFoundException, SQLException{
		this.Taskname = Taskname;
		this.startDate = StartDate;
		this.DueDate = DueDates;
		this.delivaerable = deliverables;
		this.ActualWorkingHours= ActualWorkingHours ;
		this.workinghours = workinghours ;
		
		subtasks = new Vector<Task>();
		Members = new Vector<Task.Member>();
		 SQL =  "jdbc:sqlserver://localhost:1433;databaseName=projectManagerTool;integratedSecurity=true";
	     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	     connection = DriverManager.getConnection(SQL);
	}
	public static class Member
	{
		String MemberName ;
		String MemberTitle;
		int WorkingHours ;
		
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
		    t.TaskID = rs.getInt("TaskID");
		    t.Taskname = rs.getString("Taskname") ;
		    t.workinghours = rs.getInt("WorkingHours") ;
		    t.delivaerable = rs.getString("TaskDilevrable") ;
		    t.startDate = rs.getDate("TaskStartDate") ;
		    t.DueDate = rs.getDate("TaskDueDate") ;
		    t.ActualWorkingHours = rs.getInt("ActualWorkingHours");
		    subtasks = t.loadSubTasks(t.TaskID);
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
	public void addSubTask (int TaskId) throws Throwable
	{
        String sql = "INSERT INTO subTasks(subTaskName,TaskID,WorkingHours,SubTaskDilevrable,SubTaskStartDate,SubTaskDueDate,ActualWorkingHours) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql) ;
        pstmt.setString(1,this.Taskname);
        pstmt.setInt(2, TaskId);
        pstmt.setInt(3, this.workinghours);
        pstmt.setString(4, this.delivaerable);
        pstmt.setDate(5, this.startDate);
        pstmt.setDate(6, this.DueDate);
        pstmt.setInt(7, this.ActualWorkingHours);
        pstmt.executeUpdate();	
	}
	public Vector<Task> loadSubTasks (int TaskId) throws Throwable
	{
		Vector <Task> Return = new Vector <Task> () ; 
        String sql = "SELECT * FROM subTasks where TaskID = '" + TaskId + "'" ;
        Statement statement = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
		 while (rs.next()) {   
			    Task t = new Task();   
			    t.Taskname = rs.getString("SubTaskName") ;
			    t.workinghours = rs.getInt("WorkingHours") ;
			    t.delivaerable = rs.getString("SubTaskDilevrable") ;
			    t.startDate = rs.getDate("SubTaskStartDate") ;
			    t.DueDate = rs.getDate("SubTaskDueDate") ;
			    t.ActualWorkingHours = rs.getInt("ActualWorkingHours");
			    Return.add(t) ;                                  
		}
		return Return;	
	}
	public static Vector<Member> loadMembers (int TaskId) throws Throwable
	{
		Vector <Member> members = new Vector <Member> () ; 
        String sql = "SELECT * FROM taskMember where TaskID = '" + TaskId + "'" ;
        Statement statement = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
		 while (rs.next()) {   
			    Member m  = new Member();   
			    m.MemberName = rs.getString("MemberName") ;
			    m.MemberTitle = rs.getString("MemberTitle") ;
			    m.WorkingHours = rs.getInt("WorkingHours");
			    members.add(m) ;                                  
			}
		return members;		
	}
	public static void addMember (int TaskId,Member m,int ProjectId) throws Throwable
	{
		String sql = "delete from taskMember where MemberName = '"+ m.MemberName + "' and TaskID = -1" ;
		PreparedStatement pstmt = connection.prepareStatement(sql) ;	
        pstmt.executeUpdate();
		
		sql = "INSERT INTO taskMember(ProjectID,TaskID,MemberName,WorkingHours,MemberTitle) VALUES(?,?,?,?,?)";
		pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, ProjectId);
        pstmt.setInt(2, TaskId);
        pstmt.setString(3, m.MemberName);
        pstmt.setInt(4, m.WorkingHours);
        pstmt.setString(5,m.MemberTitle);
        pstmt.executeUpdate();		
	}
	public void addPredecessor() throws Throwable
	{
		String sql = "INSERT INTO taskPredecessor(TaskID,PredecessorTaskID) VALUES(?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql) ;	
		for (int i = 0 ; i < predecessor.size();i++)
		{	
	        pstmt.setInt(1, this.TaskID);
	        pstmt.setInt(2, predecessor.get(i));
	        pstmt.executeUpdate();		
		}
	}
	public static Vector<Integer> loadPredecessor(int TaskId) throws Throwable
	{
		Vector <Integer> p = new Vector <Integer> () ;
        String sql = "SELECT * FROM taskPredecessor where TaskID = '" + TaskId + "'" ;
        Statement statement = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
		 while (rs.next()) {   
	           p.add(rs.getInt("PredecessorTaskID"));		       
			}
        return p;
		
	}
	

}
