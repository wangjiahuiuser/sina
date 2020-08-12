package sina.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import sina.domain.UserPhoto;

@Repository("userPhotoDao")
public interface UserPhotoDao {

    //插入默认的背景图片
    @Insert("insert into userphoto (uid,bigphoto,smallphoto) values (#{uid},#{bigphoto},#{smallphoto})")
    void insertDefault(@Param("uid") int uid,@Param("bigphoto") String bigphoto,@Param("smallphoto") String smallphoto);

    //根据用户id 返回其对应的背景图片
    @Select("select * from userphoto where uid=#{uid}")
    UserPhoto findPhoto(int uid);

    //修改背景图片的 大图
    @Update("update userphoto set bigphoto=#{bigphoto} where pid=#{pid}")
    void updateBigphoto(@Param("pid") int pid,@Param("bigphoto") String bigphoto);

    //修改背景图片的 小图
    @Update("update userphoto set smallphoto=#{smallphoto} where pid=#{pid}")
    void updateSmallphoto(@Param("pid") int pid,@Param("smallphoto") String smallphoto);
}
