package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.Book;
import beans.User;
import exception.SQLRuntimeException;

public class StatusDao {

	public List<Book> getBookInfo(Connection connection, User loginUser) {

		PreparedStatement ps = null;
		try {
			String sql = "select * from books where borrower = ? ";

			ps = connection.prepareStatement(sql);

			ps.setInt(1, loginUser.getId());

			ResultSet rs = ps.executeQuery();
			List<Book> bookList = toBookList(rs);

			if (bookList.isEmpty() == true) {
				return null;
			} else {
				return bookList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
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
				Timestamp borrowedTime = rs.getTimestamp("borrowed_time");
				Timestamp dueDate = rs.getTimestamp("due_date");

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
				book.setBorrowedTime(borrowedTime);
				book.setDueDate(dueDate);


				ret.add(book);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<Integer> getReservedBookId(Connection connection, int loginUserId) {

		PreparedStatement ps = null;
		try {
			String sql = "select * from reservations where user_id = ? ";

			ps = connection.prepareStatement(sql);

			ps.setInt(1, loginUserId);

			ResultSet rs = ps.executeQuery();
			List<Integer> bookList = toBookIdList(rs);

			if (bookList.isEmpty() == true) {
				return null;
			} else {
				return bookList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Integer> toBookIdList(ResultSet rs) throws SQLException {

		List<Integer> ret = new ArrayList<Integer>();
		try {
			while (rs.next()) {
				int bookId = rs.getInt("book_id");

				ret.add(bookId);
			}return ret;
		} finally {
			close(rs);
		}
	}

	public List<Integer> getReservationNumber(Connection connection, int bookId) {

		PreparedStatement ps = null;
		try {
			String sql = "select * from reservations where book_id = ? and reservation_type = 0 ";

			ps = connection.prepareStatement(sql);

			ps.setInt(1, bookId);

			ResultSet rs = ps.executeQuery();
			List<Integer> reservationNumberList = toReservationNumberList(rs);

			if (reservationNumberList.isEmpty() == true) {
				return null;
			} else {
				return reservationNumberList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Integer> toReservationNumberList(ResultSet rs) throws SQLException {

		List<Integer> ret = new ArrayList<Integer>();
		try {
			while (rs.next()) {
				int reservationNumber = rs.getInt("reservation_number");

				ret.add(reservationNumber);
			}return ret;
		} finally {
			close(rs);
		}
	}
	// ログインIDから指定の本の予約番号をとる
	public int getReservationNumberOf(Connection connection, int loginId, int bookId) {

		PreparedStatement ps = null;
		try {
			String sql = "select * from reservations where book_id = ? and user_id = ? ";

			ps = connection.prepareStatement(sql);

			ps.setInt(1, bookId);
			ps.setInt(2, loginId);

			ResultSet rs = ps.executeQuery();
			int reservationNumberOf = toReservationNumberOf(rs);

			if (reservationNumberOf == 0) {
				return 0;
			} else {
				return reservationNumberOf;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private int toReservationNumberOf(ResultSet rs) throws SQLException {

		int ret = 0;
		try {
			while (rs.next()) {
				int reservationNumber = rs.getInt("reservation_number");

				ret = reservationNumber;
			}return ret;
		} finally {
			close(rs);
		}
	}

}
