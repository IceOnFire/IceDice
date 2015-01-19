package ice.dice.icedice.ui.swt;


import ice.dice.icedice.IceDiceConstants;
import ice.dice.icedice.Main;
import ice.dice.icedice.config.ConfigurationFactory;
import ice.dice.icedice.domain.Die;
import ice.dice.icedice.domain.IceDice;

import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class IceDiceShell implements ShellListener {
	
	/** Private components. */
	private static Vector<DieButton> dieButtons;
	static Composite dicePanel;
	private static Label gameLabel, resultLabel;
	
	/** Components accessed by the listeners. */
	static Combo gameBox, numberBox;
	static Button rollButton;
	
	/**
	 * Initializes the menubar.
	 */
	private static void createMenuBar(Shell shell) {
		
	}
	
	private static void init(Shell shell) {
		shell.setLayout(new RowLayout(SWT.VERTICAL));
		
		/* menubar with a few options */
//		shell.setMenuBar(new IceDiceMenuBar(shell));
//		createMenuBar(shell);
		
		dieButtons = new Vector<DieButton>();
		
		/* first row of the UI: panel for the game-specific option */
		Composite firstRow = new Composite(shell, 0);
		firstRow.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		/* game-specific label */
		gameLabel = new Label(firstRow, SWT.CENTER);
		
		/* first option selection box */
		gameBox = new Combo(firstRow, SWT.DROP_DOWN);
		
		/* second row of the UI: dice number panel */
		Composite secondRow = new Composite(shell, 0);
		secondRow.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		/* dice number label */
		Label numberLabel = new Label(secondRow, SWT.CENTER);
		numberLabel.setText("Number:");
		
		/* dice number selection box */
		String[] number = new String[Main.MAX_DICE];
		for (int i=0; i<Main.MAX_DICE; i++)
			number[i] = new Integer(i+1).toString();
		numberBox = new Combo(secondRow, SWT.DROP_DOWN);
		numberBox.setItems(number);
		numberBox.select(0);
		
		/* third row of the UI: panel showing the dice faces */
		dicePanel = new Composite(shell, SWT.BORDER);
		dicePanel.setLayout(new GridLayout(5, true));
		
		setCurrentGame(ConfigurationFactory.getInstance().getProperty(IceDiceConstants.GAME), shell);
		
		/* fourth row of the UI: roll button and sum label */
		Composite fourthRow = new Composite(shell, 0);
		fourthRow.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		/* button for dice rolling */
		rollButton = new Button(fourthRow, SWT.PUSH);
		rollButton.setText("Roll Dice");
		rollButton.addMouseListener(new IceDiceMouseListener());
		
		/* label with the result */
		resultLabel = new Label(fourthRow, SWT.BORDER);
		
		resetResultLabel();
		shell.pack();
	}
	
	static String getCurrentGameName() {
		return IceDice.getInstance().getCurrentGame().getName();
	}
	
	/**
	 * Sets the right label and combo box according to the
	 * current game's name.
	 */
	static void setCurrentGame(String gameName, Shell shell) {
		IceDice.getInstance().setCurrentGame(gameName);
		if (gameName.equals(Main.DND)) {
			gameLabel.setText("Dice Type:");
			gameBox.setItems(Main.types);
			gameBox.select(5);
		} else if (gameName.equals(Main.WOD)) {
			gameLabel.setText("Challenge:");
			gameBox.setItems(Main.challenges);
			gameBox.select(4);
		}
		
		setDiceNumber(1);
		
		/* dice panel */
		for (DieButton dieButton : dieButtons) {
			dieButton.getAvatar().dispose();
		}
		dieButtons.clear();
//		dicePanel.removeAll();
		Vector<Die> dice = IceDice.getInstance().getCurrentGame().getDice();
		for (Die die : dice) {
			DieButton dieButton = new DieButton(die);
			dieButtons.add(dieButton);
//			dicePanel.add(dieButton);
		}
		shell.pack();
	}

	static void resetResultLabel() {
		resultLabel.setText("0");
	}
	
	static void updateGameResult() {
		IceDice iceDice = IceDice.getInstance();
		iceDice.updateGameResult();
		resultLabel.setText(iceDice.getGameResult());
	}
	
	static void setFirstOption(String value) {
		IceDice.getInstance().setFirstOption(value);
	}
	
	static void setDiceNumber(int number) {
		IceDice.getInstance().setDiceNumber(number);
	}

	static void rollDice() {
		IceDice iceDice = IceDice.getInstance();
		iceDice.rollDice();
		for (DieButton dieButton : dieButtons) {
			dieButton.updateUI();
		}
		resultLabel.setText(iceDice.getGameResult());
	}
	
	public void shellClosed(ShellEvent e) {
		ConfigurationFactory.getInstance().saveProperties();
	}
	
	public void shellActivated(ShellEvent e) {}
	public void shellDeactivated(ShellEvent e) {}
	public void shellDeiconified(ShellEvent e) {}
	public void shellIconified(ShellEvent e) {}
	
	/**
	 * Main method of the application.
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		init(shell);
		
		shell.open();
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		display.dispose();
	}
}
