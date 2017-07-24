package com.team1.vo;

import java.util.Date;

public class boardVo {
	private int num;
	private String title_name;
	private int	up;
	private int hits;
	private String orgfilename;
	private String savefilename;
	private String content;
	private	Date regdate;
	private String writer;
	private int f_num;
	private int s_num;
	private int blind;
	private int report;
	private int top;
	
	public boardVo(){}
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle_name() {
		return title_name;
	}
	public void setTitle_name(String title_name) {
		this.title_name = title_name;
	}
	public int getUp() {
		return up;
	}
	public void setUp(int up) {
		this.up = up;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getOrgfilename() {
		return orgfilename;
	}
	public void setOrgfilename(String orgfilename) {
		this.orgfilename = orgfilename;
	}
	public String getSavefilename() {
		return savefilename;
	}
	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getF_num() {
		return f_num;
	}
	public void setF_num(int f_num) {
		this.f_num = f_num;
	}
	public int getS_num() {
		return s_num;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	public int getBlind() {
		return blind;
	}
	public void setBlind(int blind) {
		this.blind = blind;
	}
	public int getReport() {
		return report;
	}
	public void setReport(int report) {
		this.report = report;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public boardVo(int num, String title_name, int up, int hits, String orgfilename, String savefilename,
			String content, Date regdate, String writer, int f_num, int s_num, int blind, int report, int top) {
		
		this.num = num;
		this.title_name = title_name;
		this.up = up;
		this.hits = hits;
		this.orgfilename = orgfilename;
		this.savefilename = savefilename;
		this.content = content;
		this.regdate = regdate;
		this.writer = writer;
		this.f_num = f_num;
		this.s_num = s_num;
		this.blind = blind;
		this.report = report;
		this.top = top;
	}
}