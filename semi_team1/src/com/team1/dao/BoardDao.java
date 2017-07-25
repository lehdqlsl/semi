package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.team1.db.DBCPBean;
import com.team1.vo.boardVo;



public class BoardDao {
	
	
	public ArrayList<boardVo> list(int f_num, int s_num, int startRow, int endRow, String search, String keyword) {
		ArrayList<boardVo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = DBCPBean.getConn();
			if(search.equals("")){
				String sql = "select * from ( "
						+ "select a.*, rownum rnum from( "
							+ "select * from board where f_num=? and s_num=? order by num desc "
						+ ")a "
					+ ") where rnum>=? and rnum <=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, f_num);
				pstmt.setInt(2, s_num);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
				rs = pstmt.executeQuery();
			}else{
				String searchCase = "";
				if(search.equals("writer")){
					searchCase = " = ? ";
				}else{
					searchCase = " like '%'||?||'%' ";
				}
					
				String sql = "select * from ( "
						+ "select a.*, rownum rnum from( "
							+ "select * from board where "+search+" "+searchCase+" order by num desc "
						+ ")a "
					+ ") where rnum>=? and rnum <=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();
			}
			while(rs.next()){
				int num = rs.getInt("num");
				String title_name = rs.getString("title_name");
				int up= rs.getInt("up");
				int hits= rs.getInt("hits");
				String orgfilename= rs.getString("orgfilename");
				String savefilename= rs.getString("savefilename");
				String content= rs.getString("content");
				Date regdate= rs.getDate("regdate");
				String writer= rs.getString("writer");
				f_num= rs.getInt("f_num");
				s_num= rs.getInt("s_num");
				int blind= rs.getInt("blind");
				int report= rs.getInt("report");
				int top= rs.getInt("top");
				list.add(new boardVo(num, title_name, up, hits, orgfilename, savefilename, content, regdate, writer, f_num, s_num, blind, report, top));
			}
			return list;
		}catch(SQLException se){
			se.printStackTrace();
			return null;
		}finally{
			DBCPBean.close(con, pstmt, rs);
		}
	}

	
	public int insert(boardVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into board values(SEQ_board_num.nextval,?,0,0,?,?,?,sysdate,?,?,?,0,0,0)";
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle_name());
			pstmt.setString(2, vo.getOrgfilename());
			pstmt.setString(3, vo.getSavefilename());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getWriter());
			pstmt.setInt(6, vo.getF_num());
			pstmt.setInt(7, vo.getS_num());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	
	public int delete(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="delete from board where num=?";
		try{
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,num);
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	
	public boardVo select(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DBCPBean.getConn();
			String sql="select * from board where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				boardVo vo=new boardVo(
						rs.getInt("num"), 
						rs.getString("title_name"), 
						rs.getInt("up"),
						rs.getInt("hits"), 
						rs.getString("orgfilename"), 
						rs.getString("savefilename"),
						rs.getString("content"), 
						rs.getDate("regdate"), 
						rs.getString("writer"), 
						rs.getInt("f_num"),
						rs.getInt("s_num"), 
						rs.getInt("blind"), 
						rs.getInt("report"), 
						rs.getInt("top")
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
	
	
	public int update(boardVo vo){
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DBCPBean.getConn();
			String sql="update board set title_name=?, content=?, orgfilename=?, savefilename=?, s_num=1 where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getTitle_name());
			pstmt.setString(2,vo.getContent());
			pstmt.setString(3,vo.getOrgfilename());
			pstmt.setString(4,vo.getSavefilename());
			pstmt.setInt(5,vo.getNum());
			return pstmt.executeUpdate();
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	
	public int getCount(String search, String keyword){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DBCPBean.getConn();
			
			if(keyword.equals("")){
				String sql = "select NVL(count(num),0) from board";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
			}else{
				String searchCase = "";
				if(search.equals("writer")){
					searchCase = " = ? ";
				}else{
					searchCase = " like '%'||?||'%' ";
				}
				String sql = "select NVL(count(num),0) from board where "+search+searchCase;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, keyword);
				rs = pstmt.executeQuery();
			}
			rs.next();
			int countNum = rs.getInt(1);
			return countNum;
		}catch(SQLException se){
			se.printStackTrace();
			return -1;
		}finally{
			DBCPBean.close(conn, pstmt, rs);
		}
	}
	
	
	public int getMaxNum(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DBCPBean.getConn();
			String sql = "select NVL(max(num),0) from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			rs.next();
			int maxNum = rs.getInt(1);
			
			return maxNum;
		}catch(SQLException se){
			se.printStackTrace();
			return -1;
		}finally{
			DBCPBean.close(conn, pstmt, rs);
		}
	}
	
}
