package game.arenas.land;
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.land.LandRacer;
import utilities.Point;
import utilities.EnumContainer.*;

/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */

public class LandArena extends Arena {

	  private Coverage coverage;
	  private Surface surface;
	 
	  /**
	   * ctor
	   * @param length
	   * @param maxRacers
	   */
	  public LandArena(double length, int maxRacers) {
		  super(length, maxRacers, 0.5);
		  this.coverage = Coverage.GRASS;
		  this.surface = Surface.FLAT;
	  }
	  /**
	   * dctor
	   */
	  public LandArena() {
		  super(800,8,0.5);
		  this.coverage = Coverage.GRASS;
		  this.surface = Surface.FLAT;
	  }
	  /**
	   * add new racer
	   */
	  @Override
	  public void addRacer(Racer newRacer) throws RacerTypeException, RacerLimitException {
		  if(!(newRacer instanceof LandRacer)) {
			  throw new RacerTypeException("Invalid Racer type");
		  }
			if( this.activeRacers.size() == this.getMAX_RACERS()) {
				throw new RacerLimitException("Arena is full! ");
			}	
			this.activeRacers.add(newRacer);
			newRacer.setCurrentLocation(new Point(0,this.MIN_Y));
			MIN_Y+=40;
		}
}
