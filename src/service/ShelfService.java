package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Shelf;
import dao.ShelfDao;

public class ShelfService {

	public List<Shelf> getShelfList() {

		Connection connection = null;
		try {
			connection = getConnection();

			ShelfDao listDao = new ShelfDao();
			List<Shelf> ret = listDao.getShelfList(connection);

			commit(connection);

			return ret;
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