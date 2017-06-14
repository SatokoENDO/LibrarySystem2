package beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int libraryId;
	private int shelfNumber;
	private String ISBN;
	private String name;
	private String authorName;
	private String publisherName;
	private int kind;
	private int status;
	private int borrower;
	private Timestamp borrowedTime;
	private Timestamp reservedTime;
	private Timestamp returnedTime;
	private int reservationNumber;

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
	public int getShelfNumber() {
		return shelfNumber;
	}
	public void setShelfNumber(int shelfNumber) {
		this.shelfNumber = shelfNumber;
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
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
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
	public Timestamp getReservedTime() {
		return reservedTime;
	}
	public void setReservedTime(Timestamp reservedTime) {
		this.reservedTime = reservedTime;
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

}


