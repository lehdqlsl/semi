package com.team1.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
}

