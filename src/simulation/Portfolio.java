package simulation;

/**
 * An instance of this class is used to encapsulate the risk/return characteristics of a portfolio.
 *
 * @author Akshay More
 */
public final class Portfolio {

	private final PortfolioType portfolioType;
	private final double risk;
	private final double returns;
	
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
