package com.team1.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBCPBean {
	private static DataSource ds;

	static {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc//myoracle");

		} catch (NamingException ne) {
			System.out.println(ne.getMessage());
		}
	}

	public static Connection getConn() throws SQLException {
		Connection con = ds.getConnection();
		return con;
	}

	public static void close(Object... ob) {
		try {
			for (int i = 0; i < ob.length; i++) {
				if (ob[i] instanceof ResultSet) {
					ResultSet rs = (ResultSet) ob[i];
					rs.close();
				} else if (ob[i] instanceof Statement) {
					Statement stmt = (Statement) ob[i];
					stmt.close();
				} else if (ob[i] instanceof Connection) {
					Connection con = (Connection) ob[i];
					con.close();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
