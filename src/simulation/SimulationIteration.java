package simulation;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * An instance of this class encapsulates the processing of a single iteration of 
 * a Monte-Carlo simulation.
 * 
 * @author Akshay More
 */
public class SimulationIteration implements Callable<Double> {

	private final Portfolio portfolio;
	private final SimulationIterationParameters iterationParameters;
	
	public SimulationIteration(Portfolio portfolio, SimulationIterationParameters iterationParameters) {
		this.portfolio = portfolio;
		this.iterationParameters = iterationParameters;
	}
	
	/**
	 * Calculates and returns the projected value of a portfolio at the end of the specified 
	 * number of years.
	 * For every year, a random nominal return is generated using the Gaussian distribution
	 * adjusted for the mean and standard deviation of the portfolio.
	 * The nominal return is then adjusted for inflation using the assumed inflation rate.
	 * A new portfolio value is calculated using the inflation adjusted rate of returns.
	 */
	@Override
	public Double call() throws Exception {
		double portfolioValue = iterationParameters.getInitialValue();
		Random random = new Random();

		for (int i = 0; i < iterationParameters.getNumberOfYears(); i++) {
			double nominalReturn = 
					portfolio.getReturns() + portfolio.getRisk() * random.nextGaussian();
			double inflationAdjustedPercentageReturn = 
					(1.0 + nominalReturn/100.0) / (1.0 + iterationParameters.getRateOfInflation()/100.0) - 1.0;
			portfolioValue += (portfolioValue * inflationAdjustedPercentageReturn);
		}
		
		return portfolioValue;
	}

}
