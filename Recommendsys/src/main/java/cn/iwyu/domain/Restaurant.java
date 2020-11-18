package cn.iwyu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Restaurant implements Serializable {
    public static Object lock = new Object();
    private Integer idRestaurant;

    private String name;

    private String intro;

    private String typeOfCuisine;

    private String address;

    private Integer idRecommandedUser;

    private String recommandReason;

    private Integer isAuditPassed;

    private String comment;

    private String resturantImage;
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date recommendTime;

    private Integer version;

    private static final long serialVersionUID = 1L;

    public Integer getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Integer idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getTypeOfCuisine() {
        return typeOfCuisine;
    }

    public void setTypeOfCuisine(String typeOfCuisine) {
        this.typeOfCuisine = typeOfCuisine == null ? null : typeOfCuisine.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getIdRecommandedUser() {
        return idRecommandedUser;
    }

    public void setIdRecommandedUser(Integer idRecommandedUser) {
        this.idRecommandedUser = idRecommandedUser;
    }

    public String getRecommandReason() {
        return recommandReason;
    }

    public void setRecommandReason(String recommandReason) {
        this.recommandReason = recommandReason == null ? null : recommandReason.trim();
    }

    public Integer getIsAuditPassed() {
        return isAuditPassed;
    }

    public void setIsAuditPassed(Integer isAuditPassed) {
        this.isAuditPassed = isAuditPassed;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getResturantImage() {
            return resturantImage;

    }

    public void setResturantImage(String resturantImage) {
        this.resturantImage = resturantImage == null ? null : resturantImage.trim();
    }

    public Date getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Date recommendTime) {
        this.recommendTime = recommendTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
        Restaurant other = (Restaurant) that;
        return (this.getIdRestaurant() == null ? other.getIdRestaurant() == null : this.getIdRestaurant().equals(other.getIdRestaurant()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getIntro() == null ? other.getIntro() == null : this.getIntro().equals(other.getIntro()))
            && (this.getTypeOfCuisine() == null ? other.getTypeOfCuisine() == null : this.getTypeOfCuisine().equals(other.getTypeOfCuisine()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getIdRecommandedUser() == null ? other.getIdRecommandedUser() == null : this.getIdRecommandedUser().equals(other.getIdRecommandedUser()))
            && (this.getRecommandReason() == null ? other.getRecommandReason() == null : this.getRecommandReason().equals(other.getRecommandReason()))
            && (this.getIsAuditPassed() == null ? other.getIsAuditPassed() == null : this.getIsAuditPassed().equals(other.getIsAuditPassed()))
            && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
            && (this.getResturantImage() == null ? other.getResturantImage() == null : this.getResturantImage().equals(other.getResturantImage()))
            && (this.getRecommendTime() == null ? other.getRecommendTime() == null : this.getRecommendTime().equals(other.getRecommendTime()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdRestaurant() == null) ? 0 : getIdRestaurant().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIntro() == null) ? 0 : getIntro().hashCode());
        result = prime * result + ((getTypeOfCuisine() == null) ? 0 : getTypeOfCuisine().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getIdRecommandedUser() == null) ? 0 : getIdRecommandedUser().hashCode());
        result = prime * result + ((getRecommandReason() == null) ? 0 : getRecommandReason().hashCode());
        result = prime * result + ((getIsAuditPassed() == null) ? 0 : getIsAuditPassed().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getResturantImage() == null) ? 0 : getResturantImage().hashCode());
        result = prime * result + ((getRecommendTime() == null) ? 0 : getRecommendTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", idRestaurant=").append(idRestaurant);
        sb.append(", name=").append(name);
        sb.append(", intro=").append(intro);
        sb.append(", typeOfCuisine=").append(typeOfCuisine);
        sb.append(", address=").append(address);
        sb.append(", idRecommandedUser=").append(idRecommandedUser);
        sb.append(", recommandReason=").append(recommandReason);
        sb.append(", isAuditPassed=").append(isAuditPassed);
        sb.append(", comment=").append(comment);
        sb.append(", resturantImage=").append(resturantImage);
        sb.append(", recommendTime=").append(recommendTime);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}