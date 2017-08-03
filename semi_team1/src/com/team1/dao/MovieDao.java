package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.team1.db.DBCPBean;
import com.team1.vo.M_ReviewVo;
import com.team1.vo.MovieVo;

public class MovieDao {
	
	public ArrayList<MovieVo> list(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from (select a.*, rownum rnum from(select * from movie where del=1 order by m_release desc )a) where rnum>=? and rnum <=?";
			pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<MovieVo> list = new ArrayList<>();
			while (rs.next()) {
				int m_num = rs.getInt("m_num");
				String m_name = rs.getString("m_name");
				String m_genre = rs.getString("m_genre");
				String m_country =rs.getString("m_country");
				String m_rt = rs.getString("m_rt");
				String m_release = rs.getString("m_release");
				String m_rate = rs.getString("m_rate");
				String m_director = rs.getString("m_director");
				String m_actor = rs.getString("m_actor");
				String orgimg = rs.getString("orgimg");
				String saveimg = rs.getString("saveimg");
				int del = rs.getInt("del");
				String story = rs.getString("story");
				String link = rs.getString("link");
				
				list.add(new MovieVo(m_num, m_name, m_genre, m_country, m_rt, m_release, m_rate, m_director, m_actor, orgimg, saveimg, del, story, link));
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public int movieCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select count(*) cnt from movie where del=1";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int cnt = rs.getInt("cnt");
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
	
	public MovieVo select(int m_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from movie where m_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				MovieVo vo = new MovieVo(
						rs.getInt("m_num"),
						rs.getString("m_name"),
						rs.getString("m_genre"),
						rs.getString("m_country"),
						rs.getString("m_rt"),
						rs.getString("m_release"),
						rs.getString("m_rate"),
						rs.getString("m_director"),
						rs.getString("m_actor"),
						rs.getString("orgimg"),
						rs.getString("saveimg"),
						rs.getInt("del"),
						rs.getString("story"),
						rs.getString("link")
						);
				return vo;
			} else {
				return null;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public int insert(MovieVo vo){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="insert into movie values(SEQ_movie_m_num.nextval,?,?,?,?,?,?,?,?,?,?,1,?,?)";
		try{
			con=DBCPBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getM_name());
			pstmt.setString(2,vo.getM_genre());
			pstmt.setString(3,vo.getM_country());
			pstmt.setString(4,vo.getM_rt());
			pstmt.setString(5,vo.getM_release());
			pstmt.setString(6,vo.getM_rate());
			pstmt.setString(7,vo.getM_director());
			pstmt.setString(8,vo.getM_actor());
			pstmt.setString(9,vo.getOrgimg());
			pstmt.setString(10,vo.getSaveimg());
		
			pstmt.setString(11,vo.getStory());
			pstmt.setString(12,vo.getLink());
			
			return pstmt.executeUpdate();
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	//메인 랭킹
	public ArrayList<M_ReviewVo> getRanking() {

		ArrayList<M_ReviewVo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DBCPBean.getConn();

			String sql = "SELECT * FROM(SELECT M_NAME, R.M_NUM, AVG(r_gpa) AVGSC FROM m_REVIEW R, movie m WHERE R.m_NUM=m.m_NUM GROUP BY m.m_NAME, R.m_num ORDER BY AVGSC DESC)WHERE ROWNUM<6";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				M_ReviewVo vo = new M_ReviewVo( // vo의 생성자 파라미터에 얻어온 값 넣기

						rs.getInt("m_num"),rs.getFloat("avgsc"),rs.getString("m_name"));

				list.add(vo);

			}

			return list;

		} catch (SQLException se) {

			System.out.println(se.getMessage());

			return null;

		} finally {

			DBCPBean.close(con, pstmt, rs);

		}

	} 
	
	
}
