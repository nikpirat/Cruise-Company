package model;

import model.enums.RoomType;

import java.util.Objects;

public class CruiseInfo {
    private int id;
    private int shipId;
    private RoomType roomType;
    private double totalPrice;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CruiseInfo)) return false;
        CruiseInfo that = (CruiseInfo) o;
        return id == that.id &&
                shipId == that.shipId &&
                Double.compare(that.totalPrice, totalPrice) == 0 &&
                roomType == that.roomType;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, shipId, roomType, totalPrice);
    }

    @Override
    public String toString() {
        return "CruiseInfoDaoDaoImpl{" +
                "id=" + id +
                ", shipId=" + shipId +
                ", roomType=" + roomType +
                ", price=" + totalPrice +
                '}';
    }
}
