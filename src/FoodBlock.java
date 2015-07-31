import java.util.Random;


public class FoodBlock {
	private Block[][] block;
	private Random rand;
	public FoodBlock(Block[][] b)
	{
		block=b;
		rand=new Random();
	}
	public void initial()
	{
		for (int i=0;i<10;i++)
		{
			generateFood();
		}
		for (int i=0;i<20;i++)
		{
			generateBlock();
		}
	}
	public void generateFood()
	{
		Boolean taken=true;
		int x=0,y=0;
		while (taken)
		{
			x=rand.nextInt(Resource.row);
			y=rand.nextInt(Resource.colum);
			if (block[x][y].frame==1)
			{
				taken=false;
				break;
			}
		}
		block[x][y].gotoAndStop(3);
	}
	public void generateBlock()
	{
		boolean taken=true;
		int x=0,y=0;
		while (taken)
		{
			x=rand.nextInt(Resource.row);
			y=rand.nextInt(Resource.colum);
			if (block[x][y].frame==1)
			{
				taken=false;
				break;
			}
		}
		block[x][y].gotoAndStop(4);
	}
}
