package com.primeton.renyh.demo.utils;


import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;

import com.primeton.renyh.demo.enums.Common;
import com.primeton.renyh.demo.exception.DemoException;
import com.primeton.renyh.demo.model.Organization;
import com.primeton.renyh.demo.model.User;

/**
 * 对数据进行校验的工具类
 * @author 任宇航
 *
 */
public class ValidateUtil {

	
	
	
	
	//校验用户登录时用户名和密码不能为空               
	public static User loginUser_IsNotNull(User user) throws DemoException{

		if (user.getUserName()== null && user.getUserName().trim().length() == 0) {
			//用户名为空抛出异常
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR,
					Common.LOGIN_USERNAMEISNULL.getCode(),Common.LOGIN_USERNAMEISNULL.getMsg()		);
		}
		if (user.getPassWord() == null && user.getPassWord().trim().length() == 0
				&& user.getPassWord().length()< 18) {
			//密码为空抛出异常
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR,
					Common.LOGIN_PASSWORDISNULL.getCode(),Common.LOGIN_PASSWORDISNULL.getMsg()		);
		}
		return user;
	}
	


	//校验创建的用户信息是否规范
	public static String checkCreateUser(User user) {
		if(StringUtils.isEmpty(user.getUserName()) ){
			return "用户名为空";
		}else if(StringUtils.isEmpty(user.getPassWord())){
			return "密码为null";
		}else if(user.getSex()!='男'&& user.getSex() != '女'){
			return "请正确填写性别";
		}else if(user.getAge() < 0 && user.getAge() >100){
			return "请正确填写年龄";
		}else{
			return "ok";
		}
	}

	
	//校验要创建的部门信息是否为空
	public static Organization checkCreateOrganization(Organization organization) throws DemoException{
		//校验是否有上级部门
		if(organization.getPid() < 0){
			//没有上级部门的时候校验部门信息是否为null
			if(StringUtils.isEmpty(organization.getDepartment())){
				throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR,
						Common.CREATE_DEPARTMENTISNULL.getCode(), Common.CREATE_DEPARTMENTISNULL.getMsg());
			}
		}else{
			//判断部门信息是否为空
			if(StringUtils.isEmpty(organization.getDepartment())){
				throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR,
						Common.CREATE_DEPARTMENTISNULL.getCode(), Common.CREATE_DEPARTMENTISNULL.getMsg());
			}
			//判断上级部门是否为空
			if(organization.getPid() < 0){
				throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR,
						Common.CREATE_SUPERIORDEPARTMENTSISNULL.getCode(), Common.CREATE_SUPERIORDEPARTMENTSISNULL.getMsg());
			}
		}
		return organization;
	}
}
