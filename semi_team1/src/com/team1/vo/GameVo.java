package com.team1.vo;

import java.sql.Date;

public class GameVo {
	private int g_num;
	private String g_name;
	private String g_jenre;
	private String flatform;
	private String company;
	private Date l_date;
	private String orgImg;
	private String saveImg;
	
	public GameVo(){}

	public GameVo(int g_num, String g_name, String g_jenre, String flatform, String company, Date l_date, String orgImg,
			String saveImg) {
		super();
		this.g_num = g_num;
		this.g_name = g_name;
		this.g_jenre = g_jenre;
		this.flatform = flatform;
		this.company = company;
		this.l_date = l_date;
		this.orgImg = orgImg;
		this.saveImg = saveImg;
	}

	public int getG_num() {
		return g_num;
	}

	public void setG_num(int g_num) {
		this.g_num = g_num;
	}

	public String getG_name() {
		return g_name;
	}

	public void setG_name(String g_name) {
		this.g_name = g_name;
	}

	public String getG_jenre() {
		return g_jenre;
	}

	public void setG_jenre(String g_jenre) {
		this.g_jenre = g_jenre;
	}

	public String getFlatform() {
		return flatform;
	}

	public void setFlatform(String flatform) {
		this.flatform = flatform;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getL_date() {
		return l_date;
	}

	public void setL_date(Date l_date) {
		this.l_date = l_date;
	}

	public String getOrgImg() {
		return orgImg;
	}

	public void setOrgImg(String orgImg) {
		this.orgImg = orgImg;
	}

	public String getSaveImg() {
		return saveImg;
	}

	public void setSaveImg(String saveImg) {
		this.saveImg = saveImg;
	}	
}