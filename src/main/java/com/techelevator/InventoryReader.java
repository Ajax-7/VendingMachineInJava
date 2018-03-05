package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class InventoryReader {

	
	public Map<String, Queue<Item>> generateInventory() throws FileNotFoundException {

		// Creating a Map
		Map<String, Queue<Item>> initialInventory = new TreeMap<>();

		// Get file
		String line = "";
		// public static void main(String[] args) throws FileNotFoundException{

		File inputFile = new File("vendingmachine.csv");
		try (Scanner fileScanner = new Scanner(inputFile)) {
			while (fileScanner.hasNextLine()) {
				line = fileScanner.nextLine();
				//System.out.println(line);
				// split line into product info
				String[] splitLine = line.split("\\|");
				String slotID = splitLine[0];
				String productName = splitLine[1];
				BigDecimal productPrice = new BigDecimal(splitLine[2]);

				// determine which product class to create
				// if chips
				if (slotID.startsWith("A")) {

					Chips newChips = new Chips(slotID, productName, productPrice);
					Queue<Item> chipQueue = new LinkedList<>();
				//	System.out.println(newChips.getConsumeSound());
					chipQueue.offer(newChips);
					chipQueue.offer(newChips);
					chipQueue.offer(newChips);
					chipQueue.offer(newChips);
					chipQueue.offer(newChips);
					
					initialInventory.put(slotID, chipQueue);
				}

				// if candybars
				if (slotID.startsWith("B")) {
					Candybar newCandybar = new Candybar(slotID, productName, productPrice);
					Queue<Item> candybarQueue = new LinkedList<>();
				//	System.out.println(newCandybar);
					candybarQueue.offer(newCandybar);
					candybarQueue.offer(newCandybar);
					candybarQueue.offer(newCandybar);
					candybarQueue.offer(newCandybar);
					candybarQueue.offer(newCandybar);
					
					initialInventory.put(slotID, candybarQueue);
				}
				// if beverage
				if (slotID.startsWith("C")) {
					Beverage newBeverage = new Beverage(slotID, productName, productPrice);
					Queue<Item> beverageQueue = new LinkedList<>();
				//	System.out.println(newBeverage);
					beverageQueue.offer(newBeverage);
					beverageQueue.offer(newBeverage);
					beverageQueue.offer(newBeverage);
					beverageQueue.offer(newBeverage);
					beverageQueue.offer(newBeverage);
					
					initialInventory.put(slotID, beverageQueue);
				}
				// if gum
				if (slotID.startsWith("D")) {
					Gum newGum = new Gum(slotID, productName, productPrice);
					Queue<Item> gumQueue = new LinkedList<>();
			//		System.out.println(newGum);
					gumQueue.offer(newGum);
					gumQueue.offer(newGum);
					gumQueue.offer(newGum);
					gumQueue.offer(newGum);
					gumQueue.offer(newGum);

					initialInventory.put(slotID, gumQueue);
				}

			} // end of while

		} // end of try

		// getInventory

		return initialInventory;
		
	}

}
