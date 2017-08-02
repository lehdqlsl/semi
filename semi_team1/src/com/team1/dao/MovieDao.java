package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.team1.db.DBCPBean;
import com.team1.vo.boardListVo;

public class MovieDao {
	
	public ArrayList<boardListVo> list(int s_num, int startRow, int endRow) {
		ArrayList<boardListVo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			if (search.equals("")) {
				String sql = "select * from ( " + "select a.*, rownum rnum from( "
						+ "select * from board where s_num=? and blind=0 order by num desc " + ")a "
						+ ") where rnum>=? and rnum <=?";
				/*
				 * String sql = "select * from ( " +
				 * "select a.*, rownum rnum from( " +
				 * "select * from board where s_num=? order by num desc "+ ")a "
				 * + ") where rnum>=? and rnum <=?";
				 */
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
				String title_name = rs.getString("title_name");
				int up = rs.getInt("up");
				int hits = rs.getInt("hits");
				String orgfilename = rs.getString("orgfilename");
				String savefilename = rs.getString("savefilename");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				String writer = rs.getString("writer");
				s_num = rs.getInt("s_num");
				int blind = rs.getInt("blind");
				int report = rs.getInt("report");
				int top = rs.getInt("top");
				list.add(new boardListVo(num, title_name, up, hits, orgfilename, savefilename, content, regdate, writer,
						1, s_num, blind, report, top));
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
