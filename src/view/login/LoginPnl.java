package view.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.user.User;
import view.listeners.AddLoginPnlListener;

public class LoginPnl extends JPanel {
	public JLabel usrLbl, pwdLbl, nameSignLbl,
			 addrsSignLbl, emailSignLbl;
	public JButton signUpBttn, logInBttn;
	public JTextField usrTxt,  nameSignTxt, addrsSignTxt,
			emailSignTxt;
	public User user;
	public TitledBorder logInBdr, signUpBrd;
	public JPasswordField pwdTxt;
	
	public LoginPnl() {
	
		//INSTANTIATING SWING COMPONENTS
		logInBdr = getBorder("LOG IN");
		signUpBrd = getBorder("SIGN UP");

		usrLbl = new JLabel("Username");
		pwdLbl = new JLabel("Password");
		nameSignLbl = new JLabel("Name");
		
		addrsSignLbl = new JLabel("Address");
		emailSignLbl = new JLabel("Email");

		
		usrTxt = new JTextField(20);
		pwdTxt = new JPasswordField(20);
		nameSignTxt = new JTextField(20);
		
		addrsSignTxt = new JTextField(20);
		emailSignTxt = new JTextField(20);

		
		logInBttn = new JButton("LOGIN");
		logInBttn.addActionListener(new AddLoginPnlListener(user, this));
		logInBttn.setActionCommand("login");
		
		signUpBttn = new JButton("SIGN UP");
		signUpBttn.addActionListener(new AddLoginPnlListener(user, this));
		signUpBttn.setActionCommand("signup");
		
		//LOG IN FORM
		JPanel leftLgInPnl = new JPanel();
		leftLgInPnl.setLayout(new BoxLayout(leftLgInPnl, BoxLayout.PAGE_AXIS));
		leftLgInPnl.add(usrLbl);
		leftLgInPnl.add(pwdLbl);
		
		JPanel rightLgInPnl = new JPanel();
		rightLgInPnl.setLayout(new BoxLayout(rightLgInPnl, BoxLayout.PAGE_AXIS));
		rightLgInPnl.add(usrTxt);
		rightLgInPnl.add(pwdTxt);
		
		JPanel logInPnl = new JPanel();
		logInPnl.setLayout(new FlowLayout());
		logInPnl.setBorder(logInBdr);
		logInPnl.add(leftLgInPnl);
		logInPnl.add(rightLgInPnl);
		
		JPanel bttnLgInPnl = new JPanel();
		bttnLgInPnl.setLayout(new BoxLayout(bttnLgInPnl, BoxLayout.PAGE_AXIS));
		bttnLgInPnl.add(logInBttn);
		
		//SIGN UP FORM
		JPanel leftSgUpPnl = new JPanel();
		leftSgUpPnl.setLayout(new BoxLayout(leftSgUpPnl, BoxLayout.PAGE_AXIS));
		leftSgUpPnl.add(nameSignLbl);
		
		leftSgUpPnl.add(addrsSignLbl);
		leftSgUpPnl.add(emailSignLbl);
		
		JPanel rightSgUpPnl = new JPanel();
		rightSgUpPnl.setLayout(new BoxLayout(rightSgUpPnl, BoxLayout.PAGE_AXIS));
		rightSgUpPnl.add(nameSignTxt);
		
		rightSgUpPnl.add(addrsSignTxt);
		rightSgUpPnl.add(emailSignTxt);
		
		JPanel sgnUpPnl = new JPanel();
		sgnUpPnl.setLayout(new FlowLayout());
		sgnUpPnl.setBorder(signUpBrd);
		sgnUpPnl.add(leftSgUpPnl);
		sgnUpPnl.add(rightSgUpPnl);

		JPanel bttnSgnUpPnl = new JPanel();
		bttnSgnUpPnl.setLayout(new BoxLayout(bttnSgnUpPnl, BoxLayout.PAGE_AXIS));
		bttnSgnUpPnl.add(signUpBttn);
		
		//MAIN PANEL
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(0, 15)));
		
		add(logInPnl);
		add(bttnLgInPnl);
		add(Box.createRigidArea(new Dimension(0, 50)));
		
		add(sgnUpPnl);
		add(bttnSgnUpPnl);
		add(Box.createRigidArea(new Dimension(0, 15)));
	}

	public Dimension getPreferredSize() {
		return super.getPreferredSize();
	}

	
	// returns a TitleBorder given the title
		private TitledBorder getBorder(String title) {
			return BorderFactory.createTitledBorder(
					BorderFactory.createEmptyBorder(), title, TitledBorder.CENTER,
					TitledBorder.ABOVE_TOP, new Font("Arial", Font.BOLD, 14),
					new Color(0, 0, 0));
		}
}
