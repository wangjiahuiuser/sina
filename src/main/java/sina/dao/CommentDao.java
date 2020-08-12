package sina.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import sina.domain.Comment;

import java.util.List;

@Repository("commentDao")
public interface CommentDao {
    //按条件查询到结果后还得按时间戳的先后排序
    @Select("select * from comment where aid=#{aid} order by cdate limit #{start} , #{commentsSize} ")
    List<Comment> findComments(@Param("aid") int aid,@Param("start") int start,@Param("commentsSize") int commentsSize);


    //插入一条评论
    @Insert("insert into comment (aid,comments,cdate,uid,clikemount) values (#{aid},#{comments},#{cdate},#{uid},#{clikemount})")
    void insertComment(@Param("uid") int uid,@Param("aid") int aid,@Param("comments") String comments,@Param("cdate") String cdate,@Param("clikemount") int clikemount);


}
