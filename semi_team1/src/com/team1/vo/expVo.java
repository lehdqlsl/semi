package com.team1.vo;

public class expVo {
	private String nick;
	private int exp;

	public expVo(String nick, int exp) {
		this.nick = nick;
		this.exp = exp;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

}
