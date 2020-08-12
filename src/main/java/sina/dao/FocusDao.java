package sina.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import sina.domain.Focus;
import sina.domain.User;

import java.util.List;

@Repository("focusDao")
public interface FocusDao {
    //查询登录用户粉丝数
    @Select("select count(*) from focus where befid =#{befid}")
    int findBeFocusNub(int befid);

    //查询登录用户关注数
    @Select("select count(*) from focus where fid =#{fid}")
    int findFocusNub(int fid);

    //增加一条关注记录
    @Insert("insert into focus (befid,date,fid) values (#{befid},#{date},#{fid})")
    void addOneFocus(@Param("befid") int befid,@Param("date") String date,@Param("fid") int fid);

    //取消一条关注记录
    @Delete("delete from focus where befid=#{befid} and fid=#{fid}")
    void deleteFocus(@Param("befid") int befid,@Param("fid") int fid);

    //返回focus表的关注记录 利用这个集合获得关注者的用户id
    @Select("select * from focus where fid=#{fid}")
    List<Focus> findFocusList(int fid);

    //返回focus表的粉丝记录 利用这个集合获得粉丝的用户id
    @Select("select * from focus where befid=#{befid}")
    List<Focus> findBeFocusList(int befid);


}
