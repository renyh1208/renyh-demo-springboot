package com.primeton.renyh.demo.service;

import com.github.pagehelper.PageInfo;
import com.primeton.renyh.demo.common.ResponseResult;
import com.primeton.renyh.demo.exception.DemoException;
import com.primeton.renyh.demo.model.User;

public interface IUserService {

	/**
	 * 查询用户
	 * @param id 用户id
	 * @return
	 * @throws DemoException
	 */
	public ResponseResult<User> getUser(Integer id)throws DemoException;
	
	/**
	 * 删除用户
	 * @param id 用户id
	 * @return
	 * @throws DemoException
	 */
	public ResponseResult<User> removeUser(Integer id) throws DemoException;

	/**
	 * 创建用户
	 * @param user 用户实体对象
	 * @return
	 * @throws DemoException
	 */
	public ResponseResult<User> createUser(User user) throws DemoException;

	/**
	 * 修改用户
	 * @param user 用户实体对象
	 * @return
	 * @throws DemoException
	 */
	public ResponseResult<User> modifyUser(User user) throws DemoException;
	
	/**
	 * 用户登录
	 * @param user 用户实体对象
	 * @return
	 * @throws DemoException
	 */
	public ResponseResult<User> login(User user) throws DemoException;

	/**
	 * 查询用户数据
	 * @param pageIndex 起始页数
	 * @param pageSize	每页数据
	 * @param userName  用户名
	 * @param sid		组织id
	 * @return
	 * @throws DemoException
	 */
	public ResponseResult<PageInfo> queryPageUsers(Integer pageIndex, Integer pageSize, String userName, Integer sid)throws DemoException;
	
}
