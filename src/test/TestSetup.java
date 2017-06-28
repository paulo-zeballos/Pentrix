package test;

import java.util.Scanner;

import view.pieces.CustomPiece;
import model.game.SetupPieces;

public class TestSetup {

	public static void main(String[] args) {
		SetupPieces stp = new SetupPieces();
		System.out.println("insert class name:");
		String line;
		Scanner sc = new Scanner (System.in);
		while((line=sc.nextLine())!=null){
			stp.removePiece(line);
			
			System.out.println(stp.printPieces());
			
			int a = stp.nextPiece();
			
			System.out.println("nextPiece: "+a);
		}
		
		

	}

}
