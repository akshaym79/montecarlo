package simulation;

import java.util.Random;
import java.util.concurrent.Callable;

public class SimulationIteration implements Callable<Double> {

	private final Portfolio portfolio;
	private final SimulationIterationParameters iterationParameters;
	
	public SimulationIteration(Portfolio portfolio, SimulationIterationParameters iterationParameters) {
		this.portfolio = portfolio;
		this.iterationParameters = iterationParameters;
	}
	
	@Override
	public Double call() throws Exception {
		double portfolioValue = iterationParameters.getInitialValue();
		Random random = new Random();

		for (int i=0; i<iterationParameters.getNumberOfYears(); i++) {
			double nominalReturn = portfolio.getReturns() + portfolio.getRisk() * random.nextGaussian();
			double inflationAdjustedPercentageReturn = 
					(1 + nominalReturn/100)/(1 + iterationParameters.getRateOfInflation()/100) - 1;
			portfolioValue += (portfolioValue * inflationAdjustedPercentageReturn);
		}
		return portfolioValue;
	}

}
