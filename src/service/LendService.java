package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import beans.Book;
import dao.LendDao;

public class LendService {

	public void lend(Book book) {

		Connection connection = null;
		try {
			connection = getConnection();

			LendDao lendDao = new LendDao();
			lendDao.update(connection, book);

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
