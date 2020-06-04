package game.racers.air;
import java.awt.Image;

import GUI.Panel;
import game.racers.Wheeled;

import utilities.EnumContainer.Color;

/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */
public class Airplane extends Wheeled implements AerialRacer {
	

	 /**
	  * ctor
	  * @param panel
	  * @param name
	  * @param maxSpeed
	  * @param acceleration
	  * @param color
	  * @param image
	  */
	public Airplane(Panel panel,String name, double maxSpeed, double acceleration, Color color,Image image) {
		super(panel,name,maxSpeed,acceleration,color,image);
		setNumOfWheels(3);
	}
	 
	@Override
	public String describeSpecific() {
		return " , num of wheels: "+getNumOfWheels();
	}
	 
	@Override
	public String className() {	
		return "[Airplane] ";
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
