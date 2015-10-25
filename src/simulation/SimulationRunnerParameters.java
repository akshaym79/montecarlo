package simulation;

/**
 * An instance of this class contains the parameters required to run a Monte-Carlo simulation.
 * These paramters are:
 * 1. Number of iterations to perform.
 * 2. Number of threads in the pool for executing iterations.
 * 3. Number of threads in the pool for performing simulations (of different portfolios).
 * 4. Timeout (in milliseconds) to wait for the result of an iteration.
 * The last three parameters are operational parameters and do not affect the results or accuracy 
 * of the simulation. They should be used to optimize the performance of the application.
 * 
 * @author Akshay More
 */
public final class SimulationRunnerParameters {

	private final int numberOfIterations;
	private final int numberOfIterationThreads;
	private final int numberOfSimulationThreads;
	private final long fetchIterationResultTimeout;
	
	public SimulationRunnerParameters(
			int numberOfIterations, 
			int numberOfIterationThreads, 
			int numberOfSimulationThreads, 
			long fetchIterationResultTimeout) {
		
		this.numberOfIterations = numberOfIterations;
		this.numberOfIterationThreads = numberOfIterationThreads;
		this.numberOfSimulationThreads = numberOfSimulationThreads;
		this.fetchIterationResultTimeout = fetchIterationResultTimeout;
	}

	public int getNumberOfIterations() {
		return numberOfIterations;
	}

	public int getNumberOfIterationThreads() {
		return numberOfIterationThreads;
	}

	public int getNumberOfSimulationThreads() {
		return numberOfSimulationThreads;
	}

	public long getFetchIterationResultTimeout() {
		return fetchIterationResultTimeout;
	}
	
}
