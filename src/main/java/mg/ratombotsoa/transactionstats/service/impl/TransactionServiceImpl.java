package mg.ratombotsoa.transactionstats.service.impl;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mg.ratombotsoa.transactionstats.model.Transaction;
import mg.ratombotsoa.transactionstats.repository.TransactionRepository;
import mg.ratombotsoa.transactionstats.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Value("${transaction.computing.interval}")
	private Long computingInterval;

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Optional<Transaction> saveTransaction(Transaction transaction) {

		Long currentTime = System.currentTimeMillis();
		Date currentDate = new Date(currentTime);
		Date transactionDate = transaction.getTimestamp();
		
		if (transactionDate.after(currentDate)) {
			LOGGER.error("The transaction date {} should never be in the future", transaction.getTimestamp());
			throw new IllegalArgumentException(
					"The transaction date should never be in the future: " + transaction.getTimestamp());
		}
		
		Transaction persistedTransaction = transactionRepository.save(transaction);
		Date boundaryDate = new Date(currentTime - computingInterval);

		if (transactionDate.before(boundaryDate)) {
			LOGGER.trace("The transaction date {} is before the boundary date", transaction.getTimestamp());
			return Optional.empty();
		}

		return Optional.of(persistedTransaction);
	}

}
