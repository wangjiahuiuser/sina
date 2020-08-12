package sina.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import sina.domain.Favorite;


@Repository("favoriteDao")
public interface FavoriteDao {

    @Select("select * from favorite where aid=#{aid} and uid=#{uid}")
    Favorite isFavorite(@Param("aid") int aid, @Param("uid") int uid);

    @Insert("insert into favorite (aid,fdate,uid) values (#{aid},#{fdate},#{uid})")
    void addFavorite(@Param("aid") int aid,@Param("uid") int uid,@Param("fdate") String fdate);

    @Delete("delete from favorite where aid=#{aid} and uid=#{uid}")
    void deleteFavorite(@Param("aid")int aid,@Param("uid") int uid);
}
