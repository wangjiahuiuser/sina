package sina.domain;

import java.io.Serializable;
import java.util.Date;

public class Alike implements Serializable {
    private User user;   //点赞该用户的用户
    private Article article;   //被点赞的文章
    private Date ldate;

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

    public Date getLdate() {
        return ldate;
    }

    public void setLdate(Date ldate) {
        this.ldate = ldate;
    }

    public Alike() {
    }

    public Alike(User user, Article article, Date ldate) {
        this.user = user;
        this.article = article;
        this.ldate = ldate;
    }

    @Override
    public String toString() {
        return "Alike{" +
                "user=" + user +
                ", article=" + article +
                ", ldate=" + ldate +
                '}';
    }

}
