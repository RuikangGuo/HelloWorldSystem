	package com.nowcoder.model;
	
	import java.util.Date;
	
	public class LoginTicket {
	  private int id;
	  private int userId;
	  private Date expired;
	  private int status;
	  private String ticket;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getExpired() {
		return expired;
	}
	public void setExpired(Date expired) {
		this.expired = expired;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	@Override
	public String toString() {
		return "LoginTicket [id=" + id + ", userId=" + userId + ", expired=" + expired + ", status=" + status + ", ticket="
				+ ticket + "]";
	}
	public LoginTicket(int id, int userId, Date expired, int status, String ticket) {
		super();
		this.id = id;
		this.userId = userId;
		this.expired = expired;
		this.status = status;
		this.ticket = ticket;
	}
	public LoginTicket() {
		super();
		// TODO Auto-generated constructor stub
	}
	  
	}
