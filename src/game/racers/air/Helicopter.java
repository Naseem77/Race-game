package game.racers.air;
import utilities.EnumContainer.*;
import java.awt.Image;

import GUI.Panel;
import game.racers.Racer;
/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */
public class Helicopter extends Racer implements AerialRacer {
	/**
	 * ctor
	 * @param panel
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 * @param image
	 */
	public Helicopter(Panel panel,String name, double maxSpeed, double acceleration, Color color,Image image) {
		super(panel,name,maxSpeed,acceleration,color,image);
		
	}
	@Override
	public String describeSpecific() {
		return "";
	}
	@Override
	public String className() {	
		return "[Helicopter] ";
	}


}
