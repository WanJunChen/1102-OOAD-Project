import java.awt.Graphics;

public class generalizationLine extends Line
{
	private int arrowW = 10, arrowH = 10;
	public generalizationLine(int x1, int y1, int x2, int y2) 
	{
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

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};
		
        g.fillPolygon(xpoints, ypoints, 3);
	}
}
