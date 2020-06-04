package utilities;

import java.util.Random;

public class Fate {
	
	// DEV change Fixable prob
	private final static double FIXABLE = 0.95;

	private static Random rand = new Random();

	
	@Deprecated
	public static boolean breakDown() {
		return rand.nextBoolean();
	}

	public static boolean breakDown(double failureProbability) {
		return rand.nextFloat() <= failureProbability;
	}

	public static boolean generateFixable() {
		// DEV changable prob
		return rand.nextFloat() < FIXABLE;
	}

	public static Mishap generateMishap() {
		return new Mishap(generateFixable(), generateTurns(), generateReduction());
	}

	private static float generateReduction() {
		return rand.nextFloat();
	}

	private static int generateTurns() {
		return rand.nextInt(5) + 1;
	}

	public static void setSeed(int seed) {
		rand.setSeed(seed);
	}
}
