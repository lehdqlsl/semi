package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.team1.db.DBCPBean;
import com.team1.vo.ProfileVo;



public class ImgUpdateDao {
	public int update(ProfileVo vo){
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DBCPBean.getConn();
			String sql="update members set m_orgfilename=?, m_savefilename=? where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getM_orgfilename());
			pstmt.setString(2,vo.getM_savefilename());
			pstmt.setInt(3,vo.getNum());
			return pstmt.executeUpdate();
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			DBCPBean.close(con, pstmt, null);
		}
	}
}
