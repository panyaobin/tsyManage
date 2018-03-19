/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tsy.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.tsy.entity.TsyCreater;
import com.thinkgem.jeesite.modules.tsy.dao.TsyCreaterDao;

/**
 * 市场部订单明细表Service
 * @author popo
 * @version 2018-01-27
 */
@Service
@Transactional(readOnly = true)
public class TsyCreaterService extends CrudService<TsyCreaterDao, TsyCreater> {

	public TsyCreater get(String id) {
		return super.get(id);
	}
	
	public List<TsyCreater> findList(TsyCreater tsyCreater) {
		return super.findList(tsyCreater);
	}
	
	public Page<TsyCreater> findPage(Page<TsyCreater> page, TsyCreater tsyCreater) {
		return super.findPage(page, tsyCreater);
	}
	
	@Transactional(readOnly = false)
	public void save(TsyCreater tsyCreater) {
		super.save(tsyCreater);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsyCreater tsyCreater) {
		super.delete(tsyCreater);
	}
	
}