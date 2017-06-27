package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Book;
import beans.User;
import dao.DemandDao;
import dao.UserDao;

public class DemandService {

//	延滞本を表示
	public  List<Book>  getDelayedBook(){

		Connection connection = null;
		try{
			connection = getConnection();

			DemandDao bookDao = new DemandDao();
			List<Book> book = bookDao. getDelayedBook(connection);

			return book ;
		}finally{

		}
	}

	public List<User> getUserInfo(){

		Connection connection = null;
		try{
			connection = getConnection();

			UserDao userDao = new UserDao();
			List<User> userInfo = userDao. getAllUser(connection);

			return userInfo ;
		}finally{

		}
	}

	public void updateDemandTime(int userId){
		Connection connection = null;
		try{
			connection = getConnection();
			DemandDao demandDao = new DemandDao();
			demandDao.updateDemandTime(connection, userId);
			commit(connection);

		} finally{
			close(connection);
		}
	}


}