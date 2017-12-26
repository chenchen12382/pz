package com.fh.dao;

import java.util.List;

import com.fh.model.Report;

import com.fh.dto.AnalyzeTotalQuery;
import com.fh.model.AnalyzeTotal;


public interface AnalyzeTotalDao {
	 List<String> selectAllCenter();
	 List<AnalyzeTotal> selectForPage(AnalyzeTotalQuery query);


	List<Report> selectCenterToDD();
}
