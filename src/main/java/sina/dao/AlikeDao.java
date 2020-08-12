package sina.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import sina.domain.Alike;

@Repository("alikeDao")
public interface AlikeDao {

    //判断当前登录用户是否点赞过该页面   bug之一，数据库与实体类没有一个数据对应上时，mybatis会封装不上对象
    @Select("select * from alike where aid=#{aid} and uid=#{uid}")
    Alike isAlike(@Param("aid") int aid,@Param("uid") int uid);

    //文章被点赞的次数
    @Select("select count(*) from alike  where aid=#{aid}")
    int findTotalLike(int aid);

    //修改article表中的点赞数

    //取消文章点赞  删除那条记录
    @Delete("delete from alike where aid=#{aid} and uid=#{uid}")
    void deleteLike(@Param("aid") int aid,@Param("uid") int uid);

    //给文章点赞 增加收藏记录
    @Insert("insert into alike (aid,ldate,uid) values (#{aid} ,#{ldate} ,#{uid})")
    void addLike(@Param("aid")int aid,@Param("uid") int uid,@Param("ldate") String ldate);
}
