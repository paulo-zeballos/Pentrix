package view.pieces;

import java.awt.Color;

public class PieceC extends CustomPiece {
	public PieceC(int x, int y) {
		super(x, y, new int[][]{{0,0},
								{0,EDGE},
								{EDGE,0},//centre
								{EDGE*2,0},
								{EDGE*2,EDGE}},new Color(233,30,99));
	}
}
