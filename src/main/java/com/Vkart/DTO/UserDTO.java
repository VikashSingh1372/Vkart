 package com.Vkart.DTO;


public class UserDTO {
	private int userId;

	private String userName;

	private String userEmail;

	private String userPassword;

	private String userGender;
	
	private String role;

	
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getUserGender() {
		return userGender;
	}


	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public UserDTO(String userName, String userEmail, String userPassword, String userGender, String role) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userGender = userGender;
		this.role = role;
	}




}
