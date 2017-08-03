package com.team1.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.team1.db.DBCPBean;
import com.team1.vo.TastyVo;

public class TastyDao {
	// 댓글 입력
	public int insert(TastyVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "insert into R_REVIEW values(SEQ_r_review_num.nextval,?,?,?,sysdate,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getB_num());
			pstmt.setString(2, vo.getM_nick());
			pstmt.setFloat(3, vo.getRating());
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
	public int update(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update r_review set del=0 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt);
		}
	}

	// 상세정보(게임상세정보,리뷰x)
	public TastyVo Select(int b_num) {
		TastyVo vo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT * FROM r_review WHERE b_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new TastyVo(rs.getInt("num"), rs.getInt("b_num"), rs.getString("m_nick"), rs.getFloat("score"),
						rs.getDate("r_date"), rs.getString("comments"), rs.getInt("up"), rs.getInt("report"),
						rs.getInt("del"));
			}
			return vo;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	// 게임별 댓글 목록
	public ArrayList<TastyVo> gReviewList(int b_num, int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT * FROM (SELECT AA.* ,ROWNUM RNUM FROM (SELECT * FROM r_review WHERE b_num=? and del=1 ORDER BY num asc) AA)WHERE RNUM>=? AND RNUM<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			ArrayList<TastyVo> list = new ArrayList<>();

			while (rs.next()) {

				int num = rs.getInt("num");
				b_num = rs.getInt("b_num");
				String m_nick = rs.getString("m_nick");
				float score = rs.getFloat("score");
				Date r_date = rs.getDate("r_date");
				String comments = rs.getString("comments");
				int up = rs.getInt("up");
				int report = rs.getInt("report");
				int del = rs.getInt("del");
				TastyVo vo = new TastyVo(num, b_num, m_nick, score, r_date, comments, up, report, del);
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

	// 댓글 페이징 처리-개수
	public int getCnt(int b_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select count(*) from r_review where b_num=? and del=1";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
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
	public float getReviewAvg(int b_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select round(avg(score),1) from r_review where b_num=? and del=1";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			rs = pstmt.executeQuery();
			rs.next();
			float cntAvg = rs.getFloat(1);
			return cntAvg;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	// 중복 리뷰 작성 제한
	public boolean isPossible(String nick, int b_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT * FROM r_review WHERE b_num=? and m_nick=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
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

	// 리뷰갯수가져오기
	public int getCount(int b_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select count(*) from r_review where b_num=?";
			// String sql="select NVL(max(r_num),0) cnt from reply";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			rs = pstmt.executeQuery();
			rs.next();
			int cnt = rs.getInt(1);
			return cnt;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	public int updateRating(float rating, int b_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update board_tasty set up = ? where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setFloat(1, rating);
			pstmt.setInt(2, b_num);
			int n = pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt);
		}
		return -1;
	}
}
