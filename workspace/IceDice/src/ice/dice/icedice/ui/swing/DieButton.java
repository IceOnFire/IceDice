package ice.dice.icedice.ui.swing;

import ice.dice.icedice.domain.Die;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class DieButton extends JButton implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	private Die die;
	
	DieButton(Die d) {
		super();
		die = d;
		setFont(new Font("Small Fonts", Font.PLAIN, 14));
//		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		setHorizontalAlignment(SwingConstants.CENTER);
		addMouseListener(this);
		
		reset();
	}
	
	void update() {
		int faceValue = die.getFaceValue();
		if (die.isResetted()) {
			reset();
		} else {
			setText("" + faceValue);
		}
	}
	
	void reset() {
		setText("#");
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			die.roll();
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			die.reset();
		}
		update();
		IceDiceFrame.getInstance().updateGameResult();
	}
	
	public void mouseReleased(MouseEvent e) {}
}
