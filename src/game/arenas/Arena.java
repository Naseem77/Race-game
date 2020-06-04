package game.arenas;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import game.arenas.exceptions.*;
import game.racers.*;
import utilities.EnumContainer.RacerEvent;
import utilities.EnumContainer.State;
import utilities.Point;


/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */

abstract public class Arena implements Observer{
	
  private ArrayList<Racer> brokenRacers;
  private ArrayList<Racer> disabledRacers;
  protected ArrayList<Racer> activeRacers;
  private ArrayList<Racer> completedRacers;
  private final int MAX_RACERS;
  private final double FRICTION;
  protected int MIN_Y = 10;
  private double length1;
  private float length2=0;
  private Observable observable;
  
   /**
    * ctor
    * @param length1
    * @param maxRacers
    * @param friction
    */
	public Arena(double length1, int maxRacers, double friction) {
	   this.MAX_RACERS = maxRacers;
	   this.FRICTION = friction;
	   this.setLength(length1);
	   this.activeRacers = new ArrayList<Racer>();
	   this.completedRacers = new ArrayList<Racer>();
	   this.disabledRacers = new ArrayList<Racer>();
	   this.brokenRacers = new ArrayList<Racer>();
	}
	 
	public abstract void addRacer(Racer newRacer) throws RacerTypeException, RacerLimitException;
	 
	/**
	 * 
	 * @return true if still there racers
	 */
	public boolean hasActiveRacers() {
		return (this.activeRacers.size() > 0);
	}
	/**
	 * init the race
	 */
	public void initRace() {
		for (Racer ra : this.activeRacers) {
			ra.initRace(this,new Point(length1,0));
		}
	}
/**
 * start the race
 */
	public void startRace() {	
		length2=1;
		ExecutorService e = Executors.newFixedThreadPool(activeRacers.size());
		for(Racer racer : this.activeRacers) {
			e.execute(racer);
		}
	}
	/**
	 * set observable
	 * @param observable
	 */
	public void setObservable(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);  
    }
	/**
	 * synchronized the race
	 */
	public synchronized void update(Observable obj, Object arg) {
		      Racer ra=(Racer)obj;
		      if(arg==RacerEvent.DISABLED) { 
		    	 activeRacers.remove(ra);
		    	 disabledRacers.add(ra);
		      }
		      else if(arg==RacerEvent.REPAIRED) {
		    	 brokenRacers.remove(ra);
		    	 activeRacers.add(ra);
		      }
		      else if(arg==RacerEvent.BROKENDOWN) {
		    	  brokenRacers.add(ra);
		      }
		      else if(arg==RacerEvent.FINISHED) {
		    	  activeRacers.remove(ra);
			      completedRacers.add(ra);
			      ra.setState(State.YES);
		      }
	}
	/**
	 * show result of the race
	 */
	public void showResults() {
		for (Racer ra : this.activeRacers) {
			ra.introduce();
		}
	}
	public boolean setLength(double length1) {
		this.length1 = length1;
		return true;
	}
	public int getMAX_RACERS() {
		return MAX_RACERS;
	}
	public double getFRICTION() {
		return FRICTION;
	}
	public float getLen() {
		return length2;
	}
}
