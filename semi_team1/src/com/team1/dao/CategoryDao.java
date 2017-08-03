package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.team1.db.DBCPBean;
import com.team1.vo.CategoryVo;

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

	public int f_num(int b_num) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select s.f_num from board b join s_category s on b.s_num=s.num where b.num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
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

	public String getTitle(int s_num) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select title_name from s_category where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("title_name");
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
		return null;
	}

	public ArrayList<CategoryVo> list(int f_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CategoryVo> list = new ArrayList<CategoryVo>();
		try {
			con = DBCPBean.getConn();
			String sql = "select * from s_category where f_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, f_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new CategoryVo(rs.getInt("num"), rs.getString("title_name"), rs.getInt("f_num")));
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
