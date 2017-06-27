package service;

import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Book;
import beans.User;
import dao.StatusDao;

public class StatusService {

	public List<Book> getBookInfo(User loginUser){

		Connection connection = null;
		try{
			connection = getConnection();

			StatusDao getBookDao = new StatusDao();
			List<Book> bookList = getBookDao.getBookInfo(connection, loginUser);
			return bookList ;
		}finally{

		}
	}

	public List<Integer> getReservedBookId(int loginUserId){

		Connection connection = null;
		try{
			connection = getConnection();

			StatusDao getBookDao = new StatusDao();
			List<Integer> bookId = getBookDao.getReservedBookId(connection, loginUserId);
			return bookId ;
		}finally{

		}
	}

	public List<Integer> getReservationNumber(int bookId){

		Connection connection = null;
		try{
			connection = getConnection();

			StatusDao getBookDao = new StatusDao();
			List<Integer> reservationNumber = getBookDao.getReservationNumber(connection, bookId);
			return reservationNumber ;
		}finally{

		}
	}

	public int getReservationNumberOf(int loginId, int bookId){

		Connection connection = null;
		try{
			connection = getConnection();

			StatusDao getBookDao = new StatusDao();
			int reservationNumber = getBookDao.getReservationNumberOf(connection, loginId, bookId);
			return reservationNumber ;
		}finally{

		}
	}

	public List<Integer> getDeliverBookId(int loginUserId){

		Connection connection = null;
		try{
			connection = getConnection();

			StatusDao getBookDao = new StatusDao();
			List<Integer> deliverBookId = getBookDao.getDelivery(connection, loginUserId);
			return deliverBookId ;
		}finally{

		}
	}
}
