import java.awt.*;
import javax.swing.*;
 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuBar extends JMenuBar
{
    private Canvas canvas;
	public MenuBar() 
	{
		canvas = Canvas.getInstance();   // Canvas is singleton 
		
		JMenu menu;
		JMenuItem mi;

		// --- File menu --- 
		menu = new JMenu("File");
		add(menu);

		mi = new JMenuItem("Clean Canvas");
		menu.add(mi);
		mi.addActionListener(new CleanListener());

		// --- Edit menu --- 
		menu = new JMenu("Edit");
		add(menu);
		
		mi = new JMenuItem("Change object name");
		menu.add(mi);
		mi.addActionListener(new ChangeNameListener());
		
		mi = new JMenuItem("Group");
		menu.add(mi);
		mi.addActionListener(new GroupObjectListener());
		
		mi = new JMenuItem("Ungroup");
		menu.add(mi);
		mi.addActionListener(new UngroupObjectListener());
	}
	
	private void changeNameForm() 
	{
		JFrame inputTextFrame = new JFrame("Change Object Name");
		inputTextFrame.setSize(400, 100);
		inputTextFrame.getContentPane().setLayout(new GridLayout(0, 1));
		
		JPanel panel = null;
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JTextField Text =  new JTextField("Object Name");
		panel.add(Text);
		inputTextFrame.getContentPane().add(panel);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JButton OKbtn = new JButton("OK");
		panel.add(OKbtn);
		
		JButton cancelbtn = new JButton("Cancel");
		panel.add(cancelbtn);

		inputTextFrame.getContentPane().add(panel);
		
		inputTextFrame.setLocationRelativeTo(null);
		inputTextFrame.setVisible(true);
		
		
		OKbtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				canvas.changeName(Text.getText());
				inputTextFrame.dispose();
				
			}
		});
		
		cancelbtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				inputTextFrame.dispose();
			}
		});
		
		
	}
	
	class CleanListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			canvas.CleanShape();
		}
	}

	class ChangeNameListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			changeNameForm();
		}
	}
	
	class GroupObjectListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			canvas.GroupShape();
		}
	}

	class UngroupObjectListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			canvas.removeGroup();
		}
	}
	
}
