package game.racers;

import game.arenas.Arena;
import utilities.EnumContainer;
import utilities.Fate;
import utilities.Mishap;
import utilities.Point;

public abstract class Racer {
	protected static int lastSerialNumber = 1;

	private int serialNumber;
	private String name;
	private Point currentLocation;
	private Point finish;
	private Arena arena;
	private double maxSpeed;
	private double acceleration;
	private double currentSpeed;
	@SuppressWarnings("unused")
	private double failureProbability; 
	private EnumContainer.Color color; 

	private Mishap mishap;

	/**
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 */
	public Racer(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color) {
		this.serialNumber = Racer.lastSerialNumber++;
		this.name = name;
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.color = color;
	}

	public abstract String className();

	public String describeRacer() {
		String s = "";
		s += "name: " + this.name + ", ";
		s += "SerialNumber: " + this.serialNumber + ", ";
		s += "maxSpeed: " + this.maxSpeed + ", ";
		s += "acceleration: " + this.acceleration + ", ";
		s += "color: " + this.color + ", ";
		s = s.substring(0, s.length() - 2);
		s += this.describeSpecific();
		return s;
	}

	public abstract String describeSpecific();

	public int getSerialNumber() {
		return serialNumber;
	}

	private boolean hasMishap() {
		if (this.mishap != null && this.mishap.getTurnsToFix() == 0)
			this.mishap = null;
		return this.mishap != null;
	}

	public void initRace(Arena arena, Point start, Point finish) {
		this.arena = arena;
		this.currentLocation = new Point(start);
		this.finish = new Point(finish);
	}

	public void introduce() {
		System.out.println("[" + this.className() + "] " + this.describeRacer());
	}

	public Point move(double friction) {
		double reductionFactor = 1;
		if (!(this.hasMishap()) && Fate.breakDown()) {
			this.mishap = Fate.generateMishap();
			System.out.println(this.name + " Has a new mishap! (" + this.mishap + ")");
		}

		if (this.hasMishap()) {
			reductionFactor = mishap.getReductionFactor();
			this.mishap.nextTurn();
		}
		if (this.currentSpeed < this.maxSpeed) {
			this.currentSpeed += this.acceleration * friction * reductionFactor;
		}
		if (this.currentSpeed > this.maxSpeed) {
			this.currentSpeed = this.maxSpeed;
		}
		double newX = (this.currentLocation.getX() + (this.currentSpeed));
		Point newLocation = new Point(newX, this.currentLocation.getY());
		this.currentLocation = newLocation;

		if (this.currentLocation.getX() >= this.finish.getX()) {
			this.arena.crossFinishLine(this);
		}
		return this.currentLocation;
	}
}
