package dk.lundogbendsen.web;

import java.time.LocalDate;

public class Order {
	private static Long next = 0L;
	private Long number;
	private LocalDate date;

	public Order() {
		super();
		this.number = next++;
		this.date = LocalDate.now();
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [number=" + number + ", date=" + date + "]";
	}

}
