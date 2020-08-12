package sina.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sina.dao.FocusDao;
import sina.dao.UserDao;
import sina.dao.UserPhotoDao;
import sina.domain.Focus;
import sina.domain.FocusUserList;
import sina.domain.User;
import sina.domain.UserPhoto;
import sina.service.FocusService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("focusService")
public class FocusServiceImpl implements FocusService {
    @Qualifier("focusDao")
    @Autowired
    private FocusDao focusDao;
    @Qualifier("userDao")
    @Autowired
    private UserDao userDao;
    @Qualifier("userPhotoDao")
    @Autowired
    private UserPhotoDao userPhotoDao;
    @Autowired
    private FocusUserList<User> focusUserList;

    //查询粉丝数 和关注数
    @Override
    public Focus findFocusAndBeFocusNub(int uid) {
        Focus focus=new Focus();
        //查询被他人关注数-->粉丝数
        int befocusNub= focusDao.findBeFocusNub(uid);
        focus.setBefocusNub(befocusNub);
        //查询关注别人的数量
        int focusNub=focusDao.findFocusNub(uid);
        focus.setFocusNub(focusNub);
        return focus;
    }

    //增加一条关注记录
    @Override
    public String addOneFocus(int befid, int uid ,boolean isFocus) {
        try {
            if(isFocus==false) {
                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sim.format(new Date());
                focusDao.addOneFocus(befid, date, uid);
                return "关注成功";
            }else{
                focusDao.deleteFocus(befid,uid);
                return "取消关注成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "关注失败，参数异常";
        }
    }

    //返回关注、粉丝用户信息
    //先返回focus表中的相应数据 根据对应id获得关注/粉丝 用户详情(包括其个人信息，和其关注数和粉丝数)
    @Override
    public FocusUserList<User> findUserList(int uid, int status) {
        List<User> userList=new ArrayList<User>();
        Focus focus=null;
        UserPhoto userPhoto=null;
        if(status==0){  //返回关注列表
            List<Focus> list=focusDao.findFocusList(uid);
            for (Focus fs:list) {
                User user = userDao.findUserByUid(fs.getBefid());
                //添加这个用户的粉丝数 关注数
                focus=findFocusAndBeFocusNub(fs.getBefid());
                user.setFocus(focus);
                //添加这个用户的背景图片信息
                userPhoto=userPhotoDao.findPhoto(user.getUid());
                user.setUserPhoto(userPhoto);
                userList.add(user);
            }
        }else if(status==1){ //返回粉丝列表
            List<Focus> list=focusDao.findBeFocusList(uid);
            for (Focus fs:list) {
                User user = userDao.findUserByUid(fs.getFid());
                focus=findFocusAndBeFocusNub(fs.getFid());
                user.setFocus(focus);
                userPhoto=userPhotoDao.findPhoto(user.getUid());
                user.setUserPhoto(userPhoto);
                userList.add(user);
            }
        }else {
            System.out.println("状态码传递错误");
            throw new RuntimeException("状态码传递错误");
        }
        focusUserList.setList(userList);
        return focusUserList;
    }
}
