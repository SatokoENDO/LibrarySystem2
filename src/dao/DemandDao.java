package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Book;
import exception.SQLRuntimeException;

public class DemandDao {

	public List<Book> getDelayedBook(Connection connection){

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM books ");

			sql.append("WHERE due_date <= CURRENT_TIMESTAMP AND status = 1 ORDER BY due_date");
			/*sql.append("AND status = 1");
			sql.append("ORDER BY due_date");*/

			ps = connection.prepareStatement(sql.toString());

			ps = connection.prepareStatement(sql.toString());

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
}
