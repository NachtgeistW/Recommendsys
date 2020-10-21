package cn.iwyu.domain;

import java.io.Serializable;

public class Preference implements Serializable {
    private Integer preferenceId;

    private Integer userId;

    private Integer restaurantId;

    private Double score;

    private String date;

    private static final long serialVersionUID = 1L;

    public Integer getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(Integer preferenceId) {
        this.preferenceId = preferenceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Preference other = (Preference) that;
        return (this.getPreferenceId() == null ? other.getPreferenceId() == null : this.getPreferenceId().equals(other.getPreferenceId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getRestaurantId() == null ? other.getRestaurantId() == null : this.getRestaurantId().equals(other.getRestaurantId()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPreferenceId() == null) ? 0 : getPreferenceId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getRestaurantId() == null) ? 0 : getRestaurantId().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", preferenceId=").append(preferenceId);
        sb.append(", userId=").append(userId);
        sb.append(", restaurantId=").append(restaurantId);
        sb.append(", score=").append(score);
        sb.append(", date=").append(date);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}