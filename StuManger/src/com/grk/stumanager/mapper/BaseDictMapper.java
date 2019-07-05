package com.grk.stumanager.mapper;

import java.util.List;

import com.grk.stumanager.pojo.BaseDict;

public interface BaseDictMapper {
	/**
	 * 跟据字典编码查询字典列表
	 * @param code
	 * @return
	 */
	List<BaseDict> getBaseDictByCode(String code); 
}
