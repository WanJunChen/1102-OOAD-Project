import java.awt.Graphics;

public class associationLine extends Line
{
	private int arrowW = 10, arrowH = 10;
	public associationLine(int x1, int y1, int x2, int y2)
	{// coordinate of two points
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public void draw(Graphics g) 
	{
		g.drawLine(x1, y1, x2, y2);

		int dx = x2 - x1; // width 
		int dy = y2 - y1; // height
		double D = Math.sqrt(dx*dx + dy*dy);  //the square root, calculate the length of line

		//draw triangle as arrow
		double xm = D - arrowW;
		double xn = xm;
		double ym = arrowH;
		double yn = -arrowH;
		double x;

		double sin = dy/D;
		double cos = dx/D;
		
		x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

		g.drawLine(x2, y2, (int)Math.round(xm), (int)Math.round(ym));
		g.drawLine(x2, y2, (int)Math.round(xn), (int)Math.round(yn));
	}

	
}