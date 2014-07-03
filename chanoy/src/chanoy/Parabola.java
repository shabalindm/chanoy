package chanoy;

public class Parabola {
	final double  a, b, c;
	
	public Parabola(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	double f(double x){
		return a*x*x + b*x + c;
	}

	public static Parabola getParabolicPath(double x1, double y1, double x2, double y2 ){
		double d = 0.4;
		double P = (y1-y2)/(x1-x2);
		
		double alpha = x1*x1 + (x1+x2)*(x1+x2)/4.0 - (x1+x2)*x1;
		double beta = P*x1 + d - P*(x1+x2)/2 - y1;
		double gamma = P*P/4;
		double D = Math.sqrt( beta*beta - 4*gamma*alpha);
		
		
		double a = (-beta + D)/(2*alpha);
		double b = P -  a*(x1+x2);
		double c = d + b*b/(4*a);
				
		return new Parabola(a, b, c) ;
		}
}
