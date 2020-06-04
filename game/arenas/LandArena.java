package game.arenas;

import java.util.ArrayList;

import game.racers.Car;
import game.racers.Horse;
import utilities.Point;

public class LandArena {

	public static final int MAX_RACERS = 8;
	private static final double FRICTION = 0.5;
	private ArrayList<Car> cars;
	private ArrayList<Horse> horses;
	private ArrayList<Object> finished;
	private Point start;
	private Point finish;

	public LandArena(Point start, Point finish) {

		this.setStart(new Point(start));
		this.setFinish(new Point(finish));
		this.setCars(new ArrayList<>());
		this.setHorses(new ArrayList<>());
		this.setFinished(new ArrayList<>());

	}
	
	public boolean addCar(Car newRacer) {
		if (this.isFull())
			return false;
		this.cars.add(newRacer);
		return true;
	}

	public boolean addHorse(Horse newRacer) {
		if (this.isFull())
			return false;
		this.horses.add(newRacer);
		return true;
	}

	public int crossFinishLine(Car car) {
		this.finished.add(car);
		return this.finished.size();
	}

	public int crossFinishLine(Horse horse) {
		this.finished.add(horse);
		return this.finished.size();
	}

	public Point getFinish() {
		return this.finish;
	}

	public ArrayList<Object> getFinished() {
		return this.finished;
	}

	private int getNumOfRacers() {
		return this.cars.size() + this.horses.size();
	}

	public Point getStart() {
		return this.start;
	}

	public boolean hasActiveRacer() {
		return ((this.cars.size() > 0) || (this.horses.size() > 0));
	}

	public void initRace() {
		for (Car car : this.cars) {
			car.initRace(this, this.start, this.finish);
			// this.activeRacers++;
		}
		for (Horse horse : this.horses) {
			horse.initRace(this, this.start, this.finish);
		}
	}

	private boolean isFull() {
		return this.getNumOfRacers() == AerialArena.MAX_RACERS;
	}

	public void playTurn() {
		for (Car car : this.cars) {
			car.move(LandArena.FRICTION);
		}
		for (Horse horse : this.horses) {
			horse.move(LandArena.FRICTION);
		}
		for (Object racer : this.finished) {
			this.cars.remove(racer);
			this.horses.remove(racer);
		}
	}

	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}

	public void setFinish(Point finish) {
		this.finish = finish;
	}

	public void setFinished(ArrayList<Object> finished) {
		this.finished = finished;
	}

	public void setHorses(ArrayList<Horse> horses) {
		this.horses = horses;
	}

	public void setStart(Point start) {
		this.start = start;
	}
}
