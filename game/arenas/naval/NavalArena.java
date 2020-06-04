package game.arenas.naval;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.naval.INavalRacer;
import utilities.EnumContainer;

@SuppressWarnings("unused")
public class NavalArena extends Arena {
	private final static double DEFAULT_FRICTION = 0.7;
	private final static int DEFAULT_MAX_RACERS = 5;
	private final static int DEFAULT_LENGTH = 1000;

	EnumContainer.Water water;
	EnumContainer.WaterSurface surface;
	EnumContainer.Body body;

	public NavalArena() {
		this(DEFAULT_LENGTH, DEFAULT_MAX_RACERS);
	}

	public NavalArena(double length, int maxRacers) {
		super(length, maxRacers, DEFAULT_FRICTION);
		this.setWater(EnumContainer.Water.SWEET);
		this.setSurface(EnumContainer.WaterSurface.FLAT);
		this.setBody(EnumContainer.Body.LAKE);
	}

	@Override
	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException {
		if (newRacer instanceof INavalRacer) {
			super.addRacer(newRacer);
		} else {
			throw new RacerTypeException(newRacer.className(), "Naval");
		}
	}

	public EnumContainer.Body getBody() {
		return body;
	}

	public EnumContainer.WaterSurface getSurface() {
		return surface;
	}

	public EnumContainer.Water getWater() {
		return water;
	}

	public void setBody(EnumContainer.Body body) {
		this.body = body;
	}

	public void setSurface(EnumContainer.WaterSurface surface) {
		this.surface = surface;
	}

	public void setWater(EnumContainer.Water water) {
		this.water = water;
	}

}
