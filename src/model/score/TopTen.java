package model.score;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TopTen {
	private String[][] topTen;
	private String fileName = "./Data/topten.txt";
	private int scoresCount;
	
	//constructor that receives a new score to be inserted in file
	public TopTen(String usrName, long score) {
		topTen = new String[10][2];
		scoresCount = 0;
		loadTopTen();
		
		int pos = isTopTen(score);
		//if top ten scores is not full append record to file anyway
		//if pos=-1, score is not high enough
		if (scoresCount<10 &&pos==-1)
			newTopTen(usrName, score, scoresCount);
		else if (pos!=-1)
			newTopTen(usrName, score, pos);
		
	}
	
	//constructor that just loads the top ten scores in the array
	public TopTen(){
		topTen = new String[10][2];
		scoresCount = 0;
		loadTopTen();
	}
	
	//insert new high score in top ten file and drops the last record
	private void newTopTen(String usrName, long score, int pos) {
		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName))) ;){
			int position = 0; 
			if (scoresCount<10)
				scoresCount++;
			for (int i = 0 ; i <scoresCount ; i++){
				if (i == pos ){
					pw.println(usrName+":"+score);
					position--;
				}else
					pw.println(topTen[position][0]+":"+topTen[position][1]);
				position++;
			}
		}catch(IOException io){
			System.out.println(io.getMessage());
		}
		
	}

	// checks if score belongs to the top ten
	// if there file is empty returns the position 0
	// if score is not high enough returns -1
	private int isTopTen(long score){
		try {
			Long.parseLong(topTen[0][1]);
		}catch(NumberFormatException n){
			return 0;
		}
		for (int i = 0 ; i<scoresCount; i++){
			if (Long.parseLong(topTen[i][1])<=score)
				return i;
		}
		return -1;
	}
	
	//populates the array with top ten scores and users
	// and count the number of high score in the file
	private boolean loadTopTen(){
		try(Scanner sc = new Scanner(new File(fileName));){
			while (sc.hasNext()){
				String line = sc.nextLine();
				topTen[scoresCount] = line.split(":"); 
				scoresCount++;
			}
			
		}catch (FileNotFoundException f){
			return false;
		}
		return true;
	}
	
	//returns the top ten array, if it is full loaded with data
	// if it is not full returns a new array 
	public String[][] getTopTenData(){
		if (scoresCount == 10)
			return topTen;
		else {
			String[][] topFilled = new String[scoresCount][2];
			for (int i = 0 ; i<scoresCount; i++)
				for (int j = 0 ; j<2; j++)
					topFilled[i][j]=topTen[i][j];
			return topFilled;
		}
	}
	
}
