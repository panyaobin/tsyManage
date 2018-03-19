/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tsy.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.tsy.entity.TsyProductInfo;

/**
 * 产品信息表DAO接口
 * @author popo
 * @version 2018-03-03
 */
@MyBatisDao
public interface TsyProductInfoDao extends CrudDao<TsyProductInfo> {
	
}