package service;

import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Book;
import dao.DemandDao;

public class DemandService {

//	延滞本を表示
	public  List<Book>  getDelayedBook(String currentTime){

		Connection connection = null;
		try{
			connection = getConnection();

			DemandDao bookDao = new DemandDao();
			List<Book> book = bookDao. getDelayedBook(connection, currentTime);

			return book ;
		}finally{

		}
	}
}