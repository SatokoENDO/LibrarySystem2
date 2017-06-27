package beans;

import java.sql.Timestamp;

public class User {
	private int id;
	private long cardNumber;
	private String name;
	private String address;
	private String tel;
	private String mail;
	private Timestamp registerTime;
	private String password;
	private int libraryId;
	private int isAdmin;
	private int borrowBooks;
	private int reservedBooks;
	private int demandCount;
	private Timestamp demandTime;


	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}
	public Timestamp getRegisterTime() {
		return registerTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBorrowBooks() {
		return borrowBooks;
	}
	public void setBorrowBooks(int borrowBooks) {
		this.borrowBooks = borrowBooks;
	}

	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getLibraryId() {
		return libraryId;
	}
	public void setLibraryId(int libraryId) {
		this.libraryId = libraryId;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public int getReservedBooks() {
		return reservedBooks;
	}
	public void setReservedBooks(int reservedBooks) {
		this.reservedBooks = reservedBooks;
	}

	public int getDemandCount() {
		return demandCount;
	}

	public void setDemandCount(int demandCount) {
		this.demandCount = demandCount;
	}

	public Timestamp getDemandTime() {
		return demandTime;
	}

	public void setDemandTime(Timestamp demandTime) {
		this.demandTime = demandTime;
	}



}
