import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;


public class Board extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Block[][] block;
	private Graphics2D g2d;
	private BufferedImage img;
	private LinkedList<Point> list;
	public String direction;
	public String oldDirection;
	public boolean gameOver;
	public boolean hasfood;
	private Random rand;
	private int score;
	private Clip clip;
	public Board()
	{
		rand=new Random();
		list=new LinkedList<Point>();
		img=new BufferedImage(600,500,BufferedImage.TYPE_INT_ARGB);
		g2d=(Graphics2D) img.getGraphics();
		block=new Block[20][15];
		g2d.setBackground(new Color(0,0,0));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (int i=0;i<20;i++)
		{
			for (int j=0;j<15;j++)
			{
				block[i][j]=new Block(i,j,g2d);
			}
		}
		new Thread(this).start();
	}
	public void initial()
	{
		try {
			clip=AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(getClass().getResource("/dint.wav")));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		score=0;
		hasfood=false;
		gameOver=false;
		direction="right";
		oldDirection="right";
		list.removeAll(list);
		g2d.setColor(new Color(0,0,0));
		g2d.setFont(new Font("Courier New",1,20));
		g2d.fillRect(0, 0, 600, 50);
		g2d.setColor(new Color(0,180,0));
		g2d.drawString("score",20,30);
		g2d.drawString(Integer.toString(score), 100, 30);
		g2d.drawString("made by nightelfel", 300, 30);
		for (int i=0;i<20;i++)
		{
			for (int j=0;j<15;j++)
			{
				block[i][j].gotoAndStop(1);
			}
		}
		list.add(new Point(0,0));
		list.add(new Point(1,0));
		list.add(new Point(2,0));
		for (int i=0;i<list.size();i++)
		{
			Point temp;
			temp=list.get(i);
			block[temp.x][temp.y].gotoAndStop(2);
		}
		repaint();
	}
	public void generateFood()
	{
		boolean taken=true;
		int x=0;
		int y=0;
		while (taken)
		{
			x=rand.nextInt(20);
			y=rand.nextInt(15);
			if (block[x][y].frame==1)
			{
				taken=false;
			}
		}
		block[x][y].gotoAndStop(3);
	}
	public void move()
	{
		if (direction.equals("null")==false)
		{
			if (hasfood==false)
			{
				generateFood();
				hasfood=true;
			}
			Point t=null;
			Point c=list.getLast();
			if (direction=="right")
			{
				t=new Point(c.x+1,c.y);
			}
			if (direction=="left")
			{
				t=new Point(c.x-1,c.y);
			}
			if (direction=="up")
			{
				t=new Point(c.x,c.y-1);
			}
			if (direction=="down")
			{
				t=new Point(c.x,c.y+1);
			}
			if (t!=null)
			{
				if (t.x<0||t.x>19||t.y<0||t.y>14)
				{
					Snake.state="gameover";
					gameOver=true;
					return;
				} else if (block[t.x][t.y].frame==2)
				{
					Snake.state="gameover";
					gameOver=true;
					return;
				}
				list.add(t);
				if (block[t.x][t.y].frame==3)
				{
					if (clip.isRunning())
					{
						clip.stop();
					}
					clip.setFramePosition(0);
					clip.start();
					hasfood=false;
					score+=10;
					g2d.setColor(new Color(0,0,0));
					g2d.setFont(new Font("Courier New",1,20));
					g2d.fillRect(0, 0, 600, 50);
					g2d.setColor(new Color(0,180,0));
					g2d.drawString("score",20,30);
					g2d.drawString(Integer.toString(score), 100, 30);
					g2d.drawString("made by nightelfel", 300, 30);
				} else
				{
					c=list.poll();
					block[c.x][c.y].gotoAndStop(1);
				}
				block[t.x][t.y].gotoAndStop(2);
				repaint();
			}
			oldDirection=new String(direction);
		} else
		{
			return;
		}
	}
	public void run()
	{
		initial();
		while (true)
		{
			move();
			if (gameOver==true)
			{
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		g2d.setColor(new Color(40,40,40,180));
		g2d.fillRect(0, 0, 600, 500);
		g2d.setColor(new Color(0,180,0));
		g2d.drawString("your score is "+score, 100, 200);
		g2d.drawString("press space to play again", 100, 250);
		this.repaint();
	}
	public void paintComponent(Graphics g)
	{
		g.drawImage(img, 0, 0, 600,500,this);
	}
}
