package model;

import java.io.Serializable;

public class Scores implements Serializable{
    //Attributes
	private int score;
	
	//Methods
	public Scores(int nScore){
		score=nScore;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int scoreNew) {
		score=scoreNew;
	}
}
