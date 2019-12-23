import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AddSubtaskForm {

	private JFrame frame;
	private JTextField Subtask_TF;
	private JTextField preID_TF;
	private JTextField workingHours_TF;
	private JTextField startDate_TF;
	private JTextField dueDate_TF;
	private JTextField deliverable_TF;
	private JTextField TaskID_TF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSubtaskForm window = new AddSubtaskForm();
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
	public AddSubtaskForm() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 368);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSubtaskName = new JLabel("Subtask name");
		lblSubtaskName.setBounds(59, 90, 100, 14);
		frame.getContentPane().add(lblSubtaskName);
		
		Subtask_TF = new JTextField();
		Subtask_TF.setColumns(10);
		Subtask_TF.setBounds(169, 87, 197, 20);
		frame.getContentPane().add(Subtask_TF);
		
		preID_TF = new JTextField();
		preID_TF.setColumns(10);
		preID_TF.setBounds(169, 118, 197, 20);
		frame.getContentPane().add(preID_TF);
		
		JLabel label_1 = new JLabel("Predecessor ID");
		label_1.setBounds(59, 121, 100, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Working Hours");
		label_2.setBounds(59, 149, 100, 14);
		frame.getContentPane().add(label_2);
		
		workingHours_TF = new JTextField();
		workingHours_TF.setColumns(10);
		workingHours_TF.setBounds(169, 146, 197, 20);
		frame.getContentPane().add(workingHours_TF);
		
		JLabel label_3 = new JLabel("Start Date");
		label_3.setBounds(59, 177, 100, 14);
		frame.getContentPane().add(label_3);
		
		startDate_TF = new JTextField();
		startDate_TF.setColumns(10);
		startDate_TF.setBounds(169, 174, 197, 20);
		frame.getContentPane().add(startDate_TF);
		
		JLabel label_4 = new JLabel("Due Date");
		label_4.setBounds(59, 205, 100, 14);
		frame.getContentPane().add(label_4);
		
		dueDate_TF = new JTextField();
		dueDate_TF.setColumns(10);
		dueDate_TF.setBounds(169, 202, 197, 20);
		frame.getContentPane().add(dueDate_TF);
		
		deliverable_TF = new JTextField();
		deliverable_TF.setColumns(10);
		deliverable_TF.setBounds(169, 230, 197, 20);
		frame.getContentPane().add(deliverable_TF);
		
		JLabel label_5 = new JLabel("Deliverable");
		label_5.setBounds(59, 233, 100, 14);
		frame.getContentPane().add(label_5);
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProjectTask t = new ProjectTask();
					t.Taskname = Subtask_TF.getText();
					t.workinghours = Integer.parseInt(workingHours_TF.getText());
					t.delivaerable = deliverable_TF.getText();
					SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy"); 
					java.util.Date dateUtil = (Date) formatter.parse(startDate_TF.getText());
					java.sql.Date sqlDate = new java.sql.Date(dateUtil.getTime());
					t.startDate = sqlDate;
					dateUtil = (Date) formatter.parse(dueDate_TF.getText());
					sqlDate = new java.sql.Date(dateUtil.getTime());
					t.DueDate = sqlDate;
					t.addSubTask(Integer.parseInt(TaskID_TF.getText()));
					MainFrame.showFrame();
				} catch (Throwable t) {
					t.printStackTrace();
				}
				frame.dispose();
			}
		});
		submitBtn.setBounds(164, 280, 89, 23);
		frame.getContentPane().add(submitBtn);
		
		JLabel lblTaskId = new JLabel("Task ID");
		lblTaskId.setBounds(59, 62, 100, 14);
		frame.getContentPane().add(lblTaskId);
		
		TaskID_TF = new JTextField();
		TaskID_TF.setColumns(10);
		TaskID_TF.setBounds(169, 59, 197, 20);
		frame.getContentPane().add(TaskID_TF);
	}
}
