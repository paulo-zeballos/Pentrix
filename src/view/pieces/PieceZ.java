package view.pieces;

import java.awt.Color;


public class PieceZ extends  Invertable {
	public PieceZ(int x, int y) {
		super(x, y, new int[][]{{0,EDGE},
								{EDGE*2,0},
								{EDGE,EDGE},//centre
								{EDGE*2,EDGE},
								{0,EDGE*2}},new Color(121,85,72));
	}
	
}
