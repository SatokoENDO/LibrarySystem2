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
				sql.append("AND author LIKE ? ");
			}

			if(publisher != null){
				sql.append("AND publisher LIKE ? ");
			}

			if(libraryId != 0){
				sql.append("AND library_id = "+ libraryId );
			}

			if(kind != 0){
				sql.append("AND kind = "+ kind);
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
}
