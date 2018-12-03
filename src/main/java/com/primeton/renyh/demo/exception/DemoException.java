package com.primeton.renyh.demo.exception;

import org.springframework.http.HttpStatus;

public class DemoException extends Exception {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -3876319857610564892L;


	/**
	 * http状态码
	 */
	private HttpStatus httpStatus;

	private Integer errCode;

	private String message;

	private Object[] params;

	/**
	 * 额外信息
	 */
	private Object additional;

	/**
	 * 无参构造方法.
	 *
	 * @deprecated 不推荐使用
	 */
	public DemoException() {
		super();
	}
	
	
	public DemoException(HttpStatus httpStatus, Integer errCode, String message) {
		this.httpStatus = httpStatus;
		this.errCode = errCode;
		this.message = message;
	}

	

	/**
	 * 构造方法.
	 *
	 * @param httpStatus http状态码
	 * @param errCode 异常编号
	 * @param message 用户自定义异常描述信息
	 * @param params  异常描述时用到的格式化参数
	 * @param cause   上层异常
	 * @param additional 额外信息，前端用
	 */
	public DemoException(HttpStatus httpStatus, Integer errCode, String message, Object[] params, Throwable cause, Object additional) {
		super(cause);
		this.httpStatus = httpStatus;
		this.errCode = errCode;
		this.message = message;
		this.params = params;
		this.additional = additional;
	}

	/**
	 * 构造方法.
	 *
	 * @param errCode 异常编号
	 * @param message 用户自定义异常描述信息
	 * @param params  异常描述时用到的格式化参数
	 */
	public DemoException(HttpStatus httpStatus,Integer errCode, String message, Object... params) {
		this(httpStatus, errCode, message, params, null);
	}

	/**
	 * 构造方法.
	 *
	 * @param errCode 异常编号
	 * @param message 用户自定义异常描述信息
	 * @param params  异常描述时用到的格式化参数
	 * @param additional 额外信息，前端用
	 */
	public DemoException(HttpStatus httpStatus,Integer errCode, String message, Object[] params, Object additional) {
		this(httpStatus,errCode, message, params, null, additional);
	}

	/**
	 * 构造方法.
	 *
	 * @param errCode 异常编号
	 * @param message 用户自定义异常描述信息
	 * @param cause   上层异常
	 */
	public DemoException(HttpStatus httpStatus,Integer errCode, Throwable cause) {
		this(httpStatus,errCode, null , null, cause, null);
	}

	/**
	 * 构造方法.
	 *
	 * @param errCode 异常编号
	 * @param message 用户自定义异常描述信息
	 * @param cause   上层异常
	 */
	public DemoException(HttpStatus httpStatus,Integer errCode, String message, Throwable cause) {
		this(httpStatus,errCode, message, null, cause, null);
	}

	/**
	 * 构造方法.
	 *
	 * @param errCode 异常编号
	 * @param message 用户自定义异常描述信息
	 * @param params  异常描述时用到的格式化参数
	 * @param cause   上层异常
	 */
	public DemoException(HttpStatus httpStatus,Integer errCode, String message, Object[] params, Throwable cause) {
		this(httpStatus,errCode, message, params, cause, null);
	}






	/**
	 * 只取得异常信息，不带编号
	 *
	 * @return 异常信息，不带编号
	 */
	public String getMessageOnly() {
		return null;
		//return ValidateUtil.format(message, params);
	}

	/**
	 * 取得额外信息
	 *
	 * @return 额外信息
	 */
	public Object getAdditional() {
		return additional;
	}

	/**
	 * 构造方法不够齐全，这个方法还是有必要的，否则补全构造方法。<br>
	 *
	 * @param additional The additional to set.
	 */
	public void setAdditional(Object additional) {
		this.additional = additional;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public Integer getErrCode() {
		return errCode;
	}

	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
