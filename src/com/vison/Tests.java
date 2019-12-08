package com.vison;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.vison.rulesengine.Rules;

class Tests {
	String[] results = new String[2];
	@Test
	void test_interestrate_1() {
		// same input as in excercise
		Product products = new Product("7-1 ARM",5.0,false);
		Person person = new Person(720,"florida");
		Rules[] rules = rulesengine.loadrules();
		results =rulesengine.run_rules(person,products,rules);
		System.out.println("Test 1");
		System.out.println("product interest rate is :" + results[0]);
		System.out.println("product disqualified is :" +results[1]);
		assertEquals(results[0],"5.2");
		assertEquals(results[1],"true");
	}
	
	@Test
	void test_interestrate_2() {
		// test with different valid product, state & credit score
		Product products = new Product("Apple Ipad",5.0,false);
		Person person = new Person(730,"california");
		Rules[] rules = rulesengine.loadrules();
		results =rulesengine.run_rules(person,products,rules);
		System.out.println("Test 2");
		System.out.println("product interest rate is :" + results[0]);
		System.out.println("product disqualified is :" +results[1]);
		
		assertEquals(results[0],"4.7");
		assertEquals(results[1],"false");
	}
	@Test
	void test_interestrate_3() {
		// test with  lower credit score
		Product products = new Product("Apple Ipad",5.0,false);
		Person person = new Person(500,"california");
		Rules[] rules = rulesengine.loadrules();
		results =rulesengine.run_rules(person,products,rules);
		System.out.println("Test 3");
		System.out.println("product interest rate is :" + results[0]);
		System.out.println("product disqualified is :" +results[1]);
		
		assertEquals(results[0],"5.5");
		assertEquals(results[1],"false");
	}
	@Test
	void test_interestrate_4() {
		// test with  lower credit score & state florida
		Product products = new Product("7-1 ARM",5.0,false);
		Person person = new Person(500,"florida");
		Rules[] rules = rulesengine.loadrules();
		results =rulesengine.run_rules(person,products,rules);
		System.out.println("Test 4");
		System.out.println("product interest rate is :" + results[0]);
		System.out.println("product disqualified is :" +results[1]);
		
		assertEquals(results[0],"6.0");
		assertEquals(results[1],"true");
	}
	@Test
	void test_interestrate_5() {
		// test with  lower credit score  product interest rate = 4
		Product products = new Product("7-1 ARM",4.0,false);
		Person person = new Person(500,"california");
		Rules[] rules = rulesengine.loadrules();
		results =rulesengine.run_rules(person,products,rules);
		System.out.println("Test 5");
		System.out.println("product interest rate is :" + results[0]);
		System.out.println("product disqualified is :" +results[1]);
		
		assertEquals(results[0],"5.0");
		assertEquals(results[1],"false");
	}
	@Test
	void test_interestrate_6() {
		// testing  NEW RULE state = hawaii then add 0.2 to interest rate
		Product products = new Product("Samsung Note 10",5.0,false);
		Person person = new Person(500,"hawaii");
		Rules[] rules = rulesengine.loadrules();
		results =rulesengine.run_rules(person,products,rules);
		System.out.println("Test 6");
		System.out.println("product interest rate is :" + results[0]);
		System.out.println("product disqualified is :" +results[1]);
		
		assertEquals(results[0],"5.7");
		assertEquals(results[1],"false");
	}
	@Test
	void test_interestrate_7() {
		// test that state cannot contain numbers or special chars
		Product products = new Product("Samsung Note 10",5.0,false);
		Person person = new Person(500,"ha4aii");
		Rules[] rules = rulesengine.loadrules();
		results =rulesengine.run_rules(person,products,rules);
		System.out.println("Test 7");
		System.out.println("product interest rate is :" + results[0]);
		System.out.println("product disqualified is :" +results[1]);
		
		assertEquals(results[0],"error");
		assertEquals(results[1],"State Input");
	}
	@Test
	void test_interestrate_8() {
		// test that credit history should be between 200-850
		Product products = new Product("Samsung TV",5.0,false);
		Person person = new Person(10,"texas");
		Rules[] rules = rulesengine.loadrules();
		results =rulesengine.run_rules(person,products,rules);
		System.out.println("Test 7");
		System.out.println("product interest rate is :" + results[0]);
		System.out.println("product disqualified is :" +results[1]);
		
		assertEquals(results[0],"error");
		assertEquals(results[1],"Credit Score is not between 200 & 850");
	}
	@Test
	void test_interestrate_9() {
		// test that product name cannot be empty
		Product products = new Product("",5.0,false);
		Person person = new Person(10,"texas");
		Rules[] rules = rulesengine.loadrules();
		results =rulesengine.run_rules(person,products,rules);
		System.out.println("Test 9");
		System.out.println("product interest rate is :" + results[0]);
		System.out.println("product disqualified is :" +results[1]);
		
		assertEquals(results[0],"error");
		assertEquals(results[1],"product input error");
	}
	@Test
	void test_interestrate_10() {
		// test that product interest cannot be <=0
		Product products = new Product("Lamborghini Aventador",-2.1,false);
		Person person = new Person(500,"texas");
		Rules[] rules = rulesengine.loadrules();
		results =rulesengine.run_rules(person,products,rules);
		System.out.println("Test 10");
		System.out.println("product interest rate is :" + results[0]);
		System.out.println("product disqualified is :" +results[1]);
		
		assertEquals(results[0],"error");
		assertEquals(results[1],"product interest rate cannot be negative");
	}


}
