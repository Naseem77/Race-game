package game.racers;
import java.awt.Image;

import GUI.Panel;
import utilities.EnumContainer.Color;
/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */

abstract public class Wheeled extends Racer{
	
    private int numOfWheels;
	/**
	 * ctor
	 * @param panel
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 * @param image
	 */
    public Wheeled(Panel panel, String name, double maxSpeed, double acceleration, Color color, Image image) {
		super(panel,name, maxSpeed, acceleration, color,image);
	}
    public int getNumOfWheels() {
		return numOfWheels;
    }
	public Boolean setNumOfWheels(int numOfWheels) {
		this.numOfWheels=numOfWheels;
		return true;
	}
}
