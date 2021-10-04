package org.winker.winweb.utils.database;

import com.alibaba.druid.DbType;

import java.util.List;


public class Table {
	private String basePath ="org.winker.winweb.dao.mysql";
	private String name;
    private String entityName;
    private String methodName;
	private List<Column> columns;
	private DbType dbType;
	private String schema;

    public Table(){

    }

	public Table(String name) {
		this.name = name;
		this.entityName=StringUtil.convertToHump(name);
        this.methodName = StringUtil.convertToFU(this.entityName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.entityName=StringUtil.convertToHump(name);
		this.methodName = StringUtil.convertToFU(this.entityName);
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
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

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
}
