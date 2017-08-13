package mg.ratombotsoa.transactionstats.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mg.ratombotsoa.transactionstats.model.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

	@Autowired
	private TransactionService service;
	
	@Test(expected = IllegalArgumentException.class)
	public void testSaveTransaction_whenDateIsInvalid() {
		Transaction transaction = new Transaction();
		transaction.setAmount(new BigDecimal("100"));
		transaction.setTimestamp(new Date(System.currentTimeMillis() + 600000));
		service.saveTransaction(transaction);
	}
	
	@Test
	public void testSaveTransaction_whenDateBeforeBoundaryDate() {
		Transaction transaction = new Transaction();
		transaction.setAmount(new BigDecimal("100"));
		transaction.setTimestamp(new Date(System.currentTimeMillis() - 6600000));
		Optional<Transaction> result = service.saveTransaction(transaction);
		
		assertFalse(result.isPresent());
	}
	
	@Test
	public void testSaveTransaction_whenDateIsInComputedRange() {
		Transaction transaction = new Transaction();
		transaction.setAmount(new BigDecimal("100"));
		transaction.setTimestamp(new Date(System.currentTimeMillis() - 40000));
		Optional<Transaction> result = service.saveTransaction(transaction);
		
		assertTrue(result.isPresent());
		assertTrue(result.get().getId() != null);
	}
}
