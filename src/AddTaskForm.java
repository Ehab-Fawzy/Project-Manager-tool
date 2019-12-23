import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AddTaskForm {

	private JFrame frame;
	private JTextField preID_TF;
	private JTextField workingHours_TF;
	private JTextField startDate_TF;
	private JTextField dueDate_TF;
	private JTextField deliverable_TF;
	private JTextField taskName_TF;
	private int projectID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTaskForm window = new AddTaskForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddTaskForm() {
		initialize();
		frame.setVisible(true);
	}
	public AddTaskForm(int projectID) {
		this.projectID = projectID;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 436, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		preID_TF = new JTextField();
		preID_TF.setColumns(10);
		preID_TF.setBounds(157, 100, 197, 20);
		frame.getContentPane().add(preID_TF);
		
		JLabel lblPredecessorId = new JLabel("Predecessors ID");
		lblPredecessorId.setBounds(47, 103, 100, 14);
		frame.getContentPane().add(lblPredecessorId);
		
		JLabel lblWorkingHours = new JLabel("Working Hours");
		lblWorkingHours.setBounds(47, 131, 100, 14);
		frame.getContentPane().add(lblWorkingHours);
		
		workingHours_TF = new JTextField();
		workingHours_TF.setColumns(10);
		workingHours_TF.setBounds(157, 128, 197, 20);
		frame.getContentPane().add(workingHours_TF);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(47, 159, 100, 14);
		frame.getContentPane().add(lblStartDate);
		
		startDate_TF = new JTextField();
		startDate_TF.setColumns(10);
		startDate_TF.setBounds(157, 156, 197, 20);
		frame.getContentPane().add(startDate_TF);
		
		JLabel lblDueDate = new JLabel("Due Date");
		lblDueDate.setBounds(47, 187, 100, 14);
		frame.getContentPane().add(lblDueDate);
		
		dueDate_TF = new JTextField();
		dueDate_TF.setColumns(10);
		dueDate_TF.setBounds(157, 184, 197, 20);
		frame.getContentPane().add(dueDate_TF);
		
		JLabel lblDeliverable = new JLabel("Deliverable");
		lblDeliverable.setBounds(47, 215, 100, 14);
		frame.getContentPane().add(lblDeliverable);
		
		deliverable_TF = new JTextField();
		deliverable_TF.setColumns(10);
		deliverable_TF.setBounds(157, 212, 197, 20);
		frame.getContentPane().add(deliverable_TF);
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Add Task to table1
				try {
					ProjectTask t = new ProjectTask();
					t.Taskname = taskName_TF.getText();
					t.workinghours = Integer.parseInt(workingHours_TF.getText());
					t.delivaerable = deliverable_TF.getText();
					SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy"); 
					java.util.Date dateUtil = (Date) formatter.parse(startDate_TF.getText());
					java.sql.Date sqlDate = new java.sql.Date(dateUtil.getTime());
					t.startDate = sqlDate;
					dateUtil = (Date) formatter.parse(dueDate_TF.getText());
					sqlDate = new java.sql.Date(dateUtil.getTime());
					t.DueDate = sqlDate;
					t.addTask(projectID);
					if (preID_TF.getText().length() != 0){
						String[] split = preID_TF.getText().split(",");
					
						for (int i=0 ; i<split.length ; i++) {
							t.predecessor.add(Integer.parseInt(split[i]));
						}
						t.addPredecessor();
					}
					MainFrame.showFrame();
				} catch (Throwable t) {
					t.printStackTrace();
				}
				frame.dispose();
			}
		});
		button.setBounds(154, 291, 89, 23);
		frame.getContentPane().add(button);
		
		taskName_TF = new JTextField();
		taskName_TF.setColumns(10);
		taskName_TF.setBounds(157, 69, 197, 20);
		frame.getContentPane().add(taskName_TF);
		
		JLabel lblTaskName = new JLabel("Task name");
		lblTaskName.setBounds(47, 72, 100, 14);
		frame.getContentPane().add(lblTaskName);
	}
}
