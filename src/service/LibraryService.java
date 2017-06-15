package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Library;
import dao.LibraryDao;

public class LibraryService {

	public List<Library> getLibraries() {

		Connection connection = null;
		try {
			connection = getConnection();

			LibraryDao libraryDao = new LibraryDao();
			List<Library> ret = libraryDao.getLibraries(connection);

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
