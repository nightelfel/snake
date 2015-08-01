package Snake;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;


public class StateControl {
	public static String state;
	private JFrame window;
	private KeyAdapter keyStart;
	private KeyAdapter keySystem;
	private KeyAdapter keyProcess;
	public static String command;
	public static String oldcommand;
	
	public StateControl(JFrame w)
	{
		window=w;
		keySystem=new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
					System.exit(0);
			}
		};
		keyStart=new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode()==KeyEvent.VK_SPACE)
				{
					processGame();
					window.addKeyListener(keyProcess);
					window.removeKeyListener(keyStart);
				}
			}
		};
		keyProcess=new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode()==KeyEvent.VK_RIGHT)
				{
					if (command!="null")
						command="right";
				}
				if (e.getKeyCode()==KeyEvent.VK_LEFT)
				{
					if (command!="null")
						command="left";
				}
				if (e.getKeyCode()==KeyEvent.VK_UP)
				{
					if (command!="null")
						command="up";
				}
				if (e.getKeyCode()==KeyEvent.VK_DOWN)
				{
					if (command!="null")	
						command="down";
				}
				if (e.getKeyCode()==KeyEvent.VK_SPACE)
				{
					if (state.equals("gameover")==true||state.equals("gamewin")==true)
					{
						processGame();
					} else if (state=="pause")
					{
							state="process";
					} else
					{
							state="pause";
					}
				}
			}
		};
	}
	public void startGame()
	{
		state="welcome";
		window.addKeyListener(keySystem);
		window.addKeyListener(keyStart);
		new StartGame();
	}
	
	public void processGame()
	{
		state="process";
		oldcommand="right";
		command="right";
		
		Board board=new Board();
		board.initial();
	}
}
