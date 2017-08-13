package mg.ratombotsoa.transactionstats.service;

import java.util.Optional;

import mg.ratombotsoa.transactionstats.model.Transaction;

public interface TransactionService {

	/**
	 * Saves the transaction object into the database<br/>
	 * Returns an Optional of transaction if the transaction date (timestamp) is
	 * between the current date and the boundary date (last 60 seconds), otherwise
	 * it returns an empty Optional
	 */
	Optional<Transaction> saveTransaction(Transaction transaction);
}
