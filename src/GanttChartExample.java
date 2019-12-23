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
  
   public GanttChartExample(String title, Vector<ProjectTask> tasks ) {  
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
  
   private IntervalCategoryDataset getCategoryDataset() {  
	   TaskSeries task = new TaskSeries("Task");
	   
	   TaskSeries predecessor = new TaskSeries("Predecessor");
	   if (tasks != null)
	   for (int i=0 ; i<tasks.size() ; i++) {
		  Vector<ProjectTask> vec = tasks.get(i).subtasks;
  		  Task initial = new Task(tasks.get(i).Taskname + ":", new SimpleTimePeriod(0,1));
  		  java.util.Date startDate = new java.util.Date(tasks.get(i).startDate.getTime());
  		  java.util.Date dueDate = new java.util.Date(tasks.get(i).DueDate.getTime());
		  task.add(new Task(tasks.get(i).Taskname, startDate, dueDate));
		  for (int j=0 ; j<vec.size() ; j++) {
			  if (tasks.get(i).startDate.equals(vec.get(j).startDate))
				  System.out.println("Same");
			  java.util.Date startDatePre = new java.util.Date(vec.get(j).startDate.getTime());
			  java.util.Date dueDatePre = new java.util.Date(vec.get(j).DueDate.getTime());
			  if (startDate == startDatePre || startDate.equals(startDatePre))
				  System.out.println("Same");
			  predecessor.add(new Task(vec.get(j).Taskname, startDatePre, dueDatePre));
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
			ProjectTask t = new ProjectTask();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy"); 
			java.util.Date dateUtil = (Date) formatter.parse("12/10/2012");
			java.sql.Date sqlDate = new java.sql.Date(dateUtil.getTime());
			t.startDate = sqlDate;
			dateUtil = (Date) formatter.parse("12/12/2012");
			sqlDate = new java.sql.Date(dateUtil.getTime());
			t.DueDate = sqlDate;
			t.Taskname = "Task";
			
			ProjectTask sub = new ProjectTask();
			java.util.Date dateUtilSub = (Date) formatter.parse("12/10/2012");
			java.sql.Date sqlDateSub = new java.sql.Date(dateUtil.getTime());
			sub.startDate = sqlDateSub;
			dateUtilSub = (Date) formatter.parse("13/11/2012");
			sqlDateSub = new java.sql.Date(dateUtilSub.getTime());
			sub.DueDate = sqlDateSub;
			sub.Taskname = "Task";
			t.subtasks.add(sub);
			tasks.add(t);
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	 	GanttChartExample example = new GanttChartExample("Gantt Chart Example",tasks);  
         example.setSize(800, 400);  
         example.setLocationRelativeTo(null);  
         example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
         example.setVisible(true);  
      });  
   }  
}  