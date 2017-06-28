package model.score;

public class Score {
	private long score;
	public static final int INCREMENT = 10;
	public Score(){
		score=0;
	}
	
	//increments score by lines done
	public void increase(int lines){
		if (lines > 0 )
			score += INCREMENT*Math.pow(2,lines*2-2);
	}
	
	//returns score value
	public long getScore(){
		return score;
	}
}
