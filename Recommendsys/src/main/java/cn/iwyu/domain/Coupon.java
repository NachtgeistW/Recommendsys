package cn.iwyu.domain;

import java.io.Serializable;

public class Coupon implements Serializable {
    private Integer couponId;

    private Integer allocation;

    private Integer userId;

    private String content;

    private String useDate;

    private String startDate;

    private String endDate;

    private String hashId;

    private Integer restaurantId;

    private static final long serialVersionUID = 1L;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getAllocation() {
        return allocation;
    }

    public void setAllocation(Integer allocation) {
        this.allocation = allocation;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate == null ? null : useDate.trim();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId == null ? null : hashId.trim();
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
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
        Coupon other = (Coupon) that;
        return (this.getCouponId() == null ? other.getCouponId() == null : this.getCouponId().equals(other.getCouponId()))
            && (this.getAllocation() == null ? other.getAllocation() == null : this.getAllocation().equals(other.getAllocation()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getUseDate() == null ? other.getUseDate() == null : this.getUseDate().equals(other.getUseDate()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getHashId() == null ? other.getHashId() == null : this.getHashId().equals(other.getHashId()))
            && (this.getRestaurantId() == null ? other.getRestaurantId() == null : this.getRestaurantId().equals(other.getRestaurantId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCouponId() == null) ? 0 : getCouponId().hashCode());
        result = prime * result + ((getAllocation() == null) ? 0 : getAllocation().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getUseDate() == null) ? 0 : getUseDate().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getHashId() == null) ? 0 : getHashId().hashCode());
        result = prime * result + ((getRestaurantId() == null) ? 0 : getRestaurantId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", couponId=").append(couponId);
        sb.append(", allocation=").append(allocation);
        sb.append(", userId=").append(userId);
        sb.append(", content=").append(content);
        sb.append(", useDate=").append(useDate);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", hashId=").append(hashId);
        sb.append(", restaurantId=").append(restaurantId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}