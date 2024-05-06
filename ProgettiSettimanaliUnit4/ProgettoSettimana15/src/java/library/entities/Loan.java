package it.epicode.library.entities;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;

@Entity
public class Loan extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	private LibraryItem item;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	private Date expectedReturnDate;

	@Temporal(TemporalType.DATE)
	private Date actualReturnDate;

	public Loan() {}
	public Loan(User user, LibraryItem item, Date startDate) {
		this.user = user;
		this.item = item;
		this.startDate = startDate;
		this.expectedReturnDate = calculateExpectedReturnDate(startDate);
	}

	public Date calculateExpectedReturnDate(Date startDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.DATE, 30);
		return calendar.getTime();
	}

	public User getUser() {
		return user;
	}

	public LibraryItem getItem() {
		return item;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getExpectedReturnDate() {
		return expectedReturnDate;
	}


	@Override
	public String toString() {
		return "Loan{" +
				"user=" + user +
				", item=" + item +
				", startDate=" + startDate +
				", expectedReturnDate=" + expectedReturnDate +
				", actualReturnDate=" + actualReturnDate +
				'}';
	}
}
