/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tsy.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 市场部订单明细表Entity
 * @author popo
 * @version 2018-01-27
 */
public class TsyCreater extends DataEntity<TsyCreater> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// 订单号
	private Integer type;		// 类型  0样品 1 量产
	private String cusNo;		// 客户代码
	private String cusModel;		// 客户型号
	private String createModel;		// 生产型号
	private String orderSum;		// 订单数量
	private Integer lotNum;		// 生产批次编号
	private Date orderDate;		// 下单日期
	private Date deliveDate;		// 交货日期
	private Integer mouldeFlag;		// 是否包模模具 0是 1否
	private Integer lookerFlag;		// 是否需要外型模具 0是 1否
	private Integer testerFlag;		// 是否需要测试架 0是 1否
	private String cusRequire;		// 客户特殊要求
	private Integer isFeed;			//是否投料
	private Integer dataFlag;		// 资料是否完成 0是 1否
	
	public TsyCreater() {
		super();
	}

	public TsyCreater(String id){
		super(id);
	}

	@Length(min=1, max=16, message="订单号长度必须介于 1 和 16 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="客户代码长度必须介于 0 和 255 之间")
	public String getCusNo() {
		return cusNo;
	}

	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}
	
	@Length(min=0, max=255, message="客户型号长度必须介于 0 和 255 之间")
	public String getCusModel() {
		return cusModel;
	}

	public void setCusModel(String cusModel) {
		this.cusModel = cusModel;
	}

	public Integer getLotNum() {
		return lotNum;
	}

	public void setLotNum(Integer lotNum) {
		this.lotNum = lotNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDeliveDate() {
		return deliveDate;
	}

	public void setDeliveDate(Date deliveDate) {
		this.deliveDate = deliveDate;
	}
	
	public Integer getMouldeFlag() {
		return mouldeFlag;
	}

	public void setMouldeFlag(Integer mouldeFlag) {
		this.mouldeFlag = mouldeFlag;
	}
	
	public Integer getLookerFlag() {
		return lookerFlag;
	}

	public void setLookerFlag(Integer lookerFlag) {
		this.lookerFlag = lookerFlag;
	}
	
	public Integer getTesterFlag() {
		return testerFlag;
	}

	public void setTesterFlag(Integer testerFlag) {
		this.testerFlag = testerFlag;
	}
	
	@Length(min=0, max=255, message="客户特殊要求长度必须介于 0 和 255 之间")
	public String getCusRequire() {
		return cusRequire;
	}

	public void setCusRequire(String cusRequire) {
		this.cusRequire = cusRequire;
	}
	
	public Integer getDataFlag() {
		return dataFlag;
	}

	public void setDataFlag(Integer dataFlag) {
		this.dataFlag = dataFlag;
	}

	public String getCreateModel() {return createModel;	}

	public void setCreateModel(String createModel) {this.createModel = createModel;	}

	public Integer getIsFeed() {
		return isFeed;
	}

	public void setIsFeed(Integer isFeed) {
		this.isFeed = isFeed;
	}

	public String getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(String orderSum) {
		this.orderSum = orderSum;
	}
}