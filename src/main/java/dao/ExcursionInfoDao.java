package dao;

import model.Excursion;
import model.ExcursionInfo;

import java.util.List;

public interface ExcursionInfoDao {
    public ExcursionInfo create(ExcursionInfo excursionInfo);
    public void update(ExcursionInfo excursionInfo);
    public void deleteById(int id);
    public List<ExcursionInfo> findAll();
    public List<ExcursionInfo> findAllByUserId(int id);
}
