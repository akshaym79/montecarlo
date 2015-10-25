package simulation;

/**
 * An instance of this class contains the parameters required to run a single iteration
 * in a Monte-Carlo simulation. These parameters are:
 * 1. The initial value of the portfolio.
 * 2. The assumed rate of inflation.
 * 3. The number of years over which to project the value of the portfolio.
 *
 * @author Akshay More
 */
public final class SimulationIterationParameters {

	private final double initialValue;
	private final double rateOfInflation;
	private final int numberOfYears;
	
	public SimulationIterationParameters(double initialValue, double interestRate, int numberOfYears) {
		this.initialValue = initialValue;
		this.rateOfInflation = interestRate;
		this.numberOfYears = numberOfYears;
	}

	public double getInitialValue() {
		return initialValue;
	}

	public double getRateOfInflation() {
		return rateOfInflation;
	}

	public int getNumberOfYears() {
		return numberOfYears;
	}
	
}
