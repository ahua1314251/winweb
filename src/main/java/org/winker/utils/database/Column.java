package org.winker.utils.database;


public class Column {


	private String name;
	private String beanName;
	private String typeName;
	private String size;
	private String nullAble;

	public Column(){

	}

	public Column(String name, String typeName, String size,
			String nullAble) {
		super();
		name = name;
		typeName = typeName;
		size = size;
		nullAble = nullAble;
		if(typeName.equalsIgnoreCase("char")||typeName.equalsIgnoreCase("nvarchar")||typeName.equalsIgnoreCase("varchar"))
		{
			size ="("+size+")";
		}
		else
		{
			size="";
		}
		typeName=typeName.toUpperCase();
		beanName = StringUtil.convertToHump(name);
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
		name = name;
	}
	public String gettypeName() {
		return typeName;
	}
	public String getTypeNameMyBatis() {
		if(typeName.equalsIgnoreCase("int"))
		{
			return "INTEGER";
		}
		if(typeName.equalsIgnoreCase("long"))
		{
			return "BIGINT";
		}
		if(typeName.equalsIgnoreCase("datetime"))
		{
			return "TIMESTAMP";
		}
		return typeName;
	}

	public String getTypeNameJava() {
		if(typeName.equalsIgnoreCase("datetime"))
		{
			return "Date";
		}
		if(typeName.equalsIgnoreCase("char")||typeName.equalsIgnoreCase("nvarchar")||typeName.equalsIgnoreCase("varchar"))
		{
			return "String";
		}
		if(typeName.equalsIgnoreCase("INT"))
		{
			return "int";
		}
		if(typeName.equalsIgnoreCase("BIT"))
		{
			return "boolean";
		}

		return typeName;
	}

	public void setTypeName(String typeName) {
		typeName = typeName;
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
		nullAble = nullAble;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		beanName = beanName;
	}
}
