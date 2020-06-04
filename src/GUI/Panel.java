package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import factory.RaceBuilder;
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.Main;
import utilities.EnumContainer.Color;

/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */

public class Panel extends JPanel {
	private static Arena arena;
	private static RaceBuilder builder = RaceBuilder.getInstance();;
	private static ArrayList<Racer> racers = new ArrayList<Racer>();
	private static final long serialVersionUID = 1L;
	private Main race;
	private Image image=null;
	ArenaPanel arenaPanel;
	JLabel label = new JLabel();
	Component contents1;
	/**
	 * ctor 
	 * @param win
	 */
	public Panel(Main win) {
		super();
		this.race=win;
		arenaPanel = new ArenaPanel(this);
		race.add(arenaPanel,BorderLayout.EAST);
		race.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	/**
	 * how to draw 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		for (Racer a : racers) {
			a.drawObject(g);
		}

	}
/**
 * build the arena 
 * @param arenaLocation
 * @param arenaType
 * @param length
 * @param maxRacers
 * @throws NoSuchMethodException
 * @throws InstantiationException
 * @throws IllegalAccessException
 * @throws IllegalArgumentException
 * @throws InvocationTargetException
 * @throws SecurityException
 * @throws ClassNotFoundException
 */
	public void ArenaBuild(Image arenaLocation,String arenaType ,int length,int maxRacers ) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, ClassNotFoundException {
		this.image=arenaLocation;
		arena=builder.buildArena(arenaType, length, maxRacers);
		if(maxRacers>8 ) {
			 label.setBounds(0, 0, length+230, 700);
		}
		else {
		    label.setBounds(0,0, length+230, 700);
		}
		repaint();
	}
	/**
	 * make a racer
	 * @param racerLocation
	 * @param racerType
	 * @param racerName
	 * @param maxSpeed
	 * @param acceleration
	 * @param colorS
	 */
	public void RacerBuild(Image racerLocation,String racerType,String racerName,double maxSpeed,double acceleration,String colorS) {
		Color color = Color.valueOf(colorS.toUpperCase());
		try {
			racers.add(builder.buildRacer(this,racerType, racerName,maxSpeed, acceleration, color,racerLocation));
			arena.addRacer(racers.get(racers.size()-1));
			repaint();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			JOptionPane.showMessageDialog(this," unable to build racer!");
		} catch (RacerTypeException e) {
			racers.remove(racers.size()-1);
			JOptionPane.showMessageDialog(null,"[Error] " + e.getMessage());
		} catch (RacerLimitException e) {
			JOptionPane.showMessageDialog(null,"[Error] " + e.getMessage());
		}
	}
	public int getRacersSize() {
		if (racers==null)
			return 0;
		else return racers.size();
	}
	
	public boolean ArenaIsActive() {
		return arena!=null;
	}
	
	public void removeRacers() {
		for(int i=0;i<racers.size();) {
			racers.remove(racers.size()-1);
		}
		
	}
	public void startRace() {
		arena.initRace();
		arena.startRace();
	}
	public ArrayList<Racer> getRacers(){
		return racers;
	}
}
