package game.racers.land;
import java.awt.Image;

import GUI.Panel;
import game.racers.Racer;
import utilities.EnumContainer.*;


/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */
public class Horse extends Racer implements LandRacer {
	
		private Breed breed;
		/**
		 * ctor
		 * @param panel
		 * @param name
		 * @param maxSpeed
		 * @param acceleration
		 * @param color
		 * @param image
		 */
		public Horse(Panel panel,String name, double maxSpeed, double acceleration, Color color,Image image) {
		    super(panel,name,maxSpeed,acceleration,color,image);
		    setBreed(Breed.THOROUGHBRED);
		}
		@Override
		public String describeSpecific() {
			return  " , Horse Breed:"+breed;
		}
		@Override
		public String className() {	
			return "[Horse] ";
		}
		public Breed getBreed() {
			return breed;
		}
		public boolean setBreed(Breed breed) {
			this.breed = breed;
			return true;
		}
}
