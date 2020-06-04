package GUI;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableRow;
import factory.RacingClassesFinder;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */

public class ArenaPanel extends JPanel implements ActionListener {
   
	private Boolean bol;
	private Panel panel;
	private JButton BuildButton;
	private JTextField length,maxRacers;
	private JComboBox<String> comboArena;
	private JLabel labArena,labLength,labMaxRacers;
	private JButton StartButton,InfoButton;
	private JButton AddButton;
	private JTextField name,maxSpeed,acceleration;
	private JComboBox<String> comboRacer,comboColor;
	private JLabel labRacer,labColor,labName,labMaxSpeed,labAcceleration;
	private final String[] colors= {"Black","Red","Yellow","Blue","Green"};
    private JLabel label = new JLabel();
	private static final long serialVersionUID = 1L;

	/**
	 * ctor og gui to make panel to the frame
	 * @param pane
	 */
	public ArenaPanel(Panel pane) {
        super(new BorderLayout());
		this.panel=pane;
		labArena = new JLabel("Choose Arena:");
		comboArena = new JComboBox();
		comboArena.addActionListener(this);
		labLength = new JLabel("Arena length:");
		length= new JTextField("1000");
		labMaxRacers = new JLabel("Max Racers:");
		JLabel empty = new JLabel();
		JLabel empty1 = new JLabel();
		maxRacers = new JTextField("8");
		BuildButton = new JButton("Build arena");
		labRacer = new JLabel("Choose Racer:");
		comboRacer = new JComboBox();
		labColor = new JLabel("Choose Color:");
		comboColor = new JComboBox<String>(colors);
		labName = new JLabel("Racer name:");
		name = new JTextField();
		labMaxSpeed = new JLabel("Max speed:");
		maxSpeed = new JTextField();
		labAcceleration = new JLabel("Acceleration:");
		acceleration = new JTextField();
		AddButton = new JButton("Add racer");
		AddButton.addActionListener(this);
		StartButton = new JButton("Start race");
		InfoButton = new JButton("Show info");
		StartButton.addActionListener(this);
		InfoButton.addActionListener(this);
		comboArena.addActionListener(this);
		BuildButton.addActionListener(this);
		for(String string : RacingClassesFinder.getInstance().getRacersNamesList()) {
			comboRacer.addItem(string);
		}
		
		for(String string : RacingClassesFinder.getInstance().getArenasNamesList()) {
			comboArena.addItem(string);
		}
		add(labArena);
		add(comboArena);
		add(labLength);
		add(length);
		add(labMaxRacers);
		add(maxRacers);
		add(BuildButton);
		add(empty);
		setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		setLayout(new GridLayout(0,1));
		add(labRacer);
		add(comboRacer);
		add(labColor);
		add(comboColor);
		add(labName);
		add(name);
		add(labMaxSpeed);
		add(maxSpeed);
		add(labAcceleration);
		add(acceleration);
		add(AddButton);
		add(empty1);
		add(StartButton);
		add(InfoButton);
	}
	
