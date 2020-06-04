package game.racers.air;

import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer.Color;

public class Airplane extends Racer implements IAerialRacer {
	private static final String CLASS_NAME = "Airplane";

	private static final int DEFAULT_WHEELS = 3;
	private static final double DEFAULT_MAX_SPEED = 885;
	private static final double DEFAULT_ACCELERATION = 100;
	private static final Color DEFAULT_color = Color.BLACK;
	private Wheeled wheeled;

	public Airplane() {
		this(CLASS_NAME + " #" + lastSerialNumber, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color,
				DEFAULT_WHEELS);
	}

	public Airplane(String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color,
			int numOfWheels) {
		super(name, maxSpeed, acceleration, color);
		this.setWheeled(new Wheeled(numOfWheels));
	}

	@Override
	public String className() {
		return CLASS_NAME;
	}

	@Override
	public String describeSpecific() {
		return this.wheeled.describeSpecific();
	}

	public Wheeled getWheeled() {
		return wheeled;
	}

	public void setWheeled(Wheeled wheeled) {
		this.wheeled = wheeled;
	}
}
