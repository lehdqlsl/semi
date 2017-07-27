package com.team1.vo;

public class Message2Vo {
	private int num;
	private String sender;
	private String receiver;
	private String content;
	private int chk;
	private int send_del;
	private int recv_del;
	private String regdate;
	private int send_cxl;
	
	public Message2Vo(){}

	public Message2Vo(int num, String sender, String receiver, String content, int chk,
			String regdate, int send_cxl) {
		this.num = num;
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.chk = chk;
		this.regdate = regdate;
		this.send_cxl = send_cxl;
		
	}
	
	public Message2Vo(int num, String sender, String receiver, String content, int chk, int send_del, int recv_del,
			String regdate, int send_cxl) {
		super();
		this.num = num;
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.chk = chk;
		this.send_del = send_del;
		this.recv_del = recv_del;
		this.regdate = regdate;
		this.send_cxl = send_cxl;
	}
	
	public Message2Vo(int num, int chk){
		this.num=num;
		this.chk=chk;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		content = content;
	}

	public int getChk() {
		return chk;
	}

	public void setChk(int chk) {
		this.chk = chk;
	}

	public int getSend_del() {
		return send_del;
	}

	public void setSend_del(int send_del) {
		this.send_del = send_del;
	}

	public int getRecv_del() {
		return recv_del;
	}

	public void setRecv_del(int recv_del) {
		this.recv_del = recv_del;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getSend_cxl() {
		return send_cxl;
	}

	public void setSend_cxl(int send_cxl) {
		this.send_cxl = send_cxl;
	}

	
	

}
