package simulation.exception;

public class SimulationException extends Exception {

	private static final long serialVersionUID = 1197195793717245727L;

	public SimulationException(String string, Exception e) {
		super(string, e);
	}

	public SimulationException(String message) {
		super(message);
	}

}
