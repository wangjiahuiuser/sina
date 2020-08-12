package sina.domain;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    //评论表应该设置三个主键才能保证每层楼主能发多次言 其中文章id最优先 用户id其次 时间第三
    private int aid;
    private int uid;
    private String comments;
    private Date cdate;
    private int clikemount;
    private boolean isCommentLike;

    private User user;   //每个楼层评论者对象的信息
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getClikemount() {
        return clikemount;
    }

    public void setClikemount(int clikemount) {
        this.clikemount = clikemount;
    }

    public boolean isCommentLike() {
        return isCommentLike;
    }

    public void setCommentLike(boolean commentLike) {
        isCommentLike = commentLike;
    }

    public Comment() {
    }

    public Comment(int aid, int uid, String comments, Date cdate, int clikemount, boolean isCommentLike, User user) {
        this.aid = aid;
        this.uid = uid;
        this.comments = comments;
        this.cdate = cdate;
        this.clikemount = clikemount;
        this.isCommentLike = isCommentLike;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", comments='" + comments + '\'' +
                ", cdate=" + cdate +
                ", clikemount=" + clikemount +
                ", isCommentLike=" + isCommentLike +
                ", user=" + user +
                '}';
    }
}
