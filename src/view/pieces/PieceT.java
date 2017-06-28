package view.pieces;

import java.awt.Color;

public class PieceT extends CustomPiece{
	public PieceT(int x, int y) {
		super(x, y, new int[][]{{0,0},
								{EDGE,0},
								{EDGE,EDGE},//centre
								{2*EDGE,0},
								{EDGE,2*EDGE}},new Color(255,235,59));
	}
}
