package com.wechatmomentsmvpdemo.moments.bean;

import com.mylibrary.ui.bean.BaseBean;

/**
 * Created by kson on 2017/6/25.
 */

public class CommentItem extends BaseBean {

	private String id;
	private User user;
	private User toReplyUser;
	private String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getToReplyUser() {
		return toReplyUser;
	}
	public void setToReplyUser(User toReplyUser) {
		this.toReplyUser = toReplyUser;
	}
	
}
