package com.mi.service.impl;

import org.springframework.stereotype.Service;

import com.mi.service.IIndexService;

/**
 * 主页接口实现
 * @author tangl
 * 2016-2-16 16:59:09
 */
@Service
public class IndexServiceImpl implements IIndexService {

	@Override
	public String getName() {
		return "spring";
	}

}
