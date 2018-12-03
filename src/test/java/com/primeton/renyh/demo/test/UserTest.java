package com.primeton.renyh.demo.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.primeton.renyh.demo.controller.UserController;
import com.primeton.renyh.demo.model.User;
/**
 * 用户测试类
 * @author 任宇航
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Autowired
	private UserController userController;
	
	
	@Test
	public void testUser() throws Exception {
		//创建用户对象
		User user = new User();
		user.setUserName("test_demo7");
		user.setPassWord("000000");
		user.setOid(1);
		user.setSex('男');
		user.setAge(24);
		user.setBirthday(null);
		
		//判断数据库中是否有该对象，有则删除
		assertNotNull(userController.removeUser(user.getUid()));
		
		//创建用户
		user= userController.createUser(user).data;
		
		assertNotNull(user.getUid());
		assertEquals(user.getPassWord(), "000000");
		
		//改变用户信息
		user.setPassWord("123456");
		user.setUserName("test_demo9");
		
		//修改用户信息
		user = userController.modifyUser(user).data;
		
		assertEquals(user.getPassWord(), "123456");
		
		//用户登录
		assertNotNull(userController.login(user).data);
		
		//查询该部门下的员工/根据名称查询
		assertNotNull(userController.queryPageUsers(1, 10, "阿花",1).data);
		
		//将用户删除
		assertNotNull(userController.removeUser(user.getUid()));
		
		//通过id查询用户
		assertNull(userController.getUser(user.getUid()).data);
		
	}
	
}
