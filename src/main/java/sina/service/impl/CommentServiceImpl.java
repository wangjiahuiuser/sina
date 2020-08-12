package sina.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sina.dao.CommentDao;
import sina.dao.UserDao;
import sina.domain.Comment;
import sina.domain.CommentsBean;
import sina.domain.User;
import sina.service.CommentService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Qualifier("commentDao")
    @Autowired
    CommentDao commentDao;
    @Qualifier("userDao")
    @Autowired
    UserDao userDao;
    @Autowired
    CommentsBean<Comment> commentsBean;

    @Override
    public CommentsBean<Comment> findComments(int currentPart, int aid) {
        int commentsSize=10;
        int start=(currentPart-1)*commentsSize;
        System.out.println("start="+start+"   currentPart="+currentPart);
        commentsBean.setCurrentPart(currentPart);
        List<Comment> list = commentDao.findComments(aid, start, commentsSize);
        List<Comment> list1 =new ArrayList<Comment>();
        for (Comment comment:list) {
            User user = userDao.findUserByUid(comment.getUid());
            user.setPassword("***");
            comment.setUser(user);
            list1.add(comment);
            System.out.println("每个comment "+comment);
        }
        commentsBean.setList(list1);
        return commentsBean;
    }

    @Override
    public String insertComment( int uid,int aid,String comments) {
        try {
            int clikemount=0;
            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String cdate = sim.format(new Date());
            commentDao.insertComment(uid,aid,comments,cdate,clikemount);
            return "评论成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "评论失败";
        }
    }
}
