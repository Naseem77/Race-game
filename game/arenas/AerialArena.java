package game.arenas;

import java.util.ArrayList;

import game.racers.Airplane;
import game.racers.Helicopter;
import utilities.Point;

public class AerialArena {

	public static final int MAX_RACERS = 6;
	private static final double FRICTION = 0.4;
	private ArrayList<Airplane> airplanes;
	private ArrayList<Helicopter> helicopters;
	private ArrayList<Object> finished;
	private Point start;
	private Point finish;

	public AerialArena(Point start, Point finish) {
		this.setStart(new Point(start));
		this.setFinish(new Point(finish));
		this.setAirplanes(new ArrayList<>());
		this.setHelicopters(new ArrayList<>());
		this.setFinished(new ArrayList<>());
	}

	public boolean addAirplane(Airplane arp) {
		if (this.isFull())
			return false;
		this.airplanes.add(arp);
		return true;
	}
	public boolean addHelicopter(Helicopter heli) {
		if (this.isFull())
			return false;
		this.helicopters.add(heli);
		return true;
	}

	public int crossFinishLine(Airplane airplane) {
		this.finished.add(airplane);
		return this.finished.size();
	}

	public int crossFinishLine(Helicopter helicopter) {
		this.finished.add(helicopter);
		return this.finished.size();
	}

	public Point getFinish() {
		return this.finish;
	}

	public ArrayList<Object> getFinished() {
		return this.finished;
	}

	private int getNumOfRacers() {
		return this.airplanes.size() + this.helicopters.size();
	}

	public Point getStart() {
		return this.start;
	}

	public boolean hasActiveRacer() {
		return ((this.airplanes.size() > 0) || (this.helicopters.size() > 0));
	}

	public void initRace() {
		for (Airplane ap : this.airplanes) {
			ap.initRace(this, this.start, this.finish);
		}
		for (Helicopter hc : helicopters) {
			hc.initRace(this, this.start, this.finish);
		}
	}

	private boolean isFull() {
		return this.getNumOfRacers() == AerialArena.MAX_RACERS;
	}

	public void playTurn() {
		for (Airplane ap : this.airplanes) {
			ap.move(AerialArena.FRICTION);
		}
		for (Helicopter hc : this.helicopters) {
			hc.move(AerialArena.FRICTION);
		}
		for (Object racer : this.finished) {
			this.airplanes.remove(racer);
			this.helicopters.remove(racer);
		}
	}

	public void setAirplanes(ArrayList<Airplane> airplanes) {
		this.airplanes = airplanes;
	}

	public void setFinish(Point finish) {
		this.finish = finish;
	}

	public void setFinished(ArrayList<Object> finished) {
		this.finished = finished;
	}

	public void setHelicopters(ArrayList<Helicopter> helicopters) {
		this.helicopters = helicopters;
	}

	public void setStart(Point start) {
		this.start = start;
	}

}
