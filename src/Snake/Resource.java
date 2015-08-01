package Snake;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Resource {
	public static int width;
	public static int height;
	public static int row;
	public static int colum;
	public static int timeInterval;
	public static int length;
	public static int lengthBar;
	public static int arc;
	
	
	public static BufferedImage img;
	public static BufferedImage block;
	public static BufferedImage food;
	public static BufferedImage head;
	
	public static Lock lock;
	
	public static Clip ding;
	public static Animation am;
	public static Color snakeColor;
	public static BufferedImage Icon;
	
	
	public static void load(Main o) throws IOException
	{	
		//integer
		lock=new ReentrantLock();
		length=30;
		timeInterval=50;
		width=600;
		height=500;
		row=20;
		colum=15;
		lengthBar=50;
		arc=10;
		
		//color
		snakeColor=new Color(0,180,0);
		
		
		//image
		Icon=ImageIO.read(o.getClass().getResourceAsStream("/snake.png"));
		block=ImageIO.read(o.getClass().getResourceAsStream("/block.png"));
		food=ImageIO.read(o.getClass().getResourceAsStream("/food.png"));
		head=ImageIO.read(o.getClass().getResourceAsStream("/head.png"));
		
		//sound
		try {
			ding=AudioSystem.getClip();
			ding.open(AudioSystem.getAudioInputStream(o.getClass().getResource("/dint.wav")));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Clip clip=AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(o.getClass().getResource("/music.wav")));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
}
