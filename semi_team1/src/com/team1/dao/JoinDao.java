package com.team1.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team1.db.DBCPBean;
import com.team1.vo.JoinVo;

public class JoinDao {
	public int insert(JoinVo vo){
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DBCPBean.getConn();
			String sql="insert into members values(SEQ_members_num.nextval,?,?,?,?,'test.jpg','test.jpg','1',0,sysdate,0,sysdate+7)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getU_pw());
			pstmt.setString(3, vo.getM_nick());
			pstmt.setString(4, vo.getM_mail());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			DBCPBean.close(con,pstmt);
		}
	}
	public boolean idcheck(String id){
		JoinVo vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean using=false;
		try{
			con=DBCPBean.getConn();
			String sql="select * from members where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				//vo=new JoinVo(rs.getInt("num"), rs.getString("id"), rs.getString("u_pw"), rs.getString("m_nick"), rs.getString("m_mail"), rs.getString("m_orgfilename"), rs.getString("m_savefilename"), rs.getString("grade"), rs.getInt("exp"), rs.getDate("reg_date"), rs.getInt("stop"), rs.getDate("limit_date"));
				using = true;
				return using;
			}else{
				return using;
			}
		}catch(SQLException se){
			System.out.println(se.getMessage());
		}finally{
			DBCPBean.close(con,pstmt,rs);
		}
		return using;
	}
	public boolean nickcheck(String m_nick){
		JoinVo vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean using=false;
		try{
			con=DBCPBean.getConn();
			String sql="select * from members where m_nick=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, m_nick);
			rs=pstmt.executeQuery();
			if(rs.next()){
				using = true;
				return using;
			}else{
				return using;
			}
		}catch(SQLException se){
			System.out.println(se.getMessage());
		}finally{
			DBCPBean.close(con,pstmt,rs);
		}
		return using;
	}
	public boolean emailcheck(String m_mail){
		JoinVo vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean using=false;
		try{
			con=DBCPBean.getConn();
			String sql="select * from members where m_mail=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, m_mail);
			rs=pstmt.executeQuery();
			if(rs.next()){
				using = true;
				return using;
			}else{
				return using;
			}
		}catch(SQLException se){
			System.out.println(se.getMessage());
		}finally{
			DBCPBean.close(con,pstmt,rs);
		}
		return using;
	}
}

