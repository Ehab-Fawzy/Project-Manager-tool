import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUI {

	private static JFrame frame;
	private JTextField projectName_TF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static void showFrame() {
		frame.setVisible(true);
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 341, 296);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton loadProjectBtn = new JButton("Load Project");
		loadProjectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MainFrame m = new MainFrame(projectName_TF.getText());
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.setVisible(false);
			}
		});
		loadProjectBtn.setBounds(99, 168, 132, 23);
		frame.getContentPane().add(loadProjectBtn);
		
		JButton btnNewButton_1 = new JButton("Create Project");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateProjectForm c = new CreateProjectForm();
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(99, 202, 132, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Project Name");
		lblNewLabel.setBounds(26, 103, 95, 14);
		frame.getContentPane().add(lblNewLabel);
		
		projectName_TF = new JTextField();
		projectName_TF.setBounds(115, 100, 184, 20);
		frame.getContentPane().add(projectName_TF);
		projectName_TF.setColumns(10);
	}
}
