package com.techelevator;

import java.io.IOException;
import java.math.BigDecimal;

import com.techelevator.view.Menu;

public class VendingMachineCLI {
	// Initialize
	private Menu menu;
	private InventoryReader IR = new InventoryReader();
	private VendingMachine VM500;
	private LogWriter logWriter = new LogWriter();
	String action = "";

	// Main Menu options and array
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_MANAGE_SYSTEM = "Manage System";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_MANAGE_SYSTEM };

	// Purchase Menu options and array
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String PURCHASE_MENU_OPTION_GO_BACK_TO_MAIN_MENU = "Go Back To Main Menu";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION,
			PURCHASE_MENU_OPTION_GO_BACK_TO_MAIN_MENU };

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	// Start the Machine

	public void run() throws IOException {
		// Instantiate variables
		boolean menuOn = true; // Create menu switch
		logWriter.createNewLogFile();
		VM500 = new VendingMachine(IR.generateInventory()); // Generate Inventory

		// Run UI program
		while (true) {
			// Perform Main Menu
			String choiceMainMenu = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			menuOn = true;

			// Go to Display menu
			if (choiceMainMenu.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println(VM500.displayCurrentInventory());
				menuOn = false;

				// Perform Purchase Menu
			} else if (choiceMainMenu.equals(MAIN_MENU_OPTION_PURCHASE)) {

				// reset switches
				menuOn = true;
			
				while (menuOn) {
					String choicePurchaseMenu = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					// Purchase Menu: Feed Money
					if (choicePurchaseMenu.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {

						// do Feed Money
						BigDecimal userInput = menu.getAmountFromUserInput();
						VM500.deposit(userInput);

						// write to log
						action = "FEED MONEY: ";
						logWriter.writeToLogFile(action, userInput, VM500.getBalance());

						// finish Feed Money
						System.out.println("Current Money Provided: $" + VM500.getBalance());

						// Purchase Menu: Select Product
					} else if (choicePurchaseMenu.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						System.out.println(VM500.displayCurrentInventory());
						// do Select Product
						String userInput = menu.getSlotIDFromUserInput(VM500.getKeySet());

						System.out.println(VM500.dispense(VM500.getBalance(), userInput));

						System.out.println("Current Money Provided: $" + VM500.getBalance());
						// VM500.getCurrentInventory(); //TEST

						// Purchase Menu: Finish Transaction
					} else if (choicePurchaseMenu.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {

						System.out.println(VM500.returnChange());
						VM500.resetBalance();
						System.out.println("Current Money Provided: $" + VM500.getBalance());
						System.out.println(VM500.consumeSoundExcitement());
						menuOn = false;
					} else if (choicePurchaseMenu.equals(PURCHASE_MENU_OPTION_GO_BACK_TO_MAIN_MENU)) {
						System.out.println("Current Money Provided: $" + VM500.getBalance());
						menuOn = false;

					}
				} // Go to Manage System menu
				if (choiceMainMenu.equals(MAIN_MENU_OPTION_MANAGE_SYSTEM)) {
//					// reset switches
//					menuOn = true;
//					
//
//					while (menuOn) {
//						String choicePurchaseMenu = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
//
//					menuOn = false;
//					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
