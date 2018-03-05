package com.techelevator;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

public class VendingMachineAustinTest {

	private InventoryReader IR;
	
	
	@Before
	public void setUp() throws Exception {
	
			IR = new InventoryReader();
				}

	@Test
	public void NotNullTest() throws FileNotFoundException {
	assertNotNull(IR.generateInventory());
	}

	@Test
	public void testClassGetConsumeSound() {
	//assertEquals("Crunch Crunch , Yum!", newChips.consumeSound("Crunch"));
		
	}
	
	
}

//Vending Machine Method Tests
//Inventory Reader Tests
//Item tests
//LogWriter Tests
//Return Change Tests

