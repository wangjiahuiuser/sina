package sina.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("flag")
public class Flag implements Serializable {
    private boolean islike;  //当前用户是否点赞过该篇文章
    private boolean isfavorite;  //当前用户是否收藏过该篇文章

    public boolean isIslike() {
        return islike;
    }

    public void setIslike(boolean islike) {
        this.islike = islike;
    }

    public boolean isIsfavorite() {
        return isfavorite;
    }

    public void setIsfavorite(boolean isfavorite) {
        this.isfavorite = isfavorite;
    }

    public Flag() {
    }

    public Flag(boolean islike, boolean isfavorite) {
        this.islike = islike;
        this.isfavorite = isfavorite;
    }

    @Override
    public String toString() {
        return "Flag{" +
                "islike=" + islike +
                ", isfavorite=" + isfavorite +
                '}';
    }
}
