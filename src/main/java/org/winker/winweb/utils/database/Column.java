package org.winker.winweb.utils.database;


import com.alibaba.druid.DbType;

public class Column {


    private String name;
    private String typeName;
    private String javaType;
    private String tableName;
    private DbType dbType;
    private String comment;
    private String nullAble;
    private String entityName;
    private String methodName;
    private String size;

    public Column() {
    }

    public String getname() {
        return name;
    }

    public String getnameFU() {
        return StringUtil.convertToFU(name);
    }

    public String getnameFL() {
        return StringUtil.convertToFL(name);
    }

    public void setname(String name) {
        this.name = name;
    }

    public String gettypeName() {
        return typeName;
    }

    public String getTypeNameMyBatis() {
        if (typeName.equalsIgnoreCase("int")) {
            return "INTEGER";
        }
        if (typeName.equalsIgnoreCase("long")) {
            return "BIGINT";
        }
        if (typeName.equalsIgnoreCase("datetime")) {
            return "TIMESTAMP";
        }
        return typeName;
    }

    public void setTypeName(String typeName) {
        if (typeName.equalsIgnoreCase("char") || typeName.equalsIgnoreCase("nvarchar") || typeName.equalsIgnoreCase("varchar")) {
            this.size = "(" + size + ")";
        } else {
            this.size = "";
        }
        typeName = typeName.toUpperCase();
        this.typeName = typeName;

        if (typeName.equalsIgnoreCase("datetime")) {
            this.javaType = "Date";
        }
        if (typeName.equalsIgnoreCase("char") || typeName.equalsIgnoreCase("nvarchar")
                || typeName.equalsIgnoreCase("varchar") || typeName.equalsIgnoreCase("mediumtext")
                || typeName.equalsIgnoreCase("text") || typeName.equalsIgnoreCase("tinytext")) {
            this.javaType = "String";
        }
        if (typeName.equalsIgnoreCase("INT") || typeName.equalsIgnoreCase("TINYINT")) {
            this.javaType = "Integer";
        }
        if (typeName.equalsIgnoreCase("BIGINT")) {
            this.javaType = "Long";
        }
        if (typeName.equalsIgnoreCase("BIT")) {
            this.javaType = "Boolean";
        }
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        size = size;
    }

    public String getnullAble() {
        return nullAble;
    }

    public void setNullAble(String nullAble) {
        this.nullAble = nullAble;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.entityName = StringUtil.convertToHump(name);
        this.methodName = StringUtil.convertToFU(this.entityName);
    }

    public String getTypeName() {
        return typeName;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public DbType getDbType() {
        return dbType;
    }

    public void setDbType(DbType dbType) {
        this.dbType = dbType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNullAble() {
        return nullAble;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
