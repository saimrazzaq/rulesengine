
package com.vison;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class rulesengine {
	// class rulesengine implements rules class, load rules & appy rules
	class Rules {
		
		String criteria;
		String condition;
		int value;
		String action;
		double parameter;
		
		
	}
	public static Rules[] loadrules()
	{
		
		// reading json file and extracting array of rule objects
		// & mapping rules to Rules class
		Gson m = new Gson();
		Rules[] Rules = null;
		try {
			Rules = m.fromJson(new FileReader("data/rules.json"), Rules[].class);
		} catch (JsonSyntaxException e) {
			
			e.printStackTrace();
		} catch (JsonIOException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	    
		
	    return Rules;
		
		
		
		
	}
	public static String[] run_rules(Person person,Product product, Rules[] rules)
	{
		double interest_rate=product.getInterest_rate();
		boolean disqualified=false;
		String[] results = new String[2];
		boolean error = false;
	 
		// checking input errors
		if(person.getCredit_score()<=200 || person.getCredit_score()>=850)
		{
			results[0] = "error";
		    results[1] = "Credit Score is not between 200 & 850";
		    error = true;
		    
		}
		if(person.getState().length()==0 || !person.getState().matches("^[a-zA-Z]*$")) 
		{
			results[0] = "error";
		    results[1] = "State Input";
		    error = true;
		    
		}
		if(product.getInterest_rate()<=0.0)
		{
			results[0] = "error";
		    results[1] = "product interest rate cannot be negative";
		    error = true;
		   
		}
		if(product.getName().length()==0)
		{
			results[0] = "error";
		    results[1] = "product input error";
		    error = true;
		   
		}
		
		if(!error)
		{
			// loop through the rules and checking if any rule applies
			for(int i=0; i<rules.length;i++)
			{
				
				
				// additional operators can easily be implemented i.e in action divide and multiply 
				
				// checking rules IF criteria is credit score
				if(rules[i].criteria.contentEquals("creditscore"))
				{
						//checking if condition is greater than equal
						if(rules[i].condition.contentEquals(">=")) 
						{
						// checking if person's credit score is greater or equal to rule value
							if(person.getCredit_score()>=rules[i].value && rules[i].action.contentEquals("-"))
								interest_rate-=rules[i].parameter;
						
						
						}
				
				
						//checking if condition is less
					    if(rules[i].condition.contentEquals("<")) 
						{
						// checking if person's credit score has value less than rules  value
							if(person.getCredit_score()<rules[i].value && rules[i].action.contentEquals("+"))
								interest_rate+=rules[i].parameter;
						}
					    
				
				
				
				}
				//checking rule IF criteria is PRODUCT
				if(rules[i].criteria.contentEquals("product"))
				{
					
					
					if(rules[i].condition.contentEquals("7-1 ARM") && product.getName().contentEquals("7-1 ARM")) 
						if(rules[i].action.contentEquals("+"))
							interest_rate+= rules[i].parameter;
					
					
					
					
				}
			
			
				//checking rule IF criteria is STATE
				if(rules[i].criteria.contentEquals("state"))
				{
					
					
					if(rules[i].condition.contentEquals("florida") && person.getState().contentEquals("florida"))
						if(rules[i].action.contentEquals("disqualified"))
						   disqualified = true;
					
					if(rules[i].condition.contentEquals("hawaii") && person.getState().contentEquals("hawaii"))
						if(rules[i].action.contentEquals("+"))
							interest_rate+= rules[i].parameter;
					
				}	
			
			}
		}
		// if there is any error in input send 
		// results with error and description
		if(error)
		{
			return results;
		}
		else
		{
		results[0] = String.valueOf(interest_rate);
		results[1] = String.valueOf(disqualified);
		return results;
		}
	 }
	 
	 }
	
	
	
	

