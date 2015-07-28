import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class StartGame extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	private Graphics2D g2d;
	private int opaque;
	public StartGame()
	{
		img=new BufferedImage(700,450,BufferedImage.TYPE_INT_ARGB);
		g2d=(Graphics2D) img.getGraphics();
		initial();
		this.setLayout(null);
	}
	public void initial()
	{
		opaque=255;
	}
	public void run()
	{
		for (int i=opaque;i>=0;i-=10)
		{
			g2d.drawImage(Resource.jiemian, 0, 0, 700,450,this);
			g2d.setColor(new Color(20,20,20,i));
			g2d.fillRect(0, 0, 700, 450);
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		g2d.setFont(new Font("Courier New",1,20));
		g2d.setColor(new Color(0,180,0));
		g2d.drawString("press space to start the game", 200, 250);
		g2d.drawString("press esc to exit", 250, 300);
		repaint();
	}
	public void paintComponent(Graphics g)
	{
		g.drawImage(img, 0, 0, 700, 450, this);
	}
}
