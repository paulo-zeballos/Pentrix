package view.pieces;

import java.awt.Color;

public class PieceCross extends CustomPiece{
	public PieceCross(int x, int y) {
		super(x, y, new int[][]{{EDGE,0},
								{0,EDGE},
								{EDGE,EDGE},
								{2*EDGE,EDGE},
								{EDGE,2*EDGE}},new Color(244,67,54));
	}
}
