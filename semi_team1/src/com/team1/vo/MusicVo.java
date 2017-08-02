package com.team1.vo;

public class MusicVo {
	private int num;
	private String title;
	private String m_nick;
	private String singer;
	private String lyrics;
	private String songwriter;
	private String lyricist;
	private String orgmimg;
	private String savemimg;
	public MusicVo(){}
	public MusicVo(int num, String title, String m_nick, String singer, String lyrics, String songwriter,
			String lyricist, String orgmimg, String savemimg) {
		super();
		this.num = num;
		this.title = title;
		this.m_nick = m_nick;
		this.singer = singer;
		this.lyrics = lyrics;
		this.songwriter = songwriter;
		this.lyricist = lyricist;
		this.orgmimg = orgmimg;
		this.savemimg = savemimg;
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
	public String getM_nick() {
		return m_nick;
	}
	public void setM_nick(String m_nick) {
		this.m_nick = m_nick;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getLyrics() {
		return lyrics;
	}
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	public String getSongwriter() {
		return songwriter;
	}
	public void setSongwriter(String songwriter) {
		this.songwriter = songwriter;
	}
	public String getLyricist() {
		return lyricist;
	}
	public void setLyricist(String lyricist) {
		this.lyricist = lyricist;
	}
	public String getOrgmimg() {
		return orgmimg;
	}
	public void setOrgmimg(String orgmimg) {
		this.orgmimg = orgmimg;
	}
	public String getSavemimg() {
		return savemimg;
	}
	public void setSavemimg(String savemimg) {
		this.savemimg = savemimg;
	}
}
