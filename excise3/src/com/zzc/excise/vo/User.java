package com.zzc.excise.vo;

public class User {
	private String userName;
	private String password;
	private String chrName;
	private String emailAddress;
	private String province;
	private String city;
	
	public User() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getChrName() {
		return chrName;
	}

	public void setChrName(String chrName) {
		this.chrName = chrName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "User{" +
				"userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", chrName='" + chrName + '\'' +
				", emailAddress='" + emailAddress + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				'}';
	}
}
