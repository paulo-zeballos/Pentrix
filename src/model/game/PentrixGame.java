package model.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import model.board.Board;
import model.score.Score;
import model.score.TopTen;
import view.game.BoardPnl;
import view.game.ScorePnl;
import view.listeners.KeyListenerPentrix;
import view.pieces.CustomPiece;
import view.pieces.Invertable;

public class PentrixGame {
	private String session;

	int panelWidth, panelHeight;
	int boardRows, boardColumns;

	int edge;
	CustomPiece current;

	Board board;

	ScorePnl scorePnl;
	Score score;
	TopTen topTen;

	Timer timer;
	int speed;

	
	private int status;//1=Running, 0=pause, -1=terminated
	
	BoardPnl boardPnl;

	public PentrixGame(BoardPnl boardPnl, Board board, String session,
			ScorePnl scorePnl) {
		this.session = session;
		this.boardPnl = boardPnl;
		// handles which pieces are added/removed in runtime
		

		// setting up edge size from ShapeModel class
		edge = CustomPiece.EDGE;

		// initialising timer
		
		speed = 1000;
		timer = new Timer(speed, getActionListener());
		//timer.start();

		// setting panel rows and columns, as well as board
		this.board = board;
		this.boardRows = board.getRows();
		this.boardColumns = board.getColumns();
		
		// initialises the score to 0
		score = new Score();
		this.scorePnl = scorePnl;
		
		// configuring boarrdPnl listeners and border
		boardPnl.setBorder(BorderFactory.createLineBorder(Color.black));
		boardPnl.addKeyListener(getKeyListener());
		boardPnl.setFocusable(true);
		startGame();
	}
	
	public KeyListener getKeyListener(){
		return new KeyListener(){
			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode()==KeyEvent.VK_PAGE_DOWN ){
					if (!detectRotAntiClkCrash()){
						System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
						//repaint(current.x,current.y,current.width,current.height);
						current.rotateAntiClock();
						System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
						//repaint(current.x,current.y,current.width+1,current.height+1);
						boardPnl.repaint();
					}
				}
				
				if (event.getKeyCode()==KeyEvent.VK_PAGE_UP ){
					if (!detectRotClkCrash()){
						System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
						//repaint(current.x,current.y,current.width,current.height);
						current.rotateClock();
						System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
						//repaint(current.x,current.y,current.width+1,current.height+1);
						boardPnl.repaint();
					}
				}
				if (event.getKeyCode()==KeyEvent.VK_UP ){
					
					if (current instanceof Invertable){
						System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
						//repaint(current.x,current.y,current.width,current.height);
						((Invertable)current).invertPiece();
						System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
						//repaint(current.x,current.y,current.width+1,current.height+1);
						boardPnl.repaint();
					}
				}	
				if (event.getKeyCode()==KeyEvent.VK_DOWN ){
					if (outOfBottomPnl() || detectDwnCrash()){
						checkGameStatus();
					}else{
						System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
						boardPnl.repaint(current.x,current.y,current.width,current.height);
						current.moveDown();
						System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
						boardPnl.repaint(current.x,current.y,current.width+1,current.height+1);
			
					}
				}
				if (event.getKeyCode()==KeyEvent.VK_LEFT ){
					if (outOfLeftPnl() || detectLeftCrash()){
					}else{
						System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
						boardPnl.repaint(current.x,current.y,current.width+1,current.height+1);
						current.moveLeft();
						System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
						boardPnl.repaint(current.x,current.y,current.width+1,current.height+1);
					}	
				}
				if (event.getKeyCode()==KeyEvent.VK_RIGHT ){
					if (outOfRightPnl() || detectRghtCrash()){
						}else{
						System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
						boardPnl.repaint(current.x,current.y,current.width+1,current.height+1);
						current.moveRight();
						System.out.println("x: "+current.x+" - y:"+current.y+ " - W:"+current.width+" - H:"+current.height);
						boardPnl.repaint(current.x,current.y,current.width+1,current.height+1);
					}
				}	
				
			}
		};
	}
	public ActionListener getActionListener(){
		return new ActionListener(){
			//handles the timer actions
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boardPnl.requestFocusInWindow();
				if (outOfBottomPnl() || detectDwnCrash()){
					checkGameStatus();
				}
				else {
					
					//repaint(current.x,current.y,current.width,current.height);
					boardPnl.movePieceDown();
					//repaint(current.x,current.y,current.width+1,current.height+1);
					boardPnl.repaint();
				}
			}
		};
	}
	//checks the game status 
	public void checkGameStatus(){
		if (status == 1){
		if (current.y == 0)
				endGame();
			else{
				updateBoard();
				processNewPiece();
				boardPnl.repaint();
			}
		}
	}
	// creates a new game
	public void startGame() {
		current = boardPnl.getNextPiece();
		timer.start();
		status = 1;
	}

	// pauses the game
	public void pauseGame() {
		timer.stop();
		status = 0;
	}

	// resumes the game
	public void resumeGame() {
		timer.start();
		status = 1;
	}

	// terminates the game and writes in top ten if achieved
	public void endGame() {
		timer.stop();
		topTen = new TopTen(session, score.getScore());
		status = -1;
		JOptionPane.showMessageDialog(null,
				"Thanks for playing Pentrix s3568672", "GAME OVER",
				JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}

	// check out of bottom panel
	public boolean outOfBottomPnl() {
		if (current.y + current.height + edge / 2 >= boardRows * edge)
			return true;
		else
			return false;
	}

	// check out of bottom panel
	public boolean detectDwnCrash() {
		int[][] bottomBlocks = current.getLowerBlocks();
		for (int i = 0; i < bottomBlocks.length; i++)
			if (board.chkDownCrash(bottomBlocks[i][0] / edge,
					bottomBlocks[i][1] / edge))
				return true;
		return false;
	}

	// check out of right boundaries
	public boolean outOfRightPnl() {
		if (current.x + current.width + edge / 2 >= boardColumns * edge)
			return true;
		else
			return false;
	}

	// check out of bottom panel
	public boolean detectRghtCrash() {
		int[][] easternBlocks = current.getRghtBlocks();
		for (int i = 0; i < easternBlocks.length; i++)
			if (board.chkRightCrash(easternBlocks[i][0] / edge,
					easternBlocks[i][1] / edge))
				return true;
		return false;
	}

	// checks out of left boundaries
	public boolean outOfLeftPnl() {
		if (current.x <= 0)
			return true;
		else
			return false;
	}

	// checks left crash
	public boolean detectLeftCrash() {
		int[][] westernBlocks = current.getLeftBlocks();
		for (int i = 0; i < westernBlocks.length; i++)
			if (board.chkLeftCrash(westernBlocks[i][0] / edge,
					westernBlocks[i][1] / edge))
				return true;
		return false;
	}

	// checks crash when rotating clockwise
	public boolean detectRotClkCrash() {
		if (board.chkRotClkCrashA(current, edge))
			return true;

		return false;
	}

	// checks crash when rotating anti-clockwise
	public boolean detectRotAntiClkCrash() {
		if (board.chkRotAntiClkCrashA(current, edge))
			return true;

		return false;
	}

	//adds the piece to the board 
		public void updateBoard(){
			int[][] blkPos =current.getPositions();
			for (int i = 0; i < blkPos.length; i++) 
				board.setPosBoard(blkPos[i][0]/edge, blkPos[i][1]/edge);
				
		}
		//processes new Piece
		public void processNewPiece(){
			int lines =board.getRowsDone(current.y/edge);
			score.increase(lines);
			scorePnl.updateScore(score.getScore()+"");
			current = boardPnl.getNextPiece();
			 
		}

}
