package sina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sina.domain.*;
import sina.service.FocusService;
import sina.service.UserService;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller("userController")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FocusService focusService;
    @Autowired
    ResultInfo info;

    @RequestMapping("/register")
    //注册的ajax形式  @ResponseBody
    //@RequestBody User user
    @ResponseBody
    public ResultInfo register(@RequestBody User user){
        System.out.println("表现层注册用户 register方法");
        boolean flag = userService.register(user);
        //4.响应结果
        if(flag){
            info.setFlag(true);
            info.setErrorMsg("注册成功");
        }else {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败!");
        }
        System.out.println("打印info  "+info);
        return info;
    }

    @RequestMapping("/login")
    @ResponseBody
    public  ResultInfo login(@RequestBody User u ,HttpSession session){
        System.out.println("表现层登录用户 login方法");
        User user =userService.login(u.getTelephone(),u.getPassword());
        if(user==null){
            info.setFlag(false);
            info.setErrorMsg("账号或密码错误");
        }else {
            info.setFlag(true);
            info.setErrorMsg("登录成功");
            //存入的这个user信息不能体现修改过个性签名后 该过的个性签名
            session.setAttribute("user",user);
        }
        System.out.println(user);
        return info;
    }

    //查询登录用户详细信息
    @RequestMapping("/findLoginUser")
    @ResponseBody
    public User findLoginUser(HttpSession session){
        System.out.println("表现层查询登录用户信息 findLoginUser方法");
        User user = (User) session.getAttribute("user");
        if(user==null){
            System.out.println("用户尚未登录，请登录");
            throw new RuntimeException("用户尚未登录，请登录");
        }
        //查询粉丝数 和关注数
        Focus focus = focusService.findFocusAndBeFocusNub(user.getUid());
        user.setFocus(focus);
        System.out.println(user);
        return user;
    }

    //注销已登录用户
    @RequestMapping("/exit")
    @ResponseBody
    public ResultInfo exit(HttpSession session){
        System.out.println("表现层注销登录用户 exit方法");
        session.invalidate();
        info.setFlag(true);
        info.setErrorMsg("");
        return info;
    }

    //登录用户添加关注
    @RequestMapping("/addFocusUser")
    @ResponseBody
    public ResultInfo addFocusUser(int befid,boolean isFocus,HttpSession session){
        System.out.println("表现层增加用户关注功能 addFocusUser方法");
        System.out.println("isFocus为"+isFocus);
        User user = (User) session.getAttribute("user");
        if(user==null){
            System.out.println("用户尚未登录，请登录");
            throw new RuntimeException("用户尚未登录，请登录");
        }
        String word=focusService.addOneFocus(befid,user.getUid(),isFocus);
        info.setErrorMsg(word);
        if(word.equals("关注成功")||word.equals("取消关注成功")){
            info.setFlag(true);
        }else {
            info.setFlag(false);
        }
        return info;
    }

    //根据传入id 和status(0代表关注用户，1代表粉丝用户)返回其两类对象的集合
    @RequestMapping("/findUserList")
    @ResponseBody
    public FocusUserList<User> findUserList(int uid, int status){
        System.out.println("表现层返回关注、粉丝用户信息 findUserList");
        System.out.println("uid为"+uid+" status为"+status);
        FocusUserList<User> list=focusService.findUserList(uid,status);
        return list;
    }

    //根据id值返回用户的基本信息  这个可以是非登录用户的所有信息查询
    @RequestMapping("/detailOne")
    @ResponseBody
    public User detailOne(int uid){
        System.out.println("表现层返回一个用户详细信息 detailOne");
        User user=userService.findDetailOne(uid);
        System.out.println("user对象"+user);
        return user;
    }


    //设置个人简介   post请求 使用实体类接收数据
    @RequestMapping("/setIntroduce")
    @ResponseBody
    public ResultInfo setIntroduce(@RequestBody User u,HttpSession session){
        System.out.println("表现层更改个人简介 setIntroduce");
        System.out.println("introduce为" +u.getIntroduce());
         User user=(User)session.getAttribute("user");
        if(user==null){
            System.out.println("用户尚未登录，请登录");
            throw new RuntimeException("用户尚未登录，请登录");
        }
        String msg = userService.updateIntroduce(u.getIntroduce(), user.getUid());
        info.setErrorMsg(msg);
        if(msg.equals("修改简介成功")){
            info.setFlag(true);
        }else {
            info.setFlag(false);
        }
        return info;
    }

    //修改个人昵称（用户名）
    @RequestMapping("/setUsername")
    @ResponseBody
    public ResultInfo setUsername(String username,int uid){
        System.out.println("表现层更改用户名 setIntroduce");
        System.out.println("username为"+username);
        String msg=userService.updateUsername(username,uid);
        info.setErrorMsg(msg);
        if(msg.equals("修改用户名成功")){
            info.setFlag(true);
        }else {
            info.setFlag(false);
        }
        return info;
    }




    @RequestMapping("/findAll")
    public @ResponseBody List<User> findAll(){
        System.out.println("表现层查询所有的用户信息");
        List<User> users = userService.findAll();
        System.out.println("打印所有user"+users);
        return users;
    }
}
