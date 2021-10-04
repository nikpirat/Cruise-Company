package dao;

import model.User;

import java.util.List;

public interface UserDao {
    public void create(User user);
    public void update(User user);
    public void deleteById(int id);
    public List<User> findAll();
    public List<User> findUsersUsingLimitAndOffset(int currentPage, int numOfRecords);
    public int getNumberOfRows();
    public User getByLoginAndPassword(String login, String password);
}
