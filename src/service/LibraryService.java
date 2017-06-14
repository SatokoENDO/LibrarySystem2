package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Library;
import dao.LibraryDao;

public class LibraryService {

	public List<Library> getLibraryList() {

		Connection connection = null;
		try {
			connection = getConnection();

			LibraryDao listDao = new LibraryDao();
			List<Library> ret = listDao.getLibraryList(connection);

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