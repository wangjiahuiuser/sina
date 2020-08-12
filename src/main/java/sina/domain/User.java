package sina.domain;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private int uid;
    private String username;
    private String telephone;
    private String email;
    private String password;
    private String uimage;
    private String introduce;

    private Focus focus;  //关注表（通过此可以显示粉丝数和关注数）
    private UserPhoto userPhoto;  //显示用户的背景照片等
    private List<Integer> fids;   //用户登录后的关注列表用户id集合
    private List<Article> articles; //该用户所发表的所有文章
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUimage() {
        return uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Focus getFocus() {
        return focus;
    }

    public void setFocus(Focus focus) {
        this.focus = focus;
    }

    public UserPhoto getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(UserPhoto userPhoto) {
        this.userPhoto = userPhoto;
    }

    public List<Integer> getFids() {
        return fids;
    }

    public void setFids(List<Integer> fids) {
        this.fids = fids;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public User() {
    }

    public User(int uid, String username, String telephone, String email, String password, String uimage, String introduce, Focus focus, UserPhoto userPhoto, List<Integer> fids, List<Article> articles) {
        this.uid = uid;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.uimage = uimage;
        this.introduce = introduce;
        this.focus = focus;
        this.userPhoto = userPhoto;
        this.fids = fids;
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", uimage='" + uimage + '\'' +
                ", introduce='" + introduce + '\'' +
                ", focus=" + focus +
                ", userPhoto=" + userPhoto +
                ", fids=" + fids +
                ", articles=" + articles +
                '}';
    }
}
