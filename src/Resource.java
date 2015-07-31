import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Resource {
	public static BufferedImage Icon;
	public static int width;
	public static int height;
	public static int row;
	public static int colum;
	public static Color snakeColor;
	public static int timeInterval;
	public static Clip ding;
	public static BufferedImage img;
	public static void load(Snake snake) throws IOException
	{	
		timeInterval=100;
		width=600;
		height=500;
		row=20;
		colum=15;
		snakeColor=new Color(0,180,0);
		Icon=ImageIO.read(snake.getClass().getResourceAsStream("/snake.png"));
		
		try {
			ding=AudioSystem.getClip();
			ding.open(AudioSystem.getAudioInputStream(snake.getClass().getResource("/dint.wav")));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Clip clip=AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(snake.getClass().getResource("/music.wav")));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
}
