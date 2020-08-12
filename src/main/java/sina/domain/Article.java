package sina.domain;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private int aid;
    private int uid;
    private String aword;  //文章内容
    private Date adate;
    private int cid;  //文章所属板块
    private int alikemount;  //点赞数
    private String aimage;   //存放图片相对路径
    private String avideo;   //存放视频的相对路径

    private User user;  //文章所属作者对象
    private Flag flag;  //判断点赞收藏与否对象
    private CommentsBean<Comment> commentsBean; //文章的评论

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

    public String getAword() {
        return aword;
    }

    public void setAword(String aword) {
        this.aword = aword;
    }

    public Date getAdate() {
        return adate;
    }

    public void setAdate(Date adate) {
        this.adate = adate;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getAlikemount() {
        return alikemount;
    }

    public void setAlikemount(int alikemount) {
        this.alikemount = alikemount;
    }

    public String getAimage() {
        return aimage;
    }

    public void setAimage(String aimage) {
        this.aimage = aimage;
    }

    public String getAvideo() {
        return avideo;
    }

    public void setAvideo(String avideo) {
        this.avideo = avideo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CommentsBean<Comment> getCommentsBean() {
        return commentsBean;
    }

    public void setCommentsBean(CommentsBean<Comment> commentsBean) {
        this.commentsBean = commentsBean;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public Article() {
    }

    public Article(int aid, int uid, String aword, Date adate, int cid, int alikemount, String aimage, String avideo, User user, Flag flag, CommentsBean<Comment> commentsBean) {
        this.aid = aid;
        this.uid = uid;
        this.aword = aword;
        this.adate = adate;
        this.cid = cid;
        this.alikemount = alikemount;
        this.aimage = aimage;
        this.avideo = avideo;
        this.user = user;
        this.flag = flag;
        this.commentsBean = commentsBean;
    }

    @Override
    public String toString() {
        return "Article{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", aword='" + aword + '\'' +
                ", adate=" + adate +
                ", cid=" + cid +
                ", alikemount=" + alikemount +
                ", aimage='" + aimage + '\'' +
                ", avideo='" + avideo + '\'' +
                ", user=" + user +
                ", flag=" + flag +
                ", commentsBean=" + commentsBean +
                '}';
    }
}
