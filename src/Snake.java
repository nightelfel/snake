import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;


public class Snake {
	private JFrame window;
	private Board board;
	private StartGame sg;
	private String state;
	public Snake()
	{
		state="welcome";
		window=new JFrame("Snake");
		window.getContentPane().setPreferredSize(new Dimension(600,500));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setUndecorated(true);
		window.setLocation(200, 200);
		window.setOpacity(0.9f);
		window.getContentPane().setLayout(null);
		
		try {
			Clip clip=AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(getClass().getResource("/music.wav")));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			Resource.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		window.setIconImage(Resource.Icon);
		
		sg=new StartGame();
		sg.setBounds(0,0,600,500);
		window.getContentPane().add(sg);
		
		window.pack();
		window.setVisible(true);
		window.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					System.exit(0);
				}
				if (e.getKeyCode()==KeyEvent.VK_RIGHT)
				{
					if (board.oldDirection.equals("left")==false)
						board.direction="right";
				}
				if (e.getKeyCode()==KeyEvent.VK_LEFT)
				{
					if (board.oldDirection.equals("right")==false)
						board.direction="left";
				}
				if (e.getKeyCode()==KeyEvent.VK_UP)
				{
					
					if (board.oldDirection.equals("down")==false)
						board.direction="up";
				}
				if (e.getKeyCode()==KeyEvent.VK_DOWN)
				{
					
					if (board.oldDirection.equals("up")==false)
						board.direction="down";
				}
				if (e.getKeyCode()==KeyEvent.VK_SPACE)
				{
					if (state=="welcome")
					{
						state="process";
						window.getContentPane().remove(sg);
						board=new Board();
						board.setBounds(0,0,600,500);
						window.getContentPane().add(board);
					} else if (board.gameOver==true)
					{
						new Thread(board).start();
					}
				}
			}
		});
	}
	public static void main(String[] args)
	{
		new Snake();
	}
}
