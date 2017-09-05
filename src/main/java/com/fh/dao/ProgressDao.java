package com.fh.dao;

import com.fh.dto.ProgressQuery;
import com.fh.model.Progress;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface ProgressDao {

	PageList<Progress> selectForPage(ProgressQuery query, PageBounds buildPageBounds);

}
