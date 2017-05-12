package com.yyf.androidserver.iservice;

import com.yyf.androidserver.bean.ResultBean;
import com.yyf.androidserver.bean.UserBean;

public interface IUserService {

	ResultBean<UserBean> getUser(int id);

	ResultBean<UserBean> login(String telephone, String password);

	ResultBean<Integer> resetPassword(String telephone, String newPassword);

	ResultBean<Integer> register(UserBean user);

	ResultBean<Boolean> isTelephoneExisted(int id, String telephone);

}
