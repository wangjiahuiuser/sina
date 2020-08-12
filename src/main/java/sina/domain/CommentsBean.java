package sina.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("commentsBean")
public class CommentsBean<T> implements Serializable {
    private int currentPart; //当前第几条评论集合

    private List<T> list;   //存放多条评论的集合

    public int getCurrentPart() {
        return currentPart;
    }

    public void setCurrentPart(int currentPart) {
        this.currentPart = currentPart;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public CommentsBean() {
    }

    public CommentsBean(int currentPart, List<T> list) {
        this.currentPart = currentPart;
        this.list = list;
    }

    @Override
    public String toString() {
        return "CommentsBean{" +
                "currentPart=" + currentPart +
                ", list=" + list +
                '}';
    }
}
