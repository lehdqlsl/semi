package com.team1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.team1.db.DBCPBean;
import com.team1.vo.ManagerVo;
import com.team1.vo.Message2Vo;
import com.team1.vo.boardListVo;
import com.team1.vo.boardVo;

public class BoardDao {

	public ArrayList<boardListVo> list(int s_num, int startRow, int endRow, String search, String keyword) {
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
						+ search + " " + searchCase + " and s_num=? and blind=0 order by num desc " + ")a "
						+ ") where rnum>=? and rnum <=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setInt(2, s_num);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
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

	public int insert(boardVo vo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into board values(SEQ_board_num.nextval,?,0,0,?,?,?,sysdate,?,?,?,0,0,?)";
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle_name());
			pstmt.setString(2, vo.getOrgfilename());
			pstmt.setString(3, vo.getSavefilename());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getWriter());
			pstmt.setInt(6, vo.getF_num());
			pstmt.setInt(7, vo.getS_num());
			pstmt.setInt(8, vo.getTop());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	public boardVo select(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				boardVo vo = new boardVo(rs.getInt("num"), rs.getString("title_name"), rs.getInt("up"),
						rs.getInt("hits"), rs.getString("orgfilename"), rs.getString("savefilename"),
						rs.getString("content"), rs.getDate("regdate"), rs.getString("writer"), rs.getInt("f_num"),
						rs.getInt("s_num"), rs.getInt("blind"), rs.getInt("report"), rs.getInt("top"));
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

	public int update(boardVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update board set title_name=?, content=?, regdate=sysdate where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle_name());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNum());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	public int getCount(int s_num, String search, String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBCPBean.getConn();

			if (keyword.equals("")) {
				String sql = "select NVL(count(num),0) from board where s_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, s_num);
				rs = pstmt.executeQuery();
			} else {
				String searchCase = "";
				if (search.equals("writer")) {
					searchCase = " = ? ";
				} else {
					searchCase = " like '%'||?||'%' ";
				}
				String sql = "select NVL(count(num),0) from board where s_num=? and " + search + searchCase;
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, s_num);
				pstmt.setString(2, keyword);
				rs = pstmt.executeQuery();
			}
			rs.next();
			int countNum = rs.getInt(1);
			return countNum;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(conn, pstmt, rs);
		}
	}

	public int getMaxNum() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBCPBean.getConn();
			String sql = "select NVL(max(num),0) from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			rs.next();
			int maxNum = rs.getInt(1);

			return maxNum;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(conn, pstmt, rs);
		}
	}

	////////////// 신고 글 조회/////////////////////////
	// 신고 글 리스트
	public ArrayList<ManagerVo> boardReport(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "SELECT * FROM (SELECT AA.* ,ROWNUM RNUM FROM (select b.num boardnum,s.title_name s_title,b.title_name b_title,b.regdate,b.writer,b.report from board b, s_category s where b.s_num=s.num and b.REPORT=1 and b.blind=0 ORDER BY REGDATE desc) AA)WHERE RNUM>=? AND RNUM<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<ManagerVo> list = new ArrayList<>();
			while (rs.next()) {
				int boardnum = rs.getInt("boardnum");
				String c_title = rs.getString("s_title");
				String b_title = rs.getString("b_title");
				Date regdate = rs.getDate("regdate");
				String writer = rs.getString("writer");
				int report = rs.getInt("report");

				ManagerVo vo = new ManagerVo(boardnum, c_title, b_title, regdate, writer, report);
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

	// 신고 글 개수
	public int getCnt() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select count(*) from board where report=1 and blind=0";
			pstmt = con.prepareStatement(sql);
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

	// 글 블라인드 처리
	public int blindUpdate(int boardnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update board set blind=1 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	public int hitupdate(int boardnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update board set hits=hits+1 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	public int upupdate(int boardnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update board set up=up+1 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	// 작성글 보기 리스트
	public ArrayList<boardVo> MyWriteList(String writer, int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from (select a.*, rownum rnum from(select * from board where writer=? and blind=0 order by num desc )a) where rnum>=? and rnum <=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			ArrayList<boardVo> list = new ArrayList<>();
			while (rs.next()) {
				int num = rs.getInt("num");
				String title_name = rs.getString("title_name");
				int up = rs.getInt("up");
				int hits = rs.getInt("hits");
				String orgfilename = rs.getString("orgfilename");
				String savefilename = rs.getString("savefilename");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				writer = rs.getString("writer");
				int s_num = rs.getInt("s_num");
				int blind = rs.getInt("blind");
				int report = rs.getInt("report");
				int top = rs.getInt("top");
				list.add(new boardVo(num, title_name, up, hits, orgfilename, savefilename, content, regdate, writer, 1,
						s_num, blind, report, top));
			}
			return list;
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	// 작성자당 쓴 글의 개수
	public int getWriteCount(String writer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select count(*) cnt from board where writer=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int cnt = rs.getInt("cnt");
				return cnt;
			}
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	// 게시글 신고하기
	public int reportUpdate(int boardnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update board set report=1 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}

	////// 조회수
	public int gethitsCount(String writer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select count(hits) cnt from board where writer=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int cnt = rs.getInt("cnt");
				return cnt;
			}
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}

	////// 게시글의 작성자 구하기
	public String getWriter(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select writer from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String writer = rs.getString("writer");
				return writer;
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

	// 베스트 게시글 리스트 가져오기
	public ArrayList<boardListVo> bestlist(int s_num) {
		ArrayList<boardListVo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from (select t.*,rownum as rnum from (select * from board where s_num = ? and up > 0 and blind = 0 order by up desc) t)a where a.rnum <=6";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();

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

	// 베스트 게시글 리스트 숫자만큼 가져오기
	public ArrayList<boardListVo> bestlist(int s_num, int cnt) {
		ArrayList<boardListVo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from (select t.*,rownum as rnum from (select * from board where s_num = ? and up > 0 and blind = 0 order by up desc) t)a where a.rnum <=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			pstmt.setInt(2, cnt);
			rs = pstmt.executeQuery();

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
	
	// 스포츠 게시판 메인 가져오기
		public ArrayList<boardListVo> sportsMain(int s_num, int cnt) {
			ArrayList<boardListVo> list = new ArrayList<>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = DBCPBean.getConn();
				String sql = "select * from (select t.*,rownum as rnum from (select * from board where s_num=? and blind=0 order by regdate desc) t)a where a.rnum <=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, s_num);
				pstmt.setInt(2, cnt);
				rs = pstmt.executeQuery();

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
	


	// 공지사항 리스트 가져오기

	public ArrayList<boardListVo> noticelist(int s_num) {
		ArrayList<boardListVo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from board where s_num=? and top=1 and blind = 0 ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();

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
	//음악게시판 갤러리 사진 출력
	public ArrayList<boardVo> galleryList(){
		ArrayList<boardVo> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DBCPBean.getConn();
			String sql="select * from(select * from board where s_num=42 and blind=0 and report=0 order by regdate desc) where rownum<6";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				int num = rs.getInt("num");
				String title_name = rs.getString("title_name");
				int up = rs.getInt("up");
				int hits = rs.getInt("hits");
				String orgfilename = rs.getString("orgfilename");
				String savefilename = rs.getString("savefilename");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				String writer = rs.getString("writer");
				int f_num=rs.getInt("f_num");
				int s_num = rs.getInt("s_num");
				int blind = rs.getInt("blind");
				int report = rs.getInt("report");
				int top = rs.getInt("top");
				list.add(new boardVo(num, title_name, up, hits, orgfilename, savefilename, content, regdate, writer,
						f_num, s_num, blind, report, top));
			}
			return list;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally{
			DBCPBean.close(con,pstmt,rs);
		}
	}
}
