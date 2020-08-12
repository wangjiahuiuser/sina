package sina.service;

import sina.domain.Comment;
import sina.domain.CommentsBean;

public interface CommentService {

    CommentsBean<Comment> findComments(int currentPart,int aid);

    String insertComment( int uid,int aid,String comments);
}
