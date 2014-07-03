package chanoy;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class FloorPanel extends JPanel {
	final private float proportion = 0.5f;
	@Override
	protected void paintComponent(Graphics g) {
		int height = this.getHeight();	
		int widht = this.getWidth();		
		g.setColor(new Color(192, 22, 22)); 
		
		for (int i = 0; i<3; i++){
			int size = (int) (widht*proportion/3);
			int pos = (int)((widht*(i+1)/4 - size/2));
			
			g.fillRect(pos, 0,  size, height);
		
		}
		
		
	}
}
