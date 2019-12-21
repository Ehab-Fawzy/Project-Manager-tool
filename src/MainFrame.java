import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Date;

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
import java.awt.event.ActionEvent;

public class MainFrame {

	private static JFrame frame;

	private JTable table1;
	private JTable table2;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	static DefaultTableModel model ;
	private DefaultTableModel model1 ;
	private DefaultTableModel model2 ;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;

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
	public static void showFrame() {
		frame.setVisible(true);
	}
	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1083, 648);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		initializeTables();
		frame.getContentPane().add(scrollPane1);
		frame.getContentPane().add(scrollPane2);
		
		
		JButton btnNewButton = new JButton("Show Members");
		btnNewButton.setBounds(538, 473, 105, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add Task");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTaskForm addTaskForm = new AddTaskForm();
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(922, 473, 135, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Show Subtasks");
		btnNewButton_2.setBounds(112, 473, 105, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		
		
		JLabel lblNewLabel = new JLabel("Tasks");
		lblNewLabel.setBounds(286, 22, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Members");
		lblNewLabel_1.setBounds(848, 22, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(650, 474, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(227, 474, 105, 20);
		frame.getContentPane().add(textField_1);
		
		JButton btnAddSubtask = new JButton("Add subtask");
		btnAddSubtask.setBounds(922, 507, 135, 23);
		frame.getContentPane().add(btnAddSubtask);
		
		JButton btnAddProjectMembers = new JButton("Add Project Member");
		btnAddProjectMembers.setBounds(776, 575, 135, 23);
		frame.getContentPane().add(btnAddProjectMembers);
		
		textField_2 = new JTextField();
		textField_2.setBounds(922, 576, 135, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton_3 = new JButton("Show Chart");
		btnNewButton_3.setBounds(112, 575, 105, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Assign Task Members");
		btnNewButton_4.setBounds(453, 575, 142, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		
	}

	private void initializeTables() {
		table1 = new JTable(new DefaultTableModel(new Object[]{"Predecessor ID" , "Task ID",  "Name", "Working Hours", "StartDate", "DueDates" , "deliverable"},0));
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
		
		scrollPane1 = new JScrollPane(table1);
		scrollPane1.setBounds(31, 47, 571, 396);
		scrollPane1.setPreferredSize(new Dimension(100, 226));	
			
		table2 = new JTable(new DefaultTableModel(new Object[]{"Name", "Task"},0));
		table2.setBounds(500, 28, 292, 392);
		table2.setFont(new Font("Consolas", Font.BOLD, 14));
		table2.setFillsViewportHeight(true);
		table2.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), Color.BLACK, Color.BLACK, Color.BLACK));
		table2.setDefaultRenderer(Object.class, centerRenderer);
		table2.setIntercellSpacing(new Dimension(40,5));
		table2.setRowHeight(30);
		model2 = (DefaultTableModel) table2.getModel();
		
		scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(690, 47, 367, 396);
		scrollPane2.setPreferredSize(new Dimension(100, 226));
		
	}
}
