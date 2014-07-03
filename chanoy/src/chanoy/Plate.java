package chanoy;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;



 class Plate {
	double widht; // Высота в долях от доступной ширины окна
	static double height; // Ширина в долях от доступной ширины окна
	double  x; //положение центра по оси х в долях от доступной ширины окна
	double  y; //положение верха по оси х в долях от доступной ширины окна
	
	
	/**Рисование тарелки в окне*/
	public  void draw(Graphics g) {
		int h = g.getClipBounds().height;
		int w = g.getClipBounds().width;
		Graphics2D g2D = (Graphics2D)g;
		g2D.setColor(new Color(192, 192, 0 ));
		g2D.fillRoundRect((int)( (x- widht/2)*w ), (int)(y*h), (int)(widht*w), (int)(height*h), 10, 10);
		g2D.setColor(new Color(0, 0, 0 ));
		g2D.drawRoundRect((int)( (x- widht/2)*w ), (int)(y*h), (int)(widht*w), (int)(height*h), 10, 10);
	}
	
			
	void move( double x1, double y1, JComponent panel){ 
		Parabola p = Parabola.getParabolicPath(x, y, x1, y1);
	
		// определение подходящей дискретизации по времени и числа шагов
		int steps = 5*ChanoyFrame.speed/1000;
		int delay = 1000/5;
		
		IntervalIterator iter = new IntervalIterator(x, x1, steps); 
		while (iter.hasNext()){
			if (Thread.currentThread().isInterrupted()){
					try {
						Thread.interrupted(); // очищаем статус треда
						Thread.sleep(100);
						Thread.currentThread().interrupt();	
						continue;
					} catch (InterruptedException e1) {
						//просто продолжаем работу					
					}
			}
			
			this.x = iter.next();
			this.y = p.f(this.x);
			panel.repaint(); 
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			
		}
		
		this.x = x1;
		this.y = y1;
		
	}
	

}

 