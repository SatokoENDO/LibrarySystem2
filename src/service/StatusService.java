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
}
