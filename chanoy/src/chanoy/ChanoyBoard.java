package chanoy;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class ChanoyBoard extends JComponent {
	public Plates plates; //= new Plates();
	public AnimationLayer animationLayer;  // Прозрачное поле, в котором будет происходить полет
	
	static class AnimationLayer extends JComponent{
		public Plate plate;
		
		@Override
		protected void paintComponent(Graphics g) {
			if (plate == null) return;
			plate.draw(g);
			
			
		}}; // AnimationLayer

		
	public ChanoyBoard(Plates plates) {
		this.plates = plates;
		animationLayer = new AnimationLayer();
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		add(animationLayer, BorderLayout.CENTER);
	
		
	}



	@Override
	protected void paintComponent(Graphics g) {
	//	plates.addPlate();
		plates.draw(g);
	//	plates.onA.get(0).erase(g);
	}
	
}


