package ice.dice.icedice.ui.swt;

import ice.dice.icedice.Main;
import ice.dice.icedice.domain.IceDice;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

/**
 * The IceDiceMenuBar is an object which listens to events
 * that occur on the menubar.
 * 
 * @author Ice
 */
public class IceDiceMenuBar extends Menu implements SelectionListener {
	public IceDiceMenuBar(Shell shell) {
		super(shell, SWT.BAR);
//		Menu menuBar = new Menu(shell, SWT.BAR);
//		shell.setMenuBar(menuBar);

		/* menus */
		MenuItem gameMenuItem = new MenuItem(this, SWT.CASCADE);
		gameMenuItem.setText("&Game");
		Menu gameMenu = new Menu(gameMenuItem);
		gameMenuItem.setMenu(gameMenu);

		MenuItem questionMenuItem = new MenuItem(this, SWT.CASCADE);
		questionMenuItem.setText("&?");
		Menu questionMenu = new Menu(questionMenuItem);
		questionMenuItem.setMenu(questionMenu);
		
		/* first set of menu items: game radio buttons */
		MenuItem dndMenuItem = new MenuItem(gameMenu, SWT.RADIO);
		dndMenuItem.setText("&"+Main.DND);
		dndMenuItem.setAccelerator(SWT.CTRL | 'D');
		dndMenuItem.addSelectionListener(this);

		MenuItem wodMenuItem = new MenuItem(gameMenu, SWT.RADIO);
		wodMenuItem.setText("&"+Main.WOD);
		wodMenuItem.setAccelerator(SWT.CTRL | 'V');
		wodMenuItem.addSelectionListener(this);
		gameMenu.setDefaultItem(wodMenuItem);

		new MenuItem(gameMenu, SWT.SEPARATOR);

		// a 'quit' menu item
		MenuItem quitMenuItem = new MenuItem(gameMenu, SWT.PUSH);
		quitMenuItem.setText("&"+Main.QUIT);
		quitMenuItem.setAccelerator(SWT.ALT | SWT.F4);
		quitMenuItem.addSelectionListener(this);

		/* the laf menu has no meaning here */

		/* third set of menu items */
		MenuItem helpMenuItem = new MenuItem(questionMenu, SWT.PUSH);
		helpMenuItem.setText("&"+Main.HELP);
		helpMenuItem.setAccelerator(SWT.CTRL | 'H');
		helpMenuItem.addSelectionListener(this);

		MenuItem aboutMenuItem = new MenuItem(questionMenu, SWT.PUSH);
		aboutMenuItem.setText("&"+Main.ABOUT);
		aboutMenuItem.setAccelerator(SWT.CTRL | 'A');
		aboutMenuItem.addSelectionListener(this);
	}
	/**
	 * Useless method that must be overridden.
	 * 
	 * @param e The SelectionEvent that has been caught.
	 */
	public void widgetSelected(SelectionEvent e) {
		widgetDefaultSelected(e);
	}
	
	/**
	 * This method overrides the one provided by SelectionListener:
	 * it performs the right action for each menu item selected.
	 * 
	 * @param e The SelectionEvent that has been caught.
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
		String selectedMenuItem = ((MenuItem)e.getSource()).getText();
		if (selectedMenuItem.equals("&"+Main.DND)
				|| selectedMenuItem.equals("&"+Main.WOD))
		{
			/* updates the current game and resets the UI */
			IceDice.getInstance().setCurrentGame(selectedMenuItem.substring(1));
			IceDiceShell.resetResultLabel();
		} else if (selectedMenuItem.equals("&"+Main.QUIT))
			/* closes the application */
			System.exit(0);
		/*else if (selectedMenuItem.equals(IceDiceConstants.HELP))
			JOptionPane.showMessageDialog(IceDice,
					"IceDiceConstants is a simple Java program that allows\n" +
					"rolegame players to roll up to " + IceDiceConstants.MAXDICE + " dice. If you\n" +
					"choose 'Dungeons&Dragons v3.5' from the menubar\n" +
					"then you can roll different kinds of dice and IceDiceConstants\n" +
					"will display their sum. Instead if you choose\n" +
					"'Vampire: The Masquerade' you choose the challenge\n" +
					"degree and the number of dice, and IceDiceConstants\n" +
					"will display the number of victories (refer to your\n" +
					"Vampire manual for more details).\n" +
					"You can also choose your preferred Look and Feel\n" +
					"from the LaF menu: Metal, Motif or System default.\n" +
					"Have fun!\n" +
					"\nIce",
					IceDiceConstants.HELP,
					JOptionPane.INFORMATION_MESSAGE);
		else if (selectedMenuItem.equals(IceDiceConstants.ABOUT))
			JOptionPane.showMessageDialog(IceDice,
					"IceDiceConstants\n" +
					"by Ice (antonymist@hotmail.com)\n\n" +
					"This software is an opensource JAVA application.",
					IceDiceConstants.ABOUT,
					JOptionPane.INFORMATION_MESSAGE);
		SwingUtilities.updateComponentTreeUI(IceDice);*/
	}
}