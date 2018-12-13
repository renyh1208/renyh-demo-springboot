package com.primeton.renyh.demo.controller;


import java.util.List;

import com.primeton.renyh.demo.common.ResponseResult;
import com.primeton.renyh.demo.model.Organization;
import com.primeton.renyh.demo.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.github.pagehelper.PageInfo;
import com.primeton.renyh.demo.exception.DemoException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * 组织增删改查的控制层  
 * @author 任宇航
 *
 */
@RestController
@RequestMapping(value = "/api/organizations")
@Api( value ="组织对外接口" ,tags="组织的增删改查")
public class OrganizationController {

	@Autowired
	private IOrganizationService organizationService;

	/**
	 * 查询组织
	 * @param id 组织id
	 * @return
	 * @author 任宇航
	 * @throws Exception
	 */
	@ApiOperation( value = "查询组织")
	@GetMapping("/{id}")
	public ResponseResult<Organization> getById(@ApiParam("根据id查询组织")
										@PathVariable("id") Integer id) throws DemoException{
		return organizationService.getById(id);
	}
	
	/**
	 * 删除组织
	 * @param id 组织id
	 * @return
	 * @throws DemoException 
	 * @throws Exception
	 */
	@ApiOperation(value = "删除组织")
	@DeleteMapping("/{id}")
	public ResponseResult<Organization> removeOrganization(@ApiParam("要删除的组织id")
										@PathVariable("id") Integer id) throws DemoException{
		return	organizationService.removeOrganization(id);
	}

	/**
	 * 创建组织
	 * @param organization 组织实体对象
	 * @return
	 * @throws DemoException
	 */
	@ApiOperation(value = "创建组织")
	@PostMapping
	public ResponseResult<Organization> createOrganization(@RequestBody 
								@ApiParam("创建组织信息") Organization organization) throws DemoException{
		return organizationService.createOrganization(organization);
	 
	}

	
	/**
	 * 修改组织
	 * @param organization 组织实体对象
	 * @return
	 * @throws DemoException 
	 * @throws Exception
	 */
	@ApiOperation(value = "修改组织")
	@PutMapping
	public ResponseResult<Organization> modifyOrganization(@RequestBody 
								@ApiParam("修改组织实体对象") Organization organization) throws DemoException{
		return 	organizationService.modifyOrganization(organization);
		
	}


	/**
	 * 查询组织所有部门
	 * @return
	 * @throws DemoException
	 * @throws Exception
	 */
	@ApiOperation(value = "查询组织所有部门")
	@GetMapping("/queryAll")
	public ResponseResult<List<Organization>> queryOrganizations() throws DemoException {
		return organizationService.queryOrganizations();
	}

	/**
	 * 查询该部门下有多少子部门
	 * @param id 上级部门id
	 * @return
	 * @throws DemoException 
	 * @throws Exception
	 */
	@ApiOperation(value = "根据组织id查询该组织下子部门")
	@GetMapping("/actions/query/{id}")
	public ResponseResult<List<Organization>> queryListOrganizations(@ApiParam("根据组织id查询子部门")
											  @PathVariable("id") Integer id) throws DemoException {
		return organizationService.queryListOrganizations(id);
	}
	
	
	/**
	 * 查询该组织下员工列表
	 * @param pageIndex 起始页数
	 * @param pageSize	每页条数
	 * @param oid		组织id
	 * @return
	 * @throws DemoException 
	 */
	@GetMapping
	@ApiOperation(value = "查询该部门多少人员",response = ResponseResult.class)
	public ResponseResult<PageInfo> queryOrganizationGroup(@ApiParam("起始页数")Integer pageIndex,
			@ApiParam("每页条数") Integer pageSize,@ApiParam("部门id") Integer oid) throws DemoException{
		return organizationService.queryOrganizationGroup(pageIndex,pageSize,oid);
	}
	
	
}
