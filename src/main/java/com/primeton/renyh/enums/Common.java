package com.primeton.renyh.enums;
/**
 * 系统异常定义
 * @author 任宇航
 *
 */
public enum Common {

	SYSTEM_ERROR(10000,"系统错误异常码"),
	ILLEGAL_PARAMETER(10001,"非法参数错误异常码"),
	UNAUTHORIZED_ERROR(10002,"未授权错误异常码"),
	NOT_LOGIN(10003,"未登录错误异常码"),
	LOCK_ACQUIRED_TIMEOUT(10004,"锁获取超时错误异常码"),
	OUT_OF_USER_COUNT(10005,"锁获取超时错误异常码"),
	REFRESH_TOKEN(10006,"TOKEN刷新失败"),
	LOGIN_USERNAMEISNULL(10013,"用戶登录的账号不正确"),
	LOGIN_PASSWORDISNULL(10014,"用戶登錄的密码不正确"),
	CHECK_USER(10018,"请规范填写数据"),
	USERNAME_EXISTS(10019,"该用户名以存在"),
	DATAILLEGALITY(10020,"数据不合法"),
	USER_NOTEXISTS(10021,"该用户不存在"),
	CREATE_DEPARTMENTISNULL(10023,"部门信息为空"),
	CREATE_SUPERIORDEPARTMENTSISNULL(10024,"上级部门信息为空"),
	DEPARTMENT_ISEXIST(10025,"该部门已经存在"),
	DEPARTMENT_ISNOTEXIST(10026,"该部门不存在"),
	SUPERIORDEPARTMENTS_ISEXIST(10027,"该上级部门已经存在"),
	SUPERIORDEPARTMENTS_ISNOTEXIST(10028,"该上级部门不存在"),
	OPERATIONDATABESE_ERROR(10029,"操作数据库失败"),
	PARENTBUSINESS_ISDEPARTMENT(10030,"上级部门不能是本部门"),
	PARAM_IN_NULL(10031,"参数为空"),
	SELECT_IS_NULL(10032,"查询失败"),
	;
	
	private Integer code;
	
	private String msg;
	
	private Common(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	
	
}
