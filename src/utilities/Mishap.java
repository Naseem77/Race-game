package utilities;
 
/**
 * 
 * @author 311549737 Ayman Abdol , 312343668 Nassim Ali 
 *
 */

public class Mishap {
	private boolean fixable;
	private double reductionFactor;
	private int turnToFix;
	/**
	 * ctor
	 * @param fixable
	 * @param turnsToFix
	 * @param reductionFactor
	 */
	public Mishap(boolean fixable,int turnsToFix,double reductionFactor) {
		setFixable(fixable);
		setTurnToFix(turnsToFix);
		setReductionFactor(reductionFactor);
	}
    
	public void nextTurn() {
		this.turnToFix--;
	}
	public void setTurnToFix(int turnToFix) {
		this.turnToFix = turnToFix;
	}
	public void setFixable(boolean fixable) {
		this.fixable = fixable;
	}
	public void setReductionFactor(double reductionFactor) {
		this.reductionFactor = reductionFactor;
	}
	public boolean getFixable() {
		return fixable;
	}
	public int getTurnToFix() {
		return turnToFix;
	}
	public double getReductionFactor() {
		return reductionFactor;
	}
	 
}
