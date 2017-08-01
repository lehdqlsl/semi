package com.team1.message2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.team1.db.DBCPBean;
import com.team1.vo.Message2Vo;
import com.team1.vo.ProfileVo;

public class sendDao2 {
	//전체 보낸 메시지 출력(페이징)
	public ArrayList<Message2Vo> list(String send, int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (" + "	SELECT AA.*,ROWNUM RNUM FROM (" + "		SELECT * FROM MESSAGE "
				+ "		WHERE SENDER=? and send_del=1 order by regdate desc) AA " + ")WHERE RNUM>=? AND RNUM<=? ";
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, send);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			System.out.println("send:" + send);

			rs = pstmt.executeQuery();
			ArrayList<Message2Vo> list = new ArrayList<>();
			while (rs.next()) {
				int num = rs.getInt("num");
				String sender = rs.getString("sender");
				String receiver = rs.getString("receiver");
				String content = rs.getString("content");
				int chk = rs.getInt("chk");
				int send_del = rs.getInt("send_del");
				int recv_del = rs.getInt("recv_del");
				String regdate = rs.getString("regdate");
				int send_cxl = rs.getInt("send_cxl");
				Message2Vo vo = new Message2Vo(num, sender, receiver, content, chk, send_del, recv_del, regdate,
						send_cxl);
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
	//보낸 사람당 보낸 메시지 총개수
	public int getCount(String sender) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select NVL(count(num),0) cnt from message where sender=? and SEND_DEL=1";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sender);
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

	public Message2Vo select(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from message where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Message2Vo vo = new Message2Vo(rs.getInt("num"), rs.getString("sender"), rs.getString("receiver"),
						rs.getString("content"), rs.getInt("chk"), rs.getString("regdate"), rs.getInt("send_cxl"));
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

	// 쪽지보내기
	public int insert(Message2Vo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into message values(SEQ_message_num.nextval,?,?,?,1,1,1,to_char(sysdate,'yy/mm/dd hh24:mi'),1)";

		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getSender());
			pstmt.setString(2, vo.getReceiver());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return 0;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	// nickcheck.jsp json (닉네임검사)
	public boolean nickcheck(String m_nick) {
		ProfileVo vo = null;
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

	public int delete(Message2Vo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update message set send_del=0 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getNum());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	public int cancel(Message2Vo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update message set send_cxl=0 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getNum());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

}
