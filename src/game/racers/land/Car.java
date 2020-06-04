package game.racers.land;
import java.awt.Image;

import GUI.Panel;
import game.racers.Wheeled;
import utilities.EnumContainer.*;
/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */ 
public class Car extends Wheeled implements LandRacer {

		private Engine engine;
	 /**
	  * ctor
	  * @param panel
	  * @param name
	  * @param maxSpeed
	  * @param acceleration
	  * @param color
	  * @param image
	  */
		public Car(Panel panel,String name, double maxSpeed, double acceleration,Color color,Image image) {
			super(panel,name, maxSpeed, acceleration, color,image);
			setEngine(Engine.FOURSTROKE);
			setNumOfWheels(4);
		}
		@Override
		public String className() {	
			return "[Car] ";
		}
		 
		@Override
		public Boolean setNumOfWheels(int numOfWheels) {
			super.setNumOfWheels(numOfWheels);
			return true;
		}
		public Engine getEngine() {
			return engine;
		}
		 
		public void setEngine(Engine engine) {
			this.engine = engine;
		}
		 
		@Override
		public String describeSpecific() {
			return " , num of wheels: "+getNumOfWheels()+", Engine Type: "+engine;
		}
		@Override
		 public int getNumOfWheels() {
				return super.getNumOfWheels();
		 }
	
}
