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
			UserBean user = null;
			if (rs.next()) {
				user = new UserBean();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setProfilePhotoURL(rs.getString("ProfilePhotoURL"));
				user.setDescription(rs.getString("description"));
				user.setTelephone(rs.getString("telephone"));
			}
			result.code = ResultBean.CODE_QUERY_SUCCESS;
			result.msg = ResultBean.MSG_QUERY_SUCCESS;
			result.data = user;
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

	@Override
	public ResultBean<UserBean> login(String telephone,
			String password) {

		ResultBean<UserBean> result = new ResultBean<UserBean>();
		Connection conn = ConnUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = " SELECT * FROM user where (userName = ? or telephone = ?) and password = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, telephone);
			preparedStatement.setString(2, telephone);
			preparedStatement.setString(3, password);
			rs = preparedStatement.executeQuery();
			UserBean user = null;
			if (rs.next()) {
				user = new UserBean();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setProfilePhotoURL(rs.getString("ProfilePhotoURL"));
				user.setDescription(rs.getString("description"));
				user.setTelephone(rs.getString("telephone"));
			}
			result.code = ResultBean.CODE_QUERY_SUCCESS;
			result.msg = ResultBean.MSG_QUERY_SUCCESS;
			result.data = user;
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

	@Override
	public ResultBean<Integer> resetPassword(String telephone,
			String newPassword) {

		ResultBean<Integer> result = new ResultBean<Integer>();
		Connection conn = ConnUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "update user set password = ? where telephone = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, newPassword);
			preparedStatement.setString(2, telephone);
			int count = preparedStatement.executeUpdate();
			result.code = ResultBean.CODE_UPDATE_SUCCESS;
			result.msg = ResultBean.MSG_UPDATE_SUCCESS;
			result.data = count;
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

	@Override
	public ResultBean<Integer> register(UserBean user) {

		ResultBean<Integer> result = new ResultBean<Integer>();
		Connection conn = ConnUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "insert into user(telephone,userName,password,description,profilePhotoURL) values(?,?,?,?,?)";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, user.getTelephone());
			preparedStatement.setString(2, user.getUserName());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getDescription());
			preparedStatement.setString(5, user.getProfilePhotoURL());
			int count = preparedStatement.executeUpdate();
			result.code = ResultBean.CODE_INSERT_SUCCESS;
			result.msg = ResultBean.MSG_INSERT_SUCCESS;
			result.data = count;
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

	@Override
	public ResultBean<Boolean> isTelephoneExisted(int id, String telephone) {

		ResultBean<Boolean> result = new ResultBean<Boolean>();
		Connection conn = ConnUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			String sql = " SELECT * FROM user where telephone = ? and id <> ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, telephone);
			preparedStatement.setInt(2, id);
			rs = preparedStatement.executeQuery();
			Boolean isExisted = false;
			if (rs.next()) {
				isExisted = true;

			}
			result.code = ResultBean.CODE_IS_EXSITED;
			result.msg = ResultBean.MSG_IS_EXSITED;
			result.data = isExisted;
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
