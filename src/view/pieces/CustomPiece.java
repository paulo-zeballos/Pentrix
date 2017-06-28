package view.pieces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class CustomPiece extends Rectangle {
	Rectangle block1, block2, block3, block4, block5;
	public static final int EDGE = 20; // LENGHT OF THE BLOCK EDGES
	protected int centreX, centreY;
	protected Rectangle[] blocks;
	private int[][] blockPositions;
	private Color color;

	public CustomPiece(int x, int y, int[][] pos, Color color) {
		super(x, y, 0, 0);
		this.color=color;
		// instantiating basic pieces or blocks

		block1 = new Rectangle(x+pos[0][0], y+pos[0][1], EDGE,EDGE);
		block2 = new Rectangle(x+pos[1][0], y+pos[1][1], EDGE,EDGE);
		block3 = new Rectangle(x+pos[2][0], y+pos[2][1], EDGE,EDGE);//centre
		block4 = new Rectangle(x+pos[3][0], y+pos[3][1], EDGE,EDGE);
		block5 = new Rectangle(x+pos[4][0], y+pos[4][1], EDGE,EDGE);

		// filling the array of blocks with basic pieces
		blocks = new Rectangle[5];
		blocks[0] = block1;
		blocks[1] = block2;
		blocks[2] = block3;
		blocks[3] = block4;
		blocks[4] = block5;

		// initialising positions array
		blockPositions = new int[5][2];
		updateArrayPositions();

		// initialising point of rotation which is block3 = blocks[2]
		centreX = blocks[2].x;
		centreY = blocks[2].y;

		updateDimensions();

	}

	// calculates and sets the whole shape dimension
	public void updateDimensions() {
		int maxX = blocks[0].x;
		int minX = blocks[0].x;//*****already a method implemented
		int maxY = blocks[0].y;
		int minY = blocks[0].y;//*****already a method implemented
		
		for (int i = 1; i < blocks.length; i++) {
			if (blocks[i].x > maxX) maxX=blocks[i].x;
			if (blocks[i].x < minX) minX=blocks[i].x;
			if (blocks[i].y > maxY) maxY=blocks[i].y;
			if (blocks[i].y < minY) minY=blocks[i].y;
		}
		this.setSize((maxX-minX) + EDGE, (maxY-minY) + EDGE);
	}

	// updates array of positions
	private void updateArrayPositions() {
		for (int i = 0; i < blocks.length; i++) {
			blockPositions[i][0] = blocks[i].x;
			blockPositions[i][1] = blocks[i].y;
		}
	}
	
	//updates the positions of the point of rotation: centreX and centreY
	private void updateCentre(){
		centreX = blocks[2].x;
		centreY = blocks[2].y;	
	}
	
	//returns the centre position X
	public int getCentreX(){
		return centreX;
	}
	
	//returns the centre position Y
		public int getCentreY(){
			return centreY;
		}
	
	//updates the location of the whole shape
	public void updateLocation(){
		this.x=getIntMinX();
		this.y=getIntMinY();
	}
	
	//returns the Min 'x' value from the basic shapes: blocks
	private int getIntMinX(){
		int minX = blocks[0].x;
		for (int i = 1; i < blocks.length; i++) 
			if (blocks[i].x < minX) 
				minX=blocks[i].x;
		return minX;
	}
	
	//returns the Min 'y' value from the basic shapes: blocks
		private int getIntMinY(){
			int minY = blocks[0].y;
			for (int i = 1; i < blocks.length; i++) 
				if (blocks[i].y < minY) 
					minY=blocks[i].y;
			return minY;
		}
	// translates Block, updates 2D array blockPositions 
	public void translateBlock(int block, int dx, int dy) {
		blocks[block - 1].translate(dx, dy);
	
	}
	
		
	// returns Delta x from the point of rotation (2*EDGE,2*EDGE)
	public int getDeltaX(int x) {
		return x - centreX;
	}

	// returns Delta y from the point of rotation (2*EDGE,2*EDGE)
	public int getDeltaY(int y) {
		return y - centreY;
	}
	
	//rotates shape anti-clockwise
	public void rotateAntiClock(){
		for (int i = 0 ;i <blocks.length; i++)
			rotBlockAntiClock(blocks[i].x, blocks[i].y);
		updateArrayPositions();
		updateDimensions();
		updateLocation();
	}
	
	// rotates shape anti-clockwise
	public void rotateClock() {
		for (int i = 0; i < blocks.length; i++)
			rotBlockClock(blocks[i].x, blocks[i].y);
		updateArrayPositions();
		updateDimensions();
		updateLocation();
	}

	// rotates block anti clockwise from the point of rotation centre
	private void rotBlockClock(int posX, int posY) {
		int deltaX = getDeltaX(posX);
		int deltaY = getDeltaY(posY);
		int factor = Math.max(Math.abs(deltaX), Math.abs(deltaY)) / EDGE;
		
		
		int block = getBlock(posX, posY);
		if (block == -1)
			return;
		
		if (deltaX == EDGE * factor && deltaY == 0)
			translateBlock(block, -1 * EDGE * factor, EDGE * factor);
		else if (deltaX == -1 * EDGE * factor && deltaY == 0)
			translateBlock(block, EDGE * factor, -1 * EDGE * factor);
		else if (deltaX == 0 && deltaY == -1 * EDGE * factor)
			translateBlock(block, EDGE * factor, EDGE * factor);
		else if (deltaX == 0 && deltaY == EDGE * factor)
			translateBlock(block, -1 * EDGE * factor, -1 * EDGE * factor);
		else if (deltaX == EDGE * factor && deltaY == -1 * EDGE * factor)
			translateBlock(block, 0, 2 * EDGE * factor);
		else if (deltaX == -1 * EDGE * factor && deltaY == -1 * EDGE * factor)
			translateBlock(block, 2 * EDGE * factor, 0);
		else if (deltaX == -1 * EDGE * factor && deltaY == EDGE * factor)
			translateBlock(block, 0, -2 * EDGE * factor);
		else if (deltaX == EDGE * factor && deltaY == EDGE * factor)
			translateBlock(block, -2 * EDGE * factor, 0);
		
	}

	// rotates block clockwise from the point of rotation centre
	private void rotBlockAntiClock(int posX, int posY) {
		int deltaX = getDeltaX(posX);
		int deltaY = getDeltaY(posY);
		int factor = Math.max(Math.abs(deltaX), Math.abs(deltaY)) / EDGE;

		int block = getBlock(posX, posY);
		if (block == -1)
			return;
		System.out.println("Centre X:"+centreX+ " Y:"+ centreY + " Block: "
				+block);
		System.out.println("	X:"+blocks[block-1].x+ " Y:"+blocks[block-1].y
				+" dx: "+deltaX+" dy:"+deltaY + " factor:");
		
		if (deltaX == EDGE * factor && deltaY == 0)
			translateBlock(block, -1 * EDGE * factor, -1 * EDGE * factor);
		else if (deltaX == -1 * EDGE * factor && deltaY == 0)
			translateBlock(block, EDGE * factor, EDGE * factor);
		else if (deltaX == 0 && deltaY == -1 * EDGE * factor)
			translateBlock(block, -1 * EDGE * factor, EDGE * factor);
		else if (deltaX == 0 && deltaY == EDGE * factor)
			translateBlock(block, EDGE * factor, -1 * EDGE * factor);
		else if (deltaX == EDGE * factor && deltaY == -1 * EDGE * factor)
			translateBlock(block, -2 * EDGE * factor, 0);
		else if (deltaX == -1 * EDGE * factor && deltaY == -1 * EDGE * factor)
			translateBlock(block, 0, 2 * EDGE * factor);
		else if (deltaX == -1 * EDGE * factor && deltaY == EDGE * factor)
			translateBlock(block, 2 * EDGE * factor, 0);
		else if (deltaX == EDGE * factor && deltaY == EDGE * factor)
			translateBlock(block, 0, -2 * EDGE * factor);
		
		System.out.println("Move to "+" X:"+blocks[block-1].x+ " Y:"+blocks[block-1].y
				+" dx: "+deltaX+" dy:"+deltaY + " factor:");
	}

	// inverts the shape, line: H=Horizontal, V=Vertical
	protected void invertBlock(int posX, int posY, String line) {
		int deltaX = getDeltaX(posX);
		int deltaY = getDeltaY(posY);

		int block = getBlock(posX, posY);
		if (block == -1)
			return;
				
		if (deltaX == 0 && deltaY == -1 * EDGE && line.equals("H"))
			translateBlock(block, 0, 2 * EDGE);
		else if (deltaX == 0 && deltaY == EDGE && line.equals("H"))
			translateBlock(block, 0, -2 * EDGE);
		else if (deltaX == -1 * EDGE && deltaY == 0 && line.equals("V"))
			translateBlock(block, 2 * EDGE, 0);
		else if (deltaX == 1 * EDGE && deltaY == 0 && line.equals("V"))
			translateBlock(block, -2 * EDGE, 0);
		else if (deltaX == -1 * EDGE && deltaY == -1 * EDGE && line.equals("H"))
			translateBlock(block, 0, 2 * EDGE);
		else if (deltaX == -1 * EDGE && deltaY == -1 * EDGE && line.equals("V"))
			translateBlock(block, 2 * EDGE, 0);
		else if (deltaX == -1 * EDGE && deltaY == EDGE && line.equals("H"))
			translateBlock(block, 0, -2 * EDGE);
		else if (deltaX == -1 * EDGE && deltaY == EDGE && line.equals("V"))
			translateBlock(block, 2 * EDGE, 0);
		else if (deltaX == EDGE && deltaY == EDGE && line.equals("H"))
			translateBlock(block, 0, -2 * EDGE);
		else if (deltaX == EDGE && deltaY == EDGE && line.equals("V"))
			translateBlock(block, -2 * EDGE, 0);
		else if (deltaX == EDGE && deltaY == -1 * EDGE && line.equals("H"))
			translateBlock(block, 0, 2 * EDGE);
		else if (deltaX == EDGE && deltaY == -1 * EDGE && line.equals("V"))
			translateBlock(block, -2 * EDGE, 0);
		
	}
	
	//moves the shape EDGE-distance to the left
	public void moveLeft(){
		for (int i = 1; i <= blocks.length; i++) 
			translateBlock(i,-1*EDGE, 0);
		this.x=x-EDGE;
		updateArrayPositions();
		updateCentre();
		updateLocation();
	}

	// moves the shape EDGE-distance to the right
	public void moveRight() {
		for (int i = 1; i <= blocks.length; i++)
			translateBlock(i, EDGE, 0);
		this.x=x+EDGE;//************************
		updateArrayPositions();
		updateCentre();
		updateLocation();
	}

	// moves the shape EDGE-distance down
	public void moveDown() {
		for (int i = 1; i <= blocks.length; i++)
			translateBlock(i,0, EDGE);
		this.y=y+EDGE;
		updateArrayPositions();
		updateCentre();
		updateLocation();
	}

	// returns the block in the array given some coordinates
	protected int getBlock(int posX, int posY) {
		for (int i = 0; i < blocks.length; i++) {
			if (posX == blocks[i].x && posY == blocks[i].y)
				return i+1;
		}
		return -1;
	}
	
	// returns the x coordinate given the block position in the array
	protected int getCoordinateX(int pos) {
		return blocks[pos - 1].x;
	}

	// returns the x coordinate given the block position in the array
	protected int getCoordinateY(int pos) {
		return blocks[pos - 1].y;
	}

	// returns the positions in and array blockPositios
	public int[][] getPositions() {
		return blockPositions;
	}
	
	// returns the positions in and array blockPositios
	public void  printArray(int[][] d) {
		for (int i = 0; i < d.length; i++) {
			for (int j = 0; j < d[0].length; j++)
				System.out.print( d[i][j] + "  ");
			System.out.println();
		}
	}
	
	//get the blocks facing the bottom
	public int[][] getLowerBlocks(){
		int n = this.width/EDGE;
		int[][] baseBlocks = new int[n][2];
		int temp;
		
		for (int i = 0; i<n; i++){
			temp = -1;//no value above this position on the board
			for (int j = 0; j<blockPositions.length; j++){
				if (blockPositions[j][0]==this.x+(i*EDGE) && blockPositions[j][1]>=temp){
					baseBlocks[i][0]=this.x+(i*EDGE);
					baseBlocks[i][1]=blockPositions[j][1];
					temp = blockPositions[j][1];
				}	 
			}
		}
		return baseBlocks;
	}
	
	// get the blocks facing right
	public int[][] getRghtBlocks() {
		int n = this.height / EDGE;
		int[][] rgtBlocks = new int[n][2];
		int temp;

		for (int i = 0; i < n; i++) {
			temp = -1;//no value more to the left than this
			for (int j = 0; j < blockPositions.length; j++) {
				if (blockPositions[j][1] == this.y + (i*EDGE)
						&& blockPositions[j][0] >= temp) {
					rgtBlocks[i][0] = blockPositions[j][0];
					rgtBlocks[i][1] = this.y + (i*EDGE);
					temp = blockPositions[j][0];
				}
			}
		}
		return rgtBlocks;
	}

	
	// get the blocks facing left
	public int[][] getLeftBlocks() {
		int n = this.height / EDGE;
		int[][] leftBlocks = new int[n][2];
		int temp;

		for (int i = 0; i < n; i++) {
			temp = this.x+this.width+EDGE;//no value more to the right than this
			for (int j = 0; j < blockPositions.length; j++) {
				if (blockPositions[j][1] == this.y + (i*EDGE)
						&& blockPositions[j][0] <= temp) {
					leftBlocks[i][0] = blockPositions[j][0];
					leftBlocks[i][1] = this.y + (i*EDGE);
					temp = blockPositions[j][0];
				}
			}
		}
		return leftBlocks;
	}
	
	//returns the orientation of the shape 
	//take as a reference the position of block 1 from the centre
	public String getOrientation(){
		int posX = getCoordinateX(1);
		if (posX - centreX ==0)
			return "V";
		else 
			return "H"; 
	}
	
	
	//Paints the shape block by block
	public void paintShape(Graphics g) {
		
		for (int i=0; i<blocks.length;i++){
			g.setColor(color);
			g.fillRect(blocks[i].x, blocks[i].y, EDGE, EDGE);
			g.setColor(Color.BLACK);
			g.drawRect(blocks[i].x, blocks[i].y, EDGE, EDGE);
		}
		

	}
}
