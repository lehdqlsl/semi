package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.team1.db.DBCPBean;

public class LoginDao {
	public boolean isMember(HashMap<String, String> map){
		String id=map.get("id");
		String u_pw=map.get("u_pw");
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DBCPBean.getConn();
			String sql="select * from members where id=? and u_pw=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, u_pw);
			rs=pstmt.executeQuery();
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return false;
		}finally{
			DBCPBean.close(con,pstmt,rs);
		}
	}
}
