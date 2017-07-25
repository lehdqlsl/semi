package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team1.db.DBCPBean;

public class CategoryDao {
	public int isMember(int s_num) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select f_num from s_category where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("f_num");
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
