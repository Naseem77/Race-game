package game.arenas;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.EnumContainer.RacerEvent;
import utilities.Point;

public abstract class Arena implements Observer {

	private final static int MIN_Y_GAP = 100;
	private final double FRICTION;
	private final int MAX_RACERS;
	private double length;
	private ArrayList<Racer> activeRacers;
	private ArrayList<Racer> brokenRacers;
	private ArrayList<Racer> compleatedRacers;
	private ArrayList<Racer> disabledRacers;

	public Arena(double length, int maxRacers, double friction) {
		this.setLength(length);
		this.MAX_RACERS = maxRacers;
		this.FRICTION = friction;
		this.setActiveRacers(new ArrayList<Racer>());
		this.setCompleatedRacers(new ArrayList<Racer>());
		this.setBrokenRacers(new ArrayList<Racer>());
		this.setDisabledRacers(new ArrayList<Racer>());
	}

	public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException {
		newRacer.addObserver(this);
		synchronized (activeRacers) {
			if (this.activeRacers.size() == this.MAX_RACERS) {
				throw new RacerLimitException(this.MAX_RACERS, newRacer.getSerialNumber());
			}
			this.activeRacers.add(newRacer);
		}
	}

	@Deprecated
	public void crossFinishLine(Racer racer) {
		this.compleatedRacers.add(racer);
		this.activeRacers.remove(racer);
		if (this.activeRacers.size() == 0) {

		}
	}

	public ArrayList<Racer> getActiveRacers() {
		synchronized (activeRacers) {
			return activeRacers;
		}
	}

	public ArrayList<Racer> getBrokenRacers() {
		synchronized (activeRacers) {
			return brokenRacers;
		}
	}

	public ArrayList<Racer> getCompleatedRacers() {
		synchronized (activeRacers) {
			return compleatedRacers;
		}
	}

	public ArrayList<Racer> getDisabledRacers() {
		synchronized (activeRacers) {
			return disabledRacers;
		}
	}

	public double getLength() {
		return length;
	}

	public boolean hasActiveRacers() {
		synchronized (activeRacers) {
			return this.activeRacers.size() > 0;
		}
	}

	public void initRace() {
		int y = 0;
		synchronized (activeRacers) {
			for (Racer racer : this.activeRacers) {
				Point s = new Point(0, y);
				Point f = new Point(this.length, y);
				racer.initRace(this, s, f, this.FRICTION);
				y += Arena.MIN_Y_GAP;
			}
		}
	}

	@Deprecated
	public void playTurn() {
		for (Racer racer : this.activeRacers) {
			racer.move();
		}
		for (Racer r : this.compleatedRacers)
			this.activeRacers.remove(r);
	}

	public void setActiveRacers(ArrayList<Racer> activeRacers) {
		this.activeRacers = activeRacers;
	}

	public void setBrokenRacers(ArrayList<Racer> brokenRacers) {
		this.brokenRacers = brokenRacers;
	}

	public void setCompleatedRacers(ArrayList<Racer> compleatedRacers) {
		this.compleatedRacers = compleatedRacers;
	}

	public void setDisabledRacers(ArrayList<Racer> disabledRacers) {
		this.disabledRacers = disabledRacers;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void showResults() {
		synchronized (activeRacers) {
			int i = 1;
			System.out.println("Compleated");
			for (Racer r : this.compleatedRacers) {
				String s = "#" + i++ + " -> ";
				s += r.describeRacer();
				System.out.println(s);
			}
			System.out.println("Disabled");
			for (Racer r : this.disabledRacers) {
				String s = "#" + i++ + " -> ";
				s += r.describeRacer();
				System.out.println(s);
			}
			// TEST verify list is empty
			System.out.println("Broken");
			for (Racer r : this.brokenRacers) {
				String s = "#" + i++ + " -> ";
				s += r.describeRacer();
				System.out.println(s);
			}
			// TEST verify list is empty
			System.out.println("Active");
			for (Racer r : this.activeRacers) {
				String s = "#" + i++ + " -> ";
				s += r.describeRacer();
				System.out.println(s);
			}
		}
	}

	public void startRace() throws InterruptedException {
		initRace();
		ExecutorService e;
		synchronized (activeRacers) {
			e = Executors.newFixedThreadPool(this.activeRacers.size());
			synchronized (this) {
				for (Racer racer : activeRacers) {
					e.execute(racer);
				}
			}
		}
		e.shutdown();
		e.awaitTermination(10, TimeUnit.MINUTES);
	}

	@Override
	public void update(Observable o, Object arg) {

		Racer racer = (Racer) o;

		switch ((RacerEvent) arg) {
		case BROKENDOWN:
			synchronized (this.activeRacers) {
				this.activeRacers.remove(racer);
				this.brokenRacers.add(racer);
			}
			break;
		case FINISHED:
			synchronized (this.activeRacers) {
				this.activeRacers.remove(racer);
				this.brokenRacers.remove(racer);
				this.compleatedRacers.add(racer);
			}
			break;
		case REPAIRED:
			synchronized (this.activeRacers) {
				this.brokenRacers.remove(racer);
				this.activeRacers.add(racer);
			}
			break;
		case DISABLED:
			synchronized (this.activeRacers) {
				this.activeRacers.remove(racer);
				this.disabledRacers.add(racer);
			}
			break;
		}
	}
}
