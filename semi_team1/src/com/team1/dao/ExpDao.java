package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team1.db.DBCPBean;

public class ExpDao {
	public int select(String nick) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select exp from exp where nick = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nick);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("exp");
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return 0;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
		return 0;
	}
}
