package com.fh.service;

import com.fh.base.AssertUtil;
import com.fh.base.BaseQuery;
import com.fh.dao.CenterDao;
import com.fh.exception.ParamException;
import com.fh.model.Center;
import com.fh.model.ProtocolNum;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/14.
 */
@Service
public class CenterService {

    @Autowired
    private CenterDao centerDao;


    public  Map<String,Object> selectForPage(BaseQuery query) {

        PageList<Center>  centers = centerDao.selectAll(query.buildPageBounds());
           Map<String,Object> result = new HashMap<>();
           result.put("rows",centers);
           result.put("total",centers.getPaginator().getTotalCount());
           return  result;



    }

    public void insert(Center center) {
        AssertUtil.isNotEmpty(center.getCenter(),"请输入中心");
        Integer temp = centerDao.findByCenter(center.getCenter());
        if(temp != 0 ){
            throw  new ParamException("您输入的中心已存在！请检查后输入");
        }

//        AssertUtil.intIsNotEmpty(temp,"您输入的中心已存在！请检查后输入！");

        centerDao.insert(center);

    }

    public void update(Center center) {
        AssertUtil.isNotEmpty(center.getCenter(),"请选择中心");
        centerDao.update(center);
    }

    public void deleteBatch(String ids) {
        AssertUtil.isNotEmpty(ids,"请选择记录进行删除");
        centerDao.deleteBatch(ids);
    }

    public Map<String,Object> selectAll() {
        List<Center> centers=centerDao.selectCenter();
        Map<String,Object> result = new HashMap<>();
        result.put("rows",centers);
        return result;
    }

    public void readExcel(@RequestParam(value = "excelFile")MultipartFile file, ProtocolNum protocolNum) {

        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(file.getInputStream());
//            workbook = new HSSFWorkbook(new FileInputStream(new File("E:/2.xls")));
            HSSFSheet sheet = workbook.getSheetAt(0);
//            List<ProtocolNum> xybh = new ArrayList<>();

//            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
//                sheet = workbook.getSheetAt(i);
                for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {// getLastRowNum，获取最后一行的行标
                    HSSFRow row = sheet.getRow(i);
                    if (row != null) {
                        for (int j = 0; j < row.getLastCellNum(); j++) {// getLastCellNum，是获取最后一个不为空的列是第几个
                            if (row.getCell(j) != null) { // getCell 获取单元格数据
//                                System.out.print(row.getCell(k) + "\t");
                                HSSFCell xybh = row.getCell(j);
                                xybh.setCellType(HSSFCell.CELL_TYPE_STRING);
                                String cellValue = xybh.getStringCellValue();
                                protocolNum.setXybh(cellValue);
                                centerDao.insertXybh(protocolNum);

                            }
                        }
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
