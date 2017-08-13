package mg.ratombotsoa.transactionstats.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mg.ratombotsoa.transactionstats.model.Statistics;
import mg.ratombotsoa.transactionstats.model.Transaction;
import mg.ratombotsoa.transactionstats.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping(value = "/transactions", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> postTransaction(@RequestBody Transaction transaction) {

		Optional<Transaction> result = null;
		try {
			result = transactionService.saveTransaction(transaction);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		if (result.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping(value = {"/statistics"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public Statistics computeStatistics() {
		return transactionService.computeTransactionStatistics();
	}
}
