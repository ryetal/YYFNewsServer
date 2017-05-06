package com.yyf.androidserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yyf.androidserver.bean.NewsBean;
import com.yyf.androidserver.bean.ResultBean;
import com.yyf.androidserver.iservice.INewsService;
import com.yyf.androidserver.service.NewsService;
import com.yyf.androidserver.util.GsonUtil;

public class NewsServlet extends HttpServlet {

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

		if (action.equals("getNews")) {

			String type = request.getParameter("type");
			String offSet = request.getParameter("offset");
			String size = request.getParameter("size");
			String sSortName = request.getParameter("id");
			String sSortOrder = request.getParameter("ASC");

			INewsService newsService = new NewsService();
			ResultBean<List<NewsBean>> result = newsService.getNews(type,
					offSet, size, sSortName, sSortOrder);

			out.print(GsonUtil.toGson(result));
			out.close();
			return;
		}

		if (action.equals("getNewsByKeyword")) {

			String keyword = request.getParameter("keyword");

			INewsService itemService = new NewsService();
			ResultBean<List<NewsBean>> result = itemService
					.getNewsByKeyword(keyword);

			out.print(GsonUtil.toGson(result));
			out.close();
			return;
		}

	}

}
