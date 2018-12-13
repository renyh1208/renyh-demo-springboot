package com.primeton.renyh.demo.serviceimpl;

import java.util.List;

import com.primeton.renyh.demo.common.ResponseResult;
import com.primeton.renyh.demo.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.primeton.renyh.demo.dao.IOrganizationDao;
import com.primeton.renyh.demo.enums.Common;
import com.primeton.renyh.demo.exception.DemoException;
import com.primeton.renyh.demo.model.Organization;
import com.primeton.renyh.demo.model.User;
import com.primeton.renyh.demo.service.IOrganizationService;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrganizationServiceImpl implements IOrganizationService {

	/**
	 * 引入dao层
	 */
	@Autowired
	private IOrganizationDao organizationDao;

	/**
	 * 查询组织
	 * @param id 组织id
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseResult<Organization> getById(int id) throws DemoException {
		//校验操作是否成功返回数据
		Organization data = organizationDao.getById(id);
		if (data != null) {
			return ResponseResult.success(data, ResponseResult.STATE_OK);
		}
		return ResponseResult.error(ResponseResult.STATE_ERR);
	}
	
	/**
	 * 查询组织
	 * @param  department 部门名称
	 * @return
	 * @throws Exception
	 */
	public ResponseResult<Organization> getByDepartment(String department) throws DemoException {
		//校验操作是否成功返回数据
		Organization data = organizationDao.getByDepartment(department);
		if (data != null) {
			return ResponseResult.success(data, ResponseResult.STATE_OK);
		}
		return ResponseResult.error(ResponseResult.STATE_ERR);
	}


	/**
	 * 根据id删除组织
	 * @param id 组织id
	 * @return
	 * @throws DemoException 
	 * @throws Exception
	 */
	@Override
	@Transactional
	public ResponseResult<Organization> removeOrganization(int id) throws DemoException {
		Organization data = organizationDao.getById(id);
		if (organizationDao.deleteOrganization(id) != 0) {
			return ResponseResult.success(data, ResponseResult.STATE_OK);
		}
		return ResponseResult.error(ResponseResult.STATE_ERR);
	}
	
	/**
	 * 创建组织
	 * @param organization 组织实体对象
	 * @return
	 * @throws DemoException
	 */
	@Override
	@Transactional
	public ResponseResult<Organization> createOrganization(Organization organization) throws DemoException {
		// 校验数据， 为空抛出异常
		Organization data = ValidateUtil.checkCreateOrganization(organization);
		
		//查询要创建的组织存在抛出异常
		checkDepartment(organization);

		//判断有没有上级部门，上级部门存不存在
		checkPidExists(data);

		//添加数据进行返回
		if (organizationDao.insertOrganization(data) == 0) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, Common.OPERATIONDATABESE_ERROR.getCode(),
					Common.OPERATIONDATABESE_ERROR.getMsg());
		}
		return getByDepartment(data.getDepartment());
		
	}

	/**
	 * 修改组织
	 * @param organization 组织实体对象
	 * @return
	 * @throws DemoException 
	 * @throws Exception
	 */
	@Override
	@Transactional
	public ResponseResult<Organization> modifyOrganization(Organization organization) throws DemoException {
		//判断数据是否为空，为空抛出异常
		Organization data = ValidateUtil.checkCreateOrganization(organization);
		
		//查询要修改的组织不存在抛出异常
		if(organizationDao.getById(data.getOid()) == null){
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, Common.DEPARTMENT_ISNOTEXIST.getCode(),
					Common.DEPARTMENT_ISNOTEXIST.getMsg());
		}
		
		//判断有没有上级部门和上级部门名称存不存在
		checkPidExists(data);

		//查询要修改的部门是否已经存在
		checkDepartment(organization);
		
		//修改数据进行返回
		if (organizationDao.updateOrganization(data) == 0) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, Common.OPERATIONDATABESE_ERROR.getCode(),
					Common.OPERATIONDATABESE_ERROR.getMsg());
		}
		return getById(organization.getOid());
	}

	/**
	 * 查询所有部门
	 * @return
	 * @throws DemoException
	 * @throws Exception
	 */
	public ResponseResult<List<Organization>> queryOrganizations()throws DemoException{
		List<Organization> listOrganizations = organizationDao.queryOrganizations();
		if (listOrganizations != null) {
			return ResponseResult.success(listOrganizations, ResponseResult.STATE_OK);
		}
		return ResponseResult.error(ResponseResult.STATE_ERR);
	}


	/**
	 * 查询该部门下有多少子部门
	 * @param id 上级部门id
	 * @return
	 * @throws DemoException 
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = true)
	public ResponseResult<List<Organization>> queryListOrganizations(Integer id) throws DemoException {
		//判断参数是否为null和是否有该部门
//		if(id != null && organizationDao.getById(id) == null){
//			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, Common.DEPARTMENT_ISNOTEXIST.getCode(),
//					Common.DEPARTMENT_ISNOTEXIST.getMsg());
//		}
		List<Organization> listOrganizations = null;
		if(id == 0  ){
			listOrganizations = organizationDao.queryOrganizations();
		}else{
			listOrganizations = organizationDao.queryListOrganizations(id);
		}
		if (listOrganizations != null) {
			return ResponseResult.success(listOrganizations, ResponseResult.STATE_OK);
		}
		return ResponseResult.error(ResponseResult.STATE_ERR);
	}
	
	
	/**
	 * 查询该组织下员工列表
	 * @param pageIndex 起始页数
	 * @param pageSize	每页条数
	 * @param oid		组织id
	 * @return
	 * @throws DemoException 
	 */
	@Override
	@Transactional(readOnly = true)
	public ResponseResult<PageInfo> queryOrganizationGroup(Integer pageIndex, Integer pageSize, Integer oid) throws DemoException {
		if(oid == null){
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, Common.PARAM_IN_NULL.getCode(), 
					Common.PARAM_IN_NULL.getMsg());
		}
		if(pageSize == null){
			pageSize = 10;
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<User> list = organizationDao.queryOrganizationGroup(oid);
		if(list == null){
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, Common.SELECT_IS_NULL.getCode(),
					Common.SELECT_IS_NULL.getMsg());
		}
		PageInfo pageinfo = new PageInfo<>(list,pageSize);
		return new ResponseResult<PageInfo>(pageinfo,"查询成功");
	}




	//校验有没有上级部门和上级部门存不存在
	private void checkPidExists(Organization data) throws DemoException{
		//判断上级部门是不是本部门
		if(data.getOid() == data.getPid()){
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, Common.PARENTBUSINESS_ISDEPARTMENT.getCode(),
					Common.PARENTBUSINESS_ISDEPARTMENT.getMsg());
		}
		if(data.getPid() != 0 && organizationDao.getById(data.getPid()) == null ){
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, Common.SUPERIORDEPARTMENTS_ISNOTEXIST.getCode(),
					Common.SUPERIORDEPARTMENTS_ISNOTEXIST.getMsg());
		}
		
	}
	
	//校验该组织是否已经存在
	private void checkDepartment(Organization data) throws DemoException{
		if(organizationDao.getByDepartment(data.getDepartment()) != null){
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, Common.DEPARTMENT_ISEXIST.getCode(),
					Common.DEPARTMENT_ISEXIST.getMsg());
		}
	}



	
	
}
