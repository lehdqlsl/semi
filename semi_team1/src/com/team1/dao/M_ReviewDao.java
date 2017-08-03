package com.team1.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.team1.db.DBCPBean;
import com.team1.vo.M_ReviewVo;

public class M_ReviewDao {
	//��� �Է�
		public int insert(M_ReviewVo vo) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DBCPBean.getConn();
				String sql = "insert into M_REVIEW values(SEQ_m_review_r_num.nextval,?,?,?,?,sysdate,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, vo.getM_num());
				pstmt.setString(2, vo.getM_nick());
				pstmt.setFloat(3, vo.getR_gpa());
				pstmt.setString(4, vo.getR_comm());
				pstmt.setInt(5, vo.getUp());
				pstmt.setInt(6, vo.getReport());
				pstmt.setInt(7, vo.getDel());
				int n = pstmt.executeUpdate();
				return n;
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			} finally {
				DBCPBean.close(con, pstmt);
			}
		}

		// ����(����)
		public int update(int r_num) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DBCPBean.getConn();
				String sql = "update r_review set del=0 where r_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, r_num);
				int n = pstmt.executeUpdate();
				return n;
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			} finally {
				DBCPBean.close(con, pstmt);
			}
		}
		
		//���Ӻ� ��� ���
		public ArrayList<M_ReviewVo> mReviewList(int m_Num, int startRow, int endRow) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DBCPBean.getConn();
				String sql = "SELECT * FROM (SELECT AA.* ,ROWNUM RNUM FROM (SELECT * FROM M_REVIEW WHERE M_NUM=? and del=1 ORDER BY R_NUM asc) AA)WHERE RNUM>=? AND RNUM<=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, m_Num);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();
				ArrayList<M_ReviewVo> list = new ArrayList<>();
				while (rs.next()) {
					int r_num = rs.getInt("r_num");
					int m_num = rs.getInt("m_num");
					String m_nick = rs.getString("m_nick");
					float r_gpa = rs.getFloat("r_gpa");
					Date reg_date = rs.getDate("reg_date");
					String r_comm = rs.getString("r_comm");
					int up = rs.getInt("up");
					int report = rs.getInt("report");
					int del = rs.getInt("del");
					M_ReviewVo vo = new M_ReviewVo(r_num, m_num, m_nick, r_gpa, r_comm, reg_date, up, report, del);
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
		//��� ����¡ ó��-����
		public int getCnt(int m_num) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DBCPBean.getConn();
				String sql = "select count(*) from m_review where m_num=? and del=1";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, m_num);
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

		// ���� �� ����
		public double getReviewAvg(int m_num) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DBCPBean.getConn();
				String sql = "select round(avg(r_gpa),1) from m_review where m_num=? and del=1";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, m_num);
				rs = pstmt.executeQuery();
				rs.next();
				double cntAvg = rs.getDouble(1);
				return cntAvg;
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return -1;	
			}finally{
				DBCPBean.close(con, pstmt, rs);
			}
		}
		//�ߺ� ���� �ۼ� ����
		public boolean isPossible(String nick, int m_num) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DBCPBean.getConn();
				String sql = "SELECT * FROM m_REVIEW WHERE m_NUM=? and m_nick=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, m_num);
				pstmt.setString(2, nick);
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
}
