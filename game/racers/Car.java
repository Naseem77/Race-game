package game.racers;

import game.arenas.LandArena;
import utilities.Point;

public class Car {

	private String name;
	private Point currentLocation;
	private Point finish;
	private LandArena arena;
	private double maxSpeed;
	private double acceleration;
	private double currentSpeed;

	public Car(String name) {
		this(name, 120, 12);
	}

	public Car(String name, double maxSpeed, double acceleration) {
		this.setName(name);
		this.setMaxSpeed(maxSpeed);
		this.setAcceleration(acceleration);
		this.setCurrentSpeed(0);
	}

	public void initRace(LandArena arena, Point start, Point finish) {
		this.setArena(arena);
		this.setCurrentLocation(start);
		this.setFinish(finish);
	}

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

	public String toString() {
		return "Car " + this.name + " (" + this.maxSpeed + ", " + this.acceleration + ")";
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

	public LandArena getArena() {
		return arena;
	}

	public void setArena(LandArena arena) {
		this.arena = arena;
	}

	private void setFinish(Point finish) {
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
