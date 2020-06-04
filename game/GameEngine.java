package game;

import game.arenas.AerialArena;
import game.arenas.ArenaType;
import game.arenas.LandArena;
import game.arenas.NavalArena;
import game.racers.Airplane;
import game.racers.Car;
import game.racers.Helicopter;
import game.racers.Horse;
import game.racers.RowBoat;
import game.racers.SpeedBoat;

public class GameEngine {

	private static GameEngine instance;

	public static GameEngine getInstance() {
		if (instance == null) {
			instance = new GameEngine();
		}
		return instance;
	}

	private AerialArena airArena;
	private LandArena landArena;
	private NavalArena navalArena;
	private ArenaType activeArena;

	private GameEngine() {
	}

	public boolean addRacer(Object newRacer) {
		switch (this.getArenaType()) {
		case AERIALARENA:
			if (newRacer instanceof Airplane) {
				return this.airArena.addAirplane((Airplane) newRacer);
			}
			if (newRacer instanceof Helicopter) {
				return this.airArena.addHelicopter((Helicopter) newRacer);
			}
			break;
		case LANDARENA:
			if (newRacer instanceof Car) {
				return this.landArena.addCar((Car) newRacer);
			}
			if (newRacer instanceof Horse) {
				return this.landArena.addHorse((Horse) newRacer);
			}
			break;
		case NEVALARENA:
			if (newRacer instanceof SpeedBoat) {
				return this.navalArena.addSpeedBoat((SpeedBoat) newRacer);
			}
			if (newRacer instanceof RowBoat) {
				return this.navalArena.addRowBoat((RowBoat) newRacer);
			}
			break;
		default:
			break;
		}
		return false;
	}

	public ArenaType getArenaType() {
		return activeArena;
	}

	public void initRace() {
		switch (this.getArenaType()) {
		case AERIALARENA:
			this.airArena.initRace();
			break;
		case LANDARENA:
			this.landArena.initRace();
			break;
		case NEVALARENA:
			this.navalArena.initRace();
			break;
		default:
			break;
		}
	}

	public boolean setArena(Object arena) {
		if (arena instanceof AerialArena) {
			this.airArena = (AerialArena) arena;
			this.setArenaType(ArenaType.AERIALARENA);
			return true;
		}
		if (arena instanceof NavalArena) {
			this.navalArena = (NavalArena) arena;
			this.setArenaType(ArenaType.NEVALARENA);
			return true;
		}
		if (arena instanceof LandArena) {
			this.landArena = (LandArena) arena;
			this.setArenaType(ArenaType.LANDARENA);
			return true;
		}
		return false;
	}

	// private! 
	// this is determined by arena type,
	// and should only be changed by an arena change
	private void setArenaType(ArenaType arenaType) {
		this.activeArena = arenaType;
	}

	public void startRace() {
		switch (this.getArenaType()) {
		case AERIALARENA:
			while (this.airArena.hasActiveRacer()) {
				this.airArena.playTurn();
			}
			System.out.println("Aerial Race ended!");
			for (Object o : airArena.getFinished()) {
				int place = airArena.getFinished().indexOf(o) + 1;
				System.out.println("#" + place + ": " + o);
			}
			break;
		case LANDARENA:
			while (this.landArena.hasActiveRacer()) {
				this.landArena.playTurn();
			}
			System.out.println("Land Race ended!");
			for (Object o : landArena.getFinished()) {
				int place = landArena.getFinished().indexOf(o) + 1;
				System.out.println("#" + place + ": " + o);
			}
			break;
		case NEVALARENA:
			while (this.navalArena.hasActiveRacer()) {
				this.navalArena.playTurn();
			}
			System.out.println("Naval Race ended!");
			for (Object o : navalArena.getFinished()) {
				int place = navalArena.getFinished().indexOf(o) + 1;
				System.out.println("#" + place + ": " + o);
			}
			break;
		default:
			break;
		}

	}

}
