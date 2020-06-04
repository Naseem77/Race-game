package game.arenas;

import java.util.ArrayList;

import game.racers.RowBoat;
import game.racers.SpeedBoat;
import utilities.Point;

public class NavalArena {

	public static final int MAX_RACERS = 5;
	private static final double FRICTION = 0.7;
	private ArrayList<RowBoat> rowBoats;
	private ArrayList<SpeedBoat> speedBoats;
	private ArrayList<Object> finished;
	private Point start;
	private Point finish;

	public NavalArena(Point start, Point finish) {
		this.setStart(new Point(start));
		this.setFinish(new Point(finish));
		this.setSpeedBoats(new ArrayList<>());
		this.setRowBoats(new ArrayList<>());
		this.setFinished(new ArrayList<>());
	}

	public boolean addRowBoat(RowBoat newRacer) {
		if (this.isFull())
			return false;
		this.rowBoats.add(newRacer);
		return true;
	}

	public boolean addSpeedBoat(SpeedBoat newRacer) {
		if (this.isFull())
			return false;
		this.speedBoats.add(newRacer);
		return true;
	}

	public int crossFinishLine(RowBoat rowBoat) {
		this.finished.add(rowBoat);
		return this.finished.size();
	}

	public int crossFinishLine(SpeedBoat speedBoat) {
		this.finished.add(speedBoat);
		return this.finished.size();
	}

	/**
	 * @return the finish
	 */
	public Point getFinish() {
		return this.finish;
	}

	public ArrayList<Object> getFinished() {
		return this.finished;
	}

	private int getNumOfRacers() {
		return this.rowBoats.size() + this.speedBoats.size();
	}

	public Point getStart() {
		return this.start;
	}

	public boolean hasActiveRacer() {
		return ((this.speedBoats.size() > 0) || (this.rowBoats.size() > 0));
	}

	public void initRace() {
		for (SpeedBoat sb : this.speedBoats) {
			sb.initRace(this, this.start, this.finish);
		}
		for (RowBoat rb : this.rowBoats) {
			rb.initRace(this, this.start, this.finish);
		}
	}

	private boolean isFull() {
		return this.getNumOfRacers() == AerialArena.MAX_RACERS;
	}

	public void playTurn() {

		for (SpeedBoat sb : this.speedBoats) {
			sb.move(NavalArena.FRICTION);
		}
		for (RowBoat rb : this.rowBoats) {
			rb.move(NavalArena.FRICTION);
		}
		for (Object racer : this.finished) {
			this.speedBoats.remove(racer);
			this.rowBoats.remove(racer);
		}
	}

	public void setFinish(Point finish) {
		this.finish = finish;
	}

	public void setFinished(ArrayList<Object> finished) {
		this.finished = finished;
	}

	public void setRowBoats(ArrayList<RowBoat> rowBoats) {
		this.rowBoats = rowBoats;
	}

	public void setSpeedBoats(ArrayList<SpeedBoat> speedBoats) {
		this.speedBoats = speedBoats;
	}

	public void setStart(Point start) {
		this.start = start;
	}

}
