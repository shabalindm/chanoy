package chanoy;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Plates {
	ChanoyBoard chanoyBoard;
		
	ArrayList<Plate> onA = new ArrayList<Plate>();
	ArrayList<Plate> onB = new ArrayList<Plate>();
	ArrayList<Plate> onC = new ArrayList<Plate>();

	
	/** ������� ����� �������, ��������� ��  � ������ A. 
	 * ������������� ������� ���� �������. �������������, ��� �� ����� ������ ������
	 * onB � onC - �����: � ��������� ������ ������� ������ RuntimeException */ 

	public void addPlate(){
		if (onB.size() > 0 || onB.size() > 0) 
			throw new IllegalStateException();
		Plate p =  new Plate();
		onA.add(p);
		Plate.height = 0.5/(onA.size() + 5);
		for(int i = 0; i< onA.size(); i++ ){
			Plate plate = onA.get(i);
			plate.widht = (1-2.0/3/(onA.size()+1)*i)*0.2;
			plate.x = Position.A.x;//  - plate.widht/2;
			plate.y = 1 - Plate.height*(i+1);
			
		}	
	}//addPlate
	
	public void reset(){
		onA.clear();
		onC.clear();
		onC.clear();
		chanoyBoard.animationLayer.plate = null; 
	}
	
	/** ���������� ���� ������� � ����� ������� �� ������. */
	protected void move(Position from, Position to) {
		//�������, �� ����� ������ (onA, onB) ��o��������� "Position from" , ����� - "Position to"
		List<ArrayList<Plate>> allPlates = Arrays.asList(onA,onB,onC);
		ArrayList<Plate> source = allPlates.get(from.ordinal());
		ArrayList<Plate> dest = allPlates.get(to.ordinal());
		
		Plate  plate = source.remove(source.size()-1); //�������  �������� ������� � �������� ������
		
		chanoyBoard.animationLayer.plate = plate; // �������� �� � ���� ��� ������
		//��������� ���������� x � y  ����������//
		double destY = 1 - (dest.size()+1 )*Plate.height;  
		double destX = to.x; 
		chanoyBoard.repaint();
		plate.move(destX, destY, chanoyBoard.animationLayer); //
		// ������������� ������� �� ����� ������ � ������ � ������.
		chanoyBoard.animationLayer.plate = null; 
		dest.add(plate); 
		chanoyBoard.repaint();
	}
	
	/**����������, ��� �������� ��������� �����*/
	
	void move(Position from, Position to, int count){
		if (count == 1){
			move(from, to);
		} else {
			Position medium = Position.values()[3 - from.ordinal()- to.ordinal()];
			move(from, medium, count - 1);
			move(from, to, 1);
			move(medium, to, count - 1);
			
		}
	}
	
	void move(){
		move(Position.A, Position.C,  onA.size());
	}
	
	
	/** ���������� ������ �������*/

	public void draw(Graphics g){		
		for(Plate plate : onA)	
			plate.draw(g);
		for(Plate plate : onB)	
			plate.draw(g);
		for(Plate plate : onC)	
			plate.draw(g);
	}
	
}