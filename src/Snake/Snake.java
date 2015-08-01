package Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.sound.sampled.Clip;

public class Snake implements Runnable{
	private LinkedList<Body> list;
	private Graphics2D g2d;
	private int length;
	private int frame;
	private int maxframe;
	private Animation render;
	private Block[][] block;
	private boolean getFood;
	private int foodx=-1,foody=-1;
	private int score=0;
	private Clip ding;
	
	public Snake(Graphics2D g,Block[][] block)
	{
		this.block=block;
		render=Resource.am;
		ding=Resource.ding;
		maxframe=5;
		g2d=g;
		frame=0;
		length=Resource.length;
		list=new LinkedList<Body>();
		list.removeAll(list);
		Body[] body=new Body[3];
		for (int i=0;i<3;i++)
		{
			body[i]=new Body(i*Resource.length,Resource.lengthBar);
			list.add(body[i]);
		}
	}
	public void move()
	{
		if (frame==0)
		{
			if (getFood==true)
			{
				if (foodx!=-1&&foody!=-1)
				{
					block[foodx][foody].gotoAndStop(1);
					Body body=new Body(foodx*length,foody*length+Resource.lengthBar);
					list.addFirst(body);
					foodx=-1;
					foody=-1;
				}
			}
			for (int i=0;i<list.size();i++)
			{
				if (i!=list.size()-1)
				{
					list.get(i).direction=list.get(i+1).direction;
					//list.get(i).direction=StateControl.command;
				} else
				{
					if (StateControl.command=="right"&&list.get(i).direction!="left")
						list.get(i).direction=StateControl.command;
					if (StateControl.command=="left"&&list.get(i).direction!="right")
						list.get(i).direction=StateControl.command;
					if (StateControl.command=="up"&&list.get(i).direction!="down")
						list.get(i).direction=StateControl.command;
					if (StateControl.command=="down"&&list.get(i).direction!="up")
						list.get(i).direction=StateControl.command;
				}
			}
			for (int i=0;i<list.size();i++)
			{
				int x=list.get(i).x/length;
				int y=(list.get(i).y-Resource.lengthBar)/length;
				block[x][y].gotoAndStop(2);
			}
			getFood=bump();
			for (int i=0;i<list.size();i++)
			{
				int x=list.get(i).x/length;
				int y=(list.get(i).y-Resource.lengthBar)/length;
				block[x][y].gotoAndStop(1);
			}
			if (getFood==true)
			{
				score+=10;
				g2d.setColor(Color.black);
				g2d.setFont(new Font("Courier New",1,20));
				g2d.clearRect(0, 0, 600, 50);
				g2d.setColor(Resource.snakeColor);
				g2d.drawString("score",20,30);
				g2d.drawString(Integer.toString(score), 100, 30);
				g2d.drawString("made by nightelfel", 300, 30);
				if (ding.isRunning())
				{
					ding.stop();
				}
				ding.setFramePosition(0);
				ding.start();
				foodx=list.get(0).x/length;
				foody=(list.get(0).y-Resource.lengthBar)/length;
				block[foodx][foody].gotoAndStop(2);
			} 
			if (StateControl.state=="gameover")
				return;
		}
		if (score==100)
		{
			StateControl.state="gamewin";
				return;
		}
		frame++;
		if (frame==maxframe)
			frame=0;
		for (int i=0;i<list.size();i++)
		{
			switch (list.get(i).direction)
			{
			case "up":
				list.get(i).moveUp();
				break;
			case "down":
				list.get(i).moveDown();
				break;
			case "right":
				list.get(i).moveRight();
				break;
			case "left":
				list.get(i).moveLeft();
				break;
			}
		}
	}
	public Boolean bump()
	{
		Body body;
		body=list.get(list.size()-1);
		int x=body.x/length;
		int y=(body.y-Resource.lengthBar)/length;
		if (body.direction=="right")
		{
			if (x+1>19)
			{
				StateControl.state="gameover";
				return(false);
			} else
			{
				if (block[x+1][y].frame==4||block[x+1][y].frame==2)
				{
					StateControl.state="gameover";
					return(false);
				}
			}
			if (block[x+1][y].frame==3)
			{
				block[x+1][y].gotoAndStop(1);
				return(true);
			}
		}
		if (body.direction=="left")
		{
			if (x-1<0)
			{
				StateControl.state="gameover";
				return(false);
			} else
			{
				if (block[x-1][y].frame==4||block[x-1][y].frame==2)
				{
					StateControl.state="gameover";
					return(false);
				}
			}
			if (block[x-1][y].frame==3)
			{
				block[x-1][y].gotoAndStop(1);
				return(true);
			}
		}
		if (body.direction=="up")
		{
			if (y-1<0)
			{
				StateControl.state="gameover";
				return(false);
			} else
			{
				if (block[x][y-1].frame==4||block[x][y-1].frame==2)
				{
					StateControl.state="gameover";
					return(false);
				}
			}
			if (block[x][y-1].frame==3)
			{
				block[x][y-1].gotoAndStop(1);
				return(true);
			}
		}
		if (body.direction=="down")
		{
			if (y+1>14)
			{
				StateControl.state="gameover";
				return(false);
			} else
			{
				if (block[x][y+1].frame==4||block[x][y+1].frame==2)
				{
					StateControl.state="gameover";
					return(false);
				}
			}
			if (block[x][y+1].frame==3)
			{
				block[x][y+1].gotoAndStop(1);
				return(true);
			}
		}
		return(false);
	}
	public void run()
	{
		while (true)
		{
			move();
			refresh();
			render.repaint();
			if (StateControl.state=="gameover"||StateControl.state=="gamewin")
			{
				break;
			}
			try {
				Thread.sleep(Resource.timeInterval/maxframe);
				//Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (StateControl.state=="gameover")
		{
			g2d.setColor(new Color(40,40,40,180));
			g2d.fillRect(0, 0, Resource.width, Resource.height);
			g2d.setColor(new Color(0,180,0));
			g2d.drawString("your score is "+score, 100, 200);
			g2d.drawString("press space to play again", 100, 250);
			render.repaint();
		} else if (StateControl.state=="gamewin")
		{
			g2d.setColor(new Color(40,40,40,180));
			g2d.fillRect(0, 0, Resource.width, Resource.height);
			g2d.setColor(new Color(0,180,0));
			g2d.drawString("you did it!", 100, 200);
			g2d.drawString("press space to go to next level", 100, 250);
			render.repaint();
		}
	}
	public void refresh()
	{
		for (int i=0;i<Resource.row;i++)
		{
			for (int j=0;j<Resource.colum;j++)
			{
				block[i][j].gotoAndStop(block[i][j].frame);
			}
		}
		for (int i=0;i<list.size();i++)
		{
			Body body=list.get(i);
			//g2d.clearRect(body.x, body.y, length, length);
			g2d.setColor(Resource.snakeColor);
			g2d.fillRoundRect(body.x+1, body.y+1, length-2, length-2, Resource.arc, Resource.arc);
		}
		for (int i=0;i<list.size()-1;i++)
		{
			if (list.get(i+1).direction!=list.get(i).direction)
			{
				int x=0,y=0;
				Body b1,b2;
				b1=list.get(i);
				b2=list.get(i+1);
				if (b1.direction=="right"||b1.direction=="left")
				{
					y=b1.y;
					x=b2.x;
				} else
				{
					y=b2.y;
					x=b1.x;
				}
				g2d.fillRoundRect(x+1, y+1, length-2, length-2, Resource.arc, Resource.arc);
			}
		}
	}
}
class Body
{
	int length;
	int x;
	int y;
	Color color;
	int maxframe;
	public String direction;
	public Body(int a, int b)
	{
		direction="right";
		maxframe=5;
		x=a;
		y=b;
		color=Resource.snakeColor;
		length=Resource.length;
	}
	public void moveRight()
	{
		x+=length/maxframe;
	}
	public void moveLeft()
	{
		x-=length/maxframe;
	}
	public void moveUp()
	{
		y-=length/maxframe;
	}
	public void moveDown()
	{
		y+=length/maxframe;
	}
}
