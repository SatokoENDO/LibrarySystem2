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
//	予約されている本のIDをすべてとってくる
	public List<Integer> getReservedBookId(){

		Connection connection = null;
		try{
			connection = getConnection();

			StatusDao getBookDao = new StatusDao();
			List<Integer> bookId = getBookDao.getReservedBookId(connection);
			return bookId ;
		}finally{

		}
	}
//	id から予約本のIDリストをとってくる
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
// 予約番号をとってくる
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
// 予約順を作成するための準備
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
// 受取資料のidをとってくる
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
