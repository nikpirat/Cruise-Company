package service;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;

import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDaoImpl();
    public void create(User user){
        userDao.create(user);
    }
    public void update(User user){
        userDao.update(user);
    }
    public void deleteById(int id){
        userDao.deleteById(id);
    }
    public List<User> findAll(){
        return userDao.findAll();
    }
    public List<User> findUsersUsingLimitAndOffset(int currentPage, int numOfRecords){
        return userDao.findUsersUsingLimitAndOffset(currentPage, numOfRecords);
    }
    public int getNumberOfRows(){
        return userDao.getNumberOfRows();
    }
    public User getByLoginAndPassword(String login, String password){
        return userDao.getByLoginAndPassword(login,password);
    }
    public User getById(int id){
        return userDao.getById(id);
    }
}
