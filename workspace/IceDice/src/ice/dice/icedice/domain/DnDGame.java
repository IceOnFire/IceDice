package ice.dice.icedice.domain;

import ice.dice.icedice.Main;

/**
 * The DnDGame class extends Game to provide support for the
 * 'Dungeons&Dragons v3.5' rules.
 * 
 * @author ice
 */
class DnDGame extends Game {
	/**
	 * Default constructor for the DnDGame.
	 */
	protected DnDGame() {
		super();
		name = Main.DND;
	}
	
	/**
	 * Overrides the abstract method setOption from the Game class to
	 * change the dice type.
	 * 
	 * @param value The dice type to be set.
	 */
	protected void setOption(String value) {
		diceType = Integer.parseInt(value.substring(1));
		for (Die die : dice) {
			die.setType(diceType);
		}
	}
	
	/**
	 * Overrides the abstract method updateResult from the Game class to
	 * calculate the result as the dice' sum.
	 */
	public void updateResult() {
		int sum = 0;
		
		for (Die die : dice) {
			if (!die.isResetted()) {
				sum += die.getFaceValue();
			}
		}
		result = "" + sum;
	}
}
