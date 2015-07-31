import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;


public class StartGame implements Runnable{

	private BufferedImage img;
	private Graphics2D g2d;
	private String buffer;
	private int timeInterval;
	private Animation render;
	public StartGame()
	{
		render=Snake.am;
		img=new BufferedImage(Resource.width,Resource.height,BufferedImage.TYPE_INT_ARGB);
		g2d=(Graphics2D) img.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		initial();
	}
	public void initial()
	{
		render.setStage(img);
		timeInterval=Resource.timeInterval/2;
		new Thread(this).start();
	}
	public void run()
	{
		g2d.setFont(new Font("Courier New",1,40));
		g2d.setColor(new Color(40,40,40));
		g2d.fillRect(0, 0, Resource.width, Resource.height);
		
		buffer="Snake";
		g2d.setColor(Resource.snakeColor);
		for (int i=0;i<=buffer.length();i++){
			g2d.drawString(buffer.substring(0,i), 110, 140);
			if (StateControl.state=="welcome")
				render.repaint();
			try {
				Thread.sleep(timeInterval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(10*timeInterval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2d.setFont(new Font("Courier New",1,20));
		buffer="press space to start the game";
		for (int i=0;i<=buffer.length();i++)
		{
			g2d.drawString(buffer.substring(0,i),60,200);
			if (StateControl.state=="welcome")
				render.repaint();
			try {
				Thread.sleep(timeInterval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(10*timeInterval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buffer="space is also used to pause/unpause the game";
		for (int i=0;i<=buffer.length();i++)
		{
			g2d.drawString(buffer.substring(0,i),60,250);
			if (StateControl.state=="welcome")
				render.repaint();
			try {
				Thread.sleep(timeInterval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(10*timeInterval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buffer="press esc to exit the game";
		for (int i=0;i<=buffer.length();i++)
		{
			g2d.drawString(buffer.substring(0,i),60,300);
			if (StateControl.state=="welcome")
				render.repaint();
			try {
				Thread.sleep(timeInterval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
