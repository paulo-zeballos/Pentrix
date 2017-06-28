package view.pieces;


import java.awt.Color;

public class PieceLine extends CustomPiece {
	/**Instantiates Piece with a line shape 
	 * 
	 * @param x coordinate	
	 * @param y coordinate
	 */
	public PieceLine(int x, int y) {
		super(x, y, new int[][]{{0,0},
								{EDGE,0},
								{2*EDGE,0},
								{3*EDGE,0},
								{4*EDGE,0}},new Color(76,175,80));
	}
		
	
}
