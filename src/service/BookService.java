package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import beans.Book;
import dao.BookDao;

public class BookService {

	public void register(Book book) {

		Connection connection = null;
		try {
			connection = getConnection();

			BookDao bookDao = new BookDao();
			bookDao.insert(connection, book);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
