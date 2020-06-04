/**
 * 
 */
package utilities;

import game.GameEngine;
import game.arenas.AerialArena;
import game.arenas.LandArena;
import game.arenas.NavalArena;
import game.racers.Airplane;
import game.racers.Car;
import game.racers.Helicopter;
import game.racers.Horse;
import game.racers.RowBoat;
import game.racers.SpeedBoat;

/**
 * @author Bar Ohayon
 *
 */
public class Program {
	public static void main(String[] args) {
		Point start = new Point(-150, -5);
		System.out.println("start = " + start);
		Point finish = new Point(15000, 0);
		AerialArena air = new AerialArena(start, finish);
		NavalArena sea = new NavalArena(start, finish);
		LandArena land = new LandArena(start, finish);
		System.out.print("setX(-10): ");
		System.out.println(finish.setX(-10));
		System.out.println("finish = " + finish);
		System.out.print("setX(10): ");
		System.out.println(finish.setX(10));
		System.out.println("air.finish = " + air.getFinish());
		GameEngine game = GameEngine.getInstance();
		System.out.println("----------");
		System.out.println("arena type = " + game.getArenaType());
		System.out.print("Set AerialArena: ");
		System.out.println(game.setArena(air));
		System.out.println("arena type = " + game.getArenaType());
		System.out.print("add Plane: ");
		System.out.println(game.addRacer(new Airplane("plane", 120, 5)));
		System.out.print("add Heli: ");
		System.out.println(game.addRacer(new Helicopter("chopper", 110, 15)));
		System.out.print("add Car: ");
		System.out.println(game.addRacer(new Car("car", 100, 10)));
		System.out.print("add SpeedBoat: ");
		System.out.println(game.addRacer(new SpeedBoat("speed", 100, 10)));
		game.initRace();
		game.startRace();
		System.out.println("----------");
		System.out.print("Set NavalArena: ");
		System.out.println(game.setArena(sea));
		System.out.println("arena type = " + game.getArenaType());
		System.out.print("add SpeedBoat: ");
		System.out.println(game.addRacer(new SpeedBoat("sb1", 100, 25)));
		System.out.print("add SpeedBoat: ");
		System.out.println(game.addRacer(new SpeedBoat("sb2", 180, 5)));
		System.out.print("add SpeedBoat: ");
		System.out.println(game.addRacer(new SpeedBoat("sb3", 120, 17)));
		System.out.print("add SpeedBoat: ");
		System.out.println(game.addRacer(new SpeedBoat("sb4", 80, 25)));
		System.out.print("add RowBoat: ");
		System.out.println(game.addRacer(new RowBoat("rb1", 95, 50)));
		System.out.print("add RowBoat: ");
		System.out.println(game.addRacer(new RowBoat("rb2", 130, 12)));
		System.out.print("add RowBoat: ");
		System.out.println(game.addRacer(new RowBoat("rb3", 150, 9)));
		System.out.print("add RowBoat: ");
		System.out.println(game.addRacer(new RowBoat("rb4", 140, 35)));
		game.initRace();
		game.startRace();
		System.out.println("----------");
		System.out.print("Set LandArena: ");
		System.out.println(game.setArena(land));
		System.out.println("arena type = " + game.getArenaType());
		System.out.print("add Car: ");
		System.out.println(game.addRacer(new Car("car1", 100, 2)));
		System.out.print("add RowBoat: ");
		System.out.println(game.addRacer(new RowBoat("rb4", 140, 35)));
		System.out.print("add Car: ");
		System.out.println(game.addRacer(new Car("car2", 120, 10)));
		System.out.print("add Car: ");
		System.out.println(game.addRacer(new Car("car3", 200, 5)));
		System.out.print("add Car: ");
		System.out.println(game.addRacer(new Car("car4", 130, 7)));
		System.out.print("add horse: ");
		System.out.println(game.addRacer(new Horse("horse1", 80, 25)));
		System.out.print("add horse: ");
		System.out.println(game.addRacer(new Horse("horse2", 84, 20)));
		System.out.print("add horse: ");
		System.out.println(game.addRacer(new Horse("horse3", 73, 4)));
		System.out.print("add horse: ");
		System.out.println(game.addRacer(new Horse("horse4", 99, 8)));
		System.out.print("add horse: ");
		System.out.println(game.addRacer(new Horse("horse5", 95, 12)));
		game.initRace();
		game.startRace();
	}

}
