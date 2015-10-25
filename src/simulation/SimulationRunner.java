package simulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import simulation.exception.SimulationException;

/**
 * This class facilitates the running of a Monte-Carlo simulation given a portfolio
 * and the relevant simulation parameters.
 * 
 * @author Akshay More
 */
public class SimulationRunner implements Callable<ISimulationResults> {

	private final Portfolio portfolio;
	private final SimulationIterationParameters iterationParameters;
	private final SimulationRunnerParameters runnerParameters;
	
	public SimulationRunner(
			Portfolio portfolio, 
			SimulationIterationParameters iterationParameters, 
			SimulationRunnerParameters runnerParameters) {
		this.portfolio = portfolio;
		this.iterationParameters = iterationParameters;
		this.runnerParameters = runnerParameters;
	}
	
	/**
	 * Calculates the entire set of projected values for portfolio.
	 * Each iteration is run as a separate thread.
	 */
	@Override
	public ISimulationResults call() throws Exception {
		
		ExecutorService execService = Executors.newFixedThreadPool(runnerParameters.getNumberOfIterationThreads());
		CompletionService<Double> completionService = new ExecutorCompletionService<Double>(execService);
		double[] projectedPortfolioValues = new double[runnerParameters.getNumberOfIterations()];
		Collection<Throwable> exceptions = new ArrayList<Throwable>();
		
		for (int i=0; i<runnerParameters.getNumberOfIterations(); i++) {
			Callable<Double> iteration = new SimulationIteration(portfolio, iterationParameters);
			completionService.submit(iteration);
//			System.out.println("Submitted iteration <" + i + ">");
		}
		
		for (int i=0; i<runnerParameters.getNumberOfIterations(); i++) {
			try {
				Future<Double> nextIterationResult = completionService.poll(
						runnerParameters.getFetchIterationResultTimeout(), 
						TimeUnit.MILLISECONDS
						);
				if (nextIterationResult != null)
					projectedPortfolioValues[i] = nextIterationResult.get();
				else {
					projectedPortfolioValues[i] = Double.NaN;
					exceptions.add(new SimulationException("Timed out waiting for result of iteration."));
				}
			} catch (InterruptedException e) {
				projectedPortfolioValues[i] = Double.NaN;
				exceptions.add(new SimulationException("Simulation was interrupted.", e));
			} catch (ExecutionException e) {
				projectedPortfolioValues[i] = Double.NaN;
				exceptions.add(new SimulationException("Failed to get result of iteration.", e));
			}
//			System.out.println("Done with iteration <" + i + ">");
		}
		
		execService.shutdown();
		return new SimulationResults(projectedPortfolioValues, exceptions.size() != 0, exceptions);
			
	}
}
