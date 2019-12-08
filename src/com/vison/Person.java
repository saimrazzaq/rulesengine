package com.vison;
/*Class Person implements Person variables and methods */
public class Person {
	private int credit_score;
	private String state;
	Person(int cs, String st)
	{
		
		credit_score = cs;
		state = st;
		
	}
	
	public int getCredit_score() {
		return credit_score;
	}
	public void setCredit_score(int credit_score) {
		this.credit_score = credit_score;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
