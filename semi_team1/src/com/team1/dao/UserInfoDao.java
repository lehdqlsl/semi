package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team1.db.DBCPBean;
import com.team1.vo.ProfileVo;


public class UserInfoDao {
	public ProfileVo select(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DBCPBean.getConn();
			String sql="select * from members where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				ProfileVo vo=new ProfileVo(
						rs.getInt("num"), 
						rs.getString("id"), 
						rs.getString("u_pw"),
						rs.getString("m_nick"),
						rs.getString("m_mail"),
						rs.getString("m_orgfilename"), 
						rs.getString("m_savefilename"),
						rs.getString("grade"), 
						rs.getInt("exp"), 
						rs.getDate("reg_date"), 
						rs.getInt("stop"),
						rs.getDate("limit_date")
						);
				return vo;
			}else{
				return null;
			}
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally{
			DBCPBean.close(con, pstmt, rs);
		}
	}
	public int update(ProfileVo vo){
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DBCPBean.getConn();
			String sql="update members set m_nick=? where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getM_nick());
			pstmt.setInt(2,vo.getNum());
			return pstmt.executeUpdate();
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			DBCPBean.close(con, pstmt, null);
		}
	}

}
