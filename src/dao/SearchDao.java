package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Book;
import beans.User;
import exception.SQLRuntimeException;

public class SearchDao {

	public List<Book> getBookFromName(Connection connection, String bookName, String author, String publisher, int libraryId, int kind){

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM books ");
			sql.append("WHERE  ");

			if(bookName != null){
				sql.append(" name LIKE ? ");
			}

			if(author != null){
				sql.append(" AND author LIKE ? ");
			}

			if(publisher != null){
				sql.append(" AND publisher LIKE ? ");
			}

			if(libraryId != 0){
				sql.append(" AND library_id = "+ libraryId );
			}

			if(kind != 0){
				sql.append(" AND kind = " + kind );
			}

			ps = connection.prepareStatement(sql.toString());

			if(bookName != null){
				ps.setString(1, "%" + bookName + "%");
			}
			if(author != null){
				ps.setString(2, "%" + author + "%");
			}
			if(publisher != null){
				ps.setString(3, "%" + publisher + "%");
			}


			ResultSet rs = ps.executeQuery();
			List<Book> bookList = toBookList(rs);

			if(bookList.isEmpty() == true) {
				return null;
			}else{
				return  bookList;
			}
		}catch (SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	private List<Book> toBookList(ResultSet rs) throws SQLException {

		List<Book> ret = new ArrayList<Book>();
		try {
			while (rs.next()) {
				int bookId = rs.getInt("id");
				int libraryId = rs.getInt("library_id");
				String bookName = rs.getString("name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				int status = rs.getInt("status");
				int borrower = rs.getInt("borrower");
				int kind = rs.getInt("kind");
				int reservationNumber = rs.getInt("reservation_number");

				Book book = new Book();

				book.setId(bookId);
				book.setLibraryId(libraryId);
				book.setName(bookName);
				book.setAuthor(author);
				book.setPublisher(publisher);
				book.setStatus(status);
				book.setBorrower(borrower);
				book.setKind(kind);
				book.setReservationNumber(reservationNumber);

				ret.add(book);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

//	氏名、住所から利用者を照会する
	public User getUser(Connection connection, String name, String address){

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users ");
			sql.append("WHERE  ");

			sql.append(" name = ? " );
			sql.append(" AND address = ? " );

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, name);
			ps.setString(2, address);

			ResultSet rs = ps.executeQuery();
			List<User> user = toGetUser(rs);

			if(user.isEmpty() == true) {
				return null;
			}else{
				return  user.get(0);
			}
		}catch (SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	private List<User> toGetUser(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				long cardNumber= rs.getLong("card_number");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String mail = rs.getString("mail");
				String password = rs.getString("password");
				int libraryId = rs.getInt("library_id");
				int borrowBooks = rs.getInt("borrow_books");
				int isAdmin = rs.getInt("is_Admin");
				int reservedBooks = rs.getInt("reserved_books");

				User user = new User();

				user.setId(id);
				user.setCardNumber(cardNumber);
				user.setName(name);
				user.setAddress(address);
				user.setTel(tel);
				user.setMail(mail);
				user.setPassword(password);
				user.setLibraryId(libraryId);
				user.setBorrowBooks(borrowBooks);
				user.setIsAdmin(isAdmin);
				user.setReservedBooks(reservedBooks);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
