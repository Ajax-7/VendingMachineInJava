package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	public BigDecimal getAmountFromUserInput() {
		for(;;){
			out.println();
			out.print("Please enter an amount: >>>");
			out.flush();
			

			String userInput = in.nextLine();
			try {
		        if(userInput.equals("0") || userInput.equals("1") || userInput.equals("2") || userInput.equals("5") || userInput.equals("10")) {
		                return new BigDecimal(userInput).setScale(2);   
		            }
		        else  {
		            out.println("Sorry. We only accept $1, $2, $5, and $10. Press 0 to return to Purchase Menu");
		            out.println();
		            out.flush();
		        }
			} catch(NumberFormatException ex) {
				out.println("Please enter a valid number.");
				out.println();
				out.flush();
			}
	}
	}

	public String getSlotIDFromUserInput(Set<String> keySet) {
		for (;;) {
			out.println();
			out.print("Please select a product id: >>>");
			out.flush();

			String userInput = in.nextLine();
			if (keySet.contains(userInput)) { // get the set of keys from the VM500. if it matches then great.
				return userInput;

			} else {
				out.println("Please enter a valid product id.");
				out.println();
				out.flush();
			}
		}
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will
			// be null
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}
}
