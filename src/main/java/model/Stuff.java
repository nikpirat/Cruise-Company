package model;

import java.util.Objects;

public class Stuff {
    private String name;
    private String position;
    private int age;
    private int shipId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stuff)) return false;
        Stuff stuff = (Stuff) o;
        return age == stuff.age &&
                shipId == stuff.shipId &&
                Objects.equals(name, stuff.name) &&
                Objects.equals(position, stuff.position);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, position, age, shipId);
    }
}

