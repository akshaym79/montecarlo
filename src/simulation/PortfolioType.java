package simulation;

public enum PortfolioType {
	CONSERVATIVE("Conservative", "Low risk, low return"),
	AGGRESSIVE("Aggressive", "High risk, high return");
	
	private final String type;
	private final String description;
	
	PortfolioType(String type, String description) {
		this.type = type;
		this.description = description;
	}

	public String type() {
		return type;
	}

	public String description() {
		return description;
	}
}
