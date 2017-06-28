package view.pieces;

import java.awt.Color;

public class PieceH extends Invertable{
	public PieceH(int x, int y) {
		super(x, y, new int[][]{{0,0},
								{0,EDGE*2},
								{0,EDGE},//centre
								{EDGE,EDGE},
								{EDGE,EDGE*2}},new Color(63,81,181));
	}
}
