package game.racers;

import game.arenas.NavalArena;
import utilities.Point;

public class RowBoat {
	private String name;
	private Point currentLocation;
	private Point finish;
	private NavalArena arena;
	private double maxSpeed;
	private double acceleration;
	private double currentSpeed;

	public RowBoat(String name, double maxSpeed, double acceleration) {
		this.setName(name);
		this.setMaxSpeed(maxSpeed);
		this.setAcceleration(acceleration);
		this.setCurrentSpeed(1);
	}

	/* Initialization */

	/**
	 * @param start
	 * @param finish
	 */
	public void initRace(NavalArena arena, Point start, Point finish) {
		this.setArena(arena);
		this.setCurrentLocation(start);
		this.setFinish(finish);
	}

	/* Game */

	public Point move(double friction) {
		if (this.currentSpeed < this.maxSpeed) {
			this.setCurrentSpeed(this.currentSpeed + this.acceleration * friction);
		}
		if (this.currentSpeed > this.maxSpeed) {
			this.setCurrentSpeed(this.maxSpeed);
		}
		Point newLocation = new Point((this.currentLocation.getX() + (1 * this.currentSpeed)),
				this.currentLocation.getY());
		this.setCurrentLocation(newLocation);

		if (this.currentLocation.getX() >= this.finish.getX()) {
			this.arena.crossFinishLine(this);
		}
		return this.currentLocation;
	}
	/* Utility */

	public String toString() {
		return "RowBoat " + this.name + " (" + this.maxSpeed + ", " + this.acceleration + ")";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Point currentLocation) {
		this.currentLocation = new Point(currentLocation);
	}

	public NavalArena getArena() {
		return arena;
	}

	public void setArena(NavalArena arena) {
		this.arena = arena;
	}

	public void setFinish(Point finish) {
		this.finish = new Point(finish);
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public double getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(double currentSpeed) {
		this.currentSpeed = currentSpeed;
	}
}
