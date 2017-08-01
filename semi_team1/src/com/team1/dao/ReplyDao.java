package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.team1.db.DBCPBean;
import com.team1.vo.ReplyVo;
import com.team1.vo.boardVo;

public class ReplyDao {
	private static ReplyDao instance = new ReplyDao();

	private ReplyDao() {
	}

	public static ReplyDao getInstance() {
		return instance;
	}

	public ArrayList<ReplyVo> replyList(int bnum, int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT * FROM (SELECT AA.* ,ROWNUM RNUM FROM (SELECT * FROM REPLY WHERE B_NUM=? ORDER BY R_NUM desc) AA)WHERE RNUM>=? AND RNUM<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			ArrayList<ReplyVo> list = new ArrayList<>();
			while (rs.next()) {

				int r_num = rs.getInt("r_num");
				String nick = rs.getString("nick");
				String content = rs.getString("content");
				int up = rs.getInt("up");
				Date reg_date = rs.getDate("reg_date");
				int b_num = rs.getInt("b_num");
				int report = rs.getInt("report");
				ReplyVo vo = new ReplyVo(r_num, nick, content, up, reg_date, b_num, report);
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

	public int getCount(int b_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select count(*) from reply where b_num=?";
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

	public int insert(String nick, String content, int b_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "insert into reply values(SEQ_reply_r_num.nextval,?,?,0,sysdate,?,0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nick);
			pstmt.setString(2, content);
			pstmt.setInt(3, b_num);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	// 댓글 삭제
	public int delete(int r_num, int b_num, String nick) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "delete from reply where r_num=? and b_num=? and nick=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, r_num);
			pstmt.setInt(2, b_num);
			pstmt.setString(3, nick);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	// 신고 댓글 리스트
	public ArrayList<ReplyVo> replyReport(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT * FROM (SELECT AA.* ,ROWNUM RNUM FROM (SELECT * FROM REPLY WHERE REPORT=1 ORDER BY REG_DATE desc) AA)WHERE RNUM>=? AND RNUM<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<ReplyVo> list = new ArrayList<>();
			while (rs.next()) {

				int r_num = rs.getInt("r_num");
				String nick = rs.getString("nick");
				String content = rs.getString("content");
				int up = rs.getInt("up");
				Date reg_date = rs.getDate("reg_date");
				int b_num = rs.getInt("b_num");
				int report = rs.getInt("report");
				ReplyVo vo = new ReplyVo(r_num, nick, content, up, reg_date, b_num, report);
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

	// 신고 댓글 개수
	public int getCnt() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select count(*) from reply where report=1";
			pstmt = con.prepareStatement(sql);
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

	// 댓글 신고하기
	public int reportUpdate(int replynum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update reply set report=1 where r_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, replynum);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	public ArrayList<ReplyVo> MyReplyList(String nick, int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from (select a.*, rownum rnum from(select * from reply where nick=? order by r_num desc )a) where rnum>=? and rnum <=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nick);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			ArrayList<ReplyVo> list = new ArrayList<>();
			while (rs.next()) {

				int r_num = rs.getInt("r_num");
				nick = rs.getString("nick");
				String content = rs.getString("content");
				int up = rs.getInt("up");
				Date reg_date = rs.getDate("reg_date");
				int b_num = rs.getInt("b_num");
				int report = rs.getInt("report");
				ReplyVo vo = new ReplyVo(r_num, nick, content, up, reg_date, b_num, report);
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

	public int getReplyCount(String nick) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select count(*) cnt from reply where nick=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nick);
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
}
