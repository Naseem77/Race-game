package factory;
import java.awt.Image;
import java.lang.reflect.*;
import GUI.Panel;
import game.arenas.Arena;
import game.racers.Racer;
import utilities.EnumContainer.Color;

/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */
public class RaceBuilder {
	private static RaceBuilder instance;
	public static RaceBuilder getInstance() {
		if (instance == null) {
			instance = new RaceBuilder();
		}
		return instance;
	}
	/**
	 * 
	 * @param arenaType
	 * @param length
	 * @param maxRacers
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public Arena buildArena(String arenaType, double length, int maxRacers) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		Class c= cl.loadClass(arenaType);
		Constructor con = c.getConstructor(double.class,int.class);
		Arena arena=  (Arena) con.newInstance(length,maxRacers);
		return arena;
	}
	/**
	 * 
	 * @param panel
	 * @param racerType
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 * @param racerLocation
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public Racer buildRacer(Panel panel,String racerType, String name, double maxSpeed, double acceleration, Color color, Image racerLocation) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class c;
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		Racer racer=null;
		c=cl.loadClass(racerType);
		Constructor con = c.getConstructor(Panel.class,String.class,double.class,double.class,Color.class,Image.class);
		racer=(Racer)con.newInstance(panel,name,maxSpeed,acceleration,color,racerLocation);	
		return racer;
	}
	/**
	 * 
	 * @param racerType
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 * @param numOfWheels
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public 	 Racer buildWheeledRacer(String racerType, String name, double maxSpeed, double acceleration, Color color, int numOfWheels) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class c;
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		Racer racer=null;
		c=cl.loadClass(racerType);
		Constructor con = c.getConstructor(String.class,double.class,double.class,Color.class,int.class);
		racer=(Racer)con.newInstance(name,maxSpeed,acceleration,color,numOfWheels);
		return racer;
	}
	
	
}