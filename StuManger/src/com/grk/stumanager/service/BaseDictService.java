package com.grk.stumanager.service;

import java.util.List;

import com.grk.stumanager.pojo.BaseDict;

public interface BaseDictService {
	/**
	 * 跟据字典编码查询字典列表
	 * @param code
	 * @return
	 */
	List<BaseDict> getBaseDictByCode(String code); 
}
