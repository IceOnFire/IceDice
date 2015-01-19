package ice.dice.icedice.ui.swing;

import ice.dice.icedice.IceDiceConstants;
import ice.dice.icedice.Main;
import ice.dice.icedice.config.ConfigurationFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class IceDiceMenuBar extends JMenuBar implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private IceDiceFrame frame;
	
	public IceDiceMenuBar(IceDiceFrame idf) {
		super();
		frame = idf;
		
		/* menus */
		JMenu gameMenu = new JMenu("Game");
		gameMenu.setMnemonic('G');
		JMenu lafMenu = new JMenu("LaF");
		lafMenu.setMnemonic('L');
		JMenu questionMenu = new JMenu("?");
		questionMenu.setMnemonic('?');

		add(gameMenu);
		add(lafMenu);
		add(questionMenu);

		/* first set of menu items: game radio buttons */
		JMenuItem dndMenuItem = new JRadioButtonMenuItem(Main.DND);
		dndMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		dndMenuItem.addActionListener(this);

		JMenuItem wodMenuItem = new JRadioButtonMenuItem(Main.WOD);
		wodMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		wodMenuItem.addActionListener(this);

		if (frame.getCurrentGameName().equals(Main.DND))
			dndMenuItem.setSelected(true);
		else if (frame.getCurrentGameName().equals(Main.WOD))
			wodMenuItem.setSelected(true);

		// radio menu items must be grouped in a button group
		ButtonGroup gameGroup = new ButtonGroup();

		gameGroup.add(dndMenuItem);
		gameGroup.add(wodMenuItem);

		gameMenu.add(dndMenuItem);
		gameMenu.add(wodMenuItem);

		gameMenu.addSeparator();

		// a 'quit' menu item
		JMenuItem quitMenuItem = new JMenuItem(Main.QUIT);
		quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		quitMenuItem.addActionListener(this);

		gameMenu.add(quitMenuItem);

		/* second set of menu items */
		JMenuItem javaMenuItem = new JRadioButtonMenuItem(Main.JAVA);
		javaMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_J, ActionEvent.CTRL_MASK));
		javaMenuItem.addActionListener(this);

		JMenuItem motifMenuItem = new JRadioButtonMenuItem(Main.MOTIF);
		motifMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		motifMenuItem.addActionListener(this);

		JMenuItem systemMenuItem = new JRadioButtonMenuItem(Main.SYSTEM);
		systemMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		systemMenuItem.addActionListener(this);
		
		String laf = ConfigurationFactory.getInstance().getProperty(IceDiceConstants.LAF);
		if (laf.equals(Main.JAVA))
			javaMenuItem.setSelected(true);
		else if (laf.equals(Main.MOTIF))
			motifMenuItem.setSelected(true);
		else if (laf.equals(Main.SYSTEM))
			systemMenuItem.setSelected(true);

		// radio menu items must be grouped in a button group
		ButtonGroup lafGroup = new ButtonGroup();

		lafGroup.add(javaMenuItem);
		lafGroup.add(motifMenuItem);
		lafGroup.add(systemMenuItem);

		lafMenu.add(javaMenuItem);
		lafMenu.add(motifMenuItem);
		lafMenu.add(systemMenuItem);

		/* third set of menu items */
		JMenuItem helpMenuItem = new JMenuItem(Main.HELP);
		helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		helpMenuItem.addActionListener(this);

		JMenuItem aboutMenuItem = new JMenuItem(Main.ABOUT);
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		aboutMenuItem.addActionListener(this);

		questionMenu.add(helpMenuItem);
		questionMenu.addSeparator();
		questionMenu.add(aboutMenuItem);
	}
	
	/**
	 * Captures the action event launched by the menu.
	 */
	public void actionPerformed(ActionEvent e) {
		String selectedMenuItem = ((JMenuItem)e.getSource()).getText();
		if (selectedMenuItem.equals(Main.DND)
				|| selectedMenuItem.equals(Main.WOD)) {
			/* updates the current game and resets the UI */
			frame.setCurrentGame(selectedMenuItem);
		} else if (selectedMenuItem.equals(Main.QUIT)) {
			System.exit(0);
		} else if (selectedMenuItem.equals(Main.JAVA)
				|| selectedMenuItem.equals(Main.MOTIF)
				|| selectedMenuItem.equals(Main.SYSTEM)) {
			frame.setLaf(selectedMenuItem);
		} else if (selectedMenuItem.equals(Main.HELP)) {
			JOptionPane.showMessageDialog(frame,
					Main.HELP_MESSAGE,
					Main.HELP,
					JOptionPane.INFORMATION_MESSAGE);
		} else if (selectedMenuItem.equals(Main.ABOUT)) {
			JOptionPane.showMessageDialog(frame,
					Main.ABOUT_MESSAGE,
					Main.ABOUT,
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
