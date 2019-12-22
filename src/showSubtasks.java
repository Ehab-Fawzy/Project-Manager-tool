import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class showSubtasks {

	private JFrame frame;

	private JTable table1;
	private JScrollPane scrollPane1;
	static DefaultTableModel model ;
	private JButton btnNewButton;
	private static Vector<Task> subTasks;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showSubtasks window = new showSubtasks();
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
	public showSubtasks() {
		initialize();
		frame.setVisible(true);
	}
	public showSubtasks(int taskID) throws Throwable {
		initialize();
		Task temp = new Task();
		subTasks = temp.loadSubTasks(taskID);
		frame.setVisible(true);
		loadData();
	}
	private static void loadData() throws Throwable {
		if (model != null) {
			model.setRowCount(0);
		}
		for( int i=0 ; i<subTasks.size() ; i++) {
			model.addRow(new Object[] {0,subTasks.get(i).TaskID, subTasks.get(i).Taskname, subTasks.get(i).workinghours, subTasks.get(i).startDate, subTasks.get(i).DueDate, subTasks.get(i).delivaerable});
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 683, 614);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		initializeTables();
		frame.getContentPane().add(scrollPane1);
		
		btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(272, 541, 89, 23);
		frame.getContentPane().add(btnNewButton);
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
		scrollPane1.setBounds(10, 11, 647, 514);
		scrollPane1.setPreferredSize(new Dimension(100, 226));	
	}
}
