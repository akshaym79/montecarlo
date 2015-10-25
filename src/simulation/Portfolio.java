package simulation;

public class Portfolio {

	private PortfolioType portfolioType;
	private double risk;
	private double returns;
	
	public Portfolio(PortfolioType portfolioType, double returns, double risk) {
		this.portfolioType = portfolioType;
		this.risk = risk;
		this.returns = returns;
	}

	public PortfolioType getPortfolioType() {
		return portfolioType;
	}

	public double getRisk() {
		return risk;
	}

	public double getReturns() {
		return returns;
	}
		
}
