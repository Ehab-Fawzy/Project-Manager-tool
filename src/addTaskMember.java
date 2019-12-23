import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import sun.applet.Main;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class addTaskMember {

	private JFrame frame;
	private JTextField working_TF;
	private JTextField taskID_TF;
	private Project project;
	private Vector<ProjectTask.Member> members;
	private JComboBox comboBox ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addTaskMember window = new addTaskMember();
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
	public addTaskMember() {
		initialize();
	}
	public addTaskMember(Project p) throws Throwable {
		initialize();
		frame.setVisible(true);
		this.project = p;
		members = p.loadMembers();
		for (int i=0 ; i<members.size() ; i++) {
			comboBox.addItem(members.get(i).MemberName);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 301, 257);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = comboBox.getSelectedItem().toString();
				for (int i=0 ; i<members.size() ; i++) {
					if (members.get(i).MemberName.equals(name)) {
						try {
							System.out.println("");
							members.get(i).WorkingHours = Integer.parseInt(working_TF.getText());
							ProjectTask.addMember(Integer.parseInt(taskID_TF.getText()), members.get(i), project.projectId);
							MainFrame.showFrame();
							frame.dispose();
						} catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
				
			}
		});
		button.setBounds(23, 129, 89, 23);
		frame.getContentPane().add(button);
		
		working_TF = new JTextField();
		working_TF.setColumns(10);
		working_TF.setBounds(114, 70, 155, 20);
		frame.getContentPane().add(working_TF);
		
		JLabel lblWorkingHours = new JLabel("Working Hours");
		lblWorkingHours.setBounds(23, 73, 70, 14);
		frame.getContentPane().add(lblWorkingHours);
		
		JLabel lblTaskId = new JLabel("Task ID");
		lblTaskId.setBounds(23, 104, 46, 14);
		frame.getContentPane().add(lblTaskId);
		
		taskID_TF = new JTextField();
		taskID_TF.setColumns(10);
		taskID_TF.setBounds(114, 98, 155, 20);
		frame.getContentPane().add(taskID_TF);
		
		comboBox = new JComboBox();
		comboBox.setBounds(134, 130, 116, 20);
		frame.getContentPane().add(comboBox);
	}
}
