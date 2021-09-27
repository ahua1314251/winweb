package org.winker.winweb.utils.database;

import com.alibaba.druid.DbType;

import java.util.List;


public class Table {
	private String name;
    private String beanName;
    private String methodName;
	private List<Column> columns;
	private DbType dbType;
	private String schema;

    public Table(){

    }

	public Table(String name) {
		this.name = name;
		this.beanName=StringUtil.convertToHump(name);
        this.methodName = StringUtil.convertToFU(this.beanName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.beanName=StringUtil.convertToHump(name);
		this.methodName = StringUtil.convertToFU(this.beanName);
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
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
