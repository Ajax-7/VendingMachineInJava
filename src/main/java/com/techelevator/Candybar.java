package com.techelevator;

import java.math.BigDecimal;

public class Candybar extends Item{

	//constructor
	public Candybar(String slotID, String name, BigDecimal price) {
		super(slotID, name, price);
	}

	
	

	//method
		public String getConsumeSound() {
			return super.consumeSound("Munch");
		}
	
}
