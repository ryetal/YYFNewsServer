package com.yyf.androidserver.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.yyf.androidserver.bean.ConfigBean;

public class ConnUtil {

	private static String mysqlUrl;
	private static String mysqlHost;
	private static String mysqlPort;
	private static String mysqlDBName;
	private static String mysqlUser;
	private static String mysqlPwd;
	private static String mysqlDriver;

	private static final String DB_CONFIG = "DB_Config.properties";

	static {
		ConfigBean dbConfig = ConfigUtil.getConfig(DB_CONFIG);
		Properties props = dbConfig.getProps();
		mysqlUrl = props.getProperty("mysqlUrl");
		mysqlHost = props.getProperty("mysqlHost");
		mysqlPort = props.getProperty("mysqlPort");
		mysqlDBName = props.getProperty("mysqlDBName");
		mysqlUser = props.getProperty("mysqlUser");
		mysqlPwd = props.getProperty("mysqlPwd");
		mysqlDriver = props.getProperty("mysqlDriver");
	}

	public static String getMysqlUrl() {
		return mysqlUrl;
	}

	public static String getMysqlHost() {
		return mysqlHost;
	}

	public static String getMysqlPort() {
		return mysqlPort;
	}

	public static String getMysqlDBName() {
		return mysqlDBName;
	}

	public static String getMysqlUser() {
		return mysqlUser;
	}

	public static String getMysqlPwd() {
		return mysqlPwd;
	}

	public static String getMysqlDriver() {
		return mysqlDriver;
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(mysqlDriver);
			conn = DriverManager.getConnection(mysqlUrl, mysqlUser, mysqlPwd);
		} catch (Exception e) {
			System.out.println(e);
		}
		return conn;
	}

}
