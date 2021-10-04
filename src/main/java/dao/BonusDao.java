package dao;

import model.Bonus;
import model.enums.RoomType;

import java.util.List;

public interface BonusDao {
    public void create(Bonus bonus);
    public void update(Bonus bonus);
    public void deleteById(int id);
    public List<Bonus> findAllWithRoomTypeNull();
    public List<Bonus> findAllByRoomType(RoomType roomType);
    public Bonus getById(int id);
}
