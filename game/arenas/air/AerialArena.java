package game.arenas.air;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.air.IAerialRacer;
import utilities.EnumContainer;

public class AerialArena extends Arena {

	private final static double DEFAULT_FRICTION = 0.4;
	private final static int DEFAULT_MAX_RACERS = 6;
	private final static int DEFAULT_LENGTH = 1500;

	EnumContainer.Vision vision;
	EnumContainer.Weather wather;
	EnumContainer.Height height;
	EnumContainer.Wind wind;

	public AerialArena() {
		this(DEFAULT_LENGTH, DEFAULT_MAX_RACERS);
	}

	/**
	 * @param length
	 *            The x value for the finish line
	 * @param maxRacers
	 *            Maximum number of racers
	 */
	public AerialArena(double length, int maxRacers) {
		super(length, maxRacers, DEFAULT_FRICTION);
		this.setVision(EnumContainer.Vision.SUNNY);
		this.setWather(EnumContainer.Weather.DRY);
		this.setHeight(EnumContainer.Height.HIGH);
		this.setWind(EnumContainer.Wind.HIGH);
	}

	@Override
	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException {
		if (newRacer instanceof IAerialRacer) {
			super.addRacer(newRacer);
		} else {
			throw new RacerTypeException(newRacer.className(), "Aerial");
		}
	}

	public EnumContainer.Height getHeight() {
		return height;
	}

	public EnumContainer.Vision getVision() {
		return vision;
	}

	public EnumContainer.Weather getWather() {
		return wather;
	}

	public EnumContainer.Wind getWind() {
		return wind;
	}

	public void setHeight(EnumContainer.Height height) {
		this.height = height;
	}

	public void setVision(EnumContainer.Vision vision) {
		this.vision = vision;
	}

	public void setWather(EnumContainer.Weather wather) {
		this.wather = wather;
	}

	public void setWind(EnumContainer.Wind wind) {
		this.wind = wind;
	}
}
