package model;

import model.enums.Role;

import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private Role role;
    private String name;
    private String surname;
    private int cruiseInfoId;
    private float balance;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getCruiseInfoId() {
        return cruiseInfoId;
    }

    public void setCruiseInfoId(int cruiseInfoId) {
        this.cruiseInfoId = cruiseInfoId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                cruiseInfoId == user.cruiseInfoId &&
                Float.compare(user.balance, balance) == 0 &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, login, password, role, name, surname, cruiseInfoId, balance);
    }

    @Override
    public String toString() {
        return "UserDao{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
