package com.primeton.renyh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.primeton.renyh.common.ResponseResult;
import com.primeton.renyh.exception.DemoException;
import com.primeton.renyh.model.User;
import com.primeton.renyh.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/*
 * 用户的增删改查控制层
 * 
 */
@RestController
@RequestMapping(value = "/api/users")
@Api(value = "用户的controller层" , tags = "用户接口")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 查询用户
	 * @param id 用户id
	 * @return
	 * @throws DemoException
	 */
	@ApiOperation( value = "查询用户")
	@GetMapping("/{id}")
	public ResponseResult<User> getById(@ApiParam("用户id") 
								@PathVariable("id") Integer id) throws DemoException{
		return userService.getById(id);
	}
	
	/**
	 * 删除用户
	 * @param id 用户id
	 * @return
	 * @throws DemoException
	 */
	@ApiOperation(value = "根据id删除用户")
	@DeleteMapping("/{id}")
	public ResponseResult<User> removeUser(@ApiParam("用户的id")
								@PathVariable("id") Integer id) throws DemoException{
		return userService.removeUser(id);
	}

	/**
	 * 创建用户
	 * @param user 用户实体对象
	 * @return
	 * @throws DemoException
	 */
	@ApiOperation(value = "添加用户")
	@PostMapping
	public ResponseResult<User> createUser(@RequestBody  @ApiParam("用户的全部信息")
								User user) throws DemoException{
		return userService.createUser(user);
	}

	
	/**
	 * 修改用户
	 * @param user 用户实体对象
	 * @return
	 * @throws DemoException
	 */
	@ApiOperation(value = "修改用户信息")
	@PutMapping
	public ResponseResult<User> modifyUser(@RequestBody @ApiParam("要修改的用户信息")
								 User user) throws DemoException{
		return userService.modifyUser(user);
	}
	
	/**
	 * 用户登录
	 * @param user 用户实体对象
	 * @return
	 * @throws DemoException
	 */
	@ApiOperation(value = "用户登录")
	@PostMapping("/actions/login")
	public ResponseResult<User> login(@RequestBody @ApiParam("用户登录")
								 User user) throws DemoException{
		return userService.login(user);
		 
	}
	
	
	
	/**
	 * 查询用户数据
	 * @param pageIndex 起始页数
	 * @param pageSize	每页数据
	 * @param userName  用户名
	 * @param oid		组织id
	 * @return
	 * @throws DemoException
	 */
	 @ApiOperation(value = "根据条件分页查询")
	 @GetMapping
	 public ResponseResult<PageInfo> queryPageUsers(@ApiParam("起始页数") Integer pageIndex,
			 @ApiParam("每页数据") Integer pageSize,@ApiParam("用户名") String userName,@ApiParam("组织id") Integer oid) throws DemoException{
		 return userService.queryPageUsers(pageIndex,pageSize,userName,oid);
	 }


}
