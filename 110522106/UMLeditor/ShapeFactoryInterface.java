import java.awt.Point;

public interface ShapeFactoryInterface 
{
	public BasicObj createObj(String objType, Point p);
	public Line createLine(String lineType, Point startP, Point endP);
}
