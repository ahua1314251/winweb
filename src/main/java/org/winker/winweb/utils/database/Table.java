package org.winker.winweb.utils.database;

import java.util.List;


public class Table {
	private String tableName;
	private List<Column> columns;
	private String dbType;
	private String schema;

	Table() {

	}

	public Table(String tableName) {
		this.tableName = tableName;
	}

	public String gettableName() {
		return tableName;
	}

	public void settableName(String tableName) {
		tableName = tableName;
	}

	public String gettableNameFU() {
		return StringUtil.convertToFU(StringUtil.convertToHump(tableName));
	}

	public String gettableNameFL() {
		return StringUtil.convertToHump(tableName);
	}

	public String getTABLE_BEAN_NAME() {
		return StringUtil.convertToHump(tableName);
	}


	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

}
