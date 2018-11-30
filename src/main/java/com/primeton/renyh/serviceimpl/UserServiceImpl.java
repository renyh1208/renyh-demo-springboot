package com.primeton.renyh.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.primeton.renyh.common.ResponseResult;
import com.primeton.renyh.dao.UserDao;
import com.primeton.renyh.enums.Common;
import com.primeton.renyh.exception.DemoException;
import com.primeton.renyh.model.User;
import com.primeton.renyh.service.UserService;
import com.primeton.renyh.utils.ValidateUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	
	/**
	 * 查询用户
	 * @param id 用户id
	 * @return
	 * @throws DemoException
	 */
	@Override
	public ResponseResult<User> getById(Integer id) {
		//校验是否成功返回数据
		return checkIsSuccessFu(userDao.getById(id));
	}
	


	/**
	 * 删除用户
	 * @param id 用户id
	 * @return
	 * @throws DemoException
	 */
	@Override
	@Transactional
	public ResponseResult<User> removeUser(Integer id) {
		User user = userDao.getById(id);
		int removeUser = userDao.deleteUser(id);
		if (removeUser != 0) {
			return ResponseResult.success(user, ResponseResult.STATE_OK);
		}
		return ResponseResult.error(ResponseResult.STATE_ERR);
	}
	
	/**
	 * 创建用户
	 * @param user 用户实体对象
	 * @return
	 * @throws DemoException
	 */
	@Override
	@Transactional
	public ResponseResult<User> createUser(User user) throws DemoException {

		// 校验数据是否为空,数据的合法性
		checkData(user);

		// 校验用户名是否已经存在
		checkUserName(user.getUserName());

		// 创建用户
		userDao.insertUser(user);
		
		//校验是否成功返回数据
		return checkIsSuccessFu(userDao.getByName(user.getUserName()));
		

	}
	
	/**
	 * 修改用户
	 * @param user 用户实体对象
	 * @return
	 * @throws DemoException
	 */
	@Override
	@Transactional
	public ResponseResult<User> modifyUser(User user) throws DemoException {

		// 校验数据是否为空,数据的合法性
		checkData(user);

		// 查詢用戶是否存在用户不存在抛出异常
		if (getById(user.getUid()) == null) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, Common.USER_NOTEXISTS.getCode(),
					Common.USER_NOTEXISTS.getMsg());
		}
		

		// 校验要修改的用户名是否已经存在
		checkUserName(user.getUserName());

		// 修改用户资料
		userDao.updateUser(user);

		// 修改数据进行返回数据
		return checkIsSuccessFu(userDao.getById(user.getUid()));
	}
	
	/**
	 * 用户登录
	 * @param user 用户实体对象
	 * @return
	 * @throws DemoException
	 */
	@Override
	@Transactional
	public ResponseResult<User> login(User user) throws DemoException {

		// 校验数据是否合法
		User valiData = ValidateUtil.loginUser_IsNotNull(user);

		// 根据用户名和密码查询用户信息
		User data = userDao.getByUserNameAndPassWord(valiData);

		// 查询不到说明该用户不存在
		if (data == null) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, Common.USER_NOTEXISTS.getCode(),
					Common.USER_NOTEXISTS.getMsg());
		} else {
			// 查询到登陆成功
			return ResponseResult.success(data, ResponseResult.STATE_OK);
		}
		
	}

	
	/**
	 * 分页查询/根据用户名分页查询
	 * @param pageIndex 起始页数
	 * @param pageSize	每页数据
	 * @param userName  用户名
	 * @param oid		组织id
	 * @return
	 * @throws DemoException
	 */
	@Override
	@Transactional(readOnly = true)
	public ResponseResult<PageInfo> queryPageUsers(Integer pageIndex, Integer pageSize, String userName, Integer oid)
			throws DemoException {
		//判断起始页数和每页条数是否为null
		if(pageIndex == null){
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR,
							Common.PARAM_IN_NULL.getCode(), Common.PARAM_IN_NULL.getMsg());
		}
		if(pageSize == null){
			pageSize = 10;
		}
		//
		PageHelper.startPage(pageIndex, pageSize);
		List<User> users = userDao.queryPageUsers(userName,oid);
		if(users == null){
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR,
					Common.SELECT_IS_NULL.getCode(), Common.SELECT_IS_NULL.getMsg());
		}
		PageInfo pageInfo = new PageInfo(users,pageSize);
		return new ResponseResult<PageInfo>(pageInfo,"查询成功");
	}

	
	
	
	
	// 校验用户名是否已经存在
	private void checkUserName(String param) throws DemoException {
		if (userDao.getByName(param) != null) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, Common.USERNAME_EXISTS.getCode(),
					Common.USERNAME_EXISTS.getMsg());
		}
	}

	// 校验数据是否为空,数据的合法性
	private void checkData(User user) throws DemoException {
		if (!"ok".equals(ValidateUtil.checkCreateUser(user))) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, Common.DATAILLEGALITY.getCode(),
					Common.DATAILLEGALITY.getMsg());
		}
	}

	//校验操作是否成功
	private ResponseResult<User> checkIsSuccessFu(User data){
		if (data != null ) {
			return ResponseResult.success(data, ResponseResult.STATE_OK);
		}
		return ResponseResult.error(ResponseResult.STATE_ERR);
	}

	

	
}
