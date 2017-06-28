package view.pieces;

import java.awt.Color;

public class PieceTonfa extends Invertable {
	public PieceTonfa(int x, int y) {
		super(x, y, new int[][]{{0,EDGE},
								{EDGE,EDGE},
								{EDGE*2,EDGE},//centre
								{EDGE*3,EDGE},
								{EDGE*2,0}},new Color(255,152,0));
	}
}
