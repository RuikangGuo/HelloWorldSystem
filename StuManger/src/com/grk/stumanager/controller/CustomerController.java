package com.grk.stumanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grk.stumanager.pojo.BaseDict;
import com.grk.stumanager.pojo.Customer;
import com.grk.stumanager.pojo.QueryVo;
import com.grk.stumanager.service.BaseDictService;
import com.grk.stumanager.service.CustomerService;
import com.grk.stumanager.utils.Page;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BaseDictService baseDictService;
	
	@Value("${customer_from_type}")
	private String customer_from_type;
	
	@Value("${customer_industry_type}")
	private String customer_industry_type;
	
	@Value("${customer_level_type}")
	private String customer_level_type;
	
	@RequestMapping("/list")
	public String list(Model model, QueryVo vo) {
		
	   List<BaseDict> fromType=	baseDictService.getBaseDictByCode(customer_from_type);
	   List<BaseDict> industryType= baseDictService.getBaseDictByCode(customer_industry_type);
	   List<BaseDict> levelType= baseDictService.getBaseDictByCode(customer_level_type);
	   model.addAttribute("fromType",fromType);
	   model.addAttribute("industryType",industryType);
	   model.addAttribute("levelType",levelType);
	   
	  Page<Customer> page=customerService.getCustomerByQueryVo(vo);
	  model.addAttribute("page",page);
	  model.addAttribute("vo", vo);
		return "customer";
	}
	
	@RequestMapping("edit")
	@ResponseBody
	public Customer edit(Integer id) {
	Customer customer=customerService.getCustomerById(id);
		return customer;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public String update(Customer customer) {
		String msg="1";
		try {
			customerService.updateCustomer(customer);
			msg = "0";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public String delete(Integer id) {
		String msg="1";
		try {
			customerService.deleteCustomer(id);
			msg = "0";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	
}
