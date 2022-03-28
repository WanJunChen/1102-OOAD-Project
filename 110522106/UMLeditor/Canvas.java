import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import javax.swing.JPanel;

public class Canvas extends JPanel 
{
	private static Canvas instance = null; 
	
	private EventListener listener = null;
	protected Mode currentMode = null;

	private List<Shape> shapes = new ArrayList<Shape>();

	public Shape tempLine = null;
	public Rectangle SelectedArea = new Rectangle();
	public Shape selectedObj = null;

	private Canvas() 
	{
		setPreferredSize(new Dimension(680,0));
		setLayout(new FlowLayout(1));
	}

	public static Canvas getInstance() 
	{
		if (instance == null)
		{
			instance = new Canvas();
		}
		return instance;
	}

	public void setCurrentMode() 
	{
		removeMouseListener((MouseListener) listener);
		removeMouseMotionListener((MouseMotionListener) listener);
		listener = currentMode;
		// System.out.println("Now listener is "+listener);
		addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
	}
	
	public void reset() 
	{
		if(selectedObj != null)
		{
			selectedObj.resetSelectedShape();   // for selected shape inside the group
			selectedObj = null;
		}
		SelectedArea.setBounds(0, 0, 0, 0);
	}
	
	public void addShape(Shape shape) 
	{
		shapes.add(shape);
	}
	
	public List<Shape> getShapeList() 
	{
		return this.shapes;
	}

	public void GroupShape() 
	{
		Group group = new Group();
		for (int i = 0; i < shapes.size(); i++) 
		{
			Shape shape = shapes.get(i);
			if (shape.group_selected) 
			{
				group.addShapes(shape);
				shapes.remove(i);
				i--;
			}
		}
		group.setBounds();
		shapes.add(group);
	}
	public void removeGroup() 
	{
		Group group = (Group) selectedObj;
		List<Shape> groupShapes = group.getShapes();
		for(int i = 0; i < groupShapes.size(); i++)
		{
			Shape shape = groupShapes.get(i);
			shapes.add(shape);
		}
		shapes.remove(selectedObj);
	}

	public void changeName(String name) 
	{
		if(selectedObj != null)
		{
			selectedObj.changeName(name);
			repaint();
		}
	}

	private boolean checkSelectedArea(Shape shape) 
	{
		Point upperleft = new Point(shape.getX1(), shape.getY1());
		Point lowerright = new Point(shape.getX2(), shape.getY2());

		//  When select object, show the 4 ports
		if (SelectedArea.contains(upperleft) && SelectedArea.contains(lowerright)) 
		{
			return true;
		}
		return false;
	}

	public void paint(Graphics g) 
	{
		Dimension dim = getSize(); // get the size of current windows 
		g.setColor(new Color(54, 68, 87));
		g.fillRect(0, 0, dim.width, dim.height);
		g.setColor(Color.WHITE);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		
		// paint all objects in shapes
		for (int i = shapes.size() - 1; i >= 0; i--) 
		{
			Shape shape = shapes.get(i);
			shape.draw(g);
			shape.group_selected = false;
			// check group select
			if (!SelectedArea.isEmpty() && checkSelectedArea(shape)) 
			{
				shape.show(g);
				shape.group_selected = true;
			}
			
		}

		// In the process of drawing a line 
		// (when the mouse is not released),
		// draw a line that follows the mouse
		if (tempLine != null) 
		{
			tempLine.draw(g);
		}
		// show ports when object is selected
		if (this.selectedObj != null) 
		{
			selectedObj.show(g);
		}
		// paint area of group selection
		if (!SelectedArea.isEmpty()) 
		{
			int alpha = 85; // 33% transparent
			g.setColor(new Color(37, 148, 216, alpha));
			g.fillRect(SelectedArea.x, SelectedArea.y, SelectedArea.width, SelectedArea.height);
			g.setColor(new Color(37, 148, 216));
			g.drawRect(SelectedArea.x, SelectedArea.y, SelectedArea.width, SelectedArea.height);

		}
	}
}
