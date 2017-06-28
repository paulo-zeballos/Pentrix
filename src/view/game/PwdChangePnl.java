package view.game;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.game.PentrixGame;
import model.user.User;

public class PwdChangePnl extends JPanel implements ActionListener{
	private JLabel oldPwdLbl, newPwdLbl, usrLbl;
	private JButton cancelBttn, changeBttn;
	private JTextField  usrTxt;
	private JPasswordField oldPwdTxt, newPwdTxt;
	User user;
	String session;
	PentrixGame game;
		
	public PwdChangePnl(PentrixGame game, String session){
			this.game = game;			
			this.session=session;				
			oldPwdLbl = new JLabel("Old password");
			newPwdLbl = new JLabel("New Password");
			usrLbl = new JLabel("Username");
						
			oldPwdTxt = new JPasswordField(20);
			newPwdTxt = new JPasswordField(20);
			usrTxt = new JTextField(20);
			usrTxt.setText(session);
			usrTxt.setEditable(false);
			
			changeBttn = new JButton("Change Password");
			changeBttn.addActionListener(this);
			changeBttn.setActionCommand("change");
			
			cancelBttn = new JButton("Cancel");
			cancelBttn.addActionListener(this);
			cancelBttn.setActionCommand("cancel");
			
			JPanel usrPnl = new JPanel();
			usrPnl.setLayout(new FlowLayout());
			usrPnl.add(usrLbl);
			usrPnl.add(usrTxt);
			
			JPanel oldPwdPnl = new JPanel();
			oldPwdPnl.setLayout(new FlowLayout());
			oldPwdPnl.add(oldPwdLbl);
			oldPwdPnl.add(oldPwdTxt);
			
			JPanel newPwdPnl = new JPanel();
			newPwdPnl.setLayout(new FlowLayout());
			newPwdPnl.add(newPwdLbl);
			newPwdPnl.add(newPwdTxt);
			
			JPanel bttnPnl = new JPanel();
			bttnPnl.setLayout(new FlowLayout());
			bttnPnl.add(cancelBttn);
			bttnPnl.add(changeBttn);
			
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			add(usrPnl);
			add(oldPwdPnl);
			add(newPwdPnl);
			add(bttnPnl);
			
			
		}
		
		public Dimension getPreferredSize(){
			return super.getPreferredSize();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("change")){
				user = new User(usrTxt.getText(),oldPwdTxt.getPassword());
				if (user.isPwdCorrect()){
					if (user.updatePwd(newPwdTxt.getPassword())){
						JOptionPane.showMessageDialog(this,
							    "Password successfully changed",
							    "Successful password change",
							    JOptionPane.PLAIN_MESSAGE);
						hideAndShow();
					}
				}
				else
				JOptionPane.showMessageDialog(this,
					    "Incorrect User or password",
					    "Authentication Error",
					    JOptionPane.ERROR_MESSAGE);
			}
			else if (e.getActionCommand().equals("cancel"))
				hideAndShow();		
		}
		
		//hides itself and shows a new frame
		private void hideAndShow(){
			this.getTopLevelAncestor().setVisible(false);
			game.resumeGame();
		}
}
