package simulation;

import java.util.Collection;

public interface ISimulationResults {

	double getPercentile(int nth);
	boolean hasExceptions();
	Collection<Throwable> getExceptions();
}
