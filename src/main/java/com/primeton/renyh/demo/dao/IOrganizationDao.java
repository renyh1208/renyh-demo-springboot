package com.primeton.renyh.demo.dao;

import java.util.List;

import com.primeton.renyh.demo.model.Organization;
import com.primeton.renyh.demo.model.User;
import org.apache.ibatis.annotations.Mapper;

import com.primeton.renyh.demo.exception.DemoException;

@Mapper
public interface IOrganizationDao {

	/**
	 * 查询组织
	 * @param oid 组织id
	 * @return
	 * @author 任宇航
	 * @throws Exception
	 */
	Organization getById(int oid);
	
	/**
	 * 查询组织
	 * @param  department 部门名称
	 * @return
	 * @throws Exception
	 */
	Organization getByDepartment(String department);
	
	/**
	 * 根据id删除组织
	 * @param oid 组织id
	 * @return
	 * @throws DemoException 
	 * @throws Exception
	 */
	int deleteOrganization(int oid);

	/**
	 * 创建组织
	 * @param organization 组织实体对象
	 * @return
	 * @throws DemoException
	 */
	int insertOrganization(Organization organization);

	/**
	 * 修改组织
	 * @param organization 组织实体对象
	 * @return
	 * @throws DemoException 
	 * @throws Exception
	 */
	int updateOrganization(Organization organization);
	
	/**
	 * 查询该部门下有多少子部门
	 * @param pid 上级部门id
	 * @return
	 * @throws DemoException 
	 * @throws Exception
	 */
	List<Organization> queryListOrganizations(Integer pid);

	/**
	 * 查询该组织下员工列表
	 * @param pageIndex 起始页数
	 * @param pageSize	每页条数
	 * @param oid		组织id
	 * @return
	 * @throws DemoException 
	 */
	List<User> queryOrganizationGroup(Integer oid);
}
