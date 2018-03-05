package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReturnChange {
	//Initialize
	BigDecimal numberOfQuarters = new BigDecimal("0");
	BigDecimal numberOfDimes = new BigDecimal("0");
	BigDecimal numberOfNickels = new BigDecimal("0");
	BigDecimal remainder = new BigDecimal("0");
	BigDecimal changeValue = new BigDecimal("0");
	
	//Methods
	public String returnChangeSeparateClass(BigDecimal balance) {
	
			remainder = balance.multiply(new BigDecimal("100"));
		//BigDecimal remainder = balance;

		if (!remainder.equals(new BigDecimal("0"))) {
			
			// Number of Quarters
			numberOfQuarters = remainder.divide(new BigDecimal("25"));
			numberOfQuarters = numberOfQuarters.setScale(0, RoundingMode.DOWN);
			remainder = remainder.subtract(numberOfQuarters.multiply(new BigDecimal("25")));
		
			// Number of Dimes
			numberOfDimes = remainder.divide(new BigDecimal("10"));
			numberOfDimes = numberOfDimes.setScale(0, RoundingMode.DOWN);
			remainder = remainder.subtract(numberOfDimes.multiply(new BigDecimal("10")));
			
			// Number of Nickels
			numberOfNickels = remainder.divide(new BigDecimal("5"));
			numberOfNickels = numberOfNickels.setScale(0, RoundingMode.DOWN);
			remainder = remainder.subtract(numberOfNickels.multiply(new BigDecimal("5")));

			return ("\n" + "Your change is: " + numberOfQuarters + " quarters, " + numberOfDimes + " dimes, " + numberOfNickels
					+ " nickels.");

		} else
			return "I'm sorry Dave, I'm afraid can't do that.";
	}
	public BigDecimal returnChangeValue(){
		
		changeValue.add(numberOfQuarters); 
		changeValue.add(numberOfDimes);  
		changeValue.add(numberOfNickels);
		return changeValue;
		
	}
	
	

}