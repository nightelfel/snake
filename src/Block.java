import java.awt.Color;
import java.awt.Graphics2D;


public class Block {
	public int x;
	public int y;
	public int length;
	private Graphics2D g2d;
	public int frame;
	public Block(int a, int b,Graphics2D d)
	{
		frame=1;
		length=30;
		g2d=d;
		x=a*length;
		y=b*length+50;
	}
	public void gotoAndStop(int a)
	{
		frame=a;
		if (a==1)
		{
			g2d.setColor(new Color(60,60,60));
		} else if (a==2)
		{
			g2d.setColor(new Color(0,139,69));
		} else if (a==3)
		{
			g2d.setColor(new Color(205,85,85));
		}
		g2d.clearRect(x, y, length, length);
		g2d.fillRoundRect(x+1, y+1, length-2, length-2, 10, 10);
	}
}
