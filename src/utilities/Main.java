package utilities;



/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */
import java.awt.Color;

import javax.swing.JFrame;

import GUI.Panel;


public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private Panel panel;
	
	/**
	 * 
	 */
	public Main() {
		
		super("Race");
		panel = new Panel(this);
		add(panel);
		panel.setVisible(true);
	}
	
    public static void main(String[] args) {
    	
		Main window = new Main();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1200,700);
		window.setVisible(true);
		window.setResizable(false);
		window.setLocationRelativeTo(null);

	}
}
	

	


