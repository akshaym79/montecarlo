package simulation.config;

public final class Parameters {
	
	private Parameters(){}

	public static final double CONSERVATIVE_RETURNS = 6.189;
	public static final double CONSERVATIVE_RISK = 6.3438;
	public static final double AGGRESSIVE_RETURNS = 9.4324;
	public static final double AGGRESSIVE_RISK = 15.675;
	
	public static final double INFLATION_RATE = 3.5;
	
	public static final int NUM_OF_ITERATIONS = 10000;
	public static final int YEARS_PER_ITERATION = 20;
	public static final int NUM_OF_ITERATION_THREADS = 4;
	public static final int NUM_OF_SIMULATION_THREADS = 2;
	public static final long FETCH_ITERATION_RESULT_TIMEOUT_MS = 1000; 
	
	public static final double PORTFOLIO_INITIAL_VALUE = 100000.00;
}
