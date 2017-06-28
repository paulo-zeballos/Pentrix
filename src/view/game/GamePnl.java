package view.game;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.board.Board;
import model.game.PentrixGame;
import model.game.SetupPieces;

public class GamePnl extends JPanel {

	private ScorePnl scorePnl;
	private BoardPnl boardPnl;
	private SetupGamePnl setupGamePnl;
	private Board board;
	private PentrixGame pentrixGame;
	private int boardRows, boardColumns;
	private SetupPieces setupPieces;

	public GamePnl(String session, int rows, int cols) {

		setupPieces = new SetupPieces();

		boardRows = rows;
		boardColumns = cols;

		board = new Board(boardRows, boardColumns);

		scorePnl = new ScorePnl();

		boardPnl = new BoardPnl(board, setupPieces);
		pentrixGame = new PentrixGame(boardPnl, board, session, scorePnl);

		setupGamePnl = new SetupGamePnl(session, setupPieces, pentrixGame);

		JPanel arrangePnl = new JPanel();
		arrangePnl.setLayout(new BorderLayout());
		arrangePnl.add(boardPnl, BorderLayout.CENTER);
		arrangePnl.add(scorePnl, BorderLayout.NORTH);

		setLayout(new BorderLayout());
		add(arrangePnl, BorderLayout.CENTER);
		add(setupGamePnl, BorderLayout.EAST);

	}

	public Dimension getPreferredSize() {
		return super.getPreferredSize();
	}
}
