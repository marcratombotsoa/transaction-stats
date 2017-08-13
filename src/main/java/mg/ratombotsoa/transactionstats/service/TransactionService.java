package mg.ratombotsoa.transactionstats.service;

import java.util.Optional;

import mg.ratombotsoa.transactionstats.model.Statistics;
import mg.ratombotsoa.transactionstats.model.Transaction;

public interface TransactionService {

	/**
	 * Saves the transaction object into the database<br/>
	 * Returns an Optional of transaction if the transaction date (timestamp) is
	 * between the current date and the boundary date (last 60 seconds), otherwise
	 * it returns an empty Optional
	 * 
	 * @param transaction - The input transaction object
	 * @return An instance of Optional which contains the persisted transaction
	 */
	Optional<Transaction> saveTransaction(Transaction transaction);

	/**
	 * Computes the transaction statistics for the last X seconds<br/>
	 * Where X is configured in properties file : transaction.computing.interval
	 * 
	 * @return an instance of {@link Statistics}
	 */
	Statistics computeTransactionStatistics();
}
