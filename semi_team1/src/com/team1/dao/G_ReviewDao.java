package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import com.team1.db.DBCPBean;
import com.team1.vo.G_ReviewVo;

public class G_ReviewDao {
	public int insert(G_ReviewVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = DBCPBean.getConn();
			String sql = "insert into game values(SEQ_game_g_num.nextval,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getGr_num());
			pstmt.setInt(2, vo.getG_num());
			pstmt.setString(3, vo.getM_nick());
			pstmt.setDouble(4, vo.getScore());
			pstmt.setDate(5, vo.getR_date());
			pstmt.setInt(6, vo.getUp());
			pstmt.setInt(7, vo.getReport());
			int n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt);
		}
	}
	public G_ReviewVo Select(int g_num) {
		G_ReviewVo vo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT * FROM GAME WHERE G_NUM=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, g_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new G_ReviewVo(rs.getInt("gr_num"), rs.getInt("g_num"), rs.getString("m_nick"), rs.getDouble("score"),
						rs.getDate("r_date"), rs.getInt("up"), rs.getInt("report"));
			}
			return vo;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	public ArrayList<G_ReviewVo> replyList(int g_Num,int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT * FROM (SELECT AA.* ,ROWNUM RNUM FROM (SELECT * FROM GAME WHERE G_NUM=? ORDER BY GR_NUM asc) AA)WHERE RNUM>=? AND RNUM<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, g_Num);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			ArrayList<G_ReviewVo> list = new ArrayList<>();
			
			while (rs.next()) {

				int gr_num = rs.getInt("gr_num");
				int g_num= rs.getInt("g_num");
				String m_nick=rs.getString("m_nick");
				double score=rs.getDouble("score");
				Date r_date=rs.getDate("r_date");
				int up=rs.getInt("up");
				int report=rs.getInt("report");
				G_ReviewVo vo = new G_ReviewVo(gr_num, g_num, m_nick, score, r_date, up, report);
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
