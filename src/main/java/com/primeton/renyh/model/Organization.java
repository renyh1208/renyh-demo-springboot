package com.primeton.renyh.model;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Component
@ApiModel(value = "组织实体类" ,description = "这是组织对象")
public class Organization {
	
	//主键
	@ApiModelProperty(value = "组织主键")
	private int oid;
	
	@ApiModelProperty(value = "组织名称")
	private String department;
	
	@ApiModelProperty(value = "上级部门id")
	private int  pid;
	
	//无参构造
	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}

	//..set/get方法
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Organization(int oid, String department, int pid) {
		super();
		this.oid = oid;
		this.department = department;
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "Organization [oid=" + oid + ", department=" + department + ", pid=" + pid + "]";
	}
	
	

	
	
}
