/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tsy.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tsy.entity.TsyPlannerFeed;

/**
 * 计划投料表(wip)DAO接口
 * @author popo
 * @version 2018-03-17
 */
@MyBatisDao
public interface TsyPlannerFeedDao extends CrudDao<TsyPlannerFeed> {
	
}