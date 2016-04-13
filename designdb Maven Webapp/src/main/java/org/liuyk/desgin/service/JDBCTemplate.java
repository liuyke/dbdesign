package org.liuyk.desgin.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.liuyk.desgin.model.DBConnInfo;

public abstract class JDBCTemplate {

	public abstract String jdbcUrl(String host);
	
	public Connection openConnection(DBConnInfo connInfo) {
		String url = jdbcUrl(connInfo.getHost());
		try {
			Connection connection = DriverManager.getConnection(url, connInfo.getUsername(), connInfo.getPassword());
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected void close(AutoCloseable... close) {
		if(close != null) {
			for (AutoCloseable closeable : close) {
				try {
					if(closeable != null) closeable.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	protected void execute(Connection conn, String sql) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			close(ps);
		}
	}

	public List<?> queryList(DBConnInfo connInfo, String sql, String schema, Callback<?> callback, Object... args) {
		Connection conn = openConnection(connInfo);
		if(conn == null) {
			throw new IllegalArgumentException("无法连接数据库");
		}
		
		if(schema != null && !"".equals(schema.trim())) {
			execute(conn, "use " + schema);
		}
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			if(args != null) {
				for (int i = 1; i <= args.length; i++) {
					Object object = args[i - 1];
					ps.setObject(i, object);
				}
			}
			rs = ps.executeQuery();
			if(callback != null) {
				return callback.doInResultCallback(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}
		return null;
	}
	
	public interface Callback<T> {
		List<T> doInResultCallback(ResultSet rs) throws SQLException ;
	}
	
	
	public static class StringListCallBack implements Callback<String> {
		@Override
		public List<String> doInResultCallback(ResultSet rs) throws SQLException {
			List<String> list = new ArrayList<String>();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			return list;
		}
	}
	
}
