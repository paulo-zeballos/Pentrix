package view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePnl extends JPanel {

private JLabel score; 
	public ScorePnl() {
		
		setProperties();
		
		score = new JLabel();
		setScoreProperties();
		add(score, BorderLayout.EAST);
		
		this.setVisible(true);
		
	}
	
	public Dimension getPreferredSize(){
		return new Dimension (super.getPreferredSize().width,30);
	}
	
	//configures properties of JPanel
	private void setProperties(){
		setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		setBackground(new Color(0, 0, 0));
		setLayout(new BorderLayout());
	}
	
	//configures properties of JLabel
	private void setScoreProperties(){
		score.setHorizontalTextPosition(JLabel.RIGHT);
		score.setFont(new Font("Courier",Font.BOLD,20));
		score.setForeground(Color.LIGHT_GRAY);
		score.setText("0");
		score.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		setToolTipText("SCORE");
	}
	
	// update the value of score
	public void updateScore(String value){
		score.setText(value);
	}
	
	
}
