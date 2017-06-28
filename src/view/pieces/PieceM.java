package view.pieces;

import java.awt.Color;

public class PieceM extends  Invertable{
	
	public PieceM(int x, int y) {
		super(x, y, new int[][]{{EDGE,0},
								{EDGE*2,0},
								
								{EDGE,EDGE},//centre
								{0,EDGE},
								{0,EDGE*2}},new Color(205,220,57));
	}

}
