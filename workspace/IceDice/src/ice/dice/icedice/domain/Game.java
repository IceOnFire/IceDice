package ice.dice.icedice.domain;

import ice.dice.icedice.Main;

import java.util.Vector;

/**
 * The Game abstract class represents a generic Game that you can
 * play with using IceDice.
 * 
 * @author ice
 */
public abstract class Game {
	/** Attributes shared by every subclass. */
	protected String name;
	protected int diceType;
	protected Vector<Die> dice;
	protected int diceNumber;
	protected String result;
	
	/** Abstract methods to be overridden. */
	protected abstract void setOption(String value);
	public abstract void updateResult();
	
	/**
	 * Default constructor for the Game class.
	 */
	protected Game() {
		dice = new Vector<Die>();
		for (int i=0; i<Main.MAX_DICE; i++) {
			Die die = new Die();
			dice.add(die);
		}
	}
	
	/**
	 * Returns this game's name.
	 * 
	 * @return The game's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the game's list of dice.
	 * @return The dice' list.
	 */
	public Vector<Die> getDice() {
		return dice;
	}
	
	/**
	 * Adds n dice to this game's vector of dice.
	 * 
	 * @param number The dice number.
	 */
	protected void setDiceNumber(int number) {
		diceNumber = number;
	}
	
	protected void rollDice() {
		for (Die die : dice) {
			die.reset();
		}
		
		for (int i=0; i<diceNumber; i++) {
			Die die = dice.get(i);
			die.roll();
		}
		updateResult();
	}
	
	/**
	 * Returns the result of the roll.
	 * 
	 * @return The result.
	 */
	public String getResult() {
		return result;
	}
}
