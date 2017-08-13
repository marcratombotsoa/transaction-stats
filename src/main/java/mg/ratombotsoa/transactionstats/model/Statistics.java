package mg.ratombotsoa.transactionstats.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Statistics {

	private BigDecimal sum;
	private BigDecimal avg;
	private BigDecimal max;
	private BigDecimal min;
	private Long count;

	public Statistics() {
		super();
	}
	
	public Statistics(BigDecimal sum, BigDecimal avg, BigDecimal max, BigDecimal min, Long count) {
		super();
		this.sum = sum;
		this.avg = avg;
		this.max = max;
		this.min = min;
		this.count = count;
	}
	
	public Statistics(BigDecimal sum, Double avg, BigDecimal max, BigDecimal min, Long count) {
		super();
		this.sum = sum;
		if (avg != null) {
			this.avg = new BigDecimal(avg);
			this.avg = this.avg.setScale(2, RoundingMode.HALF_UP);
		}
		this.max = max;
		this.min = min;
		this.count = count;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public BigDecimal getAvg() {
		return avg;
	}

	public void setAvg(BigDecimal avg) {
		this.avg = avg;
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Statistics [sum=" + sum + ", avg=" + avg + ", max=" + max + ", min=" + min + ", count=" + count + "]";
	}
}
