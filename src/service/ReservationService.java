package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import dao.ReservationDao;

public class ReservationService {

	public void reservation(int loginId,int bookId) {

		Connection connection = null;
		try {
			connection = getConnection();

			ReservationDao reservationDao = new ReservationDao();
			reservationDao.insert(connection, loginId, bookId);

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
