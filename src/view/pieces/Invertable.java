package view.pieces;

import java.awt.Color;

/**
 * When setting up the coordinates for the new piece take into account that
 * the first block will define the line of inversion
 * @author s3568672
 *
 */
public abstract class Invertable extends CustomPiece {
	
	public Invertable(int x, int y, int[][] posBlocks, Color color){
		super(x,y,posBlocks, color);
	}
	
	public void invertPiece() {
		String line = getOrientation(); 
		for (int i = 0 ;i <blocks.length; i++)
			invertBlock(blocks[i].x,blocks[i].y,line);
		
	}
}
