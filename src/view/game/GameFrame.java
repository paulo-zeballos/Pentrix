package view.game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameFrame {
	private String session;
	int rows, cols;
	public GameFrame(String session, int rows, int cols){
		this.session=session;
		this.rows = rows;
		this.cols = cols;
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	createAndShowGUI();
	            }
	        });	
	}
	
	private void createAndShowGUI() {

        JFrame f = new JFrame("PENTRIX");
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new GamePnl(session, rows, cols));
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);//centred the frame on the screen
        f.setVisible(true);
    }
}
