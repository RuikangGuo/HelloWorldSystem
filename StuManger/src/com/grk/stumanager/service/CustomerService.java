package com.grk.stumanager.service;

import com.grk.stumanager.pojo.Customer;
import com.grk.stumanager.pojo.QueryVo;
import com.grk.stumanager.utils.Page;

/**
 * 客户信息持久化接口
 * @author tel1187
 *
 */
public interface CustomerService {

	/**
	 * 根据查询条件，分页查询用户列表
	 * @param vo
	 * @return
	 */
	Page<Customer> getCustomerByQueryVo(QueryVo vo); 

	
	/**
	 * 跟据id查询用户信息
	 * @param id
	 * @return
	 */
	Customer getCustomerById(Integer id);
	
	/**
	 * 更新用户信息
	 * @param customer
	 */
	void updateCustomer(Customer customer);
	
	/**
	 * 删除用户信息
	 * @param id
	 */
	void deleteCustomer(Integer id );
	
}
