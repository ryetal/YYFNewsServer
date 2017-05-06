package com.yyf.androidserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yyf.androidserver.bean.CommentBean;
import com.yyf.androidserver.bean.ResultBean;
import com.yyf.androidserver.iservice.ICommentService;
import com.yyf.androidserver.service.CommentService;
import com.yyf.androidserver.util.GsonUtil;

public class CommentServlet extends HttpServlet {

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

		if (action.equals("getComments")) {

			String newsId = request.getParameter("newsId");
			String offset = request.getParameter("offset");
			String size = request.getParameter("size");
			String sSortName = request.getParameter("id");
			String sSortOrder = request.getParameter("ASC");

			ICommentService commentService = new CommentService();
			ResultBean<List<CommentBean>> result = commentService.getComments(
					Integer.valueOf(newsId), offset, size, sSortName,
					sSortOrder);

			out.print(GsonUtil.toGson(result));
			out.close();
			return;
		}
	}

}
