package com.nowcoder.model;

import java.util.Date;

public class Question {
	private int id;
	private String title;
	private String content;
	private int userId;
	private Date createdTime;
	private  int commentCount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", content=" + content + ", userId=" + userId
				+ ", createdTime=" + createdTime + ", commentCount=" + commentCount + "]";
	}
	public Question(int id, String title, String content, int userId, Date createdTime, int commentCount) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.createdTime = createdTime;
		this.commentCount = commentCount;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
