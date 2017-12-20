package com.fh.dao;

import com.fh.base.BaseQuery;
import com.fh.model.PrintContract;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Administrator on 2017/12/20.
 */
public interface PrintContractDao {

    @Insert("INSERT INTO t_print_contract (" +
            "center_name," +
            "contract_type," +
            "f_name," +
            "f_phone," +
            "f_work," +
            "m_name," +
            "m_phone," +
            "m_work," +
            "b_name," +
            "b_nike_name," +
            "b_birthday," +
            "b_sex," +
            "class_num," +
            "start_date," +
            "end_date," +
            "give_class," +
            "give_ticket," +
            "create_date," +
            "update_date," +
            "is_valid" +
            ")values(#{centerName},#{contractType},#{fName},#{fPhone},#{fWork},#{mName}" +
            ",#{mPhone},#{mWork},#{bName},#{bNikeName},#{bBirthday},#{bSex},#{classNum},#{startDate},#{endDate}" +
            ",#{giveClass},#{giveTicket},now(),now(),1 )")
    void insert(PrintContract printContract);

    @Select("select center_name," +
            "contract_type," +
            " f_name," +
            " f_phone," +
            " f_work," +
            " m_name," +
            " m_phone," +
            " m_work," +
            " b_name," +
            " b_nike_name," +
            " b_birthday," +
            " b_sex," +
            " class_num," +
            " start_date," +
            " end_date," +
            " give_class," +
            " give_ticket," +
            " create_date," +
            " update_date," +
            "is_valid  from  t_print_contract where is_valid=1")
    PageList<PrintContract> selectForPage(BaseQuery query, PageBounds pageBounds);
}
