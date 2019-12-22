import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MainFrame {

	private static JFrame frame;
	private JTable table1;
	private JTable table2;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	static DefaultTableModel model ;
	private DefaultTableModel model2 ;
	private JTextField textField;
	private JTextField textField_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private static Project project;
	private static Vector<Task> tasks;
	private JTextField TaskID_TF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void showFrame() throws Throwable {
		frame.setVisible(true);
		if (tasks != null)
			tasks.clear();
		loadData();
	}
	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		frame.setVisible(true);
	}
	public MainFrame(String projectName) throws Throwable {
		Project temp = new Project();
		this.project = temp.load(projectName);
		initialize();
		frame.setVisible(true);
		loadData();
	}
	public static void setTasks(Vector<Task> v ) {
		tasks = v;
	}
	private static void loadData() throws Throwable {
		Project temp = new Project();
		project = temp.load(project.name);
		Vector<Task> tasks = project.tasks;
		if (model != null) {
			model.setRowCount(0);
		}
		for( int i=0 ; i<tasks.size() ; i++) {
			model.addRow(new Object[] {0,tasks.get(i).TaskID, tasks.get(i).Taskname, tasks.get(i).workinghours, tasks.get(i).startDate, tasks.get(i).DueDate, tasks.get(i).delivaerable});
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 957, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		initializeTables();
		frame.getContentPane().add(scrollPane1);
		frame.getContentPane().add(scrollPane2);
		
		
		JButton btnNewButton = new JButton("Show Members");
		btnNewButton.setBounds(627, 463, 127, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add Task");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTaskForm addTaskForm = new AddTaskForm(project.projectId);
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(146, 463, 170, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
		
		JLabel lblNewLabel = new JLabel("Tasks");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(286, 22, 69, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Members");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(739, 25, 182, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(765, 464, 143, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAddSubtask = new JButton("Add subtask");
		btnAddSubtask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddSubtaskForm form = new AddSubtaskForm();
				frame.setVisible(false);
			}
		});
		btnAddSubtask.setBounds(31, 463, 105, 23);
		frame.getContentPane().add(btnAddSubtask);
		
		JButton btnAddProjectMembers = new JButton("Add Member");
		btnAddProjectMembers.setBounds(627, 497, 127, 23);
		frame.getContentPane().add(btnAddProjectMembers);
		
		textField_2 = new JTextField();
		textField_2.setBounds(765, 498, 143, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton_3 = new JButton("Show Chart");
		btnNewButton_3.setBounds(31, 497, 105, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Assign Task Members");
		btnNewButton_4.setBounds(146, 497, 170, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton showSubTasks = new JButton("Show Subtasks");
		showSubTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showSubtasks form = new showSubtasks(Integer.parseInt(TaskID_TF.getText()));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		});
		showSubTasks.setBounds(326, 497, 121, 23);
		frame.getContentPane().add(showSubTasks);
		
		TaskID_TF = new JTextField();
		TaskID_TF.setColumns(10);
		TaskID_TF.setBounds(459, 498, 143, 20);
		frame.getContentPane().add(TaskID_TF);
		
		
	}

	private void initializeTables() {
		table1 = new JTable(new DefaultTableModel(new Object[]{"Predecessor ID" , "Task ID",  "Name", "Working Hours", "StartDate", "DueDate" , "deliverable"},0));
		table1.setBounds(500, 28, 292, 392);
		table1.setFont(new Font("Consolas", Font.BOLD, 14));
		table1.setFillsViewportHeight(true);
		table1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), Color.BLACK, Color.BLACK, Color.BLACK));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table1.setDefaultRenderer(Object.class, centerRenderer);
		table1.setIntercellSpacing(new Dimension(40,5));
		table1.setRowHeight(30);
		model = (DefaultTableModel) table1.getModel();
		
		table1.getColumnModel().getColumn(0).setMinWidth(100);
		table1.getColumnModel().getColumn(1).setMaxWidth(60);
		table1.getColumnModel().getColumn(3).setMinWidth(100);
		
		scrollPane1 = new JScrollPane(table1);
		scrollPane1.setBounds(31, 47, 571, 396);
		scrollPane1.setPreferredSize(new Dimension(100, 226));	
			
		table2 = new JTable(new DefaultTableModel(new Object[]{"Name","Title", "Task"},0));
		table2.setBounds(500, 28, 292, 392);
		table2.setFont(new Font("Consolas", Font.BOLD, 14));
		table2.setFillsViewportHeight(true);
		table2.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), Color.BLACK, Color.BLACK, Color.BLACK));
		table2.setDefaultRenderer(Object.class, centerRenderer);
		table2.setIntercellSpacing(new Dimension(40,5));
		table2.setRowHeight(30);
		model2 = (DefaultTableModel) table2.getModel();
		
		scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(627, 47, 281, 396);
		scrollPane2.setPreferredSize(new Dimension(100, 226));
		
	}
}
