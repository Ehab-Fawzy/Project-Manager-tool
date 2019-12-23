import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;


public class Project {
	 int projectId ;
	 String name ,startDay;
	 int cost;
	 Date StartingDate;
	 Date DueDates ;
	 int hoursPerDay, ActualProjectHours;
	 Vector<ProjectTask> tasks;
	 Vector<ProjectTask.Member> ProjectMembers;
	 private static String SQL ;
	 private static Connection connection;
	
	 public Project() throws ClassNotFoundException, SQLException {
		 tasks = new Vector<ProjectTask>();
		 SQL =  "jdbc:sqlserver://localhost:1433;databaseName=projectManagerTool;integratedSecurity=true";
	     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	     connection = DriverManager.getConnection(SQL);
	     ProjectMembers = new Vector<ProjectTask.Member>();

	 }
	 
	 
	 public Project ( String name, int cost, Date StartingDate, Date DueDates , String startDay, int hoursPerDay, int ActualProjectHourst) throws ClassNotFoundException, SQLException {
		this.name = name;
		this.cost = cost;
		this.StartingDate = StartingDate;
		this.DueDates = DueDates;
		this.startDay = startDay;
		this.hoursPerDay = hoursPerDay;
		this.ActualProjectHours = ActualProjectHourst;
	     ProjectMembers = new Vector<ProjectTask.Member>();
		tasks = new Vector<ProjectTask>();
		 SQL =  "jdbc:sqlserver://localhost:1433;databaseName=projectManagerTool;integratedSecurity=true";
	     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	     connection = DriverManager.getConnection(SQL);

	 }
	 
	public static void main(String[] args) throws Throwable {
		//Project p = new Project ("Hatem",50,new Date(15-11-1999),new Date(20/11/1999),"Friday",50,20) ;
		//p.add();
		Project p = new Project() ;
		p.load("Hatem") ;
		System.out.println("Name: "  + p.name + "Cost: "+ p.cost + " Date: " + p.StartingDate + " ID:" +p.projectId) ;
		System.out.println(p.tasks.get(0).Taskname);
	}
	public Project load(String name) throws SQLException, Throwable {
        String sql = "SELECT * FROM Project  Where ProjectName = '" + name+"'" ;
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
		 while (rs.next()) {     
			    this.projectId = rs.getInt("ProjectID");
			    this.name = rs.getString("ProjectName") ;
			    this.cost = rs.getInt("ProjectCost") ;
			    this.StartingDate = rs.getDate("ProjectStartDate") ;
			    this.DueDates = rs.getDate("ProjectDueDate") ;
			    this.startDay = rs.getString("StartDayOfWeek") ;
			    this.hoursPerDay = rs.getInt("HoursPerDay") ;
			    this.ActualProjectHours = rs.getInt("ActualProjectHours") ;	    
		}
		 
		 System.out.println( "Flagggggg : " + this.projectId );
		 
			ProjectTask t = new ProjectTask () ;
			tasks = t.load(projectId) ;
		return this;
	}
	public void add () throws SQLException, ClassNotFoundException
	{
        String sql = "INSERT INTO Project(ProjectName,ProjectCost,ProjectStartDate,ProjectDueDate,StartDayOfWeek,HoursPerDay,ActualProjectHours) VALUES(?,?,?,?,?,?,?)";
                
        PreparedStatement pstmt = connection.prepareStatement(sql) ;
        pstmt.setString(1, this.name);
        pstmt.setInt(2, this.cost);
        pstmt.setDate(3, this.StartingDate);
        pstmt.setDate(4, this.DueDates);
        pstmt.setString(5, this.startDay);
        pstmt.setInt(6, this.hoursPerDay);
        pstmt.setInt(7, this.ActualProjectHours);
        pstmt.executeUpdate();
    
	}
	public void addMembers(ProjectTask.Member m) throws Throwable {
		ProjectTask.addMember(-1, m ,this.projectId);
	}
	
	public Vector<ProjectTask.Member> loadMembers() throws Throwable{
		Vector <ProjectTask.Member> members = new Vector <ProjectTask.Member> () ; 
		ProjectMembers = new Vector<ProjectTask.Member>();
        String sql = "SELECT DISTINCT MemberName,MemberTitle FROM taskMember where ProjectID = '" + this.projectId + "'" ;
        Statement statement = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
		 while (rs.next()) {   
			    ProjectTask.Member m  = new ProjectTask.Member();   
			    m.MemberName = rs.getString("MemberName") ;
			    m.MemberTitle = rs.getString("MemberTitle") ;
			    members.add(m) ;
			    ProjectMembers.add(m);
			}
		return members;	
	}
	
}
