package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team1.db.DBCPBean;
import com.team1.vo.BoardUpVo;
import com.team1.vo.ReplyUpVo;

public class BoardUpDao {
	public boolean upOK(BoardUpVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBCPBean.getConn();
			String sql = "select * from board_up where b_num = ? and num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getB_num());
			pstmt.setInt(2, vo.getNum());
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

	public int upUpdate(BoardUpVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBCPBean.getConn();
			String sql = "insert into board_up values (?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getB_num());
			pstmt.setInt(2, vo.getNum());

			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt);
		}
	}

	public int upCnt(BoardUpVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBCPBean.getConn();
			String sql = "select count(*) cnt from board_up where b_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getB_num());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("cnt");
			} else {
				return -1;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
}
