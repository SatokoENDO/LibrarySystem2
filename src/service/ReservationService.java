package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import dao.ReservationDao;

public class ReservationService {

	public void reservation(int loginUserId,int bookId) {

		Connection connection = null;
		try {
			connection = getConnection();

			ReservationDao reservationDao = new ReservationDao();
			reservationDao.insert(connection, loginUserId, bookId);

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

	public void request(int loginUserId,int bookId) {

		Connection connection = null;
		try {
			connection = getConnection();

			ReservationDao reservationDao = new ReservationDao();
			reservationDao.request(connection, loginUserId, bookId);

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

	public void delete(int loginUserId,int bookId) {

		Connection connection = null;
		try {
			connection = getConnection();

			ReservationDao reservationDao = new ReservationDao();
			reservationDao.delete(connection, loginUserId, bookId);

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

	public int select(int bookId){

		Connection connection = null;
		try{
			connection = getConnection();

			ReservationDao reservationDao = new ReservationDao();
			int ret = reservationDao.select(connection, bookId);
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

	public void updateNotificationTime(int bookId){
		Connection connection = null;
		try{
			connection = getConnection();
			ReservationDao reservationDao = new ReservationDao();
			reservationDao.updateNotificationTime(connection, bookId);
			commit(connection);

		} finally{
			close(connection);
		}
	}

	public void update(int bookId){
		Connection connection = null;
		try{
			connection = getConnection();
			ReservationDao reservationDao = new ReservationDao();
			reservationDao.updateNotificationTime(connection, bookId);
			commit(connection);

		} finally{
			close(connection);
		}
	}
}
