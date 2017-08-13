package mg.ratombotsoa.transactionstats.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mg.ratombotsoa.transactionstats.model.Statistics;
import mg.ratombotsoa.transactionstats.model.Transaction;
import mg.ratombotsoa.transactionstats.repository.TransactionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

	@Autowired
	private TransactionService service;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Before
	public void setupData() {
		transactionRepository.deleteAll();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSaveTransaction_whenDateIsInvalid() {
		Transaction transaction = new Transaction(new BigDecimal("100"), new Date(System.currentTimeMillis() + 600000));
		service.saveTransaction(transaction);
	}
	
	@Test
	public void testSaveTransaction_whenDateBeforeBoundaryDate() {
		Transaction transaction = new Transaction(new BigDecimal("100"), new Date(System.currentTimeMillis() - 6600000));
		Optional<Transaction> result = service.saveTransaction(transaction);
		
		assertFalse(result.isPresent());
	}
	
	@Test
	public void testSaveTransaction_whenDateIsInComputedRange() {
		Transaction transaction = new Transaction(new BigDecimal("100"), new Date(System.currentTimeMillis() - 40000));
		Optional<Transaction> result = service.saveTransaction(transaction);
		
		assertTrue(result.isPresent());
		assertTrue(result.get().getId() != null);
	}
	
	@Test
	public void testComputeTransactionStatistics_whenNoRowsSelected() {
		Statistics stats = service.computeTransactionStatistics();
		
		assertNotNull(stats);
		assertNull(stats.getSum());
		assertNull(stats.getAvg());
		assertNull(stats.getMax());
		assertNull(stats.getMin());
		assertNotNull(stats.getCount());
	}
	
	@Test
	public void testComputeTransactionStatistics_whenComputationsAreDone() {
		Date almostCurrentDate = new Date(System.currentTimeMillis() - 30000);
		Transaction transaction1 = new Transaction(new BigDecimal("100"), almostCurrentDate);
		service.saveTransaction(transaction1);
		Transaction transaction2 = new Transaction(new BigDecimal("200"), almostCurrentDate);
		service.saveTransaction(transaction2);
		Transaction transaction3 = new Transaction(new BigDecimal("200"), almostCurrentDate);
		service.saveTransaction(transaction3);
		Statistics stats = service.computeTransactionStatistics();
		
		assertNotNull(stats);
		assertTrue(new BigDecimal("166.67").compareTo(stats.getAvg()) == 0);
		assertTrue(BigDecimal.valueOf(500).compareTo(stats.getSum()) == 0);
		assertTrue(new BigDecimal("100").compareTo(stats.getMin()) == 0);
		assertTrue(new BigDecimal("200").compareTo(stats.getMax()) == 0);
		assertEquals(Long.valueOf(3), stats.getCount());
	}
}
