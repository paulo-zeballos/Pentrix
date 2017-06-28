package view.leaderboard;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TopTenFrame {
	private String session;
	public TopTenFrame(String session){
		this.session=session;
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	createAndShowGUI();
            }
        });
	}
	
	private void createAndShowGUI() {

        JFrame f = new JFrame("PENTRIX LEADER BOARD");
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new TopTenPnl(session));
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);//centred the frame on the screen
        f.setVisible(true);
    }
}
