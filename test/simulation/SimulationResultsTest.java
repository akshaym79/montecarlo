package simulation;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimulationResultsTest {

	@Test
	public void testInvalidPercentilesQuery() {
		ISimulationResults simulationResults = new SimulationResults(new double[]{1.0, 2.0}, false, null);
		
		// Test query for 101st percentile.
		Double result = simulationResults.getPercentile(101);
		assertTrue("Fetching 101st percentile should return NaN. The actual result is " + result, 
				result.equals(Double.NaN));
		
		// Test query for negative percentile.
		result = simulationResults.getPercentile(-1);
		assertTrue("Fetching negative percentile should return NaN. The actual result is " + result, 
				result.equals(Double.NaN));
	}
	
	@Test
	public void testValidPercentileQueries() {
		ISimulationResults simulationResults = new SimulationResults(new double[]{1.0, 2.0, 3.0}, false, null);
		
		// Test query for 0th percentile.
		Double result = simulationResults.getPercentile(0);
		assertTrue("Fetching 0th percentile should return 1.0. The actual result is " + result, 
				result.equals(1.0));
		
		// Test query for 100th percentile.
		result = simulationResults.getPercentile(100);
		assertTrue("Fetching 100th percentile should return 3.0. The actual result is " + result, 
				result.equals(3.0));
		
		// Test query for 50th percentile.
		result = simulationResults.getPercentile(50);
		assertTrue("Fetching 50th percentile should return 2.0. The actual result is " + result, 
				result.equals(2.0));

		// Test query for 33rd percentile.
		result = simulationResults.getPercentile(33);
		assertTrue("Fetching 33rd percentile should return 1.0. The actual result is " + result, 
				result.equals(1.0));
		
		// Test query for 66th percentile.
		result = simulationResults.getPercentile(66);
		assertTrue("Fetching 66th percentile should return 2.0. The actual result is " + result, 
				result.equals(2.0));
	}
	
	@Test
	public void testResultsWithException() {
		// Verify that results with an exception return a NaN for any percentile query.
		ISimulationResults simulationResults = new SimulationResults(new double[]{1.0, 2.0, 3.0}, true, null);
		Double result = simulationResults.getPercentile(50);
		assertTrue("Querying for any percentile on results containing an exception should return NaN. The actual result is " + result, 
				result.equals(Double.NaN));
	}
	
	@Test
	public void testNullResults() {
		// Verify that null results return a NaN for any percentile query.
		ISimulationResults simulationResults = new SimulationResults(null, false, null);
		Double result = simulationResults.getPercentile(50);
		assertTrue("Querying for any percentile on null results should return NaN. The actual result is " + result, 
				result.equals(Double.NaN));
	}

	@Test
	public void testEmptyResults() {
		// Verify that empty results return a NaN for any percentile query.
		ISimulationResults simulationResults = new SimulationResults(new double[]{}, false, null);
		Double result = simulationResults.getPercentile(50);
		assertTrue("Querying for any percentile on empty results should return NaN. The actual result is " + result, 
				result.equals(Double.NaN));
	}
}
