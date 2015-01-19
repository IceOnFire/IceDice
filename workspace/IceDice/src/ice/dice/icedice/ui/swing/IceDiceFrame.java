package ice.dice.icedice.ui.swing;

import ice.dice.icedice.IceDiceConstants;
import ice.dice.icedice.Main;
import ice.dice.icedice.config.ConfigurationFactory;
import ice.dice.icedice.domain.Die;
import ice.dice.icedice.domain.IceDice;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

/**
 * @author Ice
 *
 * This class represents the main window and all its components.
 */
public class IceDiceFrame extends JFrame implements WindowListener, ActionListener {
	public static final long serialVersionUID = 1;
	
	private static IceDiceFrame singleton; 
	
	private Vector<DieButton> dieButtons;
	private JPanel dicePanel;
	private JLabel gameLabel, resultLabel;
	private JComboBox gameBox, numberBox;
	private JButton rollButton;
	private GridBagConstraints c;
	
	public static IceDiceFrame getInstance() {
		if (singleton == null) {
			singleton = new IceDiceFrame("IceDice");
		}
		return singleton;
	}
	
	/**
	 * Constructor for the IceDiceFrame.
	 * 
	 * @param title - the frame's title
	 */
	public IceDiceFrame(String title) {
		super(title);
		setLaf(ConfigurationFactory.getInstance().getProperty(IceDiceConstants.LAF));
		
		dieButtons = new Vector<DieButton>();
		
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		
		/* game-specific label */
		gameLabel = new JLabel();
		gameLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 70;
		c.weighty = 10;
		add(gameLabel, c, 0, 0, 3, 1);
		
		/* game-specific selection box */
		gameBox = new JComboBox();
		gameBox.addActionListener(this);
		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 10;
		add(gameBox, c, 4, 0, 1, 1);
		
		/* dice number label */
		JLabel numberLabel = new JLabel("Number:");
		numberLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 70;
		c.weighty = 10;
		add(numberLabel, c, 0, 1, 3, 1);
		
		/* dice number selection box */
		String[] number = new String[Main.MAX_DICE];
		for (int i=0; i<Main.MAX_DICE; i++)
			number[i] = new Integer(i+1).toString();
		numberBox = new JComboBox(number);
		numberBox.addActionListener(this);
		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 10;
		add(numberBox, c, 4, 1, 1, 1);
		
		/* panel showing the dice faces */
		dicePanel = new JPanel(new GridLayout(0, 5));
//		dicePanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 70;
		c.weighty = 70;
		add(dicePanel, c, 0, 2, 5, Main.MAX_DICE/5);
		
		setCurrentGame(ConfigurationFactory.getInstance().getProperty(IceDiceConstants.GAME));
		
		/* button for dice rolling */
		rollButton = new JButton("Roll Dice");
		rollButton.addActionListener(this);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0;
		c.weighty = 10;
		add(rollButton, c, 0, 2 + Main.MAX_DICE/5, 2, 1);
		
		/* label with the result */
		resultLabel = new JLabel();
		resultLabel.setFont(new Font("Small Fonts", Font.PLAIN, 14));
		resultLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 10;
		add(resultLabel, c, 2, 2 + Main.MAX_DICE/5, 3, 1);
		
		resetResultLabel();
		
		/* menubar with a few options */
		setJMenuBar(new IceDiceMenuBar(this));
		
		/* sets size and location */
//		setSize(175, 250);
//		setResizable(false);
		pack();
		
		String xpos = ConfigurationFactory.getInstance().getProperty(IceDiceConstants.XPOS);
		String ypos = ConfigurationFactory.getInstance().getProperty(IceDiceConstants.YPOS);
		if (!xpos.equals("") && !ypos.equals("")) {
			setLocation(Integer.parseInt(xpos), Integer.parseInt(ypos));
		} else {
			setLocationRelativeTo(null);
		}
		
		/* adds a listener which saves the properties to file */
		addWindowListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void add(JComponent jComponent, GridBagConstraints gbc, int x, int y, int width, int height) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		add(jComponent, gbc);
	}
	
	String getCurrentGameName() {
		return IceDice.getInstance().getCurrentGame().getName();
	}
	
	/**
	 * Sets the right label and combo box according to the
	 * current game's name.
	 */
	void setCurrentGame(String gameName) {
		IceDice.getInstance().setCurrentGame(gameName);
		if (gameName.equals(Main.DND)) {
			gameLabel.setText("Dice Type:");
			gameBox.setModel(new DefaultComboBoxModel(Main.types));
			gameBox.setSelectedIndex(5);
		} else if (gameName.equals(Main.WOD)) {
			gameLabel.setText("Challenge:");
			gameBox.setModel(new DefaultComboBoxModel(Main.challenges));
			gameBox.setSelectedIndex(4);
		}
		
		setDiceNumber(1);
		
		/* dice panel */
		dieButtons.clear();
		dicePanel.removeAll();
		Vector<Die> dice = IceDice.getInstance().getCurrentGame().getDice();
		for (Die die : dice) {
			DieButton dieButton = new DieButton(die);
			dieButtons.add(dieButton);
			dicePanel.add(dieButton);
		}
		pack();
	}
	
	void resetResultLabel() {
		resultLabel.setText("0");
	}
	
	void updateGameResult() {
		IceDice iceDice = IceDice.getInstance();
		iceDice.updateGameResult();
		resultLabel.setText(iceDice.getGameResult());
	}
	
	void setFirstOption(String value) {
		IceDice.getInstance().setFirstOption(value);
	}
	
	void setDiceNumber(int number) {
		IceDice.getInstance().setDiceNumber(number);
	}
	
	void rollDice() {
		IceDice iceDice = IceDice.getInstance();
		iceDice.rollDice();
		for (DieButton dieButton : dieButtons) {
			dieButton.update();
		}
		resultLabel.setText(iceDice.getGameResult());
	}
	
	/**
	 * Tries to set the proper Look and Feel, given the LaF name.
	 * 
	 * @param laf The Look and Feel's name.
	 */
	protected void setLaf(String laf) {
		try {
			if (laf.equals(Main.JAVA))
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			else if (laf.equals(Main.MOTIF))
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			else if (laf.equals(Main.SYSTEM))
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			ConfigurationFactory.getInstance().setProperty(IceDiceConstants.LAF, laf);
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		pack();
	}
	
	public void windowClosing(WindowEvent arg0) {
		ConfigurationFactory.getInstance().saveProperties();
	}
	
	public void windowClosed(WindowEvent arg0) {}
	public void windowActivated(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gameBox) {
			setFirstOption((String)gameBox.getSelectedItem());
		} else if (e.getSource() == numberBox) {
			setDiceNumber(Integer.parseInt((String)numberBox.getSelectedItem()));
		} else if (e.getSource() == rollButton) {
			rollDice();
		}
	}
}
