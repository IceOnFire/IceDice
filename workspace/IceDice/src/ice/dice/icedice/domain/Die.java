package ice.dice.icedice.domain;


/**
 * The die is an object which can roll itself to produce
 * a random integer number. Different types of dice have
 * a different number of faces.
 * 
 * @author Ice
 */
public class Die {
	private int type;
	private int faceValue;
	
	public void setType(int type) {
		this.type = type;
	}
	
	public void reset() {
		faceValue = 0;
	}
	
	public boolean isResetted() {
		return faceValue == 0;
	}
	
	/**
	 * Rolls the die, modifying the die's face with a
	 * random integer number.
	 */
	public void roll() {
		faceValue = (int)(Math.random()*type) + 1;
	}
	
	/**
	 * Returns this die's face.
	 * 
	 * @return The die's face.
	 */
	public int getFaceValue() {
		return faceValue;
	}
	
	/**
	 * Main method for testing.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Die die = new Die();
		for (int i=0; i<30; i++) {
			die.setType(10);
			die.roll();
			System.out.print(die.getFaceValue() + " ");
		}
	}
}