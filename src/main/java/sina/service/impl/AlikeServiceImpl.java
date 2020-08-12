package sina.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sina.dao.AlikeDao;
import sina.service.AlikeService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service("alikeService")
public class AlikeServiceImpl implements AlikeService {
    @Qualifier("alikeDao")
    @Autowired
    private AlikeDao alikeDao;

    //改变文章点赞的状态
    @Override
    public String changeStatus(int status,int aid,int uid) {
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(status==0){
            String ldate = sim.format(new Date());
            alikeDao.addLike(aid,uid,ldate);
            return "点赞成功";
        }else if(status==1){
            //证明该登录用户已经点过赞了
            alikeDao.deleteLike(aid,uid);
            return "取消点赞成功";
        }else {
            //throw new RuntimeException("状态设置错误");
            return "状态设置错误";
        }
    }
}
