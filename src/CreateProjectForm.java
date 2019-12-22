import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class CreateProjectForm {

	private static JFrame frame;
	private JTextField name_TF;
	private JTextField cost_TF;
	private JTextField startingDate_TF;
	private JTextField dueDate_TF;
	private JTextField hoursPerDay_TF;
	private JTextField startDay_TF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProjectForm window = new CreateProjectForm();
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
	public CreateProjectForm() {
		initialize();
		this.frame.setVisible(true);
		this.frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	GUI.showFrame();
		    	frame.dispose();
		    }
		});
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 408, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		name_TF = new JTextField();
		name_TF.setBounds(150, 91, 197, 20);
		frame.getContentPane().add(name_TF);
		name_TF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(40, 94, 100, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblCost = new JLabel("Cost");
		lblCost.setBounds(40, 125, 100, 14);
		frame.getContentPane().add(lblCost);
		
		cost_TF = new JTextField();
		cost_TF.setColumns(10);
		cost_TF.setBounds(150, 122, 197, 20);
		frame.getContentPane().add(cost_TF);
		
		JLabel lblStartingDate = new JLabel("Starting Date");
		lblStartingDate.setBounds(40, 153, 100, 14);
		frame.getContentPane().add(lblStartingDate);
		
		startingDate_TF = new JTextField();
		startingDate_TF.setColumns(10);
		startingDate_TF.setBounds(150, 150, 197, 20);
		frame.getContentPane().add(startingDate_TF);
		
		JLabel lblDueDate = new JLabel("Due Date");
		lblDueDate.setBounds(40, 181, 100, 14);
		frame.getContentPane().add(lblDueDate);
		
		dueDate_TF = new JTextField();
		dueDate_TF.setColumns(10);
		dueDate_TF.setBounds(150, 178, 197, 20);
		frame.getContentPane().add(dueDate_TF);
		
		JLabel lblHoursday_1 = new JLabel("Hours per day");
		lblHoursday_1.setBounds(40, 209, 100, 14);
		frame.getContentPane().add(lblHoursday_1);
		
		hoursPerDay_TF = new JTextField();
		hoursPerDay_TF.setColumns(10);
		hoursPerDay_TF.setBounds(150, 206, 197, 20);
		frame.getContentPane().add(hoursPerDay_TF);
		
		JLabel lblHoursday = new JLabel("Start Day");
		lblHoursday.setBounds(40, 237, 100, 14);
		frame.getContentPane().add(lblHoursday);
		
		startDay_TF = new JTextField();
		startDay_TF.setColumns(10);
		startDay_TF.setBounds(150, 234, 197, 20);
		frame.getContentPane().add(startDay_TF);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//createProject
				Project p = null;
				try {
					p = new Project();
				
					p.name = name_TF.getText();
					p.startDay = startDay_TF.getText();
					p.cost = Integer.parseInt(cost_TF.getText());
					p.hoursPerDay = Integer.parseInt(hoursPerDay_TF.getText());
					SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");  
					java.util.Date dateUtil = (Date) formatter.parse(startingDate_TF.getText());
					java.sql.Date sqlDate = new java.sql.Date(dateUtil.getTime());
					p.StartingDate = sqlDate;
					dateUtil = (Date) formatter.parse(dueDate_TF.getText());
					sqlDate = new java.sql.Date(dateUtil.getTime());
					p.DueDates = sqlDate;
					p.add();
				}  catch (Throwable t) {	
					t.printStackTrace();
				}
				
				GUI.showFrame();
				frame.dispose();
			}
		});
		btnSubmit.setBounds(147, 313, 89, 23);
		frame.getContentPane().add(btnSubmit);
	}

}