	/**
	 * when the user choose action 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String arenaName = comboArena.getItemAt(comboArena.getSelectedIndex()),
			   Location = "icons\\";
		int _maxRacers= Integer.parseInt(maxRacers.getText());
		int _length= Integer.parseInt(length.getText());
		if(arg0.getSource()==BuildButton) {
			panel.removeRacers();
			String arenaType=null;
			Location+=(arenaName+".jpg"); 
			if(_length>3000||_length<100||_maxRacers<1||_maxRacers>20) {
				JOptionPane.showMessageDialog(this,"Invailed values! ");
			}
			else {
			try {
				setArena(Location,RacingClassesFinder.getInstance().getArenasList().get(comboArena.getSelectedIndex()),_length,_maxRacers);
				bol=true;
			} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException | ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(this," cant build arena");
				e1.printStackTrace();
			}	
			}
			
		} 
		if (arg0.getSource()==AddButton) {
			if(bol==true) {
			if(panel.ArenaIsActive()) {
				if (panel.getRacersSize()<_maxRacers) {
					String racer = comboRacer.getItemAt(comboRacer.getSelectedIndex());
					String colorS = comboColor.getItemAt(comboColor.getSelectedIndex());	
					Location+=(racer+colorS+".png");
					if(!(maxSpeed.getText().equals(""))&&!(acceleration.getText().equals(""))&&!(name.getText().equals(""))) {
				    String _name = name.getText();
				    double _maxSpeed = Double.parseDouble(maxSpeed.getText());
				    double _acceleration = Double.parseDouble(acceleration.getText());
				    addRacer(Location,RacingClassesFinder.getInstance().getRacersList().get(comboRacer.getSelectedIndex()),_name,_maxSpeed,_acceleration,colorS);   
					}
					else {
						JOptionPane.showMessageDialog(this,"Please fill all the fields above");
					}
				}
			
			}
			else {
				JOptionPane.showMessageDialog(this,"Please build arena  !");
			}
			}
		}
		if(arg0.getSource()==StartButton) {
			panel.startRace();
			bol=false;
		}
		
		if(arg0.getSource()==InfoButton) {
			JFrame window = new JFrame("Racers Info");
			
			String[] columnNames = {"Racer Name","Current Speed","Max Speed","Current Location","Finished"};
			 
			 TableColumn column=null;
			 TableRow row=null;
			    
			Object [][] racers = new Object[panel.getRacersSize()][5]; 
			
			for(int i=0;i<panel.getRacersSize();i++) {	
					racers[i][0]=panel.getRacers().get(i).getName();
					racers[i][1]=panel.getRacers().get(i).getCurrentSpeed();
					racers[i][2]=panel.getRacers().get(i).getMaxSpeed();
					racers[i][3]=panel.getRacers().get(i).getCurrentLocation().getX();
					racers[i][4]=panel.getRacers().get(i).Checkstate();							
			}
			
			JTable table = new JTable(racers,columnNames);
			    
		    for (int i = 0; i < 5; i++) {
		        column = table.getColumnModel().getColumn(i);
		        column.setPreferredWidth(200);
		    } 
		    table.getTableHeader().setBackground(Color.orange);
		    window.setLayout(new BorderLayout());
		    window.add(table.getTableHeader(), BorderLayout.PAGE_START);
		    window.add(table, BorderLayout.CENTER);
			window.setVisible(true);
			window.setLocationRelativeTo(null);
			window.pack();
			window.setResizable(false);
			
		}
	}
		/**
		 * set the arena above to the user 
		 * @param arenaLocation
		 * @param arenaType
		 * @param _length
		 * @param _maxRacers
		 * @throws NoSuchMethodException
		 * @throws InstantiationException
		 * @throws IllegalAccessException
		 * @throws IllegalArgumentException
		 * @throws InvocationTargetException
		 * @throws SecurityException
		 * @throws ClassNotFoundException
		 */
	 public void setArena(String arenaLocation,String arenaType,int _length,int _maxRacers ) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, ClassNotFoundException {
		 try {
				panel.ArenaBuild(ImageIO.read(new File(arenaLocation)),arenaType,_length,_maxRacers);
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(this,"faild loading ");
			} 	 
	 }
	 /**
	  * add racer to the array list 
	  * @param racerLocation
	  * @param racerType
	  * @param name
	  * @param maxSpeed
	  * @param acceleration
	  * @param colorS
	  */
	 public void addRacer(String racerLocation,String racerType,String name,double maxSpeed,double acceleration,String colorS) {
		 try {
			 panel.RacerBuild(ImageIO.read(new File(racerLocation)), racerType, name, maxSpeed, acceleration,colorS);
		 }
		 catch(IOException e) {
			 JOptionPane.showMessageDialog(this,"faild loading ");
		 } 
	 }

}

