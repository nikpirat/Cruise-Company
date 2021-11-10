package dao;

import model.User;

import java.util.List;

public interface UserDao {
    void create(User user);
    void update(User user);
    void deleteById(int id);
    List<User> findAll();
    List<User> findUsersUsingLimitAndOffset(int currentPage, int numOfRecords);
    int getNumberOfRows();
    User getByLoginAndPassword(String login, String password);
}
