package game.arenas.naval;
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.naval.NavalRacer;
import utilities.Point;
import utilities.EnumContainer.*;
 
/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */

public class NavalArena extends Arena {

		private Water water;
		private WaterSurface surface;
		private Body body;
		   
		/**
		 * ctor
		 * @param length
		 * @param maxRacers
		 */
		public NavalArena(double length, int maxRacers) {
			super(length, maxRacers, 0.7);
			this.water = Water.SWEET;
			this.surface = WaterSurface.FLAT;
	        this.body = Body.LAKE;
		}
		/**
		 * d-ctor
		 */
		public NavalArena() {
			super(1000,5,0.7);
			this.water = Water.SWEET;
			this.surface = WaterSurface.FLAT;
	        this.body = Body.LAKE;
		}
		/**
		 * add new raacer to the race
		 */
		@Override 
		public void addRacer(Racer newRacer) throws RacerTypeException, RacerLimitException {
			if(!(newRacer instanceof NavalRacer)) {
				throw new RacerTypeException("Invalid Racer type ");
			}
			if( this.activeRacers.size() == this.getMAX_RACERS()) {
				throw new RacerLimitException("Arena is full!  ");
			}	
			this.activeRacers.add(newRacer);
			newRacer.setCurrentLocation(new Point(0,this.MIN_Y));
			MIN_Y+=40;
		}
		
}
