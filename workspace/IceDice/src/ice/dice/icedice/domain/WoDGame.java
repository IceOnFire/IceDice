package ice.dice.icedice.domain;

import ice.dice.icedice.Main;

/**
 * The WoDGame class extends Game to provide support for the
 * 'World of Darkness' rules.
 * 
 * @author ice
 */
class WoDGame extends Game {
	/** Constant strings used only by this game. */
	private final static String CRITICAL = "Critical!";
	private final static String LOST = "Lost";
	
	private int challenge;
	
	/**
	 * Default constructor for the WoDGame.
	 */
	protected WoDGame() {
		super();
		name = Main.WOD;
		diceType = 10;
		for (Die die : dice) {
			die.setType(diceType);
		}
	}
	
	/**
	 * Overrides the abstract method setOption from the Game class to
	 * change the challenge degree.
	 * 
	 * @param value challenge degree to be set.
	 */
	protected void setOption(String value) {
		challenge = Integer.parseInt(value);
	}
	
	/**
	 * Overrides the abstract method updateResult from the Game class to
	 * calculate the result as the total number of successes.
	 */
	public void updateResult() {
		int wins = 0;
		int ones = 0;
		
		for (Die die : dice) {
			if (!die.isResetted()) {
				int faceValue = die.getFaceValue();
				// sums the victories
				if (faceValue >= challenge)
					wins++;
				// sums the ones
				else if (faceValue == 1)
					ones++;
			}
		}
		if (wins == 0 && ones > 0)
			result = CRITICAL;
		else if (wins <= ones)
			result = LOST;
		else
			result = "" + (wins - ones);
	}
}
