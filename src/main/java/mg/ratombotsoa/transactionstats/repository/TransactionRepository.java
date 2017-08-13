package mg.ratombotsoa.transactionstats.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mg.ratombotsoa.transactionstats.model.Statistics;
import mg.ratombotsoa.transactionstats.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query("select new mg.ratombotsoa.transactionstats.model.Statistics(sum(amount), avg(amount), max(amount) "
			+ " , min(amount), count(id)) from Transaction where timestamp between ?1 and ?2")
	Statistics computeTransactionStatistics(Date startDate, Date endDate);
}
