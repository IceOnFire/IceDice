package ice.dice.icedice;

import ice.dice.icedice.config.ConfigurationFactory;
import ice.dice.icedice.ui.swing.IceDiceFrame;

import javax.swing.ImageIcon;

public class Main {
	/** The objects listed in the game specific boxes of the UI. */
	public static final String[]
	                           types = new String[] {"d4", "d6", "d8", "d10", "d12", "d20", "d100"},
	                           challenges = new String[] {"2", "3", "4", "5", "6", "7", "8", "9", "10"};
	
	/** Strings used by the games and the UI's menubar. */
	public static final String
	DND = "Dungeons&Dragons v3.5",
	WOD = "World of Darkness",
	QUIT = "Quit",
	JAVA = "Metal (Java)",
	MOTIF = "Motif",
	SYSTEM = "System default",
	HELP = "Help",
	ABOUT = "About...";
	
	/** Maximum number of dice. */
	public static int MAX_DICE;
	public static String ABOUT_MESSAGE;
	public static String HELP_MESSAGE;
	
	static {
		MAX_DICE = Integer.parseInt(
				ConfigurationFactory.getInstance().getProperty(IceDiceConstants.MAX_DICE));
		ABOUT_MESSAGE =
			ConfigurationFactory.getInstance().getProperty(IceDiceConstants.ABOUT_MESSAGE);
		HELP_MESSAGE =
			ConfigurationFactory.getInstance().getProperty(IceDiceConstants.HELP_MESSAGE);
	}
	
	/**
	 * Main method of the application.
	 */
	public static void main(String[] args) {
//		IceDiceFrame.setDefaultLookAndFeelDecorated(true);
		IceDiceFrame frame = IceDiceFrame.getInstance();
		frame.setIconImage(new ImageIcon("icon.gif").getImage());
		frame.setVisible(true);
	}
}
