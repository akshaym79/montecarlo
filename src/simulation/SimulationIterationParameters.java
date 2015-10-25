package simulation;

public class SimulationIterationParameters {

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
