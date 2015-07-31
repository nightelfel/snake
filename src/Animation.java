import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class Animation extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	public void setStage(BufferedImage bm)
	{
		img=bm;
	}
	public void paintComponent(Graphics g)
	{
		g.drawImage(img,0,0,Resource.width,Resource.height,this);
	}
}
