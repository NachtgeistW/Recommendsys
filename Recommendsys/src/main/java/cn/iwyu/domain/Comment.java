package cn.iwyu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private Integer idComment;

    private Integer idRestaurant;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date time;

    private Integer idUser;

    private Integer idCommentReply;

    private Integer numLike;

    private String context;

    private Integer score;

    private static final long serialVersionUID = 1L;

    public Integer getIdComment() {
        return idComment;
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    public Integer getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Integer idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdCommentReply() {
        return idCommentReply;
    }

    public void setIdCommentReply(Integer idCommentReply) {
        this.idCommentReply = idCommentReply;
    }

    public Integer getNumLike() {
        return numLike;
    }

    public void setNumLike(Integer numLike) {
        this.numLike = numLike;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
        Comment other = (Comment) that;
        return (this.getIdComment() == null ? other.getIdComment() == null : this.getIdComment().equals(other.getIdComment()))
            && (this.getIdRestaurant() == null ? other.getIdRestaurant() == null : this.getIdRestaurant().equals(other.getIdRestaurant()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getIdUser() == null ? other.getIdUser() == null : this.getIdUser().equals(other.getIdUser()))
            && (this.getIdCommentReply() == null ? other.getIdCommentReply() == null : this.getIdCommentReply().equals(other.getIdCommentReply()))
            && (this.getNumLike() == null ? other.getNumLike() == null : this.getNumLike().equals(other.getNumLike()))
            && (this.getContext() == null ? other.getContext() == null : this.getContext().equals(other.getContext()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIdComment() == null) ? 0 : getIdComment().hashCode());
        result = prime * result + ((getIdRestaurant() == null) ? 0 : getIdRestaurant().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getIdUser() == null) ? 0 : getIdUser().hashCode());
        result = prime * result + ((getIdCommentReply() == null) ? 0 : getIdCommentReply().hashCode());
        result = prime * result + ((getNumLike() == null) ? 0 : getNumLike().hashCode());
        result = prime * result + ((getContext() == null) ? 0 : getContext().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", idComment=").append(idComment);
        sb.append(", idRestaurant=").append(idRestaurant);
        sb.append(", time=").append(time);
        sb.append(", idUser=").append(idUser);
        sb.append(", idCommentReply=").append(idCommentReply);
        sb.append(", numLike=").append(numLike);
        sb.append(", context=").append(context);
        sb.append(", score=").append(score);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}