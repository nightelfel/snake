import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class StartGame extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	private Graphics2D g2d;
	private String buffer;
	private int timeInterval;
	public StartGame()
	{
		img=new BufferedImage(600,500,BufferedImage.TYPE_INT_ARGB);
		g2d=(Graphics2D) img.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		initial();
		this.setLayout(null);
	}
	public void initial()
	{
		timeInterval=50;
		new Thread(this).start();
	}
	public void run()
	{
		g2d.setFont(new Font("Courier New",1,40));
		g2d.setColor(new Color(40,40,40));
		g2d.fillRect(0, 0, 600, 500);
		
		buffer="Snake";
		g2d.setColor(new Color(0,180,0));
		for (int i=0;i<=buffer.length();i++){
			g2d.drawString(buffer.substring(0,i), 110, 140);
			repaint();
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
			repaint();
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
			repaint();
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
			repaint();
			try {
				Thread.sleep(timeInterval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void paintComponent(Graphics g)
	{
		g.drawImage(img, 0, 0, 600, 500, this);
	}
}
