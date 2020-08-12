package sina.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component("userPhoto")
public class UserPhoto implements Serializable {
    //用户个人页面图片集
    private int pid;
    private int uid;
    private String bigphoto;
    private String smallphoto;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getBigphoto() {
        return bigphoto;
    }

    public void setBigphoto(String bigphoto) {
        this.bigphoto = bigphoto;
    }

    public String getSmallphoto() {
        return smallphoto;
    }

    public void setSmallphoto(String smallphoto) {
        this.smallphoto = smallphoto;
    }

    public UserPhoto() {
    }

    public UserPhoto(int pid, int uid, String bigphoto, String smallphoto) {
        this.pid = pid;
        this.uid = uid;
        this.bigphoto = bigphoto;
        this.smallphoto = smallphoto;
    }

    @Override
    public String toString() {
        return "UserPhoto{" +
                "pid=" + pid +
                ", uid=" + uid +
                ", bigphoto='" + bigphoto + '\'' +
                ", smallphoto='" + smallphoto + '\'' +
                '}';
    }
}
