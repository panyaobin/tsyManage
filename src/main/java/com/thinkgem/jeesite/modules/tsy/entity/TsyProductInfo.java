/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tsy.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 产品信息表Entity
 * @author popo
 * @version 2018-03-03
 */
public class TsyProductInfo extends DataEntity<TsyProductInfo> {
	
	private static final long serialVersionUID = 1L;
	private String createModel;		// 生产型号
	private Integer leng;		// 长
	private Integer wide;		// 宽
	private Integer setPcs;		// set_pcs
	private Integer pnlSet;		// pnl_set
	private Integer ctCount;		// 正面冲次
	private Integer cbCount;		// 反面冲次
	private Integer loCount;		// 外形1冲次
	private Integer ltCount;		// 外形2冲次
	private Integer baseType;		//基材类型
	private Integer packType;		//包封类型
	private Integer filmType;		//电磁膜类型
	private String isHot;		// 是否热固绿油
	private String ctFilm;	//是否有正面电磁模
	private String cbFilm;	//是否有反面电磁模
	private Integer ftCount;	//正面电磁模冲次
	private Integer fbCount;	//反面电磁模冲次
	private String isMask;		// 是否阻焊
	
	public TsyProductInfo() {
		super();
	}

	public TsyProductInfo(String id){
		super(id);
	}

	@Length(min=0, max=255, message="生产型号长度必须介于 0 和 255 之间")
	public String getCreateModel() {
		return createModel;
	}

	public void setCreateModel(String createModel) {
		this.createModel = createModel;
	}
	
	public Integer getLeng() {
		return leng;
	}

	public void setLeng(Integer leng) {
		this.leng = leng;
	}
	
	public Integer getWide() {
		return wide;
	}

	public void setWide(Integer wide) {
		this.wide = wide;
	}
	
	public Integer getSetPcs() {
		return setPcs;
	}

	public void setSetPcs(Integer setPcs) {
		this.setPcs = setPcs;
	}
	
	public Integer getPnlSet() {
		return pnlSet;
	}

	public void setPnlSet(Integer pnlSet) {
		this.pnlSet = pnlSet;
	}
	
	public Integer getCtCount() {
		return ctCount;
	}

	public void setCtCount(Integer ctCount) {
		this.ctCount = ctCount;
	}
	
	public Integer getCbCount() {
		return cbCount;
	}

	public void setCbCount(Integer cbCount) {
		this.cbCount = cbCount;
	}
	
	public Integer getLoCount() {
		return loCount;
	}

	public void setLoCount(Integer loCount) {
		this.loCount = loCount;
	}
	
	public Integer getLtCount() {
		return ltCount;
	}

	public void setLtCount(Integer ltCount) {
		this.ltCount = ltCount;
	}

	public String getIsHot() {
		return isHot;
	}

	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}

	public String getCtFilm() {
		return ctFilm;
	}

	public void setCtFilm(String ctFilm) {
		this.ctFilm = ctFilm;
	}

	public String getCbFilm() {
		return cbFilm;
	}

	public void setCbFilm(String cbFilm) {
		this.cbFilm = cbFilm;
	}

	public String getIsMask() {
		return isMask;
	}

	public void setIsMask(String isMask) {
		this.isMask = isMask;
	}

	public Integer getFtCount() {
		return ftCount;
	}

	public void setFtCount(Integer ftCount) {
		this.ftCount = ftCount;
	}

	public Integer getFbCount() {
		return fbCount;
	}

	public void setFbCount(Integer fbCount) {
		this.fbCount = fbCount;
	}

	public Integer getBaseType() {
		return baseType;
	}

	public void setBaseType(Integer baseType) {
		this.baseType = baseType;
	}

	public Integer getPackType() {
		return packType;
	}

	public void setPackType(Integer packType) {
		this.packType = packType;
	}

	public Integer getFilmType() {
		return filmType;
	}

	public void setFilmType(Integer filmType) {
		this.filmType = filmType;
	}

}