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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainFrame {

	private static JFrame frame;
	private JTable table1;
	private JTable table2;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	static DefaultTableModel model ;
	private static DefaultTableModel model2 ;
	private static JTextField memberTaskID_TF;
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
			String s = new String();
			Vector<Integer> pre = Task.loadPredecessor(tasks.get(i).TaskID);
			for (int j=0 ; j<pre.size() ; j++){
				if (j != pre.size() -1)
					s += (pre.get(j) + ",");
				else
					s += pre.get(j);
			}
			model.addRow(new Object[] {s,tasks.get(i).TaskID, tasks.get(i).Taskname, tasks.get(i).workinghours, tasks.get(i).startDate, tasks.get(i).DueDate, tasks.get(i).delivaerable});
		}
		Vector<Task.Member> members = temp.loadMembers();
		if (model2 != null) {
			model2.setRowCount(0);
		}
		for( int i=0 ; i<members.size() ; i++) {
			model2.addRow(new Object[] {members.get(i).MemberName, members.get(i).MemberTitle, '-', '-'});
		}
		
		
	}
	public static void loadTaskMembers() throws Throwable {
		Vector<Task.Member> members = Task.loadMembers(Integer.parseInt(memberTaskID_TF.getText()));
		if (model2 != null) {
			model2.setRowCount(0);
		}
		for( int i=0 ; i<members.size() ; i++) {
			model2.addRow(new Object[] {members.get(i).MemberName, members.get(i).MemberTitle, memberTaskID_TF.getText(), members.get(i).WorkingHours});
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 957, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initializeTables();
		
		
		JButton btnNewButton = new JButton("Show Members");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadTaskMembers();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Add Task");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTaskForm addTaskForm = new AddTaskForm(project.projectId);
				frame.setVisible(false);
			}
		});
		
		
		
		JLabel lblNewLabel = new JLabel("Tasks");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Members");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		memberTaskID_TF = new JTextField();
		memberTaskID_TF.setColumns(10);
		
		JButton btnAddSubtask = new JButton("Add subtask");
		btnAddSubtask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddSubtaskForm form = new AddSubtaskForm();
				frame.setVisible(false);
			}
		});
		
		JButton btnAddProjectMembers = new JButton("Add Member");
		btnAddProjectMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProjectMember form = new AddProjectMember(project);
				frame.setVisible(false);
			}
		});
		
		btnNewButton_3 = new JButton("Show Chart");
		
		btnNewButton_4 = new JButton("Assign Task Members");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("");
					addTaskMember form = new addTaskMember(project);
					frame.setVisible(false);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
		});
		
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
		
		TaskID_TF = new JTextField();
		TaskID_TF.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(286)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addGap(384)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
					.addGap(20))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
							.addGap(25))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(btnNewButton_4, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
							.addGap(13)
							.addComponent(btnAddProjectMembers, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
							.addGap(176))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAddSubtask, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
							.addGap(316)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
							.addGap(11)
							.addComponent(memberTaskID_TF, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(showSubTasks, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
							.addGap(12)
							.addComponent(TaskID_TF, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
					.addGap(33))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAddSubtask)
							.addComponent(btnNewButton_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(memberTaskID_TF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_3)
						.addComponent(btnNewButton_4)
						.addComponent(btnAddProjectMembers)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(TaskID_TF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(showSubTasks))
					.addGap(17))
		);
		frame.getContentPane().setLayout(groupLayout);
		
		
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
		scrollPane1.setPreferredSize(new Dimension(100, 226));	
			
		table2 = new JTable(new DefaultTableModel(new Object[]{"Name","Title", "Task", "Working Hours"},0));
		table2.setBounds(500, 28, 292, 392);
		table2.setFont(new Font("Consolas", Font.BOLD, 14));
		table2.setFillsViewportHeight(true);
		table2.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), Color.BLACK, Color.BLACK, Color.BLACK));
		table2.setDefaultRenderer(Object.class, centerRenderer);
		table2.setIntercellSpacing(new Dimension(40,5));
		table2.setRowHeight(30);
		model2 = (DefaultTableModel) table2.getModel();
		
		scrollPane2 = new JScrollPane(table2);
		scrollPane2.setPreferredSize(new Dimension(100, 226));
		
	}
}
