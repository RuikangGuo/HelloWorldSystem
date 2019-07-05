package com.grk.stumanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grk.stumanager.mapper.BaseDictMapper;
import com.grk.stumanager.pojo.BaseDict;
@Service
public class BaseDictServiceImpl implements BaseDictService {

	@Autowired
	private  BaseDictMapper baseDictMapper;
	
	@Override
	public List<BaseDict> getBaseDictByCode(String code) {
		return baseDictMapper.getBaseDictByCode(code);
	}

}
