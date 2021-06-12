import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Manager implements ActionListener{
	
	// variables initialized
	private static JFrame frame;
	private static JPanel panel, a_pane, d_pane, e_pane, p_pane, s_pane;
	private static JLabel intro, addtask, addstatus, deletetask, oldtask, newtask, newstatus, complete, remove, change;
	private static JButton add, delete, edit, print, sort, reset, submit;
	private static JTextField t_field, p_field, d_field, ot_field, nt_field, np_field;
	Settings main = new Settings();
	
	// constructor
	public Manager(String title) {
		frame = new JFrame(title);
	}
	
	public JPanel menu() {
		panel = new JPanel();
		panel.setLayout(null);
		
		intro = new JLabel("Welcome to Resolve");
		intro.setBounds(140, 50, 160, 25);
		panel.add(intro);
		
		add = new JButton("Add");
		add.setBounds(170, 90, 65, 25);
		panel.add(add);
		
		delete = new JButton("Delete");
		delete.setBounds(170, 120, 65, 25);
		panel.add(delete);
		
		edit = new JButton("Edit");
		edit.setBounds(170, 150, 65, 25);
		panel.add(edit);
		
		print = new JButton("Print");
		print.setBounds(170, 180, 65, 25);
		panel.add(print);
		
		sort = new JButton("Sort");
		sort.setBounds(170, 210, 65, 25);
		panel.add(sort);
		
		add.addActionListener(this);
		delete.addActionListener(this);
		edit.addActionListener(this);
		print.addActionListener(this);
		sort.addActionListener(this);
		
		return panel;
	}
	
	public void set_menu() {
		this.frame.setSize(400, 400);
		this.frame.add(menu());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}

	// buttons commands
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// remove panel
		this.frame.remove(panel);
		
		if (e.getSource() == add) {
			a_pane = new JPanel();
			add(a_pane);
		}
		else if (e.getSource() == delete) {
			d_pane = new JPanel();
			delete(d_pane);
		}
		else if (e.getSource() == edit) {
			e_pane = new JPanel();
			edit(e_pane);
		}
		else if (e.getSource() == print) {
			p_pane = new JPanel();
			print(p_pane);
		}
		else {
			s_pane = new JPanel();
			sort(s_pane);
		}
	}
	
	public void add(JPanel a_pane) {
		
		a_pane.setLayout(null);
		
		// initialize labels
		addtask = new JLabel("Enter Task:");
		addtask.setBounds(60, 80, 85, 25);
		a_pane.add(addtask);
		
		addstatus = new JLabel("Enter Priority Status:");
		addstatus.setBounds(60, 110, 200, 25);
		a_pane.add(addstatus);
		
		complete = new JLabel("");
		complete.setBounds(120, 210, 150, 25);
		a_pane.add(complete);
		
		// initialize text fields
		t_field = new JTextField(100);
		t_field.setBounds(200, 80, 120, 25);
		a_pane.add(t_field);
		
		p_field = new JTextField(100);
		p_field.setBounds(200, 110, 120, 25);
		a_pane.add(p_field);
		
		// submit button
		a_pane.add(submit(a_pane));
		
		// reset button
		a_pane.add(reset(a_pane));
				
		this.frame.add(a_pane);
		this.frame.validate();
	}
	
	public void delete(JPanel d_pane) {
		
		d_pane.setLayout(null);
		
		deletetask = new JLabel("Enter task to be deleted:");
		deletetask.setBounds(40, 100, 170, 25);
		d_pane.add(deletetask);
		
		d_field = new JTextField(100);
		d_field.setBounds(200, 100, 120, 25);
		d_pane.add(d_field);
		
		remove = new JLabel("");
		remove.setBounds(120, 210, 170, 25);
		d_pane.add(remove);
		
		d_pane.add(submit(d_pane));
		d_pane.add(reset(d_pane));
		
		this.frame.add(d_pane);
		this.frame.validate();
	}
	
	public void edit(JPanel e_pane) {
		
		e_pane.setLayout(null);
		
		oldtask = new JLabel("Enter old task:");
		oldtask.setBounds(30, 60, 100, 25);
		e_pane.add(oldtask);
		
		ot_field = new JTextField(100);
		ot_field.setBounds(200, 60, 120, 25);
		e_pane.add(ot_field);
		
		newtask = new JLabel("Enter new task:");
		newtask.setBounds(30, 90, 100, 25);
		e_pane.add(newtask);
		
		nt_field = new JTextField(100);
		nt_field.setBounds(200, 90, 120, 25);
		e_pane.add(nt_field);
		
		newstatus = new JLabel("Enter new priority status:");
		newstatus.setBounds(30, 120, 160, 25);
		e_pane.add(newstatus);
		
		np_field = new JTextField(100);
		np_field.setBounds(200, 120, 120, 25);
		e_pane.add(np_field);
		
		change = new JLabel("");
		change.setBounds(120, 210, 170, 25);
		e_pane.add(change);
		
		e_pane.add(submit(e_pane));
		e_pane.add(reset(e_pane));
		
		this.frame.add(e_pane);
		this.frame.validate();
	}
	
	public void print(JPanel p_pane) {
		
		System.out.println("------------ List ------------");
		Task list = main.getList();
		int index = 1;
		
		if (list == null) {
			System.out.println("EMPTY!");
			return;
		}
		
		while (list != null) {
			System.out.printf("%d.) %s\nPriority Status: %d\n\n", index++, list.assignment, list.status);
			list = list.next;
		}
		System.out.println("------------------------------");
		
		p_pane.add(reset(p_pane));
		this.frame.add(p_pane);
		this.frame.validate();
	}
	
	public void sort(JPanel s_pane) {
		
		Task temp, begin = main.getList();
		
		temp = begin;
		while (temp.next != null)
			temp = temp.next;
		
		main.sort(begin, temp);
		print(s_pane);
	}
	
	public JButton reset(JPanel pane) {
		
		 reset = new JButton("Return to Menu");
		 reset.setBounds(120, 180, 125, 25);
		 reset.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 
				 // remove "command" panel
				 frame.remove(pane);
				 
				 // add menu panel back to frame
				 frame.add(menu());
				 frame.validate();
			 }
		 });
		 
		 return reset;
	}
	
	public JButton submit(JPanel pane) {
		
		submit = new JButton("Submit");
		submit.setBounds(150, 150, 70, 25);
		submit.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				
				// "add"
				if (pane == a_pane) {
					String task_a = t_field.getText();
					int status = Integer.parseInt(p_field.getText());
					main.add(task_a, status);
					complete.setText("Task added to List!");
					
					// clear text fields
					t_field.setText("");
					p_field.setText("");
				}
				// "delete"
				else if (pane == d_pane) {
					String task_d = d_field.getText();
					main.delete(task_d);
					remove.setText("Task removed from List!");
					
					// clear text field
					d_field.setText("");
				}
				// "edit"
				else if (pane == e_pane) {
					String old_t = ot_field.getText();
					String new_t = nt_field.getText();
					int new_s = Integer.parseInt(np_field.getText());
					main.edit(old_t, new_t, new_s);
					change.setText("Task edited!");
					
					// clear text fields
					ot_field.setText("");
					nt_field.setText("");
					np_field.setText("");
				}
			}});
		
		return submit;
	}
}
