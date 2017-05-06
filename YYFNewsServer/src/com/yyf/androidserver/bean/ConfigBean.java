package com.yyf.androidserver.bean;

import java.util.Properties;

public class ConfigBean {

	private String configName; // 配置文件名
	private Properties props; // 配置文件内容

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

}
