package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import utils.CipherUtil;
import beans.User;
import dao.UserDao;

public class UserService {
//  登録
	public void register(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.insert(connection, user);

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
//  最後に追加されたユーザーのidをゲットする
	public int getUserId() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao idDao = new UserDao();
			int ret = idDao.getUserId(connection);

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


//  自動生成したカード番号を登録する
	public void registerCardNumber(int cardNumber){
		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.insert(connection, cardNumber);

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

	public User getUser(long cardNumber){
		Connection connection = null;
		try{
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.getUser(connection, cardNumber);

			commit(connection);

			return user;
		}catch (RuntimeException e){
			rollback(connection);
			throw e;
		}finally{
			close(connection);
		}
	}

	/*public User getUser(){
		Connection connection = null;
		try{
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.getUser(connection);

			commit(connection);

			return user;
		}catch (RuntimeException e){
			rollback(connection);
			throw e;
		}finally{
			close(connection);
		}
	}*/

	//ユーザー編集
	public void update(User user){
		Connection connection = null;
		try{
			connection = getConnection();
			if(user.getPassword().isEmpty()){
				user.setPassword(user.getPassword());
			}else{
			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);
			}

			UserDao userDao = new UserDao();
			userDao.update(connection, user);

			commit(connection);

		}  catch(RuntimeException e){
			 rollback(connection);
			 throw e;
		 } catch(Error e){
			 throw e;
		 } finally{
			 close(connection);
		 }
	}
}
