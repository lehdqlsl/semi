package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.org.apache.regexp.internal.recompile;
import com.team1.db.DBCPBean;
import com.team1.vo.JoinVo;
import com.team1.vo.MusicVo;

public class MusicDao {
	//음악 등록
	public int insert(MusicVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con=DBCPBean.getConn();
			String sql="insert into music values(seq_music_num.nextval,?,'admin',?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getSinger());
			pstmt.setString(3, vo.getLyrics());
			pstmt.setString(4, vo.getSongwriter());
			pstmt.setString(5, vo.getLyricist());
			pstmt.setString(6, vo.getOrgmimg());
			pstmt.setString(7, vo.getSavemimg());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			DBCPBean.close(con,pstmt,null);
		}
	}
	//음악 조회
	public MusicVo select(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DBCPBean.getConn();
			String sql="select * from music where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				MusicVo vo=new MusicVo(rs.getInt("num"), rs.getString("title"), rs.getString("m_nick"), rs.getString("singer"), rs.getString("lyrics"), rs.getString("songwriter"), rs.getString("lyricist"), rs.getString("orgmimg"), rs.getString("savemimg"));
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
	//음악 전체 정보 출력
	public ArrayList<MusicVo> musiclist(int startRow,int endRow){
		ArrayList<MusicVo> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DBCPBean.getConn();
			String sql="SELECT * FROM (SELECT AA.* ,ROWNUM RNUM FROM(SELECT * FROM MUSIC) AA) WHERE RNUM>=? AND RNUM<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()){
				MusicVo vo=new MusicVo(rs.getInt("num"), rs.getString("title"), rs.getString("m_nick"), rs.getString("singer"), rs.getString("lyrics"), rs.getString("songwriter"), rs.getString("lyricist"), rs.getString("orgmimg"), rs.getString("savemimg"));
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
	//음악 전체 개수
	public int getMusicCount(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DBCPBean.getConn();
			String sql="select count(*) from music";
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
}
