package com.wechatmomentsmvpdemo.moments.bean;

import com.mylibrary.ui.bean.BaseBean;

import java.io.Serializable;

/**
 * Created by kson on 2017/6/25.
 */

public class FavortItem extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private User user;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
