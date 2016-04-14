package org.liuyk.desgin.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.liuyk.desgin.model.Column;
import org.liuyk.desgin.model.DBConnInfo;
import org.liuyk.desgin.model.TableInfo;
import org.springframework.stereotype.Service;


@SuppressWarnings("unchecked")
@Service
public class MySqlDBService extends JDBCTemplate implements IDBService {
	
	private static final Logger log = Logger.getLogger(MySqlDBService.class);
	
	private static final String MYSQL_SCHEMA = "information_schema";
	private static final String MYSQL_PRIMARY = "PRI";
	private static final String MYSQL_NOT_NULLABLE = "NO";
	
	
	@Override
	public String jdbcUrl(String host) {
		return String.format("jdbc:mysql://%s", host);
	}

	@Override
	public List<String> showDatabases(DBConnInfo connInfo) {
		return (List<String>) queryList(connInfo, "show databases", null, new StringListCallBack());
	}

	@Override
	public List<String> showTableNames(DBConnInfo connInfo, String schema) {
		return (List<String>) queryList(connInfo, "show tables", schema, new StringListCallBack());
	}
	
	@Override
	public TableInfo showTable(DBConnInfo connInfo, String schema, String tableName) {
		List<TableInfo> queryList = (List<TableInfo>) queryList(connInfo, "select * from TABLES where TABLE_SCHEMA=? and TABLE_NAME=?", MYSQL_SCHEMA, new Callback<TableInfo>() {
			@Override
			public List<TableInfo> doInResultCallback(ResultSet rs)	throws SQLException {
				if(rs.next()) {
					String tName = rs.getString("table_name");
					String tComment = rs.getString("table_comment");
					TableInfo info = new TableInfo(tName, tComment);
					return Arrays.asList(info);
				}
				return null;
			}
		}, schema, tableName);
		if(queryList != null && !queryList.isEmpty()) {
			TableInfo tableInfo = queryList.get(0);
			List<Column> columns = showColumns(connInfo, schema, tableInfo.getName());
			tableInfo.setColumns(columns);
			
			List<String> indexColumns = showIndexColumns(connInfo, schema, tableName);
			tableInfo.setIndexColumns(indexColumns);
			return tableInfo;
		}
		return null;
	}

	@Override
	public List<Column> showColumns(DBConnInfo connInfo, String schema, String tableName) {
		List<Column> queryList = (List<Column>) queryList(connInfo, "select * from COLUMNS where TABLE_SCHEMA=? and TABLE_NAME=?", MYSQL_SCHEMA, new Callback<Column>() {
			@Override
			public List<Column> doInResultCallback(ResultSet rs)	throws SQLException {
				List<Column> columns = new ArrayList<Column>();
				while(rs.next()) {
					String table = rs.getString("TABLE_NAME");
					String columnName = rs.getString("COLUMN_NAME");
					String columnType = rs.getString("COLUMN_TYPE");
					boolean primary = MYSQL_PRIMARY.equalsIgnoreCase(rs.getString("COLUMN_KEY"));
					String extra = rs.getString("EXTRA");
					boolean nullable = MYSQL_NOT_NULLABLE.equalsIgnoreCase(rs.getString("IS_NULLABLE"));
					String comment = rs.getString("COLUMN_COMMENT");
					Column column = new Column(table, columnName, columnType, primary, nullable, extra, comment);
					columns.add(column);
				}
				return columns;
			}
		}, schema, tableName);
		return queryList;
	}

	@Override
	public List<String> showIndexColumns(DBConnInfo connInfo, String schema, String tableName) {
		return (List<String>) queryList(connInfo, "show index from " + tableName + " where key_name != 'PRIMARY'", schema, new Callback<String>() {
			@Override
			public List<String> doInResultCallback(ResultSet rs) throws SQLException {
				List<String> list = new ArrayList<String>();
				while(rs.next()) {
					String column = rs.getString("Column_name");
					if(!list.contains(column)) {
						list.add(column);
					}
				}
				return list;
			}
		});
	}
	
}
