package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Book;
import exception.SQLRuntimeException;

public class BookDao {
	public void insert(Connection connection, Book book) {

		PreparedStatement ps = null;
		try {
			StringBuilder mySql = new StringBuilder();
			mySql.append("insert into books (");
			mySql.append("library_id");
			mySql.append(", shelf_id");
			mySql.append(", isbn");
			mySql.append(", name");
			mySql.append(", author");
			mySql.append(", publisher");
			mySql.append(", kind");
			mySql.append(", status");
			mySql.append(", borrower");
			mySql.append(", borrowed_time");
			mySql.append(", returned_time");
			mySql.append(", reservation_number");
			//status, borrower, borrowed_time, returned_time, reservation_numberもデフォルト値を設定する
			mySql.append(") values (");
			mySql.append("?"); //library_id
			mySql.append(", ?"); //shelf_number
			mySql.append(", ?"); //isbn
			mySql.append(", ?"); //name
			mySql.append(", ?"); //author_name
			mySql.append(", ?"); //publisher_name
			mySql.append(", ?"); //kind
			mySql.append(", ?"); //status
			mySql.append(", ?"); //borrower
			mySql.append(", 0000-00-00"); //borrowed_time
			mySql.append(", 0000-00-00"); //returned_time
			mySql.append(", 0"); //reservation_number
			mySql.append(")");

			ps = connection.prepareStatement(mySql.toString());

			ps.setInt(1, book.getLibraryId());
			ps.setInt(2, book.getShelfId());
			ps.setString(3, book.getISBN());
			ps.setString(4, book.getName());
			ps.setString(5, book.getAuthor());
			ps.setString(6, book.getPublisher());
			ps.setInt(7, book.getKind());
			ps.setInt(8, 0);
			ps.setInt(9, 0);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
