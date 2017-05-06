package com.yyf.androidserver.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yyf.androidserver.bean.NewsBean;
import com.yyf.androidserver.bean.ResultBean;
import com.yyf.androidserver.iservice.INewsService;
import com.yyf.androidserver.util.ConnUtil;

public class NewsService implements INewsService {

	public ResultBean<List<NewsBean>> getNews(String type, String offset,
			String size, String sSortName, String sSortOrder) {

		ResultBean<List<NewsBean>> result = new ResultBean<List<NewsBean>>();

		List<NewsBean> listNews = new ArrayList<NewsBean>();
		Connection conn = ConnUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = " SELECT * FROM news where type = " + type;
			if (sSortName != null && sSortOrder != null) {
				sql = sql + " ORDER BY " + sSortName + " " + sSortOrder;
			}
			if (offset != null && size != null) {
				sql = sql + " LIMIT " + offset + "," + size;
			}
			preparedStatement = conn.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NewsBean news = new NewsBean();
				news.setId(rs.getInt("id"));
				news.setUrl(rs.getString("url"));
				news.setTitle(rs.getString("title"));
				news.setSource(rs.getString("source"));
				news.setCommentCount(rs.getInt("commentCount"));
				news.setDate(rs.getString("date"));
				news.setImage1(rs.getString("image1"));
				news.setImage2(rs.getString("image2"));
				news.setImage3(rs.getString("image3"));
				listNews.add(news);
			}

			if (listNews.size() == 0) {
				result.code = ResultBean.CODE_DATASET_EMPTY;
				result.msg = ResultBean.MSG_DATASET_EMPTY;
				result.data = null;
			} else {
				result.code = ResultBean.CODE_DATASET_NOT_EMPTY;
				result.msg = ResultBean.MSG_DATASET_NOT_EMPTY;
				result.data = listNews;
			}

		} catch (Exception e) {
			e.printStackTrace();

			result.code = ResultBean.CODE_ERROR;
			result.msg = ResultBean.MSG_ERROR;
			result.data = null;
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
		return result;
	}

	@Override
	public ResultBean<NewsBean> getNews(int id) {

		ResultBean<NewsBean> result = new ResultBean<NewsBean>();

		NewsBean news = null;
		Connection conn = ConnUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = " SELECT * FROM news where id = " + id;
			preparedStatement = conn.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				news = new NewsBean();
				news.setId(rs.getInt("id"));
				news.setUrl(rs.getString("url"));
				news.setTitle(rs.getString("title"));
				news.setSource(rs.getString("source"));
				news.setCommentCount(rs.getInt("commentCount"));
				news.setDate(rs.getString("date"));
				news.setImage1(rs.getString("image1"));
				news.setImage2(rs.getString("image2"));
				news.setImage3(rs.getString("image3"));

				result.code = ResultBean.CODE_SINGLE_HAVE;
				result.msg = ResultBean.MSG_SINGLE_HAVE;
				result.data = news;
			} else {
				result.code = ResultBean.CODE_SINGLE_NOT_HAVE;
				result.msg = ResultBean.MSG_SINGLE_NOT_HAVE;
				result.data = null;
			}

		} catch (Exception e) {
			e.printStackTrace();

			result.code = ResultBean.CODE_ERROR;
			result.msg = ResultBean.MSG_ERROR;
			result.data = null;
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
		return result;
	}

	@Override
	public ResultBean<List<NewsBean>> getNewsByKeyword(String keyword) {

		ResultBean<List<NewsBean>> result = new ResultBean<List<NewsBean>>();

		List<NewsBean> listNews = new ArrayList<NewsBean>();
		Connection conn = ConnUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = " SELECT * FROM news where title like ? ";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, "%" + keyword + "%");
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NewsBean news = new NewsBean();
				news.setId(rs.getInt("id"));
				news.setUrl(rs.getString("url"));
				news.setTitle(rs.getString("title"));
				news.setSource(rs.getString("source"));
				news.setCommentCount(rs.getInt("commentCount"));
				news.setDate(rs.getString("date"));
				news.setImage1(rs.getString("image1"));
				news.setImage2(rs.getString("image2"));
				news.setImage3(rs.getString("image3"));
				listNews.add(news);
			}

			if (listNews.size() == 0) {
				result.code = ResultBean.CODE_DATASET_EMPTY;
				result.msg = ResultBean.MSG_DATASET_EMPTY;
				result.data = null;
			} else {
				result.code = ResultBean.CODE_DATASET_NOT_EMPTY;
				result.msg = ResultBean.MSG_DATASET_NOT_EMPTY;
				result.data = listNews;
			}

		} catch (Exception e) {
			e.printStackTrace();

			result.code = ResultBean.CODE_ERROR;
			result.msg = ResultBean.MSG_ERROR;
			result.data = null;
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
		return result;
	}

}
