/* =====
 * USAGE
 * =====
 * 
 * When simulating a Match between the homeTeam and awayTeam,
 * the following random number generators are available:
 * 
 * -> Given a homeTeam and an awayTeam:
 * (1) Random.homeGoals(homeTeam, awayTeam) returns the goals of the homeTeam
 * (2) Random.awayGoals(homeTeam, awayTeam) returns the goals of the awayTeam
 * 
 * (3) Random.yellowCards() returns the yellowCards of any FootballTeam
 * (4) Random.redCards() returns the redCards of any FootballTeam
 * 
 * ==========
 * DISCLAIMER
 * ==========
 * 
 * A prob array consisting of the values {0.3, 0.45, 0.25} means that it is
 * 0.3 likely that the number 0 should be returned, 0.45 likely that 1 is
 * returned and 0.25 likely that 2 is returned
 * 
 */

package trunk.Java;

public class Random {
	// post: Returns a suitable amount of homeGoals based on the DELTA_FACTOR
	//       which is equal to homeTeam.getFactor() - awayTeam.getFactor()
	public static int homeGoals(FootballTeam homeTeam, FootballTeam awayTeam) {
		double[] probs = {0.236766121, 0.308950914, 0.256977863, 0.121270452,
				          0.051010587, 0.015399423, 0.005774783, 0.001924928,
				          0.001924928};
		
		// Modify the probability based on the teams' factors
		modifyProbs(probs, homeTeam, awayTeam);
		
		return determineValue(probs);
	}
	
	// post: Returns a suitable amount of awayGoals based on the DELTA_FACTOR
	//       which is equal to awayTeam.getFactor() - homeTeam.getFactor()
	public static int awayGoals(FootballTeam homeTeam, FootballTeam awayTeam) {
		double[] probs = {0.332050048, 0.330125120, 0.200192493, 0.104908566,
				          0.022136670, 0.007699711, 0.002887392};
		
		// Modify the probability based on the teams' factors
		modifyProbs(probs, awayTeam, homeTeam);
		
		return determineValue(probs);
	}
	
	// post: Returns a suitable amount of yellowCards based on probability
	public static int yellowCards() {
		double[] probs = {0.183349374, 0.319056785, 0.264196343, 0.147738210,
				          0.066891242, 0.014918191, 0.002887392, 0.000481232,
				          0.000481232};
		
		return determineValue(probs);
	}
	
	// post: Returns a suitable amount of yellowCards based on probability
	public static int redCards() {
		double[] probs = {0.926852743, 0.070259865, 0.002887392}; 
		
		return determineValue(probs);
	}
	
	// A helper function for all of the probability functions.
	// post: Returns a suitable amount of entities based on a given probability array
	public static int determineValue(double[] probs) {
		double prob = Math.random();
		for (int i = 0; i < probs.length; i++) {
			if (prob <= sumProbs(probs, 0, i+1)) {
				return i;
			}
		}
		// Because of loss of decimal precision in modifyProbs it might happen
		// that the forloop above doesnt return when approximately 0.9999 < prob < 1.0.
		// In that extreme case, return the following value
		return probs.length-1;
	}
	
	// post: Returns the sum of contents on the interval [inclusive,exclusive[ in array
	private static double sumProbs(double[] array, int inclusive, int exclusive) {
		double sum = 0.0;
		for (int i = inclusive; i < exclusive; i++) {
			sum += array[i];
		}
		return sum;
	}
	
	// pre:  -> To be called by homeGoals or awayGoals.
	//       homeGoals calls modifyProbs(homeTeam, awayTeam),
	//       awayGoals calls modifyProbs(awayTeam, homeTeam). This is so that
	//       superior gets more probability to score if superior.getFactor() > inferior.getFactor()
	//       and less probability to score if superior.getFactor() < inferior.getFactor()
	// post: Modifies the probability values in probs based on the team factors. In other words,
	//       this function helps homeGoals and awayGoals in such a way that a homeTeam that is stronger
	//       than an awayTeam is more likely to score more goals. Also, a weaker homeTeam is less
	//       likely to score more goals than a stronger awayTeam.
	private static void modifyProbs(double[] probs, FootballTeam superior, FootballTeam inferior) {
		final double DELTA_FACTOR = superior.getFactor() - inferior.getFactor();
		
		final double PROB_SHIFT_STEP = (DELTA_FACTOR/3)/(probs.length-1);
		double probShiftResidue = 0;
		
		probs[0] -= DELTA_FACTOR/3;
		for (int i = probs.length-1; i > 0; i--) {
			probs[i] += PROB_SHIFT_STEP - probShiftResidue;

			// When reducing probability in probs[i] and it
			// turns out there wasn't enough probability there
			if (probs[i] < 0) {
				probShiftResidue = -probs[i];
				probs[i] = 0; // Because probabilities can't be negative
			} else probShiftResidue = 0;
		}
		
		for (double value : probs) {
			System.out.println(value + ", ");
		}
		System.out.println("\n" + sumProbs(probs, 0, probs.length) + "\n");
	}
}