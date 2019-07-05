package com.grk.stumanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grk.stumanager.mapper.CustomerMapper;
import com.grk.stumanager.pojo.Customer;
import com.grk.stumanager.pojo.QueryVo;
import com.grk.stumanager.utils.Page;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public Page<Customer> getCustomerByQueryVo(QueryVo vo) {
		
		vo.setStart((vo.getPage()-1)*vo.getRows());
		System.out.println(vo);
		Integer total=customerMapper.getCountByQueryVo(vo);
	    List<Customer> customerList=customerMapper.getCustomerByQueryVo(vo);
	    Page<Customer> page=new Page<Customer>(total, vo.getPage(),vo.getRows(), customerList);
		return page;
	}

	@Override
	public Customer getCustomerById(Integer id) {
		return customerMapper.getCustomerById(id);
	}

	@Override
	public void updateCustomer(Customer customer) {
		this.customerMapper.updateCustomer(customer);

	}

	@Override
	public void deleteCustomer(Integer id) {
		this.customerMapper.deleteCustomer(id);

	}

}
