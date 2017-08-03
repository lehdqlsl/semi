package com.team1.vo;

public class MovieVo {
	private int m_num;
	private String m_name;
	private String m_genre;
	private String m_country;
	private String m_rt;
	private String m_release;
	private String m_rate;
	private String m_director;
	private String m_actor;
	private String orgimg;
	private String saveimg;
	private int del;
	private String story;
	private String link;

	public MovieVo(){}

	public MovieVo(int m_num, String m_name, String m_genre, String m_country, String m_rt, String m_release,
			String m_rate, String m_director, String m_actor, String orgimg, String saveimg, int del, String story,
			String link) {
		super();
		this.m_num = m_num;
		this.m_name = m_name;
		this.m_genre = m_genre;
		this.m_country = m_country;
		this.m_rt = m_rt;
		this.m_release = m_release;
		this.m_rate = m_rate;
		this.m_director = m_director;
		this.m_actor = m_actor;
		this.orgimg = orgimg;
		this.saveimg = saveimg;
		this.del = del;
		this.story = story;
		this.link = link;
	}

	public int getM_num() {
		return m_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_genre() {
		return m_genre;
	}

	public void setM_genre(String m_genre) {
		this.m_genre = m_genre;
	}

	public String getM_country() {
		return m_country;
	}

	public void setM_country(String m_country) {
		this.m_country = m_country;
	}

	public String getM_rt() {
		return m_rt;
	}

	public void setM_rt(String m_rt) {
		this.m_rt = m_rt;
	}

	public String getM_release() {
		return m_release;
	}

	public void setM_release(String m_release) {
		this.m_release = m_release;
	}

	public String getM_rate() {
		return m_rate;
	}

	public void setM_rate(String m_rate) {
		this.m_rate = m_rate;
	}

	public String getM_director() {
		return m_director;
	}

	public void setM_director(String m_director) {
		this.m_director = m_director;
	}

	public String getM_actor() {
		return m_actor;
	}

	public void setM_actor(String m_actor) {
		this.m_actor = m_actor;
	}

	public String getOrgimg() {
		return orgimg;
	}

	public void setOrgimg(String orgimg) {
		this.orgimg = orgimg;
	}

	public String getSaveimg() {
		return saveimg;
	}

	public void setSaveimg(String saveimg) {
		this.saveimg = saveimg;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
	
}
