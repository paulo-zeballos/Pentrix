package view.pieces;

import java.awt.Color;

public class PieceL extends  Invertable{

	public PieceL(int x, int y) {
		super(x, y, new int[][]{{0,EDGE},
								{EDGE,EDGE},
								{EDGE*2,EDGE},//centre
								{EDGE*3,EDGE},
								{EDGE*3,0}},new Color(0,150,136));
	}


}
