package be.pxl.hotel.domain;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
	private double amount;
	private List<Transaction> transactions = new ArrayList<>();

	public Wallet(double initialAmount) {
		this.amount = initialAmount;
		transactions.add(new Transaction(initialAmount, TransactionType.RECEIPT, "Starting amount"));
	}

	public double getAmount() {
		return amount;
	}


	public void registerPayment(double price, String description) {
		// Zorg voor een implementatie om een correcte betaling te doen.
		// Er moet voldoende geld aanwezig zijn en enkel een positief bedrag (groter dan 0) is toegelaten.
		// Zorg voor een correcte foutafhandeling.
		// Vergeet de betaling niet te registreren in de transactions-lijst.
		if (price <= 0) {
			throw new IllegalArgumentException("Payment amount must be greater than 0");
		}
		if (amount - price > 0) {
			amount -= price;
			transactions.add(new Transaction(price, TransactionType.PAYMENT, description));
		} else {
			throw new UnsufficientMoneyException("Not enough money to make the payment");
		}
	}

	public void registerReceipt(double price, String description) {
		// Zorg voor een implementatie om een bedrag te ontvangen.
		// Een negatief bedrag of 0 is niet toegelaten.
		// Zorg voor een correcte foutafhandeling.
		// Vergeet de ontvangst van het bedrag niet te registreren in de transactions-lijst.
		if (price <= 0) {
			throw new IllegalArgumentException("Receipt amount must be greater than 0");
		}

		amount += price;
		transactions.add(new Transaction(price, TransactionType.RECEIPT, description));
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}
}
