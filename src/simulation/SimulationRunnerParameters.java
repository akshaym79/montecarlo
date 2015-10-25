package simulation;

public class SimulationRunnerParameters {

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
