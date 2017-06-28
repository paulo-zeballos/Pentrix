package view.game;

import java.awt.Dialog;
import java.awt.Window;

import javax.swing.JDialog;

import model.game.PentrixGame;

public class PwdChangeDialog extends JDialog {
	public PwdChangeDialog(Window owner, PentrixGame game, String session) {
		super(owner, "CHANGE PASSWORD", Dialog.ModalityType.APPLICATION_MODAL);

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		add(new PwdChangePnl(game, session));
		pack();
		setResizable(false);
		setLocationRelativeTo(null);// centre of screen
		setVisible(true);

	}

}
