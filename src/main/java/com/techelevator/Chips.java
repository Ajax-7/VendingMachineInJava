package com.techelevator;

import java.math.BigDecimal;

public class Chips extends Item{
	//constructor
	public Chips(String slotID, String name, BigDecimal price) {
		super(slotID, name, price);
	}

	
	//method
	public String getConsumeSound() {
		return super.consumeSound("Crunch");
	}

}
