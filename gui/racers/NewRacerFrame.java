package gui.racers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import factory.RaceBuilder;
import factory.RacingClassesFinder;
import game.arenas.Arena;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.EnumContainer;

public class NewRacerFrame extends JFrame implements ActionListener {
	private static RaceBuilder builder = RaceBuilder.getInstance();;
	private static ArrayList<Racer> racers;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfArenaLength;
	private JTextField tfMaxRacers;
	private JTextField tfRacerName;
	private JTextField tfMaxSpeed;
	private JTextField tfAcceleration;
	private JComboBox<String> cmbArenas;
	private JComboBox<String> cmbRacers;
	private JComboBox<String> colors;
	private int arenaLength = 1000;
	private int arenaHeight = 700;
	private int maxRacers = 8;
	private String chosenArena = null;
	private Arena arena = null;
	private int racersNumber = 0;
	private ImageIcon racersImages[] = null;
	private JFrame infoTable = null;
	private boolean raceStarted = false;

	private boolean raceFinished = false;

	public NewRacerFrame() {
		super("Race");
		this.setContentPane(getMainPanel());
		this.pack();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		this.setLocation(x, y);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {

		case "Build arena":
			if (raceStarted && !raceFinished) {
				JOptionPane.showMessageDialog(this, "Race started! Please wait.");
				return;
			}
			try {
				arenaLength = Integer.parseInt(tfArenaLength.getText());
				maxRacers = Integer.parseInt(tfMaxRacers.getText());
				if (arenaLength < 100 || arenaLength > 3000 || maxRacers <= 0 || maxRacers > 20)
					throw new Exception();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Invalid input values! Please try again.");
				return;
			}

			racersNumber = 0;
			raceStarted = raceFinished = false;
			int newHeight = (maxRacers + 1) * 60;
			if (newHeight > 700)
				this.arenaHeight = newHeight;
			else
				this.arenaHeight = 700;

			racers = new ArrayList<>();
			racersImages = new ImageIcon[maxRacers];
			chosenArena = (String) cmbArenas.getSelectedItem();
			try {
				if (chosenArena.equals("AerialArena")) {
					arena = builder.buildArena("game.arenas.air.AerialArena", arenaLength, maxRacers);
				} else if (chosenArena.equals("LandArena")) {
					arena = builder.buildArena("game.arenas.land.LandArena", arenaLength, maxRacers);
				} else if (chosenArena.equals("NavalArena")) {
					arena = builder.buildArena("game.arenas.naval.NavalArena", arenaLength, maxRacers);
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
			updateFrame();
			break;

		case "Add racer":
			if (raceFinished) {
				JOptionPane.showMessageDialog(this, "Race finished! Please build a new arena.");
				return;
			}
			if (raceStarted) {
				JOptionPane.showMessageDialog(this, "Race started! No racers can be added.");
				return;
			}
			if (arena == null) {
				JOptionPane.showMessageDialog(this, "Please build arena first!");
				return;
			}
			if (racersNumber == maxRacers) {
				JOptionPane.showMessageDialog(this, "No more racers can be added!");
				return;
			}
			String name;
			double maxSpeed;
			double acceleration;
			try {
				name = tfRacerName.getText();
				maxSpeed = Double.parseDouble(tfMaxSpeed.getText());
				acceleration = Double.parseDouble(tfAcceleration.getText());
				if (name.isEmpty() || maxSpeed <= 0 || acceleration <= 0)
					throw new Exception();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Invalid input values! Please try again.");
				return;
			}

			String racerType = (String) cmbRacers.getSelectedItem();

			String color = (String) colors.getSelectedItem();
			EnumContainer.Color col = null;
			if (color.equals("Red"))
				col = EnumContainer.Color.RED;
			else if (color.equals("Black"))
				col = EnumContainer.Color.BLACK;
			else if (color.equals("Green"))
				col = EnumContainer.Color.GREEN;
			else if (color.equals("Blue"))
				col = EnumContainer.Color.BLUE;
			else if (color.equals("Yellow"))
				col = EnumContainer.Color.YELLOW;

			String racerClass = null;
			if (racerType.equals("Helicopter"))
				racerClass = "game.racers.air.Helicopter";
			else if (racerType.equals("Airplane"))
				racerClass = "game.racers.air.Airplane";
			else if (racerType.equals("Car"))
				racerClass = "game.racers.land.Car";
			else if (racerType.equals("Horse"))
				racerClass = "game.racers.land.Horse";
			else if (racerType.equals("Bicycle"))
				racerClass = "game.racers.land.Bicycle";
			else if (racerType.equals("SpeedBoat"))
				racerClass = "game.racers.naval.SpeedBoat";
			else if (racerType.equals("RowBoat"))
				racerClass = "game.racers.naval.RowBoat";

			try {
				Racer racer = builder.buildRacer(racerClass, name, maxSpeed, acceleration, col);
				arena.addRacer(racer);
				arena.initRace();
				racers.add(racer);
			} catch (RacerTypeException ex) {
				JOptionPane.showMessageDialog(this, "Recer does not fit to arena! Choose another racer.");
				return;
			} catch (Exception ex) {
				System.out.println(ex);
			}

			racersImages[racersNumber] = new ImageIcon(new ImageIcon("icons/" + racerType + color + ".png").getImage()
					.getScaledInstance(70, 70, Image.SCALE_DEFAULT));
			racersNumber++;

			updateFrame();
			break;

		case "Srart race":
			if (arena == null || racersNumber == 0) {
				JOptionPane.showMessageDialog(this, "Please build arena first and add racers!");
				return;
			}
			if (raceFinished) {
				JOptionPane.showMessageDialog(this, "Race finished! Please build a new arena and add racers.");
				return;
			}
			if (raceStarted) {
				JOptionPane.showMessageDialog(this, "Race already started!");
				return;
			}
			try {
				raceStarted = true;
				(new Thread() {
					public void run() {
						while (arena.hasActiveRacers()) {
							try {
								Thread.sleep(30);
							} catch (InterruptedException ex) {
								ex.printStackTrace();
							}
							updateFrame();
						}
						updateFrame();
						raceFinished = true;
					}
				}).start();
				arena.startRace();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			break;

		case "Show info":
			if (arena == null || racersNumber == 0) {
				JOptionPane.showMessageDialog(this, "Please build arena first and add racers!");
				return;
			}
			String[] columnNames = { "Racer name", "Current speed", "Max speed", "Current X location", "Finished" };
			String[][] data = new String[racersNumber][5];
			int i = 0;
			for (Racer r : arena.getCompleatedRacers()) {
				data[i][0] = r.getName();
				data[i][1] = "" + r.getCurrentSpeed();
				data[i][2] = "" + r.getMaxSpeed();
				data[i][3] = "" + r.getLocation().getX();
				data[i][4] = "COMP";
				i++;
			}

			for (Racer r : arena.getActiveRacers()) {
				data[i][0] = r.getName();
				data[i][1] = "" + r.getCurrentSpeed();
				data[i][2] = "" + r.getMaxSpeed();
				data[i][3] = "" + r.getLocation().getX();
				data[i][4] = "ACT";
				i++;
			}
			for (Racer r : arena.getBrokenRacers()) {
				data[i][0] = r.getName();
				data[i][1] = "" + r.getCurrentSpeed();
				data[i][2] = "" + r.getMaxSpeed();
				data[i][3] = "" + r.getLocation().getX();
				data[i][4] = "BROK";
				i++;
			}
			for (Racer r : arena.getDisabledRacers()) {
				data[i][0] = r.getName();
				data[i][1] = "" + r.getCurrentSpeed();
				data[i][2] = "" + r.getMaxSpeed();
				data[i][3] = "" + r.getLocation().getX();
				data[i][4] = "DIS";
				i++;
			}

			JTable table = new JTable(data, columnNames);
			table.setPreferredScrollableViewportSize(table.getPreferredSize());
			JScrollPane scrollPane = new JScrollPane(table);

			JPanel tabPan = new JPanel();
			// tabPan.setLayout(new GridLayout(1,0));
			tabPan.add(scrollPane);

			if (infoTable != null)
				infoTable.dispose();
			infoTable = new JFrame("Racers information");
			infoTable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			infoTable.setContentPane(tabPan);
			infoTable.pack();
			infoTable.setVisible(true);

			break;
		}
	}

	public JPanel getArenaPanel() {
		JPanel arenaPanel = new JPanel();
		arenaPanel.setLayout(null);
		arenaPanel.setPreferredSize(new Dimension(arenaLength + 80, arenaHeight));
		ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("icons/" + chosenArena + ".jpg").getImage()
				.getScaledInstance(arenaLength + 80, arenaHeight, Image.SCALE_DEFAULT));
		JLabel picLabel1 = new JLabel(imageIcon1);
		picLabel1.setLocation(0, 0);
		picLabel1.setSize(arenaLength + 80, arenaHeight);
		arenaPanel.add(picLabel1);

		for (int i = 0; i < racersNumber; i++) {
			JLabel picLabel2 = new JLabel(racersImages[i]);
			picLabel2.setLocation((int) racers.get(i).getLocation().getX() + 5,
					(int) racers.get(i).getLocation().getY());
			picLabel2.setSize(70, 70);
			picLabel1.add(picLabel2);
		}

		return arenaPanel;
	}

	public JPanel getControlsPanel() {
		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(null);
		controlsPanel.setPreferredSize(new Dimension(140, arenaHeight));

		cmbArenas = new JComboBox<>();
		int i = 0;
		for (String string : RacingClassesFinder.getInstance().getArenasNamesList()) {
			cmbArenas.addItem(string);
			if (i == 0)
				cmbArenas.setSelectedItem(string);
			i++;
		}

		if (chosenArena != null)
			cmbArenas.setSelectedItem(chosenArena);

		// controlsPanel.setAlignmentX(0.0f);
		JLabel l1 = new JLabel("Choose arena:");
		controlsPanel.add(l1);
		l1.setLocation(10, 20);
		l1.setSize(100, 10);
		controlsPanel.add(cmbArenas);
		cmbArenas.setLocation(10, 40);
		cmbArenas.setSize(100, 20);

		JLabel l2 = new JLabel("Arena length:");
		l2.setLocation(10, 75);
		l2.setSize(100, 10);
		controlsPanel.add(l2);

		tfArenaLength = new JTextField("" + arenaLength);
		tfArenaLength.setLocation(10, 95);
		tfArenaLength.setSize(100, 25);
		controlsPanel.add(tfArenaLength);

		JLabel l3 = new JLabel("Max racers number:");
		l3.setLocation(10, 135);
		l3.setSize(150, 10);
		controlsPanel.add(l3);

		tfMaxRacers = new JTextField("" + maxRacers);
		tfMaxRacers.setLocation(10, 155);
		tfMaxRacers.setSize(100, 25);
		controlsPanel.add(tfMaxRacers);

		JButton buildArenaBut = new JButton("Build arena");
		buildArenaBut.setLocation(10, 195);
		buildArenaBut.setSize(100, 30);
		buildArenaBut.addActionListener(this);
		controlsPanel.add(buildArenaBut);

		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
		sep.setLocation(0, 240);
		sep.setSize(150, 10);
		controlsPanel.add(sep);

		cmbRacers = new JComboBox<>();
		for (String string : RacingClassesFinder.getInstance().getRacersNamesList()) {
			cmbRacers.addItem(string);
		}
		JLabel l4 = new JLabel("Choose racer:");
		controlsPanel.add(l4);
		l4.setLocation(10, 260);
		l4.setSize(100, 10);

		controlsPanel.add(cmbRacers);
		cmbRacers.setLocation(10, 280);
		cmbRacers.setSize(100, 20);

		JLabel l5 = new JLabel("Choose color:");
		controlsPanel.add(l5);
		l5.setLocation(10, 315);
		l5.setSize(100, 10);

		colors = new JComboBox<>();
		colors.addItem("Black");
		colors.addItem("Green");
		colors.addItem("Blue");
		colors.addItem("Red");
		colors.addItem("Yellow");
		controlsPanel.add(colors);
		colors.setLocation(10, 335);
		colors.setSize(100, 20);

		JLabel l6 = new JLabel("Racer name:");
		l6.setLocation(10, 370);
		l6.setSize(150, 10);
		controlsPanel.add(l6);

		tfRacerName = new JTextField("R");
		tfRacerName.setLocation(10, 390);
		tfRacerName.setSize(100, 25);
		controlsPanel.add(tfRacerName);

		JLabel l7 = new JLabel("Max speed:");
		l7.setLocation(10, 425);
		l7.setSize(150, 14);
		controlsPanel.add(l7);

		tfMaxSpeed = new JTextField("120");
		tfMaxSpeed.setLocation(10, 445);
		tfMaxSpeed.setSize(100, 25);
		controlsPanel.add(tfMaxSpeed);

		JLabel l8 = new JLabel("Acceleration:");
		l8.setLocation(10, 485);
		l8.setSize(150, 10);
		controlsPanel.add(l8);

		tfAcceleration = new JTextField("15");
		tfAcceleration.setLocation(10, 505);
		tfAcceleration.setSize(100, 25);
		controlsPanel.add(tfAcceleration);

		JButton addRacerBut = new JButton("Add racer");
		addRacerBut.setLocation(10, 545);
		addRacerBut.setSize(100, 30);
		addRacerBut.addActionListener(this);
		controlsPanel.add(addRacerBut);

		JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
		sep2.setLocation(0, 590);
		sep2.setSize(150, 10);
		controlsPanel.add(sep2);

		JButton startRaceBut = new JButton("Srart race");
		startRaceBut.setLocation(10, 605);
		startRaceBut.setSize(100, 30);
		startRaceBut.addActionListener(this);
		controlsPanel.add(startRaceBut);

		JButton printInfoBut = new JButton("Show info");
		printInfoBut.setLocation(10, 650);
		printInfoBut.setSize(100, 30);
		printInfoBut.addActionListener(this);
		controlsPanel.add(printInfoBut);

		return controlsPanel;
	}

	public JPanel getMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		mainPanel.add(getArenaPanel(), BorderLayout.WEST);
		mainPanel.add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.CENTER);
		mainPanel.add(getControlsPanel(), BorderLayout.EAST);
		return mainPanel;
	}

	private void updateFrame() {
		this.setContentPane(getMainPanel());
		this.pack();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		this.setLocation(x, y);
		this.setVisible(true);
	}

}