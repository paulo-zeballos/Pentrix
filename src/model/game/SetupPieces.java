package model.game;

import java.util.ArrayList;
import java.util.Random;

public class SetupPieces {
	private ArrayList<String> pieces;
	private final String[][] arrayPieces = { { "PieceM", "0" },
			{ "PieceLine", "1" }, { "PieceCross", "2" }, { "PieceL", "3" },
			{ "PieceZ", "4" }, { "PieceTonfa", "5" }, { "PieceH", "6" },
			{ "PieceGun", "7" }, { "PieceC", "8" }, { "PieceT", "9" } };;

	private Random r;

	/**
	 * This class returns an available piece number from and array list of
	 * pieces modified in runtime
	 */
	public SetupPieces() {
		pieces = new ArrayList<>();
		r = new Random();
		loadAllPieces();
	}

	// returns the next random piece from available ones in arraylist
	public int nextPiece() {
		int random = r.nextInt(pieces.size());
		int pos = getPosition(pieces.get(random));
		if (pos != -1)
			return pos;
		return -1;
	}

	// adds the given piece to the array list
	public boolean addPiece(String pName) {
		int pos = getPieceFrmArray(pName);
		if (pieces.size() < 10 && pos != -1) {
			pieces.add(arrayPieces[pos][0]);
			return true;
		} else
			return false;
	}

	// removes the given piece from the array list
	public boolean removePiece(String pName) {
		int pos = getPieceFrmAList(pName);
		if (pieces.size() > 1 && pos != -1) {
			pieces.remove(pos);
			return true;
		} else
			return false;
	}

	// returns the position of the piece from the
	// arrayPieces matching a given name
	private int getPieceFrmArray(String pName) {
		for (int i = 0; i < arrayPieces.length; i++) {
			if (arrayPieces[i][0].equals(pName))
				return i;
		}
		return -1;
	}

	// returns the position of the CustomShape from the
	// arrayList matching a given class name
	private int getPieceFrmAList(String pName) {
		for (int i = 0; i < pieces.size(); i++) {
			if (pieces.get(i).equals(pName))
				return i;
		}
		return -1;
	}

	// loads all the pieces from array into array list
	private void loadAllPieces() {
		for (int i = 0; i < arrayPieces.length; i++)
			pieces.add(arrayPieces[i][0]);
	}

	// print the array list
	public String printPieces() {
		String[] p = pieces.toArray(new String[0]);
		String print = "";
		for (int i = 0; i < pieces.size(); i++)
			print += p[i] + " :: ";
		return print;
	}

	// returns the position of the piece in the array given the name
	private int getPosition(String piece) {
		for (int i = 0; i < arrayPieces.length; i++)
			if (arrayPieces[i][0].equals(piece))
				return Integer.parseInt(arrayPieces[i][1]);
		return -1;
	}
}
