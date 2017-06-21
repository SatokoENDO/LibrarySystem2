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
}
