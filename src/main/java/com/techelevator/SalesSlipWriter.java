package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class SalesSlipWriter {
	//Initialize
	DateTimeFormatter timeStampPattern;
	String action = "";
	BigDecimal transaction;
	BigDecimal balance;
	String file = "Sales_Slip_File.csv";
	File logFile = new File(file);
	
	
	//Methods
		public String formatProductInfo(String productName, String slotID) {
			return productName + " " + slotID;
		}
		
		public void createNewLogFile() throws IOException {
			logFile.delete(); //remove previous file
			logFile.createNewFile(); //create new file
		}

		public void writeToLogFile(String action, BigDecimal transaction, BigDecimal balance) throws IOException{
				
			this.action = action;
	        this.transaction = transaction;
	        this.balance = balance;
			timeStampPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a ");
			 try (FileWriter writer = new FileWriter("Sales_Slip_File.csv", true)) {
			writer.write(timeStampPattern.format(java.time.LocalDateTime.now()) + action + " | " + balance + "\n");
	        writer.flush();
	        writer.close();
			

			 }
		}
}
