package mg.ratombotsoa.transactionstats.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.ratombotsoa.transactionstats.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
