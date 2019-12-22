import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddProjectMember {

	private JFrame frame;
	private JTextField name_TF;
	private JTextField title_TF;
	private Project project;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProjectMember window = new AddProjectMember();
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
	public AddProjectMember() {
		initialize();
	}

	public AddProjectMember(Project project) {
		initialize();
		frame.setVisible(true);
		this.project = project;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 263, 163);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Task.Member m = new  Task.Member();
				m.MemberName = name_TF.getText();
				m.MemberTitle = title_TF.getText();
				try {
					project.addMembers(m);
					MainFrame.showFrame();
					frame.dispose();
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(76, 78, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		name_TF = new JTextField();
		name_TF.setBounds(82, 11, 155, 20);
		frame.getContentPane().add(name_TF);
		name_TF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 14, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(10, 45, 46, 14);
		frame.getContentPane().add(lblTitle);
		
		title_TF = new JTextField();
		title_TF.setColumns(10);
		title_TF.setBounds(82, 42, 155, 20);
		frame.getContentPane().add(title_TF);
	}
}
