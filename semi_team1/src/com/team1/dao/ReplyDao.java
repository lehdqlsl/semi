package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.team1.db.DBCPBean;
import com.team1.vo.ReplyVo;

public class ReplyDao {
	private static ReplyDao instance=new ReplyDao();
	private ReplyDao(){}
	public static ReplyDao getInstance(){
		return instance;
	}
	public ArrayList<ReplyVo> replyList(int startRow,int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DBCPBean.getConn();
			String sql="SELECT * FROM (SELECT AA.* ,ROWNUM RNUM FROM (SELECT * FROM REPLY ORDER BY R_NUM desc) AA)WHERE RNUM>=? AND RNUM<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<ReplyVo> list=new ArrayList<>();
			while(rs.next()){
				
				int r_num=rs.getInt("r_num");
				String nick=rs.getString("nick");
				String content=rs.getString("content");
				int up=rs.getInt("up");
				Date reg_date=rs.getDate("reg_date");
				int b_num=rs.getInt("b_num");
				int report=rs.getInt("report");
				ReplyVo vo=new ReplyVo(r_num, nick, content, up, reg_date, b_num, report);
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
	public int getCount(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DBCPBean.getConn();
			String sql="select NVL(max(r_num),0) cnt from reply";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int cnt=rs.getInt(1);
			return cnt;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			DBCPBean.close(con,pstmt,rs);
		}
	}
	public int cntTot(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DBCPBean.getConn();
			String sql="select count(*) from reply";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int cntTot=rs.getInt(1);
			return cntTot;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			DBCPBean.close(con,pstmt,rs);
		}
	}
	public int insert(String content,int b_num){
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DBCPBean.getConn();
			String sql="insert into reply values(SEQ_reply_r_num.nextval,'���',?,0,sysdate,?,0)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,content);
			pstmt.setInt(2, b_num);
			return pstmt.executeUpdate();
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			DBCPBean.close(con,pstmt,null);
		}
	}
}
