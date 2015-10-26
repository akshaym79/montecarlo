package simulation;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.*;

import simulation.config.Parameters;

/**
 * This class runs Monte-Carlo simulations on a collection of portfolios and generates 
 * the comparable results of projected values.
 * 
 * @author Akshay More
 */
public class MonteCarloPortfolioComparator {

	private List<Portfolio> portfoliosToCompare;
	private SimulationIterationParameters iterationParameters;
	private SimulationRunnerParameters runnerParameters;

	public MonteCarloPortfolioComparator() {
		initializePortfolios();
		initializeIterationParameters();
		initializeRunnerParameters();
	}

	private void initializeRunnerParameters() {
		this.runnerParameters = new SimulationRunnerParameters(
				Parameters.NUM_OF_ITERATIONS, 
				Parameters.NUM_OF_ITERATION_THREADS,
				Parameters.NUM_OF_SIMULATION_THREADS,
				Parameters.FETCH_ITERATION_RESULT_TIMEOUT_MS
				);
	}

	private void initializeIterationParameters() {
		this.iterationParameters = new SimulationIterationParameters(
				Parameters.PORTFOLIO_INITIAL_VALUE, 
				Parameters.INFLATION_RATE,
				Parameters.YEARS_PER_ITERATION
				);
	}

	private void initializePortfolios() {
		this.portfoliosToCompare = new ArrayList<Portfolio>();
		Portfolio conservativePortfolio = new Portfolio(
				PortfolioType.CONSERVATIVE, 
				Parameters.CONSERVATIVE_RETURNS,
				Parameters.CONSERVATIVE_RISK
				);
		Portfolio aggressivePortfolio = new Portfolio(
				PortfolioType.AGGRESSIVE,
				Parameters.AGGRESSIVE_RETURNS, 
				Parameters.AGGRESSIVE_RISK
				);
		portfoliosToCompare.add(conservativePortfolio);
		portfoliosToCompare.add(aggressivePortfolio);
	}

	public void generateComparison() {
		ExecutorService execService = 
				Executors.newFixedThreadPool(Parameters.NUM_OF_SIMULATION_THREADS);
		CompletionService<ISimulationResults> completionService = 
				new ExecutorCompletionService<ISimulationResults>(execService);
		Map<Future<ISimulationResults>, Portfolio> portfolioForSimulationTask = new HashMap<>(); 

		for (Portfolio portfolio: portfoliosToCompare) {
			Callable<ISimulationResults> simulationRunner = new SimulationRunner(
					portfolio, 
					iterationParameters, 
					runnerParameters
					);
			Future<ISimulationResults> future = completionService.submit(simulationRunner);
			portfolioForSimulationTask.put(future, portfolio);
		}
		
		printHeaders();
		
		for (int i=0; i<portfoliosToCompare.size(); i++) {
			Portfolio portfolioForFuture = null;
			try {
				Future<ISimulationResults> future = completionService.take();
				portfolioForFuture = portfolioForSimulationTask.get(future); 
				printResults(future, portfolioForFuture);
			} catch (InterruptedException | ExecutionException e) {
				if (portfolioForFuture == null) {
					System.out.println("An exception was encountered running the simulation.\n" + e.getMessage());
				} else {
					System.out.println("An exception was encountered running the simulation for portfolio <" 
							+ portfolioForFuture.getPortfolioType().name() + ">.\n" + e.getMessage()
							);
				}
			}
		}
		execService.shutdown();
		
	}
	
	private void printHeaders() {
		System.out.println();
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb, Locale.US);
		formatter.format("%15s", "Portfolio Type");
		formatter.format("%25s", "Median 20th Year ($)");
		formatter.format("%25s", "10% Best Case ($)");
		formatter.format("%25s", "10% Worst Case ($)");
		System.out.println(sb.toString());
		formatter.close();
	}

	private void printResults(Future<ISimulationResults> future, Portfolio portfolio) 
			throws InterruptedException, ExecutionException {
		ISimulationResults simulationResults = future.get();
		if (simulationResults == null) {
			System.out.println("A problem was encountered running the simulation for portfolio <" 
					+ portfolio.getPortfolioType().name() + ">."
					);
			return;
		}
		if (simulationResults.hasExceptions()) {
			System.out.println("Simulation did not complete successfully. The following <"
					+ simulationResults.getExceptions().size()
					+ "> exceptions occurred ...");
			for (Throwable t : simulationResults.getExceptions())
				System.out.println(t.getMessage());
		} else {
			StringBuilder sb = new StringBuilder();
			Formatter formatter = new Formatter(sb, Locale.US);
			formatter.format("%15s", portfolio.getPortfolioType().type());
			formatter.format("%,25.2f", simulationResults.getPercentile(50));
			formatter.format("%,25.2f", simulationResults.getPercentile(90));
			formatter.format("%,25.2f", simulationResults.getPercentile(10));
			System.out.println(sb.toString());
			formatter.close();
		}
	}

	public static void main(String[] args) {
		MonteCarloPortfolioComparator portfolioComparator = new MonteCarloPortfolioComparator();
		portfolioComparator.generateComparison();
	}

}
