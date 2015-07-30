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
		new Thread(this).start();
	}
	public void run()
	{
		g2d.setFont(new Font("Courier New",1,40));
		buffer="Snake";
		for (int i=0;i<=buffer.length();i++)
		{
			String sub=buffer.substring(0, i);
			g2d.setColor(new Color(20,20,20));
			g2d.fillRect(0, 0, 600, 500);
			g2d.setColor(new Color(0,180,0));
			g2d.drawString(sub, 140, 150);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
		buffer="press space to start the game";
		for (int i=0;i<=buffer.length();i++)
		{
			String sub=buffer.substring(0, i);
			g2d.setColor(new Color(20,20,20));
			g2d.fillRect(0, 0, 600, 500);
			g2d.setColor(new Color(0,180,0));
			g2d.setFont(new Font("Courier New",1,20));
			g2d.drawString(sub, 150, 200);
			g2d.setFont(new Font("Courier New",1,40));
			g2d.drawString("Snake", 140, 150);
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		buffer="press esc to exit the game";
		for (int i=0;i<=buffer.length();i++)
		{
			String sub=buffer.substring(0, i);
			g2d.setColor(new Color(20,20,20));
			g2d.fillRect(0, 0, 600, 500);
			g2d.setColor(new Color(0,180,0));
			g2d.setFont(new Font("Courier New",1,20));
			g2d.drawString(sub, 150, 250);
			g2d.setFont(new Font("Courier New",1,40));
			g2d.drawString("Snake", 140, 150);
			g2d.setFont(new Font("Courier New",1,20));
			g2d.drawString("press space to start the game", 150, 200);
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		buffer="press p to pause";
		for (int i=0;i<=buffer.length();i++)
		{
			String sub=buffer.substring(0, i);
			g2d.setColor(new Color(20,20,20));
			g2d.fillRect(0, 0, 600, 500);
			g2d.setColor(new Color(0,180,0));
			g2d.setFont(new Font("Courier New",1,20));
			g2d.drawString(sub, 150, 300);
			g2d.setFont(new Font("Courier New",1,40));
			g2d.drawString("Snake", 140, 150);
			g2d.setFont(new Font("Courier New",1,20));
			g2d.drawString("press space to start the game", 150, 200);
			g2d.setFont(new Font("Courier New",1,20));
			g2d.drawString("press esc to exit the game", 150, 250);
			repaint();
			try {
				Thread.sleep(100);
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
