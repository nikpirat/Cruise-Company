package model;

import java.sql.Date;
import java.util.Objects;

public class Ship {
    private int id;
    private String name;
    private int passengerAmount;
    private String routeFrom;
    private String routeTo;
    private int amountPorts;
    private Date travelStart;
    private Date travelEnd;
    private float price;


    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassengerAmount() {
        return passengerAmount;
    }

    public void setPassengerAmount(int passengerAmount) {
        this.passengerAmount = passengerAmount;
    }

    public String getRouteFrom() {
        return routeFrom;
    }

    public void setRouteFrom(String routeFrom) {
        this.routeFrom = routeFrom;
    }

    public String getRouteTo() {
        return routeTo;
    }

    public void setRouteTo(String routeTo) {
        this.routeTo = routeTo;
    }

    public int getAmountPorts() {
        return amountPorts;
    }

    public void setAmountPorts(int amountPorts) {
        this.amountPorts = amountPorts;
    }

    public java.sql.Date getTravelStart() {
        return travelStart;
    }

    public void setTravelStart(Date travelStart) {
        this.travelStart = travelStart;
    }

    public java.sql.Date getTravelEnd() {
        return travelEnd;
    }

    public void setTravelEnd(Date travelEnd) {
        this.travelEnd = travelEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ship)) return false;
        Ship ship = (Ship) o;
        return id == ship.id &&
                passengerAmount == ship.passengerAmount &&
                amountPorts == ship.amountPorts &&
                Float.compare(ship.price, price) == 0 &&
                Objects.equals(name, ship.name) &&
                Objects.equals(routeFrom, ship.routeFrom) &&
                Objects.equals(routeTo, ship.routeTo) &&
                Objects.equals(travelStart, ship.travelStart) &&
                Objects.equals(travelEnd, ship.travelEnd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, passengerAmount, routeFrom, routeTo, amountPorts, travelStart, travelEnd, price);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passengerAmount=" + passengerAmount +
                ", routeFrom='" + routeFrom + '\'' +
                ", routeTo='" + routeTo + '\'' +
                ", amountPorts=" + amountPorts +
                ", travelStart=" + travelStart +
                ", travelEnd=" + travelEnd +
                ", price=" + price +
                '}';
    }
}
