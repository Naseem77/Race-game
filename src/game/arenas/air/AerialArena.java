package game.arenas.air;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.air.AerialRacer;
import utilities.EnumContainer.*;
import utilities.Point;

/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */


public class AerialArena extends Arena{

	private Vision vision;
	private Weather weather;
	private Height height;
	private Wind wind;
	  
	/**
	 * add racer to the race
	 */
	@Override
	public void addRacer(Racer newRacer) throws RacerTypeException, RacerLimitException {
		if(!(newRacer instanceof AerialRacer)) {
			throw new RacerTypeException("Invalid racer type");
		}
		if( this.activeRacers.size() == this.getMAX_RACERS()) {
			throw new RacerLimitException("Arena is full!");
		}	
		this.activeRacers.add(newRacer);
		newRacer.setCurrentLocation(new Point(0,this.MIN_Y));
		MIN_Y+=40;
	}
	/**
	 * dctor
	 */
	public AerialArena() {
		super(1500,6,0.4);
		this.vision = Vision.SUNNY;
		this.weather = Weather.DRY;
		this.height = Height.HIGH;
		this.wind = Wind.HIGH;
	}
	/**
	 * ctor
	 * @param length
	 * @param maxRacers
	 */
	public AerialArena(double length, int maxRacers) {
		super(length, maxRacers, 0.4);
		this.vision = Vision.SUNNY;
		this.weather = Weather.DRY;
		this.height = Height.HIGH;
		this.wind = Wind.HIGH;
	}
	
}