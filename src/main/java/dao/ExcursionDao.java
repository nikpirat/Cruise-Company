package dao;

import model.Excursion;
import model.User;

import java.util.List;

public interface ExcursionDao {
    public void create(Excursion excursion);
    public void update(Excursion excursion);
    public void deleteById(int id);
    public List<Excursion> findAll();
    public Excursion getById(int id);

}
