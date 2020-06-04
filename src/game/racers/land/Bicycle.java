package game.racers.land;
import java.awt.Image;

import GUI.Panel;
import game.racers.Wheeled;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Type2;
 

/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */

public class Bicycle extends Wheeled implements LandRacer {

		private Type2 type;
		/**
		 * ctor
		 * @param panel
		 * @param name
		 * @param maxSpeed
		 * @param acceleration
		 * @param color
		 * @param image
		 */
		public Bicycle(Panel panel,String name, double maxSpeed, double acceleration, Color color,Image image) {
			super(panel,name, maxSpeed, acceleration, color,image);
			setType2(Type2.MOUNTAIN);
			setNumOfWheels(2);
		}
		@Override
		public String describeSpecific() {
			return " , num of wheels: "+getNumOfWheels()+", Bicycle Type: "+type;
		}
		@Override
		public String className() {	
			return "[Bicycle] ";
		}
		public Type2 getType2() {
			return type;
		}
	   
		public boolean setType2(Type2 type) {
			this.type = type;
			return true;
		}
		@Override
		public Boolean setNumOfWheels(int numOfWheels) {
			super.setNumOfWheels(numOfWheels);
			return true;
		}
		
		@Override
		 public int getNumOfWheels() {
				return super.getNumOfWheels();
		 }

}
