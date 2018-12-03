package com.primeton.renyh.demo.common;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Integer state;
	public T data;
	private Integer code;
	private String message;
	public static final Integer STATE_OK=200;
	public static final String STATE_ERR="操作失败";

	
	public ResponseResult(Integer code) {
		super();
		this.code = code;
	}

	public ResponseResult(String message) {
		super();
		this.message = message;
	}
	
	public ResponseResult(T data, Integer code) {
		this.data = data;
		this.code = code;
	}
	
	public ResponseResult(T data, String message) {
		this.data = data;
		this.message = message;
	}
	public ResponseResult(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public ResponseResult(T data, Integer code, String message) {
		super();
		this.data = data;
		this.code = code;
		this.message = message;
	}
	public ResponseResult(Integer state, T data, Integer code, String message) {
		super();
		this.state = state;
		this.data = data;
		this.code = code;
		this.message = message;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ResponseResult [state=" + state + ", data=" + data + ", code=" + code + ", message=" + message + "]";
	}
	
	public static <T> ResponseResult<T> success(int code){
		return new ResponseResult<T>(code);
	};
	
	public static <T> ResponseResult<T> success(T data,int code){
		return new ResponseResult<T>(data,code);
	};
	
	public static <T> ResponseResult<T> error(String message){
		return new ResponseResult<T>(message);
	};
	
  
}