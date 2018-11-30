package com.primeton.renyh.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.primeton.renyh.common.ResponseResult;
import com.primeton.renyh.exception.DemoException;
import com.primeton.renyh.model.Organization;


public interface OrganizationService {


	/**
	 * 查询组织
	 * @param id 组织id
	 * @return
	 * @author 任宇航
	 * @throws Exception
	 */
	public ResponseResult<Organization> getById(int id)throws DemoException;
	
	/**
	 * 根据id删除组织
	 * @param id 组织id
	 * @return
	 * @throws DemoException 
	 * @throws Exception
	 */
	public ResponseResult<Organization>  removeOrganization(int id) throws DemoException;

	/**
	 * 创建组织
	 * @param organization 组织实体对象
	 * @return
	 * @throws DemoException
	 */
	public ResponseResult<Organization> createOrganization(Organization organization) throws DemoException;

	/**
	 * 修改组织
	 * @param organization 组织实体对象
	 * @return
	 * @throws DemoException 
	 * @throws Exception
	 */
	public ResponseResult<Organization> modifyOrganization(Organization organization) throws DemoException;
	
	/**
	 * 查询该部门下有多少子部门
	 * @param id 上级部门id
	 * @return
	 * @throws DemoException 
	 * @throws Exception
	 */
	public ResponseResult<List<Organization>> queryListOrganizations(Integer id)throws DemoException;
	
	/**
	 * 查询该组织下员工列表
	 * @param pageIndex 起始页数
	 * @param pageSize	每页条数
	 * @param oid		组织id
	 * @return
	 * @throws DemoException 
	 */
	public ResponseResult<PageInfo> queryOrganizationGroup(Integer pageIndex, Integer pageSize, Integer oid)throws DemoException;
}
