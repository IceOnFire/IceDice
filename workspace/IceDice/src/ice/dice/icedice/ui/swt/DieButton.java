package ice.dice.icedice.ui.swt;

import ice.dice.icedice.domain.Die;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Button;

public class DieButton implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	private Die die;
	private Button avatar;
	
	DieButton(Die d) {
		avatar = new Button(IceDiceShell.dicePanel, SWT.CENTER);
		die = d;
		avatar.addMouseListener(this);
		
		reset();
	}
	
	Button getAvatar() {
		return avatar;
	}
	
	void updateUI() {
		int faceValue = die.getFaceValue();
		if (die.isResetted()) {
			reset();
		} else {
			avatar.setText("" + faceValue);
		}
	}
	
	void reset() {
		avatar.setText("#");
	}

	public void mouseDoubleClick(MouseEvent e) {}
	public void mouseDown(MouseEvent e) {
		if (e.button == SWT.BUTTON1) {
			die.roll();
		} else if (e.button == SWT.BUTTON3) {
			die.reset();
		}
		updateUI();
		IceDiceShell.updateGameResult();
	}
	public void mouseUp(MouseEvent e) {}
}
