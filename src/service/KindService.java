<<<<<<< HEAD
package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Kind;
import dao.KindDao;

public class KindService {

	public List<Kind> getKindList() {

		Connection connection = null;
		try {
			connection = getConnection();

			KindDao listDao = new KindDao();
			List<Kind> ret = listDao.getKindList(connection);

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

=======
package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Kind;
import dao.KindDao;

public class KindService {

	public List<Kind> getKindList() {

		Connection connection = null;
		try {
			connection = getConnection();

			KindDao listDao = new KindDao();
			List<Kind> ret = listDao.getKindList(connection);

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

>>>>>>> 81f42805c7fb3bedbacd964fa48c9cfcf0e2e4a5
}