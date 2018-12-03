package com.primeton.renyh.demo.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.primeton.renyh.demo.controller.OrganizationController;
import com.primeton.renyh.demo.model.Organization;

/**
 * 组织测试类
 * @author 任宇航
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationTest {

	@Autowired
	private OrganizationController organizationController;
	
	
	@Test
	public void TestOrganization()throws Exception{
		//添加部门信息
		Organization organization = new Organization();
		organization.setDepartment("test_demo");
		organization.setPid(1);
		
		//判断数据库中是否有该部门，有则删除
		assertNotNull(organizationController.removeOrganization(organization.getOid()));
		
		//创建部门信息
		organization =  organizationController.createOrganization(organization).data;
				
		assertNotNull(organization.getOid());
		
		//对部门信息进行改变
		organization.setDepartment("test_demo1");
		organization.setPid(3);
		
		//对部门信息进行修改
		organization = organizationController.modifyOrganization(organization).data;
		
		//查询该部门人员列表
		assertNotNull(organizationController.queryOrganizationGroup(1, 10, 3).data);
		
		//查询该部门下有多少子部门
		assertNotNull(organizationController.queryListOrganizations(organization.getPid()).data);
		
		//根据id将部门删除
		assertNotNull(organizationController.removeOrganization(organization.getOid()));
		
		//根据id查询
		assertNull(organizationController.getById(organization.getOid()).data);
		
	}
	
	
}
