package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.team1.db.DBCPBean;
import com.team1.vo.JoinVo;

public class LoginDao {
	public JoinVo isMember(HashMap<String, String> map){
		JoinVo vo=null;
		String id=map.get("id");
		String u_pw=map.get("u_pw");
		String m_nick=null;
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
				vo=new JoinVo(rs.getString("id"), rs.getString("u_pw"), rs.getString("m_nick"), rs.getString("m_mail"));
				return vo;
			}else{
				return null;
			}
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally{
			DBCPBean.close(con,pstmt,rs);
		}
	}
}
