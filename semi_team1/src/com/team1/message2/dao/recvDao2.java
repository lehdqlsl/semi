package com.team1.message2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.team1.db.DBCPBean;
import com.team1.vo.Message2Vo;

public class recvDao2 {
	
	public ArrayList<Message2Vo> list(String recv, int startRow, int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=	"SELECT * FROM ("+
					"	SELECT AA.*,ROWNUM RNUM FROM ("+
					"		SELECT * FROM message "+ 
					"		WHERE receiver=? and recv_del=1 and send_cxl=1 order by regdate desc) AA "+ 
					")WHERE RNUM>=? AND RNUM<=? ";
		try{
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, recv);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs=pstmt.executeQuery();
			ArrayList<Message2Vo> list=new ArrayList<>();
			while(rs.next()){
				int num=rs.getInt("num");
				String sender=rs.getString("sender");
				String receiver=rs.getString("receiver");
				String content=rs.getString("content");
				int chk=rs.getInt("chk");
				int send_del=rs.getInt("send_del");
				int recv_del=rs.getInt("recv_del");
				String regdate=rs.getString("regdate");
				int send_cxl=rs.getInt("send_cxl");
				
				Message2Vo vo=new Message2Vo(num, sender, receiver, content, chk, send_del, recv_del, regdate, send_cxl);
				list.add(vo);
			}
			return list;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally{
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	//전체 글의 갯수 구하기
	public int getCount(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DBCPBean.getConn();
			String sql="select NVL(count(num),0) cnt from message";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int cnt=rs.getInt(1);
			return cnt;		
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	//받은메시지 상세보기
	public Message2Vo select(int num){
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		String sql1="update message set chk=0 where num=?";
		String sql2="select * from message where num=?";
		try{
			con=DBCPBean.getConn();
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1, num);
			int n=pstmt1.executeUpdate();	
			if(n>0){
				pstmt2=con.prepareStatement(sql2);
				pstmt2.setInt(1, num);
				rs=pstmt2.executeQuery();
				if(rs.next()){
					Message2Vo vo=new Message2Vo(
							rs.getInt("num"),
							rs.getString("sender"),
							rs.getString("receiver"),
							rs.getString("content"),
							rs.getInt("chk"),
							rs.getString("regdate"),
							rs.getInt("send_cxl")
							);
					return vo;
				}else{
					
				}
			}
			return null;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally{
			try{
			rs.close();
			pstmt2.close();
			pstmt1.close();
			con.close();
			}catch(SQLException se){
				System.out.println(se.getMessage());
			}
		}
	}
	
	//받은메시지 삭제
	public int delete(Message2Vo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update message set recv_del=0 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getNum());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	//받은쪽지함 신규메시지 개수 표시
	public int getMsgCount(String m_nick) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select count(*) cnt from message where chk=1 and send_cxl=1 and receiver=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_nick);
			rs=pstmt.executeQuery();
			if(rs.next()){
				int cnt=rs.getInt("cnt");
				return cnt;
			}
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
}
