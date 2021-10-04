package model;

import java.util.Objects;

public class ExcursionInfo {
    private int id;
    private int excursionId;
    private int userId;
    private int cruiseInfoId;

    public int getCruiseInfoId() {
        return cruiseInfoId;
    }

    public void setCruiseInfoId(int cruiseInfoId) {
        this.cruiseInfoId = cruiseInfoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExcursionId() {
        return excursionId;
    }

    public void setExcursionId(int excursionId) {
        this.excursionId = excursionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExcursionInfo)) return false;
        ExcursionInfo that = (ExcursionInfo) o;
        return id == that.id &&
                excursionId == that.excursionId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, excursionId, userId);
    }

    @Override
    public String toString() {
        return "ExcursionInfo{" +
                "id=" + id +
                ", excursionId=" + excursionId +
                ", userId=" + userId +
                '}';
    }
}
