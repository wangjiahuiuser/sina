package sina.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sina.dao.ArticleDao;
import sina.dao.FavoriteDao;
import sina.dao.AlikeDao;
import sina.dao.UserDao;
import sina.domain.*;
import sina.service.ArticleService;
import sina.service.CommentService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Qualifier("articleDao")
    @Autowired
    private ArticleDao articleDao;
    @Qualifier("userDao")
    @Autowired
    private UserDao userDao;
    @Qualifier("alikeDao")
    @Autowired
    private AlikeDao likeDao;
    @Qualifier("favoriteDao")
    @Autowired
    private FavoriteDao favoriteDao;
    @Autowired
    CommentService commentService;
    @Autowired
    private PageBean<Article> pageBean;
    /*@Autowired
    private Flag flag;*/


    @Override
    public PageBean<Article> findPage(int currentPage, int cid, String aword,int uid) {
        int pageSize=10;
        int start=(currentPage-1)*pageSize;
        System.out.println("start="+start+"   currentPage="+currentPage);
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        //在不用SpringMvc接收参数下要判断为null情况  如果aword本身就是null那么,则不做拼接
        if(aword==null){
            aword="%%";
        }else {aword="%"+aword+"%";}
        System.out.println("查询的关键字"+aword);
        List<Article> list = articleDao.findPage(cid,start,pageSize,aword);
        List<Article> list1 = new ArrayList<Article>();
        for (Article article:list) {
            int aid=article.getAid();
            article = packageArticle(aid, uid);
            User user = userDao.findUserByUid(article.getUid());
            user.setPassword("***");
            article.setUser(user);
            list1.add(article);
            System.out.println("findPage方法的article  "+ article);
        }
        pageBean.setList(list1);
        return pageBean;
    }

    @Override
    public Article findOneArticle(int aid, int uid ,int currentPart) {
        Article article = packageArticle(aid, uid);
        User user = userDao.findUserByUid(article.getUid());
        user.setPassword("***");
        article.setUser(user);
        //点进一篇文章时，加载文章的评论
        CommentsBean<Comment> comments = commentService.findComments(currentPart, aid);
        article.setCommentsBean(comments);
        //时间戳
        System.out.println("时间"+ article.getAdate());
        System.out.println("findOneArticle中的article  "+article);
        return article;
    }

    @Override
    public List<Article> findOwnArticle(int uid, int currentNub) {
        int size=10;
        int start=(currentNub-1)*size;
        //添加这个用户所发表的所有文章  分页形式
        List<Article>articles=articleDao.findOwnArticle(uid,start,size);
        //返回文章的一些详细信息，如点赞数 还有自己是否点赞过文章，收藏过文章
        List<Article> list1 = new ArrayList<Article>();
        for (Article article:articles) {
            int aid=article.getAid();
            article = packageArticle(aid, uid);
            list1.add(article);
            System.out.println("findOwnArticle方法  产生的article");
        }
        return list1;
    }

    //寻找热门博文(10条)  先按点赞数排序  相同的话再按发表时间排序
    @Override
    public List<Article> findHotArticle() {
        int start=0;
        int size=6;
        List<Article> articles=articleDao.findHotArticle(start,size);
        return articles;
    }

    //集成类，可以显示传入uid用户是否点赞过这篇文章 并且把点赞数设置到article表的AlikeMount属性
    public Article packageArticle(int aid,int uid){
        System.out.println("业务层的findAndPackageArticle方法");
        Article article = articleDao.findOne(aid);
        if(uid!=0) {
            Flag flag = new Flag();
            Favorite favorite = favoriteDao.isFavorite(aid, uid);
            if (favorite != null) {
                flag.setIsfavorite(true);
            } else {
                flag.setIsfavorite(false);
            }
            Alike like = likeDao.isAlike(aid, uid);
            if (like != null) {
                flag.setIslike(true);
            } else {
                flag.setIslike(false);
            }
            article.setFlag(flag);
        }
        int totalLike = likeDao.findTotalLike(aid);
        article.setAlikemount(totalLike);
        //修改article表的点赞数的值
        articleDao.updateAlikemount(aid,totalLike);
        //System.out.println("flag对象 " +"flag"+"  总的点赞量="+totalLike);
        return article;
    }

    //文章发表   并且返回插入文章后生成的id号
    @Override
    public Article addArticle(Article article) {
        try {
            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String adate = sim.format(new Date());
            articleDao.insertArticle(article,adate);
            return article;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文章发表失败");
        }
    }
}
