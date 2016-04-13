package org.liuyk.desgin.model;

import java.io.Serializable;

public class Column implements Serializable {

	private static final long serialVersionUID = 6781206994406714308L;
	private String table;
	private String name;
	private String columnType;
	private boolean primary;
	private boolean nullable;
	private String extra;
	private String comment;

	public Column() {
	}
	
	public Column(String table, String name, String columnType,
			boolean primary, boolean nullable, String extra, String comment) {
		super();
		this.table = table;
		this.name = name;
		this.columnType = columnType;
		this.primary = primary;
		this.nullable = nullable;
		this.extra = extra;
		this.comment = comment;
	}

	
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public boolean isPrimary() {
		return primary;
	}
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Column [table=" + table + ", name=" + name + ", columnType="
				+ columnType + ", primary=" + primary + ", nullable="
				+ nullable + ", extra=" + extra + ", comment=" + comment + "]";
	}
	
	
	
}
