package org.liuyk.desgin.service;

import java.sql.Connection;
import java.util.List;

import org.liuyk.desgin.model.Column;
import org.liuyk.desgin.model.DBConnInfo;
import org.liuyk.desgin.model.TableInfo;

public interface IDBService {

	public Connection openConnection(DBConnInfo connInfo);
	
	public List<String> showDatabases(DBConnInfo connInfo);
	
	public List<String> showTableNames(DBConnInfo connInfo, String schema);
	
	public TableInfo showTable(DBConnInfo connInfo, String schema, String tableName);
	
	public List<Column> showColumns(DBConnInfo connInfo, String schema, String tableName);
	
}
