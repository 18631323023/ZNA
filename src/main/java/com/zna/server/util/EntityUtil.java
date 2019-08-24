package com.zna.server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *     根据数据库生成一套基础增删改查
 *  Created by Xiaowen  on 2019/03/04
 *  @version  v1.0
 *
 */
public class EntityUtil
{
	
	
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
    	String url="jdbc:mysql://wisewin-tech.com:3306/hotel";
    	String user="root";
    	String password="Sunshibo1";
    	String tableName="hm_member";
    	//test(url,user,password);
    entity(url,user,password,tableName);
   //     mapper(url,user,password,tableName);
        
    }
	
    public static void test(String url,String user,String password) throws ClassNotFoundException, SQLException {
    	String str="العربية";
    	System.out.println(str.trim());
    	/*Class.forName("com.mysql.jdbc.Driver");
    	Connection  conn=DriverManager.getConnection(url, user, password);
		Statement   ste=conn.createStatement();
    	String sql="SELECT flag FROM `hm_child_order` WHERE  id=2234";
    	ResultSet  res=ste.executeQuery(sql);
    	while(res.next()) {
    		System.out.println(res.getObject(1));;
		}*/
    }
	
	public static void mapper(String url, String user, String password, String tableName) {
		try {
			int i=url.lastIndexOf("/");
			Class.forName("com.mysql.jdbc.Driver");
			Connection  conn=DriverManager.getConnection(url, user, password);
			Statement   ste=conn.createStatement();
			String sql="select COLUMN_NAME  from information_schema.COLUMNS where table_name = '"+tableName+"' "
					+ "and table_schema='"+url.substring(i+1)+"'" ;
			ResultSet  res=ste.executeQuery(sql);
			StringBuffer  buff1=new StringBuffer(" <insert id=\"\" parameterType=\"\" > \r\n    INSERT INTO "+tableName+"(");
			StringBuffer  buff11=new StringBuffer("VALUES(");
			StringBuffer  buff2=new StringBuffer("<delete id=\"\" parameterType=\"int\">  \r\n     DELETE FROM "+tableName+" WHERE id = #{id}  \r\n</delete>");
			StringBuffer  buff3=new StringBuffer("<update id=\"\"  parameterType=\"\"  > \r\n   UPDATE "+tableName+" SET ");
			StringBuffer  buff4=new StringBuffer("<select id=\"\"  parameterType=\"\"  resultType=\"\" >\r\n    SELECT * FROM "+tableName +" WHERE id=#{id}  \r\n</select> ");
			while(res.next()) {
				String clounm=res.getString(1);
				String proName=getProperty(res.getString(1));
				
				buff1.append(clounm).append(",");
				buff11.append("#{"+proName+"}").append(",");
				
				buff3.append("   <if test=\""+proName+"!=null \" >  "+clounm+"=#{"+proName+"} ,</if>  \r\n"   );
				
			}
			
			String str1=buff1.toString();
			str1=str1.substring(0,str1.length()-1)+") ";
			String str11=buff11.toString();
			str11=str11.substring(0,str11.length()-1)+")  \r\n </insert> ";
			System.out.println(str1+"\r\n"+str11);
			System.out.println();
			System.out.println(buff2.toString());
			System.out.println();
			String str3=buff3.toString().substring(0,buff3.length()-1)+"   WHERE  id=#{id} \r\n </update>";
			System.out.println(str3);
			System.out.println();
			System.out.println(buff4.toString());
			res.close();
			ste.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	public static void entity(String url,String user,String password,String tableName) {
		
		try {
			int i=url.lastIndexOf("/");
			Class.forName("com.mysql.jdbc.Driver");
			Connection  conn=DriverManager.getConnection(url, user, password);
			Statement   ste=conn.createStatement();
			String sql="select COLUMN_NAME,COLUMN_COMMENT,DATA_TYPE  from information_schema.COLUMNS where table_name = '"+tableName+"' "
					+ "and table_schema='"+url.substring(i+1)+"'" ;
			ResultSet  res=ste.executeQuery(sql);
	
			while(res.next()) {
				StringBuffer  strbuff=new StringBuffer("private ");	
		
				strbuff.append(getStr(res.getString(3)));
				strbuff.append(getProperty(res.getString(1))).append(";");
				strbuff.append(" //").append(res.getString(2));
				System.out.println(strbuff.toString());
				
			}
			System.out.println();
			
			res.close();
			ste.close();
			conn.close();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static String getStr(String str) {
		switch (str) {
		
		case "tinyint":
		case "smallint":
		case "mediumint":
		case "integer":	
		case "int":
			return "Integer ";
		case "bit":
		case "real":	
		case "bigint":
			return "Long ";
		case "numeric":
		case "float":
		case "double":
		case "decimal":
			return "BigDecimal ";
		case "longtext":	
		case "text":	
		case "tinytext":	
		case "char":	
		case "varchar":	
			return "String ";
		case "date":	
		case "datetime":
			return "Date ";
			
		default:
			return "Object ";
		}
		
		
	}
	
	private static String getProperty(String name) {
		String[]  names=name.split("_");
		if(names.length<2) {
			return name;
		}
		StringBuffer  strBuff=new StringBuffer(names[0]);
		for(int i=1;i<names.length;i++) {
			strBuff.append(names[i].substring(0,1).toUpperCase());
			if(names[i].length()>1) {
				strBuff.append(names[i].substring(1));
			}
		}
		
		return strBuff.toString();
	}
	
	
}
