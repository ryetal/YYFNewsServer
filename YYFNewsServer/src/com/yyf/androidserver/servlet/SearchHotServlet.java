package com.yyf.androidserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yyf.androidserver.bean.ResultBean;
import com.yyf.androidserver.util.GsonUtil;

public class SearchHotServlet extends HttpServlet {

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

		List<String> datas = new ArrayList<String>();
		datas.add("锅内锅外");
		datas.add("一席地");
		datas.add("生命");
		datas.add("杭州乐园");
		datas.add("鸡排");
		datas.add("宜家");
		datas.add("泰迪");
		datas.add("韩式烤肉");
		datas.add("雾霾");
		datas.add("火锅");
		datas.add("洗浴中心");

		ResultBean<List<String>> result = new ResultBean<List<String>>();
		result.code = ResultBean.CODE_DATASET_NOT_EMPTY;
		result.msg = ResultBean.MSG_DATASET_NOT_EMPTY;
		result.data = datas;

		out.print(GsonUtil.toGson(result));
		out.close();
		return;
	}

}
