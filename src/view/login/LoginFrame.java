package view.login;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class LoginFrame {
	public LoginFrame(){
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	createAndShowGUI();
            }
        });
	}
	
	private void createAndShowGUI() {

        JFrame f = new JFrame("PENTRIX LOGIN");
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new LoginPnl());
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);//centred the frame on the screen
        f.setVisible(true);
    }
}
