import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JToolBar
{
	private Canvas canvas;
    private JButton holdBtn = null;

    //Constructor
    public ToolBar() 
    {
        canvas = Canvas.getInstance();
        
        setPreferredSize(new Dimension(100,0)); //Set height to 0 because it doesn't affect
        setLayout(new FlowLayout());
        
        ImageIcon imageIcon = new ImageIcon("../img/select.png");
        ToolBtn Btn = new ToolBtn("select", setBtnImgSize(imageIcon), new selectMode());
        add(Btn);

        imageIcon = new ImageIcon("../img/association line.png");
        Btn = new ToolBtn("select", setBtnImgSize(imageIcon), new createLineMode("AssociateLine"));
        add(Btn);

        imageIcon = new ImageIcon("../img/generalization line.png");
        Btn = new ToolBtn("select", setBtnImgSize(imageIcon), new createLineMode("GeneralLine"));
        add(Btn);

        imageIcon = new ImageIcon("../img/composition line.png");
        Btn = new ToolBtn("select", setBtnImgSize(imageIcon), new createLineMode("CompositionLine"));
        add(Btn);

        imageIcon = new ImageIcon("../img/class.png");
        Btn = new ToolBtn("select", setBtnImgSize(imageIcon), new createObjMode("Class"));
        add(Btn);

        imageIcon = new ImageIcon("../img/use case.png");
        Btn = new ToolBtn("select", setBtnImgSize(imageIcon), new createObjMode("UseCase"));
        add(Btn);

    }
    private class ToolBtn extends JButton
    {
        Mode ToolMode;
        public ToolBtn(String ToolName, ImageIcon icon, Mode ToolMode) 
        {
            this.ToolMode = ToolMode;
            setIcon(icon);
            setPreferredSize(new Dimension(100,80));
            setBackground(Color.GRAY);
            addActionListener(new toolListener());
        }
        class toolListener implements ActionListener 
        {
            public void actionPerformed(ActionEvent e) 
            {
				if(holdBtn != null)
					holdBtn.setBackground(Color.GRAY);
				holdBtn = (JButton) e.getSource();
				holdBtn.setBackground(Color.BLACK);
				canvas.currentMode = ToolMode;
				canvas.setCurrentMode();
                
				canvas.reset();
				canvas.repaint();
			}
		 }
    }
    public ImageIcon setBtnImgSize(ImageIcon imageIcon)
    {
        Image image = imageIcon.getImage().getScaledInstance(70, 56,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        return imageIcon;
    }
}
