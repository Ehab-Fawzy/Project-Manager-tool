//import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;  
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import javax.swing.JFrame;  
import javax.swing.SwingUtilities;  
import javax.swing.WindowConstants;  
import java.util.Vector;  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.data.category.IntervalCategoryDataset;  
import org.jfree.data.gantt.Task;  
import org.jfree.data.gantt.TaskSeries;  
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

  
public class GanttChartExample extends JFrame {  
  
   private static final long serialVersionUID = 1L;  
   private static Vector<ProjectTask> tasks;
  
   public GanttChartExample(String title, Vector<ProjectTask> tasks ) throws Throwable {  
      super(title);
      this.tasks = tasks;
      // Create dataset  
      IntervalCategoryDataset dataset = getCategoryDataset();  
      
      // Create chart  
      JFreeChart chart = ChartFactory.createGanttChart(  
            "Gantt Chart Example", // Chart title  
            "Software Development Phases", // X-Axis Label  
            "Timeline", // Y-Axis Label  
            dataset);  
  
      ChartPanel panel = new ChartPanel(chart);  
      setContentPane(panel);  
   }  
  
   private IntervalCategoryDataset getCategoryDataset() throws Throwable {  
	   TaskSeries task = new TaskSeries("Task");
	   
	   TaskSeries predecessor = new TaskSeries("Subtask");
	   if (tasks != null)
	   for (int i=0 ; i<tasks.size() ; i++) {
		  Vector<ProjectTask> vec = tasks.get(i).loadSubTasks(tasks.get(i).TaskID);
		  java.util.Date startDate = new java.util.Date(tasks.get(i).startDate.getTime());
  		  java.util.Date dueDate = new java.util.Date(tasks.get(i).DueDate.getTime());
		  task.add(new Task(tasks.get(i).Taskname, startDate, dueDate));
		  for (int j=0 ; j<vec.size() ; j++) {
			  java.util.Date startDatePre = new java.util.Date(vec.get(j).startDate.getTime());
			  java.util.Date dueDatePre = new java.util.Date(vec.get(j).DueDate.getTime());
			  predecessor.add(new Task(tasks.get(i).Taskname, startDatePre, dueDatePre));
		  }
	   }
  
	   TaskSeriesCollection dataset = new TaskSeriesCollection();  
	   dataset.add(task);
	   dataset.add(predecessor);  
	   return dataset;  
   }  
  
 public static void main(String[] args) {  
 SwingUtilities.invokeLater(() -> {  
	 	Vector<ProjectTask> tasks = new Vector<ProjectTask> ();
	 	try {
			/*ProjectTask t = new ProjectTask();
			java.sql.Date sqlDate = new java.sql.Date(1999-1-1);
			t.startDate = sqlDate;
			System.out.println(t.startDate);
			java.sql.Date sqlDate2 = new java.sql.Date(1999-1-1);
			t.DueDate = sqlDate2 ;
			t.Taskname = "Task";
			
			ProjectTask sub = new ProjectTask();
			java.sql.Date sqlDateSub = new java.sql.Date(1999-1-1);
			sub.startDate = sqlDateSub;
			java.sql.Date sqlDateSub2 = new java.sql.Date(1999-1-1);
			System.out.println(sqlDateSub2);
			sub.DueDate = sqlDateSub2;
			sub.Taskname = "Task";
			t.subtasks.add(sub);*/
			Project p = new Project();
			p.load("khaled");
			tasks = p.tasks;
			System.out.println(tasks.get(0).startDate);
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	 	 GanttChartExample example;
		try {
			example = new GanttChartExample("Gantt Chart Example",tasks);
			example.setSize(800, 400);  
			example.setLocationRelativeTo(null);  
			example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
			example.setVisible(true);  
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
      });  
   }  
}  