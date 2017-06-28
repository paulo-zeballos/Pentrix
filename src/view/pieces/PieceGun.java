package view.pieces;

import java.awt.Color;

public class PieceGun extends Invertable{
	public PieceGun(int x, int y) {
		super(x, y, new int[][]{{0,EDGE},
								{EDGE,0},
								{EDGE,EDGE},//centre
								{EDGE*2,EDGE},
								{0,EDGE*2}},new Color(156,39,176));
	}
}
