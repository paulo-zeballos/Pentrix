package view.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import view.pieces.CustomPiece;
import view.pieces.PieceC;
import view.pieces.PieceCross;
import view.pieces.PieceGun;
import view.pieces.PieceH;
import view.pieces.PieceL;
import view.pieces.PieceLine;
import view.pieces.PieceM;
import view.pieces.PieceT;
import view.pieces.PieceTonfa;
import view.pieces.PieceZ;
import model.board.Board;
import model.game.SetupPieces;


public class BoardPnl extends JPanel {
	private int initX, initY;
	private int boardRows, boardColumns;
	private int edge;
	private CustomPiece current;

	private Board board;

	private SetupPieces setupPieces;
	
	public BoardPnl(Board board, SetupPieces stpPieces) {
		// handles which pieces are added/removed in runtime
		setupPieces = stpPieces;

		// setting up edge size from ShapeModel class
		edge = CustomPiece.EDGE;

		// setting panel rows and columns, as well as board
		boardRows = board.getRows();
		boardColumns = board.getColumns();
		this.board = board;

		// initial piece position, Y position is always 0
		initX = ((boardColumns / 2) - 2) * edge;
		initY = 0;

		// instantiating Pieces and adding it to the array list
		//current = getNewPiece(setupPieces.nextPiece(), initX, initY);

		// configuring panel listeners and border
		setBorder(BorderFactory.createLineBorder(Color.black));

		setFocusable(true);
	}

	// set dimensions of panel
	public Dimension getPreferredSize() {
		return new Dimension(boardColumns * edge - 10, boardRows * edge - 10);
	}

	// sets then new piece in current
	public CustomPiece getNextPiece() {
		current = getNewPiece(setupPieces.nextPiece(), initX, initY);
		return current;
	}

	// moves current piece down
	public void movePieceDown() {
		current.moveDown();
	}
	
	//returns the current Shape
	public CustomPiece getCurrent() {
		return current;
	}

	// returns a new Piece given and integer position
	private CustomPiece getNewPiece(int piece, int x, int y) {
		switch (piece) {
		case 0:
			return new PieceM(x, y);
		case 1:
			return new PieceLine(x, y);
		case 2:
			return new PieceCross(x, y);
		case 3:
			return new PieceL(x, y);
		case 4:
			return new PieceZ(x, y);
		case 5:
			return new PieceTonfa(x, y);
		case 6:
			return new PieceH(x, y);
		case 7:
			return new PieceGun(x, y);
		case 8:
			return new PieceC(x, y);
		case 9:
			return new PieceT(x, y);
		default:
			return new PieceLine(x, y);
		}
	}
	
	//custom painting of the panel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int y = 0; y < boardRows; y++) {
			for (int x = 0; x < boardColumns; x++) {
				g.drawRect(x * edge, y * edge, edge, edge);
				g.setColor(Color.DARK_GRAY);
				if (board.getValue(x, y) == 1)
					g.fillRect(x * edge, y * edge, edge, edge);

			}
		}
		if (current!=null)
			current.paintShape(g);

	}
}
