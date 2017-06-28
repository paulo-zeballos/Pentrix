package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.game.GameFrame;
import view.leaderboard.TopTenPnl;



public class AddListenerTopTenPnl implements ActionListener {
	
	TopTenPnl ttPnl;
	 int boardRows, boardColumns;
	 String session;
	public AddListenerTopTenPnl(TopTenPnl ttPnl, String session){
		this.ttPnl = ttPnl;
		this.boardColumns = 10;
		this.boardRows = 20;
		this.session = session;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if (e.getActionCommand().equals("default")){
			boardRows = 20;
			boardColumns = 10;
		}else if (e.getActionCommand().equals("wide")){
			boardRows = 20;
			boardColumns = 20;
		}else if (e.getActionCommand().equals("big")){
			boardRows = 30;
			boardColumns = 30;
		}else if (e.getActionCommand().equals("start")){
			ttPnl.getTopLevelAncestor().setVisible(false);
			new GameFrame(session,boardRows,boardColumns);
		}
		
	}

}
