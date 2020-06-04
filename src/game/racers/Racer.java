package game.racers;
import utilities.*;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.RacerEvent;
import utilities.EnumContainer.State;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;

import GUI.Panel;
import game.arenas.Arena;

 
/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */

 abstract public class Racer extends Observable implements Runnable {
		private volatile boolean running = true;
		protected static int counter=1;
		private int serialNumber;
		private String name;
		private Point currentLocation;
		private Point finish;
		protected Arena arena;
		private double maxSpeed;
		private double acceleration;
		private double currentSpeed;
		private double failureProbability;
		private Thread thread;
		private Color color;
		private Mishap mishap=null;
		protected Image image;
		private Panel panel;
		private State state = State.NO;

    /**
     * ctor
     * @param panel
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     * @param image
     */
		public Racer(Panel panel ,String name, double maxSpeed, double acceleration, Color color, Image image) {
			this.panel=panel;
	    	this.setImage(image);
	    	this.setColor(color);
			this.setName(name);
			this.setMaxSpeed(maxSpeed);
			this.setAcceleration(acceleration);
			this.setCurrentSpeed(0);
			this.serialNumber=counter;
			this.failureProbability=0.05;
			counter++;
			thread=new Thread(this);
		}
		public abstract String describeSpecific();
		/**
		 * check the proccess of mishap
		 */
	    public void checkMishap() {
			if(mishap!=null ) {
				if(!mishap.getFixable()) {
					setChanged();
					running=false;
					this.notifyObservers(RacerEvent.DISABLED);
				}
				else if(mishap.getFixable() && mishap.getTurnToFix()>0) {
					mishap.nextTurn();
				  	}
				else if(mishap.getFixable()&&mishap.getTurnToFix()<1){
					setChanged();
					this.notifyObservers(RacerEvent.REPAIRED);
				    }
			}
			else {
				if(Fate.breakDown(failureProbability)) {
					setChanged();
					this.notifyObservers(RacerEvent.BROKENDOWN);
					mishap=Fate.generateMishap(); 
			    }
			}
	    }
/**
 * check my state 
 * @return yes or no 
 */
		public String Checkstate() {
			if(this.getState() == State.NO)
				return "NO";
			else 
				return "YES";
		}
  /**
   * draw the racer 
   * @param g
   */
		public void drawObject(Graphics g) {
			if(arena!=null)
				g.drawImage(image, (int)(currentLocation.getX()/arena.getLen()), (int)currentLocation.getY(),40,40,panel);
			else 
				g.drawImage(image, (int)(currentLocation.getX()), (int)currentLocation.getY(),40,40,panel);
		}
		/**
		 * make move 
		 */
		@Override
		public void run() {
			Point location ;
			while(running) {
				try {
					Thread.sleep(30);
				} catch (InterruptedException e1) {}
				try {
					location= move(arena.getFRICTION());
				if(this.getFinish().getX()<=location.getX()) {
					setChanged();
					notifyObservers(RacerEvent.FINISHED);
					running = false;
					currentLocation.setX(this.getFinish().getX());
				}
				}catch (InterruptedException e) {
					running= false;
				}
				panel.repaint();
			}
		}
		 /**
		  * init the race and raacers
		  * @param arena
		  * @param finish
		  */
		public void initRace(Arena arena, Point finish) {
			this.setArena(arena);
			this.setFinish(finish);
			panel.repaint();
			arena.setObservable(this);
		}
	     /**
	      * move the racer 
	      * @param friction
	      * @return
	      * @throws InterruptedException
	      */
		public Point move(double friction)throws InterruptedException  {
			checkMishap();
			if (this.currentSpeed<this.maxSpeed) {
				if(mishap!=null)
					setCurrentSpeed(this.currentSpeed+(acceleration*mishap.getReductionFactor()*friction));
				else
					setCurrentSpeed(this.currentSpeed+(acceleration*friction));
			}
		     if(this.currentSpeed>this.maxSpeed)
		    	 	setCurrentSpeed(this.maxSpeed);
			this.currentLocation.setX(currentLocation.getX()+currentSpeed);
			return this.currentLocation;
		}
		public abstract  String className();
		
		public void introduce() {
			System.out.println(className()+describeRacer()+describeSpecific());
		}
		public String describeRacer() {
			return "name: "+name+", SerialNumber:" +serialNumber+", maxSpeed: "+maxSpeed+", acceleration:"+ acceleration+", Color: "+color;
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		public boolean setCurrentSpeed(double currentSpeed) {
			this.currentSpeed = currentSpeed;
			return true;
		}
		public boolean setFinish(Point finish) {
			this.finish = new Point(finish);
			return true;
		}
		public boolean setArena(Arena arena) {
			this.arena = arena;
			return true;
		}
		public Point getFinish() {
			return finish;
		}
		public State getState() {
			return state;
		}
		public void setImage(Image image){
		    this.image =image;
		}
		public boolean setName(String name) {
			this.name = name;
			return true;
		}
		public boolean setColor(Color color) {
			this.color = color;
			return true;
		}
		public boolean setMaxSpeed(double maxSpeed) {
			this.maxSpeed = maxSpeed;
			return true;
		}
		public boolean setAcceleration(double acceleration) {
			this.acceleration = acceleration;
			return true;
		}
		public double getCurrentSpeed() {
			return currentSpeed;
		}
		public Point getCurrentLocation() {
			return currentLocation;
		}
		public String getName() {
			return name;
		}
		public double getMaxSpeed() {
			return maxSpeed;
		}
		public void setState(State state) {
			this.state = state;
		}
		public boolean setCurrentLocation(Point currentLocation) {
			this.currentLocation = new Point(currentLocation);
			return true;
		}
}
