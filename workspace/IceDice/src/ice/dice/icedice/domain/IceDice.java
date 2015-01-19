/**
 * IceDice is a simple Swing application which rolls up to 15
 * dice and calculates their result, according to the game in play.
 * 
 * IceDice supports two types of rolegames:
 * - Dungeons&Dragons v3.5 - there are 7 types of dice to roll, and
 *   the result consists of the sum of the dice faces; and
 * - World of Darkness - there is only one type of die, but
 *   9 challenge degrees can be selected.
 */
package ice.dice.icedice.domain;

import ice.dice.icedice.Main;

import java.util.Vector;

public class IceDice {
	private static IceDice singleton;
	private Game currentGame;
	
	/**
	 * Static method to retrieve the IceDice singleton instance.
	 * 
	 * @return The IceDice singleton.
	 */
	public static IceDice getInstance() {
		if (singleton == null)
			singleton = new IceDice();
		return singleton;
	}
	
	/**
	 * Returns the current game's dice.
	 * 
	 * @return The current game's dice.
	 */
	public Vector<Die> getDice() {
		return currentGame.getDice();
	}
	
	/**
	 * Returns the name of the game in progress.
	 * 
	 * @return The current game's name.
	 */
	public String getGameName() {
		return currentGame.getName();
	}
	
	public Game getCurrentGame() {
		return currentGame;
	}
	
	public void updateGameResult() {
		currentGame.updateResult();
	}
	
	/**
	 * Returns the last result computed.
	 * 
	 * @return The current game's last result.
	 */
	public String getGameResult() {
		return currentGame.getResult();
	}
	
	/**
	 * Updates the game in progress with the new one.
	 * 
	 * @param gameName the new current game's name.
	 */
	public void setCurrentGame(String gameName) {
		if (gameName.equals(Main.DND)) {
			currentGame = new DnDGame();
		} else if (gameName.equals(Main.WOD)) {
			currentGame = new WoDGame();
		}
	}
	
	/**
	 * Sets the current game's first option.
	 * 
	 * @param value The option to be set.
	 */
	public void setFirstOption(String value) {
		currentGame.setOption(value);
	}
	
	/**
	 * Sets the current game's dice number.
	 * 
	 * @param number The number of dice to be set.
	 */
	public void setDiceNumber(int number) {
		currentGame.setDiceNumber(number);
	}
	
	/**
	 * Rolls the current game's dice.
	 */
	public void rollDice() {
		currentGame.rollDice();
	}
}
