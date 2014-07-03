package chanoy;
import java.awt.EventQueue;

import javax.swing.JFrame;



public class Main {

	public static void main(String[] args) {
		Runnable windowThread = new Runnable () {			
			@Override
			public void run() {
				JFrame frame = new ChanoyFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
			}//run
		}; //new Runnable ()
		
		EventQueue.invokeLater(windowThread);
	}//main

}
