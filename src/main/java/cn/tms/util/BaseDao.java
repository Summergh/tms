package cn.tms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class BaseDao {
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String url = "jdbc:mysql://localhost:3306/tms";
	private final static String username = "root";
	private final static String pwd = "";

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public Connection getConnection() {
		try {
			Class.forName(driver);
			if (con==null||con.isClosed()) {
				con = DriverManager.getConnection(url, username, pwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public void closeResource(){
		try {
			if(rs!=null){
				rs.close();
			}
			if (ps!=null) {
				ps.close();
			}if (con!=null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 03.дһ�������ݿ�������ݵķ���
	public ResultSet executeQuery(String sql,Object... paras){
		try {
			//01.��ȡ���Ӷ���
			con=getConnection();
			//02.����SQL
			ps=con.prepareStatement(sql);
			//03.�Բ�����ֵ
			if (paras.length>0) {
				for (int i = 0; i < paras.length; i++) {
					ps.setObject(i+1,paras[i]);
				}
			}
			//04.������ִ����
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	// 04.дһ��ִ����ɾ�Ĳ����ķ���
	public int executeUpdate(String sql,Object... paras){
		int count=0;
		try {
			//01.��ȡ����
			   con=getConnection();
			   //02.�����������
			   ps=con.prepareStatement(sql);
			   //03.��������ֵ
			   if (paras.length>0) {
				  for (int i = 0; i < paras.length; i++) {
					 ps.setObject(i+1,paras[i]);
				  }
			   }
			   //04.��ִ����ѽ
			   count=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}



















