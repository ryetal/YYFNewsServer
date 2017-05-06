package com.yyf.androidserver.iservice;

import java.util.List;

import com.yyf.androidserver.bean.CommentBean;
import com.yyf.androidserver.bean.ResultBean;

public interface ICommentService {

	ResultBean<List<CommentBean>> getComments(int newsId, String offset,
			String size, String sSortName, String sSortOrder);

}
