package model.board;

import view.pieces.CustomPiece;

public class Board {
	int[][] board;
	private int rows;
	private int columns;

	public Board(int rows, int cols) {
		this.rows = rows;
		this.columns = cols;
		board = new int[rows][columns];
		fillBoard();

	}
	
	//accessors board 
	public int getRows(){
		return this.rows;
	}
	public int getColumns(){
		return this.columns;
	}

	// fills the matrix with 0's
	private void fillBoard() {
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				board[i][j] = 0;
	}

	// sets in to the given position in the array
	public void setPosBoard(int posX, int posY) {
		board[posY][posX] = 1;
	}

	// resets a given row in the array and calls a method to update rows above
	public void resetRow(int row) {
		for (int i=0; i<columns; i++)
			board[row][i] = 0;
		
		updateBoard(row);
	}
	
	//translates the values one row down from a given row
	private void updateBoard(int row){
		for (int r = row; r >0; r--){
			for(int c=0; c<columns ; c++){
				board[r][c]= board[r-1][c];
			}
		}
	}
	
	// reset in to the given position in the array
	public int getValue(int posX, int posY) {
		return board[posY][posX];
	}

	// return true if there is collision if moving left 
	public boolean chkLeftCrash(int posX, int posY) {
		if (posX==0)//to avoid out position -1 in the board
			return true;
		
		if (board[posY][posX-1] == 1)
			return true;
		else
			return false;
	}

	// return true if there is collision if moving right
	public boolean chkRightCrash(int posX, int posY) {
		if (board[posY][posX+1] == 1)
			return true;
		else
			return false;
	}

	// return true if there is collision if moving down
	public boolean chkDownCrash(int posX, int posY) {
		if (board[posY+1][posX] == 1)
			return true;
		else
			return false;
	}

	// return true if there is collision if rotating anti-clockwise
		public boolean chkRotClkCrashA(CustomPiece piece, int edge) {
			int xPos = piece.x / edge;
			int yPos = piece.y / edge;
			int height = piece.height / edge;
			int width = piece.width / edge;
			int centreX = piece.getCentreX() / edge;
			int centreY = piece.getCentreY() / edge;
			int topArm = centreY - yPos + 1;// +1 for the centre block
			int bttmArm = yPos + height - centreY - 1;// -1 for the centre block
			int leftArm = centreX - xPos;
			int rightArm = xPos + width - centreX;

			// check collision outside the boundaries of the board
			if (leftArm > centreY || rightArm + centreY > rows
					|| bttmArm > centreX || topArm + centreX > columns)
				return true;

			if (height == width)
				return chkSquareArea(yPos, xPos, height);
			else {
				String orientation = piece.getOrientation();
				if (orientation.equals("V"))
					return chkSquareArea(yPos, xPos - bttmArm, height);
				else
					return chkSquareArea(yPos - leftArm, xPos, width);
			}
		}	
	// return true if there is collision if rotating anti-clockwise
	public boolean chkRotAntiClkCrashA(CustomPiece piece, int edge) {
		int xPos = piece.x / edge;
		int yPos = piece.y / edge;
		int height = piece.height / edge;
		int width = piece.width / edge;
		int centreX = piece.getCentreX() / edge;
		int centreY = piece.getCentreY() / edge;
		int topArm = centreY - yPos;
		int bttmArm = yPos + height - centreY;
		int leftArm = centreX - xPos + 1;// +1 for the centre block
		int rightArm = xPos + width - centreX - 1;// -1 for the centre block

		// check collision outside the boundaries of the board
		if (rightArm > centreY || leftArm + centreY > rows || topArm > centreX
				|| bttmArm + centreX > columns)
			return true;

		if (height == width)
			return chkSquareArea(yPos, xPos, height);
		else {
			String orientation = piece.getOrientation();
			if (orientation.equals("V"))
				return chkSquareArea(yPos, xPos - topArm, height);
			else
				return chkSquareArea(yPos - rightArm, xPos, width);
		}
	}

	// returns true if found at least a 1 in a certain board area
	private boolean chkSquareArea(int row, int col, int side) {
		if (row<0||col<0)	return true; //*****revise negative values when rotating
		for (int r = row; r < row + side; r++)
			for (int c = col; c < col + side; c++)
				if (board[r][c] == 1)
					return true;

		return false;
	}
		
	//checks if lines are completed in the board & calls a method to reset them
	public int getRowsDone(int posY){
		int nLines=0;
		int sum;
		for(int r =posY; r<rows ; r++){
			sum=0;
			for(int c=0; c<columns;c++ )
				if (board[r][c]==1)
					sum++;
			if (sum==columns){
				nLines++;
				resetRow(r);
			}
		}
		
		return nLines;
	}

}
