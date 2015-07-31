package Snake;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;


public class Main {
	public Main()
	{
		try {
			Resource.load(this);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JFrame window=new JFrame("Snake");
		window.getContentPane().setPreferredSize(new Dimension(Resource.width,Resource.height));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setUndecorated(true);
		window.setLocation(200, 200);
		window.setOpacity(0.9f);
		window.getContentPane().setLayout(null);
		window.setIconImage(Resource.Icon);
		
		Resource.am=new Animation();
		Resource.am.setBounds(0,0,Resource.width,Resource.height);
		window.getContentPane().add(Resource.am);
		
		new StateControl(window).startGame();
		
		window.pack();
		window.setVisible(true);
	}
	public static void main(String[] args)
	{
		new Main();
	}
}
