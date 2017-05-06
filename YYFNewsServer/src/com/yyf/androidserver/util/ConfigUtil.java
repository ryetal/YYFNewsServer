package com.yyf.androidserver.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Properties;

import com.yyf.androidserver.bean.ConfigBean;

public class ConfigUtil {

	private static HashMap<String, ConfigBean> configs = new HashMap<String, ConfigBean>();

	public static ConfigBean getConfig(String configName) {
		if (!configs.containsKey(configName)) {
			ConfigBean config = new ConfigBean();
			try {
				InputStreamReader is;
				is = new InputStreamReader((InputStream) ConfigUtil.class
						.getClassLoader().getResource(configName).getContent(),
						"UTF-8");
				Properties props = new Properties();
				props.load(is);
				is.close();
				config.setConfigName(configName);
				config.setProps(props);
				configs.put(configName, config);
				return config;
			} catch (UnsupportedEncodingException e1) {
			} catch (IOException e) {
			}
			return null;
		} else {
			return configs.get(configName);
		}
	}

}
