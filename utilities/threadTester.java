package utilities;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import factory.RaceBuilder;
import game.arenas.Arena;
import game.arenas.air.AerialArena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.EnumContainer.Color;

public class threadTester {
	private static Arena arena;
	private static RaceBuilder builder = RaceBuilder.getInstance();;
	private static ArrayList<Racer> racers;

	public static void main(String[] args) {
		try {
			arena = builder.buildArena("game.arenas.air.AerialArena", 1450, 4);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			System.out.println("Unable to build arena!");
			arena = new AerialArena();
		}
		racers = new ArrayList<>();
		try {
			racers.add(builder.buildWheeledRacer("game.racers.air.Airplane", "Bob", 220, 10, Color.BLUE, 3));
			racers.add(builder.buildWheeledRacer("game.racers.air.Airplane", "John", 175, 20, Color.BLUE, 3));
			racers.add(builder.buildWheeledRacer("game.racers.air.Airplane", "Frank", 180, 15, Color.BLUE, 3));
			racers.add(builder.buildRacer("game.racers.air.Helicopter", "Matt", 230, 8, Color.RED));
			racers.add(builder.buildWheeledRacer("game.racers.land.Car", "car", 15, 1, Color.GREEN, 3));
			racers.add(builder.buildRacer("game.racers.air.Helicopter", "Alby", 200, 8, Color.BLUE));
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}

		for (Racer racer : racers) {
			try {
				arena.addRacer(racer);
			} catch (RacerLimitException e) {
				System.out.println("[Error] " + e.getMessage());
			} catch (RacerTypeException e) {
				System.out.println("[Error] " + e.getMessage());
			}
		}

		try {
			arena.startRace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		arena.showResults();
	}
}
