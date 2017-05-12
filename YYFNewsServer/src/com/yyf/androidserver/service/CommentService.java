package com.yyf.androidserver.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yyf.androidserver.bean.CommentBean;
import com.yyf.androidserver.bean.NewsBean;
import com.yyf.androidserver.bean.ResultBean;
import com.yyf.androidserver.bean.UserBean;
import com.yyf.androidserver.iservice.ICommentService;
import com.yyf.androidserver.iservice.INewsService;
import com.yyf.androidserver.iservice.IUserService;
import com.yyf.androidserver.util.ConnUtil;

public class CommentService implements ICommentService {

	@Override
	public ResultBean<List<CommentBean>> getComments(int newsId, String offset,
			String size, String sSortName, String sSortOrder) {

		ResultBean<List<CommentBean>> result = new ResultBean<List<CommentBean>>();

		List<CommentBean> listComment = new ArrayList<CommentBean>();
		Connection conn = ConnUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = " SELECT * FROM comment where newsId = " + newsId;
			if (sSortName != null && sSortOrder != null) {
				sql = sql + " ORDER BY " + sSortName + " " + sSortOrder;
			}
			if (offset != null && size != null) {
				sql = sql + " LIMIT " + offset + "," + size;
			}
			preparedStatement = conn.prepareStatement(sql);
			rs = preparedStatement.executeQuery();

			INewsService newsService = new NewsService();
			NewsBean news = newsService.getNews(newsId).data;
			IUserService userServive = new UserService();
			while (rs.next()) {
				CommentBean comment = new CommentBean();
				comment.setId(rs.getInt("id"));
				comment.setComment(rs.getString("comment"));
				comment.setDate(rs.getString("date"));
				comment.setNews(news);

				UserBean user = userServive.getUser(rs.getInt("userId")).data;
				comment.setUser(user);

				listComment.add(comment);
			}

			if (listComment.size() == 0) {
				listComment = null;
			}
			result.code = ResultBean.CODE_QUERY_SUCCESS;
			result.msg = ResultBean.MSG_QUERY_SUCCESS;
			result.data = listComment;

		} catch (Exception e) {
			e.printStackTrace();

			result.code = ResultBean.CODE_SQL_OPERATOR_ERROR;
			result.msg = ResultBean.MSG_SQL_OPERATOR_ERROR;
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
