package com.fh.dao;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.fh.model.ProtocolNum;
import com.fh.model.UpLog;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Created by Administrator on 2017/11/9.
 */
public interface ProtocolNumDao {

    @Select("select id, center_id,sjbh,create_date,update_date from t_sjbh where is_valid = 1" + " order by id ")
    PageList<ProtocolNum> selectAll1(PageBounds pageBounds);
    
    @Select("select id, center_id,xybh,create_date,update_date from t_xybh where is_valid = 1" + " order by id ")
    PageList<ProtocolNum> selectAll2(PageBounds pageBounds);

    /*
    @Insert("insert into t_sjbh (center_id,sjbh,is_valid,create_date,update_date) values " +
            " ( #{centerId},#{sjbh},1,now(),now() )")
    void insert1(ProtocolNum protocolNum);
    @Insert("insert into t_sjbh (center_id,sjbh,is_valid,create_date,update_date) values " +
            " ( #{centerId},#{sjbh},1,now(),now() )")
    void insert2(ProtocolNum protocolNum);
    @Insert("insert into t_xybh (center_id,xybh,is_valid,create_date,update_date) values " +
            " ( #{centerId},#{xybh},1,now(),now() )")*/
  /*  
    @Update("update t_sjbh set center_id=#{centerId},sjbh=#{sjbh},update_date=now() where id= #{id} ")
    void update1(ProtocolNum protocolNum);
    @Update("update t_xybh set center_id=#{centerId},xybh=#{xybh},update_date=now() where id= #{id} ")
    void update2(ProtocolNum protocolNum);*/
    
    

    @Update("update t_sjbh set is_valid = 0 , update_date=now()  where id in (${ids})")
    void deleteBatch1(@Param("ids") String ids);
    @Update("update t_xybh set is_valid = 0 , update_date=now()  where id in (${ids})")
    void deleteBatch2(@Param("ids") String ids);

        
    @Select("select id,center_id,sjbh,create_date,update_date from t_sjbh where is_valid=1" + " order by id  ")
    List<ProtocolNum> selectSjbh();
    @Select("select id,center_id,xybh,create_date,update_date from t_xybh where is_valid=1" + " order by id  ")
    List<ProtocolNum> selectXybh();
    
    
    
    
  /*    
    @Select("select sjbh from t_sjbh where sjbh = #{sjbh} and is_valid = 1 ")
    String findBySjbh(@Param("sjbh") String sjbh); 
    @Select("select sjbh from t_xybh where xybh = #{xybh} and is_valid = 1 ")
    String findByXybh(@Param("sjbh") String xybh);*/
}
