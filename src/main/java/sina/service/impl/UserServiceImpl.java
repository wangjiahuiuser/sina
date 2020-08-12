package sina.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import sina.dao.ArticleDao;
import sina.dao.FocusDao;
import sina.dao.UserDao;
import sina.dao.UserPhotoDao;
import sina.domain.Article;
import sina.domain.Focus;
import sina.domain.User;
import sina.domain.UserPhoto;
import sina.service.FocusService;
import sina.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Qualifier("userDao")
    @Autowired
    private UserDao userDao;
    @Qualifier("userPhotoDao")
    @Autowired
    private UserPhotoDao userPhotoDao;
    @Qualifier("focusDao")
    @Autowired
    private FocusDao focusDao;
    @Autowired
    private FocusService focusService;

    //注册功能
    @Override
    public boolean register(User user) {
        //传入字符串类型的电话号码
        User u = userDao.findUserByTelephone(user.getTelephone());
        if(u!=null){
            return false;
        }
        else {
            String bigdefault="./bimages/big/bdefault.jpg";
            String smalldefault="./bimages/small/sdefault.jpg";
            user.setUimage("./images/picture1.jpg");
            user.setIntroduce("该用户很懒，什么也没留下");
            try {
                userDao.saveUser(user);
                //用户注册时将用户背景图片持久化到数据库（默认图片）  建立userPhotoDao 永久保存
                //通过插入返回的用户id实现
                userPhotoDao.insertDefault(user.getUid(),bigdefault,smalldefault);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("注册失效");
            }
            System.out.println("注册成功");
            return true;
        }
    }
    //登录功能
    @Override
    public User login(String telephone, String password) {
        User user=userDao.findByTelAndPassword(telephone,password);
        if(user==null){
            return null;
        }
        System.out.println(user.getUid());
        List<Integer> list1=new ArrayList<Integer>();
        //把登录用户的主页照片信息封装到user对象中去  返回给前台
        UserPhoto userPhoto=userPhotoDao.findPhoto(user.getUid());
        user.setUserPhoto(userPhoto);
        //把登录用户的关注列表对象的id集合封装到user对象中去
        List<Focus> list = focusDao.findFocusList(user.getUid());
        for (Focus focus:list) {
            list1.add(focus.getBefid());
        }
        user.setFids(list1);
        return user;
    }

    //查看个人数据详情
    @Override
    public User findDetailOne(int uid) {
        User user=userDao.findUserByUid(uid);
        //添加这个用户的粉丝数 关注数
        Focus focus=focusService.findFocusAndBeFocusNub(user.getUid());
        user.setFocus(focus);
        //添加这个用户的背景图片信息
        UserPhoto userPhoto=userPhotoDao.findPhoto(user.getUid());
        user.setUserPhoto(userPhoto);
        return user;
    }

    //设置个人简介
    @Override
    public String updateIntroduce(String introduce, int uid) {
        try {
            userDao.updateIntroduce(introduce,uid);
            return "修改简介成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "修改简介失败";
        }
    }

    //修改用户名
    @Override
    public String updateUsername(String username, int uid) {
        try {
            userDao.updateUsername(username,uid);
            return "修改用户名成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "修改用户名失败";
        }
    }


    @Override
    public  List<User> findAll() {
        System.out.println("业务层，查询所有账户信息");
        System.out.println("业务层的user"+userDao.findAll());
        return userDao.findAll();
    }
}
