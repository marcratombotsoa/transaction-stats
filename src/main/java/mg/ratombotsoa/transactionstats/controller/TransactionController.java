package mg.ratombotsoa.transactionstats.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mg.ratombotsoa.transactionstats.model.Statistics;
import mg.ratombotsoa.transactionstats.model.Transaction;

@RestController
public class TransactionController {

	@PostMapping(value = "/transactions", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> postTransaction(@RequestBody Transaction transaction) {
		/*
		 * TODO
		 */
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/statistics")
	public Statistics computeStatistics() {
		/*
		 * TODO 
		 */
		return new Statistics();
	}
}
