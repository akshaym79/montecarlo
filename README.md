# montecarlo
Monte-Carlo Simulation

The application can be executed by running the main method in class MonteCarloPortfolioComparator.
The various simulation and operational parameters are configured in class Parameters.
To run the JUnit tests in the test folder, please include the jars in the lib folder in the classpath.

Some important calculations that govern projected values for portfolios are as follows:

1. If a portfolio has historical mean returns of R with standard deviation of S, then a randomly generated nominal return using a Gaussian distribution is given by
R + S * random.nextGaussian()

2. If nominal rate of return is N and rate of inflation is I, then real rate of return (or inflation adjusted rate of return) is given by
((1+N)/(1+I) - 1)

3. If N iterations are run, generating N projected values that are sorted in ascending order, then the rank in the sorted array of the Pth percentile value is given by 
Math.ceil((P/100.0) * N)
