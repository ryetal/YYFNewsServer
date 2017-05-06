package com.yyf.androidserver.iservice;

import java.util.List;

import com.yyf.androidserver.bean.NewsBean;
import com.yyf.androidserver.bean.ResultBean;

public interface INewsService {

	ResultBean<List<NewsBean>> getNews(String type, String offset, String size,
			String sSortName, String sSortOrder);

	ResultBean<NewsBean> getNews(int id);

	ResultBean<List<NewsBean>> getNewsByKeyword(String keyword);

}
