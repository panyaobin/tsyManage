/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tsy.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tsy.entity.TsyCreater;

/**
 * 市场部订单明细表DAO接口
 * @author popo
 * @version 2018-01-27
 */
@MyBatisDao
public interface TsyCreaterDao extends CrudDao<TsyCreater> {
	
}