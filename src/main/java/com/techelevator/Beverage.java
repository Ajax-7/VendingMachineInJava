package com.techelevator;

import java.math.BigDecimal;

public class Beverage extends Item{

	
	//constructor
	public Beverage(String slotID, String name, BigDecimal price) {
		super(slotID, name, price);
	}

	//method
		public String getConsumeSound() {
			return super.consumeSound("Glug");
		}
}
