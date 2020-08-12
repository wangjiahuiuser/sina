package sina.domain;

import java.io.Serializable;
import java.util.Date;

public class Favorite implements Serializable {
    private User user;  //点赞该用户的用户
    private Article article;  //被点赞的文章
    private Date fdate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Date getFdate() {
        return fdate;
    }

    public void setFdate(Date fdate) {
        this.fdate = fdate;
    }

    public Favorite() {
    }

    public Favorite(User user, Article article, Date fdate) {
        this.user = user;
        this.article = article;
        this.fdate = fdate;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "user=" + user +
                ", article=" + article +
                ", fdate=" + fdate +
                '}';
    }
}
