package cn.iwyu.domain;

import java.io.Serializable;

public class ComplainRecord implements Serializable {
    private Integer idComplainRecord;

    private Byte isProcessed;

    private Integer idRestaurant;

    private String reason;

    private Integer idUser;

    private Integer idAdmin;

    private String result;

    private static final long serialVersionUID = 1L;

    public Integer getIdComplainRecord() {
        return idComplainRecord;
    }

    public void setIdComplainRecord(Integer idComplainRecord) {
        this.idComplainRecord = idComplainRecord;
    }

    public Byte getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(Byte isProcessed) {
        this.isProcessed = isProcessed;
    }

    public Integer getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Integer idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
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
        ComplainRecord other = (ComplainRecord) that;
        return (this.getIdComplainRecord() == null ? other.getIdComplainRecord() == null : this.getIdComplainRecord().equals(other.getIdComplainRecord()))
            && (this.getIsProcessed() == null ? other.getIsProcessed() == null : this.getIsProcessed().equals(other.getIsProcessed()))
            && (this.getIdRestaurant() == null ? other.getIdRestaurant() == null : this.getIdRestaurant().equals(other.getIdRestaurant()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getIdUser() == null ? other.getIdUser() == null : this.getIdUser().equals(other.getIdUser()))
            && (this.getIdAdmin() == null ? other.getIdAdmin() == null : this.getIdAdmin().equals(other.getIdAdmin()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdComplainRecord() == null) ? 0 : getIdComplainRecord().hashCode());
        result = prime * result + ((getIsProcessed() == null) ? 0 : getIsProcessed().hashCode());
        result = prime * result + ((getIdRestaurant() == null) ? 0 : getIdRestaurant().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getIdUser() == null) ? 0 : getIdUser().hashCode());
        result = prime * result + ((getIdAdmin() == null) ? 0 : getIdAdmin().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", idComplainRecord=").append(idComplainRecord);
        sb.append(", isProcessed=").append(isProcessed);
        sb.append(", idRestaurant=").append(idRestaurant);
        sb.append(", reason=").append(reason);
        sb.append(", idUser=").append(idUser);
        sb.append(", idAdmin=").append(idAdmin);
        sb.append(", result=").append(result);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}