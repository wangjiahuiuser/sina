package sina.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import sina.domain.User;

import java.util.List;

@Repository("userDao")
public interface UserDao {

    //根据电话号码查询用户
    @Select("select * from user where telephone =#{telephone}")
    User findUserByTelephone(String telephone);

    //保存用户
    //并且要求返回插入一条数据后生成的 id号
    @Insert("insert into user (username,telephone,password,email,uimage,introduce) values (#{username},#{telephone},#{password},#{email},#{uimage},#{introduce})")
    @Options(useGeneratedKeys = true,keyColumn = "uid",keyProperty = "uid")
    void saveUser(User user);

    //查询所有用户
    @Select("select * from user")
    List<User> findAll();

    //查询电话和密码是否存在
    //查询语句传入多个参数时需要用@Param指定参数名称
    @Select("select * from user where telephone =#{telephone} and password=#{password}")
    User findByTelAndPassword(@Param("telephone") String telephone, @Param("password") String password);

    //根据uid查询查询用户
    @Select("select * from user where uid =#{uid}")
    User findUserByUid(int uid);

    //修改个人简介
    @Update("update user set introduce=#{introduce} where uid=#{uid}")
    void updateIntroduce(@Param("introduce") String introduce,@Param("uid") int uid);

    //修改用户名
    @Update("update user set username=#{username} where uid=#{uid}")
    void updateUsername(@Param("username") String username,@Param("uid") int uid);

    //修改个人照片
    @Update("update user set uimage=#{uimage} where uid=#{uid}")
    void updateUimage(@Param("uid") int uid,@Param("uimage") String uimage);


}
