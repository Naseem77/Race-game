package game.arenas;

import java.util.ArrayList;

import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.Point;

public abstract class Arena {

	private final static int MIN_Y_GAP = 10;
	private ArrayList<Racer> activeRacers;
	private ArrayList<Racer> compleatedRacers;

	private double length;
	private final int MAX_RACERS;
	private final double FRICTION;

	/**
	 * 
	 * @param length
	 *            the x value for the finish line
	 * @param maxRacers
	 *            Maximum number of racers
	 * @param friction
	 *            Coefficient of friction
	 * 
	 */
	protected Arena(double length, int maxRacers, double friction) {
		this.length = length;
		this.MAX_RACERS = maxRacers;
		this.FRICTION = friction;
		this.activeRacers = new ArrayList<Racer>();
		this.compleatedRacers = new ArrayList<Racer>();
	}

	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException {
		if (this.activeRacers.size() == this.MAX_RACERS) {
			throw new RacerLimitException(this.MAX_RACERS, newRacer.getSerialNumber());
		}
		this.activeRacers.add(newRacer);
	}

	public void crossFinishLine(Racer racer) {
		this.compleatedRacers.add(racer);
	}

	public ArrayList<Racer> getActiveRacers() {
		return activeRacers;
	}

	public ArrayList<Racer> getCompleatedRacers() {
		return compleatedRacers;
	}

	public boolean hasActiveRacers() {
		return this.activeRacers.size() > 0;
	}

	public void initRace() {
		int y = 0;
		for (Racer racer : this.activeRacers) {
			Point s = new Point(0, y);
			Point f = new Point(this.length, y);
			racer.initRace(this, s, f);
			y += Arena.MIN_Y_GAP;
		}
	}

	public void playTurn() {
		for (Racer racer : this.activeRacers) {
			racer.move(this.FRICTION);
		}

		for (Racer r : this.compleatedRacers)
			this.activeRacers.remove(r);
	}

	public void showResults() {
		for (Racer r : this.compleatedRacers) {
			String s = "#" + this.compleatedRacers.indexOf(r) + " -> ";
			s += r.describeRacer();
			System.out.println(s);
		}
		// for (int i = 0; i < this.activeRacers.size(); i++) {
		// System.out.println("#" + (i + 1) + ": " + this.activeRacers.get(i));
		// }
	}
}
