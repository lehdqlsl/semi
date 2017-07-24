package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.team1.db.DBCPBean;
import com.team1.vo.ReplyVo;

public class ReplyDao {
	private static ReplyDao instance=new ReplyDao();
	private ReplyDao(){}
	public static ReplyDao getInstance(){
		return instance;
	}
	public ArrayList<ReplyVo> replyList(){
		ArrayList<ReplyVo> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DBCPBean.getConn();
			String sql="select * from reply";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ReplyVo vo=new ReplyVo(
						rs.getInt("r_num"),
						rs.getString("nick"),
						rs.getString("content"),
						rs.getInt("up"),
						rs.getDate("reg_date"),
						rs.getInt("num"),
						rs.getInt("report"));
				list.add(vo);
			}
			return list;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally{
			DBCPBean.close(con,pstmt,rs);
		}
	}	
}
