package org.liuyk.desgin.model;

import java.io.Serializable;
import java.util.List;

public class TableInfo implements Serializable {

	private static final long serialVersionUID = -3455687305746907056L;

	private String name;
	private String comment;
	private List<Column> columns;
	private List<String> indexColumns;
	
	public TableInfo(String name, String comment) {
		super();
		this.name = name;
		this.comment = comment;
	}

	public TableInfo() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public List<String> getIndexColumns() {
		return indexColumns;
	}

	public void setIndexColumns(List<String> indexColumns) {
		this.indexColumns = indexColumns;
	}

	@Override
	public String toString() {
		return "TableInfo [name=" + name + ", comment=" + comment
				+ ", columns=" + columns + ", indexColumns=" + indexColumns
				+ "]";
	}

	
}
