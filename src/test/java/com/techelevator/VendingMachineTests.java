package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

public class VendingMachineTests {
	private VendingMachine vm;
	private InventoryReader IR;
	private ReturnChange Change;
	Chips newChip;

	@Before
	public void setUp() throws Exception {
		IR = new InventoryReader();
		vm = new VendingMachine(IR.generateInventory());
		Change = new ReturnChange();
		newChip = new Chips("A3", "nachoChip", new BigDecimal("1.25"));
	}

	@Test
	public void existentialTest() {
		assertNotNull(vm);
	}

	// Product Rack and Product Methods
	@Test
	public void getSlotIDTest() throws FileNotFoundException {
		assertEquals("A3", newChip.getSlotID());
	}

	@Test
	public void getNameTest() throws FileNotFoundException {
		assertEquals("nachoChip", newChip.getName());
	}

	@Test
	public void getPriceTest() throws FileNotFoundException {
		assertEquals(new BigDecimal("1.25"), newChip.getPrice());
	}

	@Test
	public void consumeSound() throws FileNotFoundException {
		assertEquals("Crunch", newChip.getConsumeSound());
	}

	@Test
	public void returnChangeTest() {
		BigDecimal balance = new BigDecimal(".95");
		assertEquals("Your change is: 3 quarters, 1 dimes, 1 nickels.", Change.returnChangeSeparateClass(balance));

	}

	@Test
	public void testGetProductRack() {
		String userInput = "A1";
		assertNotNull(vm.getProductRack(userInput));
	}

	@Test
	public void testGetIndividualProduct() {
		String userInput = "A1";
		assertNotNull(vm.getIndividualProduct(userInput));

	}

	// Payment Methods
	@Test
	public void testDeposit() {
		BigDecimal enteredMoney = new BigDecimal("10");
		BigDecimal balance = new BigDecimal("10");
		assertEquals(new BigDecimal("20"), vm.deposit(enteredMoney));
	}

	@Test
	public void testResetBalance() {
		vm.setBalance(new BigDecimal("10"));
		assertEquals(new BigDecimal("0"), vm.resetBalance());
	}

	// Inventory Methods

@Test
	public void testDispense() {
	BigDecimal balance = vm.getBalance();
	String userInput = "A1";
	assertNotNull(vm.dispense(balance, userInput));
	}

	// 2. Create set of keys method

	@Test
	public void testGetKeySet() {
		assertNotNull(vm.getKeySet());
	}

	// 3. Create array of slotIds
	@Test
	public void testCreateArrayOfSlotIDs() {
		assertNotNull(vm.createArrayOfSlotIDs());
	}

	@Test
	public void testDisplayCurrentInventory() {
		assertNotNull(vm.displayCurrentInventory());
	}

	@Test
	public void testGetQuantity() {
		assertEquals(5, vm.getQuantity(vm.getProductRack("A1")));
	}

}
