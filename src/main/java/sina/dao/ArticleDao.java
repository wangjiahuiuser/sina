package sina.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import sina.domain.Article;
import sina.domain.PageBean;

import java.util.List;



@Repository("articleDao")
public interface ArticleDao {
    //按条件查询文章
    //带有条件的复杂sql语句，可以使用where or and配合使用
    //"select * , DATE_FORMAT ( adate, '%Y-%m-%d %H:%i:%S') as date from article where 1 = 1 and (cid = #{cid} or #{cid}=0) and (#{aword} is null or aword like#{aword}) order by date desc limit #{start} ,#{pageSize}"
    @Select("select *  from article where 1 = 1 and (cid = #{cid} or #{cid}=0) and aword like#{aword} order by adate desc limit #{start} ,#{pageSize}")
    List<Article> findPage(@Param("cid") int cid, @Param("start")int start,@Param("pageSize") int pageSize, @Param("aword") String aword);

    //根据id找一篇文章
    @Select("select * from article where aid =#{aid}")
    Article findOne(int aid);

    //根据用户id找到他所发表的所有文章
    @Select("select *  from article where uid=#{uid} order by adate desc limit #{start} , #{size}")
    List<Article> findOwnArticle(@Param("uid") int uid,@Param("start") int start,@Param("size") int size);

    //寻找热门博文(10条)  先按点赞数排序  相同的话再按发表时间排序
    @Select("select * from article where aimage is not null order by alikemount desc,adate desc limit #{start} , #{size}")
    List<Article> findHotArticle(@Param("start") int start,@Param("size") int size);

    //修改（更新）文章的点赞数
    @Update("update article set alikemount=#{alikemount} where aid=#{aid}")
    void updateAlikemount(@Param("aid") int aid,@Param("alikemount") int alikemount);

    //上传文章图片
    @Update("update article set aimage=#{aimage} where aid=#{aid}")
    void updateAimage(@Param("aid") int aid,@Param("aimage") String aimage);

    //上传文章video
    @Update("update article set avideo=#{avideo} where aid=#{aid}")
    void updateAvideo(@Param("aid") int aid,@Param("avideo") String avideo);

    //增加一条文章记录  并且返回插入的文章aid
    @Insert("insert into article (uid,aword,adate,cid) values (#{article.uid},#{article.aword},#{adate},#{article.cid})")
    @Options(useGeneratedKeys = true,keyColumn = "aid",keyProperty = "article.aid")
    void insertArticle(@Param("article") Article article,@Param("adate") String adate);

}
