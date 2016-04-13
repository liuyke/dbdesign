package org.liuyk.desgin.model;

import java.io.Serializable;

/**
 * 数据库的连接信息
 * 
 * @author liuyk
 * 
 */
public class DBConnInfo implements Serializable {

	private static final long serialVersionUID = -2765425915876866546L;

	private String host;
	private int port;
	private String username;
	private String password;
	private String dbtype;

	public DBConnInfo(String host, int port, String username, String password,
			String dbtype) {
		super();
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.dbtype = dbtype;
	}

	public DBConnInfo() {
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbtype() {
		return dbtype;
	}

	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}

}
