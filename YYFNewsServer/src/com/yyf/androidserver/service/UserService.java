package com.yyf.androidserver.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yyf.androidserver.bean.ResultBean;
import com.yyf.androidserver.bean.UserBean;
import com.yyf.androidserver.iservice.IUserService;
import com.yyf.androidserver.util.ConnUtil;

public class UserService implements IUserService {

	@Override
	public ResultBean<UserBean> getUser(int id) {

		ResultBean<UserBean> result = new ResultBean<UserBean>();
		Connection conn = ConnUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = " SELECT * FROM user where id = " + id;
			preparedStatement = conn.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				UserBean user = new UserBean();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setProfilePhotoURL(rs.getString("ProfilePhotoURL"));
				user.setDescription(rs.getString("description"));
				user.setTelephone(rs.getString("telephone"));

				result.code = ResultBean.CODE_SINGLE_HAVE;
				result.msg = ResultBean.MSG_SINGLE_HAVE;
				result.data = user;
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
	public ResultBean<UserBean> login(String userNameOrTelephone,
			String password) {

		ResultBean<UserBean> result = new ResultBean<UserBean>();
		Connection conn = ConnUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = " SELECT * FROM user where (userName = ? or telephone = ?) and password = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, userNameOrTelephone);
			preparedStatement.setString(2, userNameOrTelephone);
			preparedStatement.setString(3, password);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				UserBean user = new UserBean();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setProfilePhotoURL(rs.getString("ProfilePhotoURL"));
				user.setDescription(rs.getString("description"));
				user.setTelephone(rs.getString("telephone"));

				result.code = ResultBean.CODE_SINGLE_HAVE;
				result.msg = ResultBean.MSG_SINGLE_HAVE;
				result.data = user;
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
	public ResultBean<Integer> resetPassword(String telephone, String newPassword) {

		ResultBean<Integer> result = new ResultBean<Integer>();
		Connection conn = ConnUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "update user set password = ? where telephone = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, newPassword);
			preparedStatement.setString(2, telephone);
			int count = preparedStatement.executeUpdate();
			result.code = ResultBean.CODE_UPDATE;
			result.msg = ResultBean.MSG_UPDATE;
			result.data = count;
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
