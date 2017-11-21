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

    

    @Update("update t_sjbh set is_valid = 0 , update_date=now()  where id in (${ids})")
    void deleteBatchSjbh(@Param("ids") String ids);
    
    @Update("update t_xybh set is_valid = 0 , update_date=now()  where id in (${ids})")
    void deleteBatchXybh(@Param("ids") String ids);

        
    @Select("select id,center_id,sjbh,create_date,update_date from t_sjbh where is_valid=1" + " order by id  ")
    List<ProtocolNum> selectSjbh();
    @Select("select id,center_id,xybh,create_date,update_date from t_xybh where is_valid=1" + " order by id  ")
    List<ProtocolNum> selectXybh();

    @Select("select id,center_id,xybh,create_date,update_date from t_xybh where is_valid=1 and center_id=#{centerId}")
    List<ProtocolNum> selectXybhById(@Param("centerId") Integer centerId);
}
