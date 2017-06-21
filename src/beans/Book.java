package beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int libraryId;
	private int shelfId;
	private String ISBN;
	private String name;
	private String author;
	private String publisher;
	private int kind;
	private int status;
	private int borrower;
	private Timestamp borrowedTime;
	private Timestamp returnedTime;
	private int reservationNumber;
	private Timestamp dueTime;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLibraryId() {
		return libraryId;
	}
	public void setLibraryId(int libraryId) {
		this.libraryId = libraryId;
	}
	public int getShelfId() {
		return shelfId;
	}
	public void setShelfId(int shelfId) {
		this.shelfId = shelfId;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getBorrower() {
		return borrower;
	}
	public void setBorrower(int borrower) {
		this.borrower = borrower;
	}
	public Timestamp getBorrowedTime() {
		return borrowedTime;
	}
	public void setBorrowedTime(Timestamp borrowedTime) {
		this.borrowedTime = borrowedTime;
	}
	public Timestamp getReturnedTime() {
		return returnedTime;
	}
	public void setReturnedTime(Timestamp returnedTime) {
		this.returnedTime = returnedTime;
	}
	public int getReservationNumber() {
		return reservationNumber;
	}
	public void setReservationNumber(int reservationNumber) {
		this.reservationNumber = reservationNumber;
	}
	public Timestamp getDueTime() {
		return dueTime;
	}
	public void setDueTime(Timestamp dueTime) {
		this.dueTime = dueTime;
	}
}
