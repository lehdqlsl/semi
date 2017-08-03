package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import com.team1.db.DBCPBean;
import com.team1.vo.G_ReviewVo;

import oracle.net.aso.p;

public class G_ReviewDao {
	//댓글 입력
	public int insert(G_ReviewVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "insert into G_REVIEW values(SEQ_G_REVIEW_GR_NUM.nextval,?,?,?,sysdate,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getG_num());
			pstmt.setString(2, vo.getM_nick());
			pstmt.setFloat(3, vo.getScore());
			pstmt.setString(4, vo.getComments());
			pstmt.setInt(5, vo.getUp());
			pstmt.setInt(6, vo.getReport());
			pstmt.setInt(7, vo.getDel());
			int n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt);
		}
	}

	// 삭제(숨김)
	public int update(int gr_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update g_review set del=0 where gr_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gr_num);
			int n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt);
		}
	}
	//상세정보(게임상세정보,리뷰x)
	public G_ReviewVo Select(int g_num) {
		G_ReviewVo vo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT * FROM G_REVIEW WHERE G_NUM=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, g_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new G_ReviewVo(rs.getInt("gr_num"), rs.getInt("g_num"), rs.getString("m_nick"),
						rs.getFloat("score"), rs.getDate("r_date"), rs.getString("comments"), rs.getInt("up"),
						rs.getInt("report"), rs.getInt("del"));
			}
			return vo;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	//게임별 댓글 목록
	public ArrayList<G_ReviewVo> gReviewList(int g_Num, int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT * FROM (SELECT AA.* ,ROWNUM RNUM FROM (SELECT * FROM G_REVIEW WHERE G_NUM=? and del=1 ORDER BY GR_NUM asc) AA)WHERE RNUM>=? AND RNUM<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, g_Num);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			ArrayList<G_ReviewVo> list = new ArrayList<>();

			while (rs.next()) {

				int gr_num = rs.getInt("gr_num");
				int g_num = rs.getInt("g_num");
				String m_nick = rs.getString("m_nick");
				float score = rs.getFloat("score");
				Date r_date = rs.getDate("r_date");
				String comments = rs.getString("comments");
				int up = rs.getInt("up");
				int report = rs.getInt("report");
				int del = rs.getInt("del");
				G_ReviewVo vo = new G_ReviewVo(gr_num, g_num, m_nick, score, r_date, comments, up, report, del);
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
	//댓글 페이징 처리-개수
	public int getCnt(int g_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select count(*) from g_review where g_num=? and del=1";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, g_num);
			rs = pstmt.executeQuery();
			rs.next();
			int cntTot = rs.getInt(1);
			return cntTot;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	// 게임 별 평점
	public double getReviewAvg(int g_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select round(avg(score),1) from g_review where g_num=? and del=1";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, g_num);
			rs = pstmt.executeQuery();
			rs.next();
			double cntAvg = rs.getDouble(1);
			return cntAvg;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;	
		}finally{
			DBCPBean.close(con, pstmt, rs);
		}
	}
	//중복 리뷰 작성 제한
	public boolean isPossible(String nick, int g_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT * FROM G_REVIEW WHERE G_NUM=? and m_nick=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, g_num);
			pstmt.setString(2, nick);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return false;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
}
