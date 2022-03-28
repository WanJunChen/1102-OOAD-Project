import java.awt.Point;

public class ShapeFactory implements ShapeFactoryInterface
{
	public BasicObj createObj(String objType, Point p) 
	{
		if(objType.equals("Class")){
			return new classObj(p.x, p.y);
		}
		else if(objType.equals("UseCase")){
			return new usecaseObj(p.x, p.y);
		}
		return null;
	}

	public Line createLine(String lineType, Point startP, Point endP) 
	{
		if(lineType.equals("AssociateLine"))
		{
			return new associationLine(startP.x, startP.y, endP.x, endP.y);
		}
		else if(lineType.equals("GeneralLine"))
		{
			return new generalizationLine(startP.x, startP.y, endP.x, endP.y);
		}
		else if(lineType.equals("CompositionLine")) 
		{
			return new compositionLine(startP.x, startP.y, endP.x, endP.y);
		}
		return null;
	}
}
