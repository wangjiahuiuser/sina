package sina.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sina.dao.FavoriteDao;
import sina.service.FavoriteService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service("favoriteService")
public class FavoriteServiceImpl implements FavoriteService {
    @Qualifier("favoriteDao")
    @Autowired
    private FavoriteDao favoriteDao;
    @Override
    public String changeStatus(int status, int aid, int uid) {
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(status==0){
            String fdate = sim.format(new Date());
            favoriteDao.addFavorite(aid,uid,fdate);
            return "收藏成功";
        }else if(status==1){
            //证明该登录用户已经点过赞了
            favoriteDao.deleteFavorite(aid,uid);
            return "取消收藏成功";
        }else {
            //throw new RuntimeException("状态设置错误");
            return "状态设置错误";
        }
    }
}
