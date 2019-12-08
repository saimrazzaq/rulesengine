package com.vison;
/*Class Product implements Product variables and methods */
public class Product {
	private String name;
	private boolean disqualified;
	private double interest_rate;
	
	Product(String na,double ir,boolean dis)
	{
		name =na;
		interest_rate=ir;
		disqualified = dis;
		
		
	}
	
	public boolean isDisqualified() {
		return disqualified;
	}
	public void setDisqualified(boolean disqualified) {
		this.disqualified = disqualified;
	}
	public double getInterest_rate() {
		return interest_rate;
	}
	public void setInterest_rate(long interest_rate) {
		this.interest_rate = interest_rate;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}

}
