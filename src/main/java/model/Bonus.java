package model;

import model.enums.RoomType;

import java.util.Objects;

public class Bonus {
    private int id;
    private String name;
    private String description;
    private RoomType roomType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bonus)) return false;
        Bonus bonus = (Bonus) o;
        return id == bonus.id &&
                Objects.equals(name, bonus.name) &&
                roomType == bonus.roomType;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, roomType);
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roomType=" + roomType +
                '}';
    }
}
