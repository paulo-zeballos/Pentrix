package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.user.User;
import view.leaderboard.TopTenFrame;
import view.login.LoginPnl;

public class AddLoginPnlListener implements ActionListener{
	
	LoginPnl logPnl;
	User user;
	public AddLoginPnlListener(User user, LoginPnl logPnl){
		this.logPnl = logPnl;
		
	}
	
	//if login button is clicked it checks validity of username and password
	//if signup button is clicked it adds a new user if user does not exist
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("login")) {
			user = new User(logPnl.usrTxt.getText(), logPnl.pwdTxt.getPassword());
			if (user.isPwdCorrect()) {
				hideAndShow();		
			} else
				JOptionPane.showMessageDialog(logPnl,
						"Incorrect User or password", "Authentication Error",
						JOptionPane.ERROR_MESSAGE);
		} else if (e.getActionCommand().equals("signup")) {
			user = new User("", logPnl.nameSignTxt.getText(),
					logPnl.emailSignTxt.getText(), logPnl.addrsSignTxt.getText());
			if (user.addUsr()) {
				JOptionPane
						.showMessageDialog(
								logPnl, "WELCOME " + user.getName() + "\n"
										    + "You username: " + user.getUsrName() + "\n" 
										    + "Your password: "	+ user.getPwd() + "\n" 
										    + "For security issues we recommend you change your password.",
										    "Successful Sign up",
										    JOptionPane.PLAIN_MESSAGE);
				hideAndShow();
			}

			else
				JOptionPane.showMessageDialog(logPnl, "User already exists",
						"Authentication Warning", JOptionPane.WARNING_MESSAGE);

		} 

	}
	//hides itself and shows a new frame
		private void hideAndShow(){
			logPnl.getTopLevelAncestor().setVisible(false);
			new TopTenFrame(user.getUsrName());
		}

}
