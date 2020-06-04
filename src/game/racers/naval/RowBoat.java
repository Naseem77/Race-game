package game.racers.naval;
import java.awt.Image;

import GUI.Panel;
import game.racers.Racer;
import utilities.EnumContainer.*;

/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */

public class RowBoat extends Racer implements NavalRacer {
		private Type type;
		private Team team;
		/**
		 * ctor
		 * @param panel
		 * @param name
		 * @param maxSpeed
		 * @param acceleration
		 * @param color
		 * @param image
		 */
		public RowBoat(Panel panel,String name, double maxSpeed, double acceleration, Color color,Image image) {
			super(panel,name,maxSpeed,acceleration,color,image);
			setType(Type.SKULLING);
			setTeam(Team.DOUBLE);
		}
		@Override
		public String describeSpecific() {
		     return " , Type: "+type+", Team: "+team;
		}
		@Override
		public String className() {	
			return "[RowBoat] ";
		}
		
		public Type getType() {
			return type;
		}
	    
		public boolean setType(Type type) {
			this.type = type;
			return true;
		}
	    
		public Team getTeam() {
			return team;
		}
	    
		public boolean setTeam(Team team) {
			this.team = team;
			return true;
		}
		 
}
