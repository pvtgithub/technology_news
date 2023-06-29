package com.technology.api.output;

import com.technology.dto.UserDTO;
import java.util.*;
public class UserOutput {
	private int page;
	private int totalPage;
	private List<UserDTO> userList = new ArrayList<>();
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<UserDTO> getUserList() {
		return userList;
	}
	public void setUserList(List<UserDTO> userList) {
		this.userList = userList;
	}

}
