package com.team1.vo;

public class CategoryVo {
	int s_num;
	String title;
	int f_num;

	public CategoryVo(int s_num, String title, int f_num) {
		super();
		this.s_num = s_num;
		this.title = title;
		this.f_num = f_num;
	}

	public int getS_num() {
		return s_num;
	}

	public void setS_num(int s_num) {
		this.s_num = s_num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getF_num() {
		return f_num;
	}

	public void setF_num(int f_num) {
		this.f_num = f_num;
	}

}
