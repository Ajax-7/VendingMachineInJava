package com.techelevator;

import java.math.BigDecimal;

public abstract class Item {

	//initialize
		private String slotID;
		private String name;
		private BigDecimal price;
		
	//constructor
		public Item(String slotID, String name, BigDecimal price) {
			this.slotID = slotID;
			this.name = name;
			this.price = price;
		}
		
	//getters
		public String getSlotID() {
			return slotID;
		}
		public String getName() {
			return name;
		}

		public BigDecimal getPrice() {
			return price;
		}
		
		
	//methods
		protected String consumeSound(String noise) {
			return noise + " " + noise + ", Yum!";
		}
		
		public abstract String getConsumeSound();
	
}

