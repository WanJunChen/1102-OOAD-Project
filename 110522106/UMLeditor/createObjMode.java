import java.awt.event.MouseEvent;

public class createObjMode extends Mode{
	private String objType = null;
	private ShapeFactoryInterface factory = new ShapeFactory();
	public createObjMode(String objType) {// objType is "Class" or "Use Case"
		this.objType = objType;
	}
	public void mousePressed(MouseEvent e) {  // when mouse pressed, create a object on canvas
		BasicObj basicObj = factory.createObj(objType, e.getPoint());
		canvas.addShape(basicObj);
		canvas.repaint();
	}
}
