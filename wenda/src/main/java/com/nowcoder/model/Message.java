package com.nowcoder.model;

import java.util.Date;

public class Message {
	private int id;
	private int fromId;
	private int toId;
	private String content;
	private Date createdDate;
	private int hasRead;
	private String conversationId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFromId() {
		return fromId;
	}
	public void setFromId(int fromId) {
		this.fromId = fromId;
	}
	public int getToId() {
		return toId;
	}
	public void setToId(int toId) {
		this.toId = toId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getHasRead() {
		return hasRead;
	}
	public void setHasRead(int hasRead) {
		this.hasRead = hasRead;
	}
	//保持conversationId保持一致
	public String getConversationId() {
		if(fromId<toId) {
			return String.format("%d_%d", fromId, toId);
		}else {
			return String.format("%d_%d", toId, fromId);
		}
	}
	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", fromId=" + fromId + ", toId=" + toId + ", content=" + content + ", createdDate="
				+ createdDate + ", hasRead=" + hasRead + ", conversationId=" + conversationId + "]";
	}
	public Message(int id, int fromId, int toId, String content, Date createdDate, int hasRead, String conversationId) {
		super();
		this.id = id;
		this.fromId = fromId;
		this.toId = toId;
		this.content = content;
		this.createdDate = createdDate;
		this.hasRead = hasRead;
		this.conversationId = conversationId;
	}
	public Message() {
	}


}
