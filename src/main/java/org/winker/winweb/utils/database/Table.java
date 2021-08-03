package org.winker.winweb.utils.database;

import com.alibaba.druid.DbType;

import java.util.List;


public class Table {
	private String tableName;
    private String tableNameFU;
    private String tableNameFL;
    private String tableNameBean;
	private List<Column> columns;
	private DbType dbType;
	private String schema;

    public Table(){

    }

	public Table(String tableName) {
		this.tableName = tableName;
        this.tableNameFU = StringUtil.convertToFU(StringUtil.convertToHump(tableName));
        this.tableNameFL= StringUtil.convertToHump(tableName);
        this.tableNameBean=StringUtil.convertToHump(tableName);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
		this.tableNameFU = StringUtil.convertToFU(StringUtil.convertToHump(tableName));
        this.tableNameFL= StringUtil.convertToHump(tableName);
        this.tableNameBean=StringUtil.convertToHump(tableName);
	}

    public String getTableNameFU() {
        return tableNameFU;
    }

    public void setTableNameFU(String tableNameFU) {
        this.tableNameFU = tableNameFU;
    }

    public String getTableNameFL() {
        return tableNameFL;
    }

    public void setTableNameFL(String tableNameFL) {
        this.tableNameFL = tableNameFL;
    }

    public String getTableNameBean() {
        return tableNameBean;
    }

    public void setTableNameBean(String tableNameBean) {
        this.tableNameBean = tableNameBean;
    }

    public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public DbType getDbType() {
		return dbType;
	}

	public void setDbType(DbType dbType) {
		this.dbType = dbType;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

}
