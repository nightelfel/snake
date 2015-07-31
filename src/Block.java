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
		if (a==1)//original
		{
			g2d.setColor(new Color(60,60,60)); 
			g2d.clearRect(x, y, length, length);
			g2d.fillRoundRect(x+1, y+1, length-2, length-2, 10, 10);
		} else if (a==2)//snake
		{
			g2d.setColor(Resource.snakeColor);
			g2d.clearRect(x, y, length, length);
			g2d.fillRoundRect(x+1, y+1, length-2, length-2, 10, 10);
		} else if (a==3)//food
		{
			g2d.setColor(new Color(205,85,85));
			g2d.clearRect(x, y, length, length);
			g2d.fillRoundRect(x+1, y+1, length-2, length-2, 10, 10);
		} else if (a==4) //block
		{
			g2d.clearRect(x,y,length,length);
			g2d.drawImage(Resource.block,x+1,y+1,length-2,length-2,Snake.am);
		}
	}
}
