package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.team1.db.DBCPBean;
import com.team1.vo.BoardTastyVo;
import com.team1.vo.boardListVo;
import com.team1.vo.boardVo;

public class BoardTastyDao {
	public ArrayList<BoardTastyVo> list(int s_num, int startRow, int endRow, String search, String keyword) {
		ArrayList<BoardTastyVo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			if (search.equals("")) {
				String sql = "select * from ( " + "select a.*, rownum rnum from( "
						+ "select * from board_tasty where s_num=? and blind=0 order by num desc " + ")a "
						+ ") where rnum>=? and rnum <=?";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, s_num);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();
			} else {
				String searchCase = "";
				if (search.equals("writer")) {
					searchCase = " = ? ";
				} else {
					searchCase = " like '%'||?||'%' ";
				}

				String sql = "select * from ( " + "select a.*, rownum rnum from( " + "select * from board where "
						+ search + " " + searchCase + " order by num desc " + ")a " + ") where rnum>=? and rnum <=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();
			}
			while (rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				float up = rs.getInt("up");
				int hits = rs.getInt("hits");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				String writer = rs.getString("writer");
				s_num = rs.getInt("s_num");
				int blind = rs.getInt("blind");
				int report = rs.getInt("report");
				int top = rs.getInt("top");
				String addr = rs.getString("addr");
				String map = rs.getString("map");
				list.add(new BoardTastyVo(num, title, up, hits, content, regdate, writer, s_num, blind, report, top,
						addr, map));
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	public String getWriter(int b_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBCPBean.getConn();
			String sql = "select writer from board_tasty where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("writer");
			} else {
				return null;
			}

		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(conn, pstmt, rs);
		}
	}

	public int getCount(int s_num, String search, String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBCPBean.getConn();

			if (keyword.equals("")) {
				String sql = "select NVL(count(num),0) from board_tasty where s_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, s_num);
				rs = pstmt.executeQuery();
			} else {
				String searchCase = "";
				if (search.equals("writer")) {
					searchCase = " = ? ";
				} else {
					searchCase = " like '%'||?||'%' ";
				}
				String sql = "select NVL(count(num),0) from board_tasty where s_num=? and " + search + searchCase;
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, s_num);
				pstmt.setString(2, keyword);
				rs = pstmt.executeQuery();
			}
			rs.next();
			int countNum = rs.getInt(1);
			return countNum;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(conn, pstmt, rs);
		}
	}

	public int insert(BoardTastyVo vo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into board_tasty values(SEQ_board_tasty_num.nextval,?,0,0,?,sysdate,?,?,0,0,0,?,?)";
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setInt(4, vo.getS_num());
			pstmt.setString(5, vo.getAddr());
			pstmt.setString(6, vo.getMap());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	public BoardTastyVo select(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from board_tasty where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				BoardTastyVo vo = new BoardTastyVo(rs.getInt("num"), rs.getString("title"), rs.getFloat("up"),
						rs.getInt("hits"), rs.getString("content"), rs.getDate("regdate"), rs.getString("writer"),
						rs.getInt("s_num"), rs.getInt("blind"), rs.getInt("report"), rs.getInt("top"),
						rs.getString("addr"), rs.getString("map"));
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

	public int hitupdate(int boardnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update board_tasty set hits=hits+1 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	public int update(String title, String content, String addr, String map, int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();

			if (map == "") {
				String sql = "update board_tasty set title=?,content=?,addr=? where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, content);
				pstmt.setString(3, addr);
				pstmt.setInt(4, num);
			} else {
				String sql = "update board_tasty set title=?,content=?,addr=?,map=? where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, content);
				pstmt.setString(3, addr);
				pstmt.setString(4, map);
				pstmt.setInt(5, num);
			}

			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	public int getS_num(int b_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select s_num from board_tasty where num=?";
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

	// 베스트 게시글 리스트 가져오기
	public ArrayList<BoardTastyVo> bestlist(int s_num) {
		ArrayList<BoardTastyVo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from (select t.*,rownum as rnum from (select * from board_tasty where s_num = ? and up > 0 order by up desc) t)a where a.rnum <=6";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				float up = rs.getInt("up");
				int hits = rs.getInt("hits");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				String writer = rs.getString("writer");
				s_num = rs.getInt("s_num");
				int blind = rs.getInt("blind");
				int report = rs.getInt("report");
				int top = rs.getInt("top");
				String addr = rs.getString("addr");
				String map = rs.getString("map");
				list.add(new BoardTastyVo(num, title, up, hits, content, regdate, writer, s_num, blind, report, top,
						addr, map));
			}

			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	// 베스트 게시글 리스트 숫자만큼 가져오기
	public ArrayList<BoardTastyVo> bestlist(int s_num, int cnt) {
		ArrayList<BoardTastyVo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from (select t.*,rownum as rnum from (select * from board_tasty where s_num = ? and up > 0 order by up desc) t)a where a.rnum <=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			pstmt.setInt(2, cnt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				float up = rs.getInt("up");
				int hits = rs.getInt("hits");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				String writer = rs.getString("writer");
				s_num = rs.getInt("s_num");
				int blind = rs.getInt("blind");
				int report = rs.getInt("report");
				int top = rs.getInt("top");
				String addr = rs.getString("addr");
				String map = rs.getString("map");
				list.add(new BoardTastyVo(num, title, up, hits, content, regdate, writer, s_num, blind, report, top,
						addr, map));
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

}
