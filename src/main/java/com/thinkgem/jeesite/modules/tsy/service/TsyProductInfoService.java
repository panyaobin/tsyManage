/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tsy.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.tsy.entity.TsyProductInfo;
import com.thinkgem.jeesite.modules.tsy.dao.TsyProductInfoDao;

/**
 * 产品信息表Service
 * @author popo
 * @version 2018-03-03
 */
@Service
@Transactional(readOnly = true)
public class TsyProductInfoService extends CrudService<TsyProductInfoDao, TsyProductInfo> {

	public TsyProductInfo get(String id) {
		return super.get(id);
	}
	
	public List<TsyProductInfo> findList(TsyProductInfo tsyProductInfo) {
		return super.findList(tsyProductInfo);
	}
	
	public Page<TsyProductInfo> findPage(Page<TsyProductInfo> page, TsyProductInfo tsyProductInfo) {
		return super.findPage(page, tsyProductInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(TsyProductInfo tsyProductInfo) {
		super.save(tsyProductInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsyProductInfo tsyProductInfo) {
		super.delete(tsyProductInfo);
	}
	
}