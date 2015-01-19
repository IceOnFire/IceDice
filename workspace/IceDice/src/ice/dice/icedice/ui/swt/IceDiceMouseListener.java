package ice.dice.icedice.ui.swt;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

public class IceDiceMouseListener implements MouseListener {
	public void mouseDown(MouseEvent e) {
		if (e.getSource() == IceDiceShell.gameBox) {
			IceDiceShell.setFirstOption((String)IceDiceShell.gameBox.getItem(IceDiceShell.gameBox.getSelectionIndex()));
		} else if (e.getSource() == IceDiceShell.numberBox) {
			IceDiceShell.setDiceNumber(Integer.parseInt((String)IceDiceShell.gameBox.getItem(IceDiceShell.gameBox.getSelectionIndex())));
		} else if (e.getSource() == IceDiceShell.rollButton) {
			IceDiceShell.rollDice();
		}
	}
	
	public void mouseDoubleClick(MouseEvent e) {}
	public void mouseUp(MouseEvent e) {}

}
