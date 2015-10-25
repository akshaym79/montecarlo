package simulation;

import java.util.Arrays;
import java.util.Collection;

/**
 * An instance of this class represent the results of a simulation.
 * If the simulation failed for some reason, the hasExceptions flag will return true
 * and the exceptions will be available as a Collection.
 * If the simulation was successful, the data can be queried for any valid percentile values.
 * 
 * @author Akshay More
 */
public class SimulationResults implements ISimulationResults {

	private double[] rawData;
	private boolean hasExceptions;
	private Collection<Throwable> exceptions;
	
	public SimulationResults(double[] rawData, boolean hasExceptions, Collection<Throwable> exceptions) {
		this.rawData = rawData;
		Arrays.sort(this.rawData);
	}
	
	@Override
	public double getPercentile(int nth) {
		if (hasExceptions) return Double.NaN;
		if (nth < 0 || nth > 100) return Double.NaN;
		if (rawData.length < (100/nth)) return Double.NaN;
		int rank = (int)Math.ceil((nth/100.0) * rawData.length);
		return rawData[rank-1];
	}

	@Override
	public boolean hasExceptions() {
		return hasExceptions;
	}

	@Override
	public Collection<Throwable> getExceptions() {
		return exceptions;
	}

}
