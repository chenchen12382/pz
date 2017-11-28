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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/9/14.
 */
@Service
public class CenterService {

    @Autowired
    private CenterDao centerDao;


    public Map<String, Object> selectForPage(BaseQuery query) {

        PageList<Center> centers = centerDao.selectAll(query.buildPageBounds());
        Map<String, Object> result = new HashMap<>();
        result.put("rows", centers);
        result.put("total", centers.getPaginator().getTotalCount());
        return result;


    }

    public void insert(Center center) {
        AssertUtil.isNotEmpty(center.getCenter(), "请输入中心");
        Integer temp = centerDao.findByCenter(center.getCenter());
        if (temp != 0) {
            throw new ParamException("您输入的中心已存在！请检查后输入");
        }

//        AssertUtil.intIsNotEmpty(temp,"您输入的中心已存在！请检查后输入！");

        centerDao.insert(center);

    }

    public void update(Center center) {
        AssertUtil.isNotEmpty(center.getCenter(), "请选择中心");
        centerDao.update(center);
    }

    public void deleteBatch(String ids) {
        AssertUtil.isNotEmpty(ids, "请选择记录进行删除");
        centerDao.deleteBatch(ids);
    }

    public Map<String, Object> selectAll() {
        List<Center> centers = centerDao.selectCenter();
        Map<String, Object> result = new HashMap<>();
        result.put("rows", centers);
        return result;
    }

    public void readExcel( MultipartFile xybh, MultipartFile sjbh,MultipartFile sjbh_lbs, ProtocolNum protocolNum) {

        HSSFWorkbook workbook = null;
        List<ProtocolNum> protocolNums = new ArrayList<>();
        Integer centerId = protocolNum.getCenterId();
        try {

            if (xybh != null) {
                workbook = new HSSFWorkbook(xybh.getInputStream());
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
                                HSSFCell xybhRead = row.getCell(j);
                                xybhRead.setCellType(HSSFCell.CELL_TYPE_STRING);
                                String cellValue = xybhRead.getStringCellValue();
//                                if(cellValue.length()!=13){
//                                    throw new ParamException("协议编号"+cellValue +" 不是13位  请检查");
//                                }
                                Pattern pattern = Pattern.compile("PZ[0-9]{11}");
                                if(!pattern.matcher(cellValue).matches()){
                                    throw new ParamException("只能以大写PZ开头并包含11位数字,不能包含其它符号");
                                }
                                protocolNum.setXybh(cellValue);
                                Integer count = centerDao.findXybh(protocolNum.getXybh());
                                if (count != 0) {
                                    throw new ParamException("\" 协议编号" + protocolNum.getXybh() + "\"  已存在,请检查!");
                                }
                                protocolNums.add(protocolNum);
                                protocolNum=new ProtocolNum();
                                protocolNum.setCenterId(centerId);
//                                centerDao.insertXybh(protocolNum);
                            }
                        }
                    }
                }

                centerDao.insertXybh(protocolNums);
            } else if (xybh == null && sjbh != null&&sjbh_lbs==null) {

                workbook = new HSSFWorkbook(sjbh.getInputStream());
                HSSFSheet sheet = workbook.getSheetAt(0);
                for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {// getLastRowNum，获取最后一行的行标
                    HSSFRow row = sheet.getRow(i);
                    if (row != null) {
                        for (int j = 0; j < row.getLastCellNum(); j++) {// getLastCellNum，是获取最后一个不为空的列是第几个
                            if (row.getCell(j) != null) { // getCell 获取单元格数据
                                HSSFCell sjbhRead = row.getCell(j);
                                sjbhRead.setCellType(HSSFCell.CELL_TYPE_STRING);
                                String cellValue = sjbhRead.getStringCellValue();
//                                if(cellValue.length()!=9){
//                                    throw new ParamException("收据编号"+cellValue +" 不是9位  请检查");
//                                }
                                Pattern pattern = Pattern.compile("PZ[0-9]{7}");
                                if(!pattern.matcher(cellValue).matches()){
                                   throw new ParamException("只能以大写PZ开头并包含7位数字,不能包含其它符号");
                                }

                                protocolNum.setSjbh(cellValue);
                                Integer count = centerDao.findSjbh(protocolNum.getSjbh());
                                if (count != 0) {
                                    throw new ParamException("\" 收据编号 " + protocolNum.getSjbh() + "\"  已存在,请检查!");
                                }
                                protocolNums.add(protocolNum);
                                protocolNum=new ProtocolNum();
                                protocolNum.setCenterId(centerId);

                            }
                        }
                    }
                }

                centerDao.insertSjbh(protocolNums);
            }else if(xybh == null && sjbh == null&&sjbh_lbs!=null){
                workbook = new HSSFWorkbook(sjbh_lbs.getInputStream());
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
                                HSSFCell xybhRead = row.getCell(j);
                                xybhRead.setCellType(HSSFCell.CELL_TYPE_STRING);
                                String cellValue = xybhRead.getStringCellValue();
//                                if(cellValue.length()!=13){
//                                    throw new ParamException("乐博士协议编号"+cellValue +" 不是13位  请检查");
//                                }
                                Pattern pattern = Pattern.compile("PL[0-9]{11}");
                                if(!pattern.matcher(cellValue).matches()){
                                    throw new ParamException("只能以大写PZ开头并包含11位数字,不能包含其它符号");
                                }
                                protocolNum.setXybh(cellValue);
                                Integer count = centerDao.findXybhLbs(protocolNum.getXybh());
                                if (count != 0) {
                                    throw new ParamException("\" 乐博士协议编号" + protocolNum.getXybh() + "\"  已存在,请检查!");
                                }
                                protocolNums.add(protocolNum);
                                protocolNum=new ProtocolNum();
                                protocolNum.setCenterId(centerId);
//                                centerDao.insertXybh(protocolNum);
                            }
                        }
                    }
                }

                centerDao.insertXybhLbs(protocolNums);

            }else{
                throw  new ParamException("参数错误，请联系管理员！");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ParamException("文件有误,只能上传xls格式的excel文件!");

        }
    }
}