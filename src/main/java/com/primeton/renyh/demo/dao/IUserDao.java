package com.primeton.renyh.demo.dao;

import java.util.List;

import com.primeton.renyh.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.primeton.renyh.demo.exception.DemoException;

@Mapper
public interface IUserDao {

	/**
	 * 查询用户
	 * @param uid 用户id
	 * @return
	 * @throws DemoException
	 */
	User getUser(Integer uid);
	
	/**
	 * 查询用户
	 */
	User getByName(@Param("userName") String userName);
	
	/**
	 * 删除用户
	 * @throws DemoException
	 */
	int deleteUser(Integer uid);
	
	/**
	 * 创建用户
	 * @param user 用户实体对象
	 * @return
	 * @throws DemoException
	 */
	int insertUser(User user);
	
	/**
	 * 修改用户
	 * @param user 用户实体对象
	 * @return
	 * @throws DemoException
	 */
	int updateUser(User user);

	/**
	 * 用户登录
	 * @param user 用户实体对象
	 * @return
	 * @throws DemoException
	 */
	User getByUserNameAndPassWord(User user);

	/**
	 * 查询用户数据
	 * @param userName  用户名
	 * @return
	 * @throws DemoException
	 */
	List<User> queryPageUsers(@Param("userName") String userName, @Param("oid") Integer sid);

}
