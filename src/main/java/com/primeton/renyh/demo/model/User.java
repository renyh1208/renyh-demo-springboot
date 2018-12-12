package com.primeton.renyh.demo.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Component
@ApiModel(value ="用户实体类",description="这是用户对象")
public class User {
	
	//主键
	@ApiModelProperty(value = "用户主键")
	private int uid;
	
	@ApiModelProperty(value = "用户名")
	private String userName;
	
	@ApiModelProperty(value = "用户密码")
	private String passWord;
	
	@ApiModelProperty(value = "组织id" )
	private int oid;
	
	@ApiModelProperty(value = "年龄")
	private int age;
	
	@ApiModelProperty(value = "性别")
	private char sex;
	
	@ApiModelProperty(value = "生日")
	private Date birthday;

	// 。。get/set方法

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	// 无参构造
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 有参构造
	public User(int uid, String userName, String passWord, int oid, int age, char sex, Date birthday) {
		super();
		this.uid = uid;
		this.userName = userName;
		this.passWord = passWord;
		this.oid = oid;
		this.age = age;
		this.sex = sex;
		this.birthday = birthday;
	}
	// 有参构造
	public User(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
	// tostring方法
	@Override
	public String toString() {
		return "User [uid=" + uid + ", userName=" + userName + ", passWord=" + passWord + ", oid=" + oid + ", age="

				+ age + ", sex=" + sex + ", birthday=" + birthday + "]";
	}

}
