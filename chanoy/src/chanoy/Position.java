package chanoy;

public enum Position {
	A(1.0/4), B(2.0/4), C(3.0/4);
	
	double x;

	private Position(double x) {
		this.x = x;
	}
	
}
