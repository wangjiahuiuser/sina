package sina.domain;

import java.io.Serializable;
import java.util.Date;

public class Focus implements Serializable {
    private int befid;  //被关注者的用户id
    private int fid;   //关注别人的用户id
    private Date date;

    private int focusNub;  //关注数
    private int befocusNub;   //被关注数

    public int getBefid() {
        return befid;
    }

    public void setBefid(int befid) {
        this.befid = befid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getFocusNub() {
        return focusNub;
    }

    public void setFocusNub(int focusNub) {
        this.focusNub = focusNub;
    }

    public int getBefocusNub() {
        return befocusNub;
    }

    public void setBefocusNub(int befocusNub) {
        this.befocusNub = befocusNub;
    }

    public Focus() {
    }

    public Focus(int befid, int fid, Date date, int focusNub, int befocusNub) {
        this.befid = befid;
        this.fid = fid;
        this.date = date;
        this.focusNub = focusNub;
        this.befocusNub = befocusNub;
    }

    @Override
    public String toString() {
        return "Focus{" +
                "befid=" + befid +
                ", fid=" + fid +
                ", date=" + date +
                ", focusNub=" + focusNub +
                ", befocusNub=" + befocusNub +
                '}';
    }
}
