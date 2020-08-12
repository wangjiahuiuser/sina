package sina.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("focusUserList")
public class FocusUserList<T> implements Serializable {

    private List<T> list;   //存放关注列表 或粉丝列表的对象

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public FocusUserList() {
    }

    public FocusUserList( List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "FocusUserList{" +
                ", list=" + list +
                '}';
    }
}
