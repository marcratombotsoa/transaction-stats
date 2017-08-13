package mg.ratombotsoa.transactionstats.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3396748531571665308L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private BigDecimal amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date timestamp;

	public Transaction() {
		super();
	}
	
	public Transaction(BigDecimal amount, Date timestamp) {
		super();
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", timestamp=" + timestamp + "]";
	}
}
