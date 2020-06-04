package game.racers.air;

import game.racers.Racer;
import utilities.EnumContainer.Color;

public class Helicopter extends Racer implements IAerialRacer {

	private static final String CLASS_NAME = "Helicopter";

	private static final double DEFAULT_MAX_SPEED = 400;
	private static final double DEFAULT_ACCELERATION = 50;
	private static final Color DEFAULT_color = Color.BLUE;

	public Helicopter() {
		this(CLASS_NAME + " #" + lastSerialNumber, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color);
	}

	public Helicopter(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color) {
		super(name, maxSpeed, acceleration, color);
	}

	@Override
	public String className() {
		return CLASS_NAME;
	}

	@Override
	public String describeSpecific() {
		return "";
	}
}
