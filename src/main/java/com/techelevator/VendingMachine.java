package com.techelevator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class VendingMachine {

	// Initialize variables
	private BigDecimal balance = new BigDecimal("0");
	private Map<String, Queue<Item>> currentInventory = new TreeMap<>();
	private Queue<Item> cart = new LinkedList<>();
	private LogWriter logWriter = new LogWriter();
	private Queue<Item> productRack = new LinkedList<>();
	private Item individualProduct;


	

	
	// Constructor
	public VendingMachine(Map<String, Queue<Item>> currentInventory) {
		this.currentInventory = currentInventory;
	}

	// Getters
	public BigDecimal getBalance() {
		return balance;
	}

	public Queue<Item> getCart() {
		return cart;
	}

	// Setters
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void setCurrentInventory(Map<String, Queue<Item>> currentInventory) {
		this.currentInventory = currentInventory;
	}

	// Product Rack and Product Methods
	public Queue<Item> getProductRack(String userInput) {
		setProductRack(userInput);
		return productRack;
	}

	private void setProductRack(String userInput) {
		this.productRack = currentInventory.get(userInput);
	}

	public Item getIndividualProduct(String userInput) {
		setIndividualProduct(userInput);
		return individualProduct;
	}

	private void setIndividualProduct(String userInput) {
		this.individualProduct = getProductRack(userInput).peek();
	}

	// Consume Method
	public String consumeSoundExcitement() {
		StringBuffer result = new StringBuffer();
		// consume it!
		while (!cart.isEmpty()) {
			// grab inventory info
			Item productFromQueue = cart.poll();
			String slotID = productFromQueue.getSlotID();

			if (slotID.startsWith("A")) {
				result.append(productFromQueue.getConsumeSound() + "\n");

			} else if (slotID.startsWith("B")) {
				result.append(productFromQueue.getConsumeSound() + "\n");

			} else if (slotID.startsWith("C")) {
				result.append(productFromQueue.getConsumeSound() + "\n");

			} else if (slotID.startsWith("D")) {
				result.append(productFromQueue.getConsumeSound() + "\n");
			}
		} return result.toString();
	}

	// Payment Methods
	public void deposit(BigDecimal enteredMoney) {
		balance = balance.add(enteredMoney);
	}

	public String returnChange() throws IOException {
		BigDecimal preTransactionBalance = balance;

		ReturnChange temp = new ReturnChange();
		// write in log:
		String action = "GIVE CHANGE: ";
		logWriter.writeToLogFile(action, preTransactionBalance, balance);
		return temp.returnChangeSeparateClass(balance);

	}

	public BigDecimal returnChangeValue() {

		ReturnChange temp = new ReturnChange();
		return temp.returnChangeValue();
	}

	public void resetBalance() {
		this.balance = new BigDecimal("0");
	}

	// Inventory Methods

	// 1. Removes item from Q, adds item to itemBin, get price for item, subtract
	// price from balance.
	public String dispense(BigDecimal balance, String userInput) throws IOException {
		// get item info from Inventory
		Queue<Item> productFromMap = getProductRack(userInput);// currentInventory.get(userInput);
		Item productFromQueue = productFromMap.peek(); 
		StringBuffer result = new StringBuffer();
		if((productFromMap.peek() != null) ) {
		String productName = productFromQueue.getName();
		BigDecimal productPrice = productFromQueue.getPrice();// productFromQueue.getPrice();
		
		// Check if money is not enough
		BigDecimal preTransactionBalance = balance;
		if (productPrice.compareTo(balance) <= 0) {
			if (productFromMap.isEmpty()) {
				result.append("Sorry, " + productName + " " + getQuantity(productFromMap) + "\n");
				result.append("Please select another item." + "\n");
				// write to log
				String action = "FAILED TRANSACTION: ITEM NOT AVAILABLE";
				logWriter.writeToLogFile(action, preTransactionBalance, getBalance());
			} else {
				// add item to cart
				cart.offer(productFromQueue);
				productFromMap.poll();// remove item from Vending Machine

				// subtract price from balance
				this.balance = balance.subtract(productPrice);
				if(productFromMap.isEmpty()) {
					result.append(userInput + " " + productName + " $" + productPrice + ", Quantity: You got the last one! Enjoy!" + "\n");
				} else {
					result.append(userInput + " " + productName + " $" + productPrice + ", " + getQuantity(productFromMap) + " left, Great Choice!" + "\n");
				}
				// write to log
				String action = logWriter.formatProductInfo(productName, userInput);
				logWriter.writeToLogFile(action, preTransactionBalance, getBalance());
			}
		
		} else {
			result.append("Sorry, not enough money deposited." + "\n");
			result.append("Please deposit more money." + "\n");
			// write to log
			String action = "FAILED TRANSACTION: NOT ENOUGH FUNDS";
			logWriter.writeToLogFile(action, preTransactionBalance, getBalance());
		}
	} else {
		result.append("I'm sorry, Dave. I'm afraid that item is not available." + "\n");
		result.append("Please select another item." + "\n");
	} return result.toString();
	}

	// 2. Create set of keys method
	public Set<String> getKeySet() {
		Set<String> keySet = currentInventory.keySet();
		return keySet;
	}

	// 3. Create array of slotIds
	public String[] createArrayOfSlotIDs() {
		Set<String> keySet = getKeySet();
		String[] arrayOfSlotIDs = null;
		for (int i = 0; i < keySet.size(); i++) {
			arrayOfSlotIDs = keySet.toArray(new String[i]);
		}
		return arrayOfSlotIDs;
	}

	// 4. Display Current Inventory
	public String displayCurrentInventory() throws FileNotFoundException {
		// Set the MAP keySet
		String[] arrayOfSlotIDs = createArrayOfSlotIDs();
		StringBuffer result = new StringBuffer();
		// System.out.println(keySet);
		for (int i = 0; i < currentInventory.size(); i++) {
			// Set the MAP values
			Queue<Item> productFromMap = currentInventory.get(arrayOfSlotIDs[i]);
			Item productFromQueue = productFromMap.peek();
			String slotID = arrayOfSlotIDs[i];
			if(!(productFromMap.peek() == null) ) {
			String productName = productFromQueue.getName();
			BigDecimal productPrice = productFromQueue.getPrice();
			String quantity = getQuantity(productFromMap);
			// Get productName
		result.append(String.format("%-3s%-3s%-20s%-5s%-5s%-3s%-5s\n", slotID, " | ", productName, " | $", productPrice.toString(), " | ", quantity.toString()));
//			result.append(slotID + " | " + productName + " | $" + productPrice + " | " + quantity + "\n");
			} else {
				result.append(slotID + " | " + "EMPTY | $00.00 | SOLD OUT" + "\n");
			}
		}
		result.append("Awesome Selections!");
		return result.toString();
	}

	// 5. Get Quantity
	private String getQuantity(Queue<Item> productQueue) {
		Integer productAmount = productQueue.size();
		if (productQueue.isEmpty()) {
			return "is SOLD OUT";
		} else

			return "Quantity: " + productAmount.toString();
	}
}

	