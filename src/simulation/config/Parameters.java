package simulation.config;

/**
 * Class that specifies control parameters.
 * 
 * @author Akshay More
 */
public final class Parameters {
	
	/**
	 * Private constructor to prevent instantiation of class.
	 */
	private Parameters(){}

	/**
	 * Historical mean return for conservative portfolio.
	 */
	public static final double CONSERVATIVE_RETURNS = 6.189;
	/**
	 * Standard deviation of historical returns for conservative portfolio.
	 */
	public static final double CONSERVATIVE_RISK = 6.3438;
	/**
	 * Historical mean return for aggressive portfolio.
	 */
	public static final double AGGRESSIVE_RETURNS = 9.4324;
	/**
	 * Standard deviation of historical returns for aggressive portfolio.
	 */
	public static final double AGGRESSIVE_RISK = 15.675;
	
	/**
	 * Initial value of portfolio.
	 */
	public static final double PORTFOLIO_INITIAL_VALUE = 100000.00;

	/**
	 * Assumed rate of inflation.
	 */
	public static final double INFLATION_RATE = 3.5;
	
	/**
	 * Number of iterations to be performed for a Monte-Carlo simulation.
	 */
	public static final int NUM_OF_ITERATIONS = 10000;
	/**
	 * Number of years over which to project the value of the portfolio.
	 */
	public static final int YEARS_PER_ITERATION = 20;

	/**
	 * Operational parameter indicating number of threads in the threadpool that runs simulation iterations.
	 */
	public static final int NUM_OF_ITERATION_THREADS = 4;
	/**
	 * Operational parameter indicating milliseconds to wait before timing out for an iteration to complete.
	 */
	public static final long FETCH_ITERATION_RESULT_TIMEOUT_MS = 1000; 
	/**
	 * Operational parameter indicating number of thread in the threadpool that runs simulations.
	 */
	public static final int NUM_OF_SIMULATION_THREADS = 2;
	
}
