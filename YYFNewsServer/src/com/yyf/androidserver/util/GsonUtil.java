package com.yyf.androidserver.util;

import com.google.gson.Gson;

public class GsonUtil {

	public static String toGson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
}
