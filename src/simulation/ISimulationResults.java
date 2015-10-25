package simulation;

import java.util.Collection;

/**
 * This interface contains the methods that the results of a Monte-Carlo simulation must implement.
 *
 * @author Akshay More
 */
public interface ISimulationResults {

	double getPercentile(int nth);
	boolean hasExceptions();
	Collection<Throwable> getExceptions();
}
