package com.primeton.renyh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.primeton.renyh.exception.DemoException;
import com.primeton.renyh.model.User;

@Mapper
public interface UserDao {

	/**
	 * 查询用户
	 * @param id 用户id
	 * @return
	 * @throws DemoException
	 */
	User getById(Integer uid);
	
	/**
	 * 查询用户
	 * @param userName 用户名称
	 * @return
	 * @throws DemoException
	 */
	User getByName(@Param("userName") String userName);
	
	/**
	 * 删除用户
	 * @param id 用户id
	 * @return
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
	 * @param pageIndex 起始页数
	 * @param pageSize	每页数据
	 * @param userName  用户名
	 * @param oid		组织id
	 * @return
	 * @throws DemoException
	 */
	List<User> queryPageUsers(@Param("userName") String userName, @Param("oid") Integer sid);

}
