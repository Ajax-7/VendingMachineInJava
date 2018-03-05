package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Item{

	//constructor
	public Gum(String slotID, String name, BigDecimal price) {
		super(slotID, name, price);
	}

	//method
		public String getConsumeSound() {
			return super.consumeSound("Chew");
		}
}
