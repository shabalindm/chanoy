package chanoy;

import java.util.Iterator;

public class IntervalIterator implements Iterator<Double> {
	
	public IntervalIterator( double from, double to, int steps) {
		this.from = from;
		this.to = to;
		this.steps = steps;
		dx = (to-from)/steps;
		currient = from;
	}
	
	double from;
	double to;
	int steps;
	private final double dx;
	private double currient;  
	private int currientstep = 0;

	@Override
	public boolean hasNext() {
		if (currientstep < steps)
			return true;
		return false;
	}

	@Override
	public Double next() {
		currientstep++;
		return  currient+=dx;
		
	
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
		
		
	}

}
