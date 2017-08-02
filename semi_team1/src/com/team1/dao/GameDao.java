package com.team1.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.team1.db.DBCPBean;
import com.team1.vo.GameVo;

public class GameDao {
	public int insert(GameVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "insert into game values(SEQ_game_g_num.nextval,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getG_name());
			pstmt.setString(2, vo.getG_jenre());
			pstmt.setString(3, vo.getFlatform());
			pstmt.setString(4, vo.getCompany());
			pstmt.setDate(5, vo.getL_date());
			pstmt.setString(6, vo.getOrgImg());
			pstmt.setString(7, vo.getSaveImg());
			int n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt);
		}
	}
	public GameVo select(int g_num) {
		GameVo vo = null;

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
				vo = new GameVo(rs.getInt("g_num"), rs.getString("g_name"), rs.getString("g_jenre"), rs.getString("flatform"),
						rs.getString("company"), rs.getDate("l_date"), rs.getString("orgImg"),
						rs.getString("saveImg"));
			}
			return vo;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	public ArrayList<GameVo> gameList(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT * FROM (SELECT AA.* ,ROWNUM RNUM FROM (SELECT * FROM GAME ORDER BY G_NUM asc) AA)WHERE RNUM>=? AND RNUM<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<GameVo> list = new ArrayList<>();
			
			while (rs.next()) {

				int g_num = rs.getInt("g_num");
				String g_name = rs.getString("g_name");
				String g_jenre = rs.getString("g_jenre");
				String flatform = rs.getString("flatform");
				String company = rs.getString("company");
				Date l_date = rs.getDate("l_date");
				String orgImg = rs.getString("orgImg");
				String saveImg = rs.getString("saveImg");
				GameVo vo = new GameVo(g_num, g_name, g_jenre, flatform, company, l_date, orgImg, saveImg);
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
