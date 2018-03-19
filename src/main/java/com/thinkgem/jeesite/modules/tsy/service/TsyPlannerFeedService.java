/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tsy.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.tsy.entity.TsyPlannerFeed;
import com.thinkgem.jeesite.modules.tsy.dao.TsyPlannerFeedDao;

/**
 * 计划投料表(wip)Service
 * @author popo
 * @version 2018-03-17
 */
@Service
@Transactional(readOnly = true)
public class TsyPlannerFeedService extends CrudService<TsyPlannerFeedDao, TsyPlannerFeed> {

	public TsyPlannerFeed get(String id) {
		return super.get(id);
	}
	
	public List<TsyPlannerFeed> findList(TsyPlannerFeed tsyPlannerFeed) {
		return super.findList(tsyPlannerFeed);
	}
	
	public Page<TsyPlannerFeed> findPage(Page<TsyPlannerFeed> page, TsyPlannerFeed tsyPlannerFeed) {
		return super.findPage(page, tsyPlannerFeed);
	}
	
	@Transactional(readOnly = false)
	public void save(TsyPlannerFeed tsyPlannerFeed) {
		super.save(tsyPlannerFeed);
	}
	
	@Transactional(readOnly = false)
	public void delete(TsyPlannerFeed tsyPlannerFeed) {
		super.delete(tsyPlannerFeed);
	}
	
}