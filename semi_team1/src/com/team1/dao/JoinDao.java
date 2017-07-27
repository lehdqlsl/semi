package com.team1.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.team1.db.DBCPBean;
import com.team1.vo.JoinVo;
import com.team1.vo.ProfileVo;

public class JoinDao {
	//////////////////// 회원가입////////////////////
	public int insert(JoinVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "insert into members values(SEQ_members_num.nextval,?,?,?,?,'test.jpg','test.jpg','unrank',sysdate,0,sysdate+7)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getU_pw());
			pstmt.setString(3, vo.getM_nick());
			pstmt.setString(4, vo.getM_mail());
			int n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt);
		}
	}

	/////////////////// 아이디 중복검사/////////////////////
	public boolean idcheck(String id) {
		JoinVo vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean using = false;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from members where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				using = true;
				return using;
			} else {
				return using;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
		return using;
	}

	////////////////// 닉네입 중복검사////////////////////////////
	public boolean nickcheck(String m_nick) {
		JoinVo vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean using = false;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from members where m_nick=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_nick);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				using = true;
				return using;
			} else {
				return using;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
		return using;
	}

	//////////////////// 이메일 중복검사///////////////////////
	public boolean emailcheck(String m_mail) {
		JoinVo vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean using = false;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from members where m_mail=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_mail);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				using = true;
				return using;
			} else {
				return using;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
		return using;
	}

	//////////////////// 로그인////////////////////////////
	public JoinVo isMember(HashMap<String, String> map) {
		JoinVo vo = null;
		String id = map.get("id");
		String u_pw = map.get("u_pw");
		String m_nick = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from members where id=? and u_pw=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, u_pw);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new JoinVo(rs.getInt("num"), rs.getString("id"), rs.getString("u_pw"), rs.getString("m_nick"),
						rs.getString("m_mail"));

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

	///////////////////////// 전체 회원 출력////////////////////
	public ArrayList<JoinVo> getList(JoinVo vo) {
		ArrayList<JoinVo> list = new ArrayList<>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from members";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JoinVo jv = new JoinVo(rs.getInt("num"), rs.getString("id"), rs.getString("u_pw"),
						rs.getString("m_nick"), rs.getString("m_mail"), rs.getString("m_orgfilename"),
						rs.getString("m_savefilename"), rs.getString("grade"),rs.getDate("reg_date"),
						rs.getInt("stop"), rs.getDate("limit_date"));
				list.add(jv);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	// 전체 회원의 수 구하기
	public int getCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select NVL(count(num),0) cnt from members";
			pstmt = con.prepareStatement(sql);
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

	// 전체 회원 정보 출력-페이징
	public ArrayList<JoinVo> list(int startRow, int endRow) {
		String sql = "SELECT * FROM (SELECT AA.* ,ROWNUM RNUM FROM(SELECT m.*,e.exp FROM MEMBERS m join exp e on m.m_nick = e.nick ORDER BY NUM DESC) AA) WHERE RNUM>=? AND RNUM<=?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<JoinVo> list = new ArrayList<>();
			while (rs.next()) {
				JoinVo jv = new JoinVo(rs.getInt("num"), rs.getString("id"), rs.getString("u_pw"),
						rs.getString("m_nick"), rs.getString("m_mail"), rs.getString("m_orgfilename"),
						rs.getString("m_savefilename"), rs.getString("grade"), rs.getDate("reg_date"),
						rs.getInt("stop"), rs.getDate("limit_date"),rs.getInt("exp"));
				list.add(jv);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	// 제재 회원 정보 출력-페이징
	public ArrayList<JoinVo> memberlimitlist(int startRow, int endRow) {
		String sql = "SELECT * FROM (SELECT AA.* ,ROWNUM RNUM FROM(SELECT * FROM MEMBERS WHERE STOP=1 ORDER BY NUM DESC) AA) WHERE RNUM>=? AND RNUM<=?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<JoinVo> list = new ArrayList<>();
			while (rs.next()) {
				JoinVo jv = new JoinVo(rs.getInt("num"), rs.getString("id"), rs.getString("u_pw"),
						rs.getString("m_nick"), rs.getString("m_mail"), rs.getString("m_orgfilename"),
						rs.getString("m_savefilename"), rs.getString("grade"), rs.getDate("reg_date"),
						rs.getInt("stop"), rs.getDate("limit_date"));
				list.add(jv);
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	// 제재 회원의 수 구하기
	public int getLimitMemCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select NVL(count(num),0) cnt from members where stop=1";
			pstmt = con.prepareStatement(sql);
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

	// limit-date update
	public int limitDateUpdate(int days, String writer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update members set stop=1, limit_date=sysdate+? where m_nick=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, days);
			pstmt.setString(2, writer);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	// remove limit-date update
	public int removeLimitDateUpdate(String writer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update members set stop=0, limit_date=sysdate-1 where m_nick=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	// 글쓰기 제재
	public int limitChk(String m_nick) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select trunc((limit_date)-sysdate,0) cnt, stop from members where m_nick=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_nick);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int cnt = rs.getInt("cnt");
				int stop = rs.getInt("stop");
				if (stop == 1) {
					return 1;
				} else if (stop == 0 && cnt < 0) {
					return 2;
				} else if (stop == 0 && cnt > 0) {
					return 3;
				}
			}
			return -1;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
}
