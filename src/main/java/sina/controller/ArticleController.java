package sina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sina.domain.*;
import sina.service.ArticleService;
import sina.service.AlikeService;
import sina.service.CommentService;
import sina.service.FavoriteService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller("articleController")
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private AlikeService alikeService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ResultInfo info;
    //返回10篇文章
    @RequestMapping("/pageQuery")
    @ResponseBody
    public PageBean<Article> pageQuery(String currentPage,String cid, String aword,HttpSession session){
        //使用springMvc接收字符串，即使什么都不传递，也会返回空字符串"" 所以aword为null情况不出现
        System.out.println("article的返回文章方法 pageQuery");
        System.out.println("currentPage="+currentPage+"  cid="+cid+"  aword="+aword);
        int cidNub=1;
        int currentPageNub=1;
        if (currentPage != null && currentPage.length() > 0 && !"null".equals(currentPage)) {
            currentPageNub = Integer.parseInt(currentPage);
        }
        if (cid != null && cid.length() > 0&& !"null".equals(cid)) {
            cidNub = Integer.parseInt(cid);
        }
        User user = (User)session.getAttribute("user");
        System.out.println("currentPageNub="+currentPageNub+"  cidNub="+cidNub);
        PageBean<Article> pageBean;
        if(user==null) {  //没登录不能看到点赞和收藏与否的状态
            pageBean = articleService.findPage(currentPageNub, cidNub, aword, 0);
        }else {
            pageBean = articleService.findPage(currentPageNub, cidNub, aword, user.getUid());
        }
        return pageBean;
    }

    @RequestMapping("/findDetailOne")
    @ResponseBody
    public Article findDetailOne(int aid,String currentPart ,HttpSession session){
        System.out.println("article的查询文章详细内容方法 findDetailOne");
        System.out.println("currentPart="+currentPart+"  aid="+aid);
        int currentPartNub=1;
        if (currentPart != null && currentPart.length() > 0 && !"null".equals(currentPart)) {
            currentPartNub = Integer.parseInt(currentPart);
        }
        User user = (User)session.getAttribute("user");
        Article article=null;
        if(user==null){
            article = articleService.findOneArticle(aid,0,currentPartNub);
        }else {
            article = articleService.findOneArticle(aid,user.getUid(),currentPartNub);
        }
        return article;
    }

    //根据用户id和当前页面码返回个人发表的文章
    @RequestMapping("/findOwnArticle")
    @ResponseBody
    public List<Article> findOwnArticle(int uid, String currentNub){
        System.out.println("article的查询个人发表的文章方法 findOwnArticle");
        int currentPage=1;
        System.out.println("uid为"+uid+"   currentNub为"+currentNub);
         if(currentNub!=null&&currentNub.length()>0){
             currentPage=Integer.parseInt(currentNub);
         }
         List<Article> articles=articleService.findOwnArticle(uid,currentPage);
         return articles;
    }

    //寻找热门博文(10条)   先按点赞数排序  相同的话再按发表时间排序
    @RequestMapping("/findHotArticle")
    @ResponseBody
    public List<Article> findHotArticle(){
        System.out.println("article的查询热门文章方法 findHotArticle");
        List<Article> articles=articleService.findHotArticle();
        return articles;
    }


    //改变用户点赞的状态  status为1代表点过赞了   status为0代表没点过赞
    @RequestMapping("/changeLikeStatus")
    @ResponseBody
    public ResultInfo changeLikeStatus(int status,int aid,HttpSession session){
        System.out.println("article的改变点赞状态方法 changeLikeStatus");
        User user = (User)session.getAttribute("user");
        String word = alikeService.changeStatus(status, aid, user.getUid());
        info.setErrorMsg(word);
        return info;
    }

    @RequestMapping("/changeFavStatus")
    @ResponseBody
    public ResultInfo changeFavStatus(int status,int aid,HttpSession session){
        System.out.println("article的改变收藏状态方法 changeFavStatus");
        User user = (User)session.getAttribute("user");
        String word = favoriteService.changeStatus(status, aid, user.getUid());
        info.setErrorMsg(word);
        return info;
    }

    //返回10条评论
    @RequestMapping("/commentQuery")
    @ResponseBody
    public CommentsBean<Comment> commentQuery(String currentPart,int aid){
        System.out.println("article的返回评论方法 commentQuery");
        System.out.println("currentPart="+currentPart+"  aid="+aid);
        int currentPartNub=1;
        if (currentPart != null && currentPart.length() > 0 && !"null".equals(currentPart)) {
            currentPartNub = Integer.parseInt(currentPart);
        }
        //User user=(User) session.getAttribute("user");
        CommentsBean<Comment> comments = commentService.findComments(currentPartNub, aid);
        return comments;
    }

    //增加一条评论 @RequestBody将传来的json数据封装到comment对象中去
    @RequestMapping("/addComment")
    @ResponseBody
    public ResultInfo addComment(@RequestBody Comment comment, HttpSession session){
        System.out.println("article的添加评论方法 addComment");
        User user = (User)session.getAttribute("user");
        System.out.println("aid为"+comment.getAid()+"  comments评论的文字"+comment.getComments());
        String msg=commentService.insertComment(user.getUid(),comment.getAid(),comment.getComments());
        info.setErrorMsg(msg);
        return info;
    }

    //文章上传
    // post请求  不能直接使用参数名称接收前台的参数 使用实体类加上@RequestBody将参数封装到对象
    @RequestMapping("/addArticle")
    @ResponseBody
    public Integer addArticle(@RequestBody Article article){
        System.out.println("article类的  addArticle方法");
        System.out.println("aword为    "+article.getAword()+'\n'+"uid为" +article.getUid()+"  cid为"+article.getCid());
        article= articleService.addArticle(article);
        return article.getAid();
    }


/*    public int loginId(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user =(User)request.getSession().getAttribute("user");
        System.out.println("loginId方法的user"+user);
        if(user!=null){
            return user.getUid();
        }else {
            System.out.println("用户未登录");
            throw new RuntimeException("用户未登录");
        }
    }*/
}
