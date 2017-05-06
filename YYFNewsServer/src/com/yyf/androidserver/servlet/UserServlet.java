package com.yyf.androidserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yyf.androidserver.bean.ResultBean;
import com.yyf.androidserver.bean.UserBean;
import com.yyf.androidserver.iservice.IUserService;
import com.yyf.androidserver.service.UserService;
import com.yyf.androidserver.util.GsonUtil;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");

		// 1.登录
		if (action.equals("login")) {

			String userName = request.getParameter("userName");
			String password = request.getParameter("password");

			IUserService userService = new UserService();
			ResultBean<UserBean> result = userService.login(userName, password);

			out.print(GsonUtil.toGson(result));
			out.close();
			return;
		}

		// 2.修改密码
		if (action.equals("resetPassword")) {

			String telephone = request.getParameter("telephone");
			String newPassword = request.getParameter("newPassword");

			IUserService userService = new UserService();
			ResultBean<Integer> result = userService.resetPassword(telephone,
					newPassword);

			out.print(GsonUtil.toGson(result));
			out.close();
			return;
		}

	}

}
