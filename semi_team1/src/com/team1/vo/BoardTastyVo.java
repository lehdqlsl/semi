package com.team1.vo;

import java.util.Date;

public class BoardTastyVo {
	private int num;
	private String title;
	private float up;
	private int hits;
	private String content;
	private Date regdate;
	private String writer;
	private int s_num;
	private int blind;
	private int report;
	private int top;
	private String addr;
	private String map;
	private int cnt;
	private String tag;

	public BoardTastyVo(int num, String title, float up, int hits, String content, Date regdate, String writer,
			int s_num, int blind, int report, int top, String addr, String map) {
		super();
		this.num = num;
		this.title = title;
		this.up = up;
		this.hits = hits;
		this.content = content;
		this.regdate = regdate;
		this.writer = writer;
		this.s_num = s_num;
		this.blind = blind;
		this.report = report;
		this.top = top;
		this.addr = addr;
		this.map = map;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getUp() {
		return up;
	}

	public void setUp(float up) {
		this.up = up;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

}
