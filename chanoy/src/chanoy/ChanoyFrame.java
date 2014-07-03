package chanoy;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class ChanoyFrame extends JFrame {
	final int DEFAULT_WIDTH = 550;
	final int DEFAULT_HIGHT = 500;
	static int speed =  2000; // Время пролета одной тарелки.
	private final String title = "Ханойские башни";
	
	public JButton addButton = new JButton("add");
	public JButton startButton = new JButton("start");
	public JButton resetButton = new JButton("reset");
	
	public Plates plates = new Plates();
	
	Thread  animationThread;
	private enum Status {READY, RUNNING, PAUSED}; 
	Status status = Status.READY;
	
	class AnimationThread extends Thread  {
		@Override
		public void run() {
			addButton.setEnabled(false);
			startButton.setText("pause");
			plates.move();
			startButton.setEnabled(false); 
			
		}
	} // AnimationThread
	
	
	private ActionListener addButtonListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			startButton.setEnabled(true);
			plates.addPlate();
			repaint();
			
		}};
		
		/** Запускает движение тарелок в предназначенном для этого треде*/
	private ActionListener startButtonListener = new ActionListener(){
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (status == Status.READY)	{
				status = Status.RUNNING;
				startButton.setText("pause");
				animationThread = new AnimationThread();
				animationThread.start();
				}
			else if (status == Status.RUNNING) { 
				status = Status.PAUSED;
				startButton.setText("continue");
				
				// Тред, в котором двигаются тарелки,
				//работает как переключаетель, на каждый вызов interrupt() он либо оставливает/возобновляет движение,
				animationThread.interrupt();
			}
			else if (status == Status.PAUSED) { 
				status = Status.RUNNING;
				startButton.setText("pause");
				
				// Тред, в котором двигаются тарелки,
				//работает как переключаетель, на каждый вызов interrupt() он либо оставливает/возобновляет движение,
				animationThread.interrupt();
			}
		}
	};

	private ActionListener resetButtonListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			addButton.setEnabled(true);	
			startButton.setEnabled(false);
			startButton.setText("start");
			if(status == Status.RUNNING)
				animationThread.interrupt();  
			plates.reset();
			status = Status.READY;
			repaint();
		
		}};
		

		
	
	public ChanoyFrame(){
		addButton = new JButton("add");
		startButton = new JButton("start");
		
		//задаем параметры окна
		setSize(DEFAULT_WIDTH, DEFAULT_HIGHT);
		setResizable(true);
		setTitle(title );
		this.setLocationByPlatform(true);
		
		Container c = this.getContentPane();
		BorderLayout layout = new BorderLayout();
		c.setLayout(layout);
		
		// Cконструируем и настроем панель с кнопками
		JPanel bottonpanel = new JPanel();
		c.add(bottonpanel, BorderLayout.NORTH);
		
		bottonpanel.add(addButton);
		bottonpanel.add(startButton);
		startButton.setEnabled(false);
		bottonpanel.add(resetButton);
		addButton.addActionListener(addButtonListener);
		startButton.addActionListener(startButtonListener);
		resetButton.addActionListener(resetButtonListener);
		

		ChanoyBoard chanoyBoard = new ChanoyBoard(plates);
		c.add(chanoyBoard, BorderLayout.CENTER);
		plates.chanoyBoard = chanoyBoard; 
		
		
				
		
		JPanel floorpanel = new FloorPanel();
		c.add(floorpanel , BorderLayout.SOUTH);
		
		
		
	}
}

