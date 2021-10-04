package model;

import java.util.Objects;

public class Excursion {
    private int id;
    private int shipId;
    private String name;
    private String duration;
    private float price;
    private String additionalInfo;

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public int getId() {
        return id;
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

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Excursion)) return false;
        Excursion excursion = (Excursion) o;
        return id == excursion.id &&
                Float.compare(excursion.price, price) == 0 &&
                Objects.equals(name, excursion.name) &&
                Objects.equals(duration, excursion.duration) &&
                Objects.equals(additionalInfo, excursion.additionalInfo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, duration, price, additionalInfo);
    }

    @Override
    public String toString() {
        return "Excursion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
