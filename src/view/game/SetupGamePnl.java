package view.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.game.PentrixGame;
import model.game.SetupPieces;
import model.user.User;

public class SetupGamePnl extends JPanel implements ItemListener, ActionListener {
	private String session;
	private SetupPieces setupPieces;
	JPanel setupPiecePnl, setupPiecePnl1, setupPiecePnl2, setupGamePnl,
			pwdPnl;
	JCheckBox pMChkBox, pLineChkBox, pCrossChkBox, pLChkBox, pZChkBox,
			pTonfaChkBox, pHChkBox, pGunChkBox, pCChkBox, pTChkBox;
	TitledBorder borderPieces, borderControls, borderPwd;
	JCheckBox[] arrayBoxes;
	JButton pauseBttn, resumeBttn, pwdChangeBttn;
	PentrixGame game;
	private int blocked;

	public SetupGamePnl(String session, SetupPieces stpPieces, PentrixGame game) {
		this.session = session;
		setupPieces = stpPieces;
		this.game = game;
		
		arrayBoxes = new JCheckBox[10];// array to contain all check boxes
		blocked = -1;// to control number of selected check boxes 
		
		borderPieces = getBorder("PIECES SETUP");
		borderControls = getBorder("PENTRIX CONTROLS");
		borderPwd = getBorder("ACCOUNT SETUP");

		pMChkBox = new JCheckBox("PieceM");
		pMChkBox.setSelected(true);
		pLineChkBox = new JCheckBox("PieceLine");
		pLineChkBox.setSelected(true);
		pCrossChkBox = new JCheckBox("PieceCross");
		pCrossChkBox.setSelected(true);
		pLChkBox = new JCheckBox("PieceL");
		pLChkBox.setSelected(true);
		pZChkBox = new JCheckBox("PieceZ");
		pZChkBox.setSelected(true);
		pTonfaChkBox = new JCheckBox("PieceTonfa");
		pTonfaChkBox.setSelected(true);
		pHChkBox = new JCheckBox("PieceH");
		pHChkBox.setSelected(true);
		pGunChkBox = new JCheckBox("PieceGun");
		pGunChkBox.setSelected(true);
		pCChkBox = new JCheckBox("PieceC");
		pCChkBox.setSelected(true);
		pTChkBox = new JCheckBox("PieceT");
		pTChkBox.setSelected(true);

		fillArrayBoxes();
		
		pMChkBox.addItemListener(this);
		pLineChkBox.addItemListener(this);
		pCrossChkBox.addItemListener(this);
		pLChkBox.addItemListener(this);
		pZChkBox.addItemListener(this);
		pTonfaChkBox.addItemListener(this);
		pHChkBox.addItemListener(this);
		pGunChkBox.addItemListener(this);
		pCChkBox.addItemListener(this);
		pTChkBox.addItemListener(this);

		setupPiecePnl1 = new JPanel();
		setupPiecePnl1
				.setLayout(new BoxLayout(setupPiecePnl1, BoxLayout.Y_AXIS));
		setupPiecePnl1.add(pMChkBox);
		setupPiecePnl1.add(pLineChkBox);
		setupPiecePnl1.add(pCrossChkBox);
		setupPiecePnl1.add(pLChkBox);
		setupPiecePnl1.add(pZChkBox);

		setupPiecePnl2 = new JPanel();
		setupPiecePnl2
				.setLayout(new BoxLayout(setupPiecePnl2, BoxLayout.Y_AXIS));
		setupPiecePnl2.add(pTonfaChkBox);
		setupPiecePnl2.add(pHChkBox);
		setupPiecePnl2.add(pGunChkBox);
		setupPiecePnl2.add(pCChkBox);
		setupPiecePnl2.add(pTChkBox);

		setupPiecePnl = new JPanel();
		setupPiecePnl.setLayout(new BoxLayout(setupPiecePnl, BoxLayout.X_AXIS));
		setupPiecePnl.add(setupPiecePnl1);
		setupPiecePnl.add(setupPiecePnl2);
		setupPiecePnl.setBorder(borderPieces);

		//BUTTONS
		pauseBttn = new JButton("Pause");
		pauseBttn.addActionListener(this);
		pauseBttn.setActionCommand("pause");
		
		resumeBttn = new JButton("Resume");
		resumeBttn.addActionListener(this);
		resumeBttn.setActionCommand("resume");
		resumeBttn.setEnabled(false);
		
		pwdChangeBttn = new JButton("Change Password");
		pwdChangeBttn.addActionListener(this);
		pwdChangeBttn.setActionCommand("pwdchange");
		
		//PANEL FOR PASSWORD CHANGE BUTTON
		pwdPnl = new JPanel();
		pwdPnl.setLayout(new BoxLayout(pwdPnl, BoxLayout.X_AXIS));
		pwdPnl.setBorder(borderPwd);
		pwdPnl.add(pwdChangeBttn);
		
		//PANEL FOR CONTROL BUTTONS
		setupGamePnl = new JPanel();
		setupGamePnl.setLayout(new BoxLayout(setupGamePnl, BoxLayout.X_AXIS));
		setupGamePnl.setBorder(borderControls);
		setupGamePnl.add(pauseBttn);
		setupGamePnl.add(Box.createRigidArea(new Dimension(10, 0)));
		setupGamePnl.add(resumeBttn);
		
		//main panel
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(0, 15)));
		add(setupGamePnl);
		add(Box.createRigidArea(new Dimension(0, 15)));
		add(setupPiecePnl);
		add(Box.createRigidArea(new Dimension(0, 15)));
		add(pwdPnl);
	}

	//adds or removes a piece in runtime 
	//ensures there is at least one check box selected
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object src = e.getItemSelectable();
		String source = "";
		boolean selected = true;

		if (src == pMChkBox)
			source = "PieceM";
		else if (src == pLineChkBox)
			source = "PieceLine";
		else if (src == pCrossChkBox)
			source = "PieceCross";
		else if (src == pLChkBox)
			source = "PieceL";
		else if (src == pZChkBox)
			source = "PieceZ";
		else if (src == pTonfaChkBox)
			source = "PieceTonfa";
		else if (src == pHChkBox)
			source = "PieceH";
		else if (src == pGunChkBox)
			source = "PieceGun";
		else if (src == pCChkBox)
			source = "PieceC";
		else if (src == pTChkBox)
			source = "PieceT";

		if (e.getStateChange() == ItemEvent.DESELECTED)
			selected = false;

		// add or remove pieces from the setupPieces collection
		if (selected)
			setupPieces.addPiece(source);
		else
			setupPieces.removePiece(source);

		// ensures at least 1 check boxes selected
		int count= numDeSelectedBoxes()[0];
		if (count >= 9){
			blocked=numDeSelectedBoxes()[1];
			blockChkBox(blocked);
		}
		else if (count<9 && blocked!=-1 ){
			unBlockChkBox(blocked);
			blocked=-1;
		}
		
			
	}

	// returns a TitleBorder given the title
	private TitledBorder getBorder(String title) {
		return BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(), title, TitledBorder.LEFT,
				TitledBorder.ABOVE_TOP, new Font("Arial", Font.BOLD, 14),
				new Color(0, 0, 0));
	}

	// returns in pos 0 the count of check boxes deselected 
	// returns in pos 1 the position of last still selected
	private int [] numDeSelectedBoxes() {
		int [] num = new int[2];
		num[0]=0;
		for (int i = 0; i < arrayBoxes.length; i++){
			if (!arrayBoxes[i].isSelected())
				num[0]++;
			else
				num[1]=i;
		}
		return num;
	}

	// blocks check boxes
	private void blockChkBox(int pos) {
		arrayBoxes[pos].setEnabled(false);
	}

	// blocks check boxes
	private void unBlockChkBox(int pos) {
		arrayBoxes[pos].setEnabled(true);
	}

	// fills the array with all check boxes
	private void fillArrayBoxes() {
		arrayBoxes[0] = pMChkBox;
		arrayBoxes[1] = pLineChkBox;
		arrayBoxes[2] = pCrossChkBox;
		arrayBoxes[3] = pLChkBox;
		arrayBoxes[4] = pZChkBox;
		arrayBoxes[5] = pTonfaChkBox;
		arrayBoxes[6] = pHChkBox;
		arrayBoxes[7] = pGunChkBox;
		arrayBoxes[8] = pCChkBox;
		arrayBoxes[9] = pTChkBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("pause")) {
			game.pauseGame();
			resumeBttn.setEnabled(true);
			pauseBttn.setEnabled(false);
		} else if (e.getActionCommand().equals("resume")) {
			game.resumeGame();
			resumeBttn.setEnabled(false);
			pauseBttn.setEnabled(true);
		} else if (e.getActionCommand().equals("pwdchange")) {
			game.pauseGame();
			new PwdChangeDialog((Window) this.getTopLevelAncestor(), game, session);

		}

		
	}

}
