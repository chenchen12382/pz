package com.fh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.fh.model.UpLog;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Created by Administrator on 2017/10/25.
 */
public interface UpLogDao {

    @Select("select id,title,log,create_date,update_date from t_up_log where is_valid = 1" + " order by id ")
    PageList<UpLog> selectAll(PageBounds pageBounds);

    @Insert("insert into t_up_log (title,log,is_valid,create_date,update_date) values " +
            " ( #{title},#{log},1,now(),now() )")
    void insert(UpLog uplog);

    @Update("update t_up_log set title=#{title},log=#{log},update_date=now() where id= #{id} ")
    void update(UpLog uplog);

    @Update("update t_up_log set is_valid = 0 , update_date=now()  where id in (${ids})")
    void deleteBatch(@Param("ids") String ids);

    @Select("select title from t_up_log where title = #{title} and is_valid = 1 ")
    String findByTitle(@Param("title") String title);

    @Select("select id,title,log,create_date,update_date from t_up_log where is_valid=1" + " order by id  ")
    List<UpLog> selectUpLog();

    @Select("select title from t_up_log where is_valid = 1")
    List<String> selectAllUpLog();
}
