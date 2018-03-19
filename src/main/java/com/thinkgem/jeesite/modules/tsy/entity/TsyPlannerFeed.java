/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tsy.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 计划投料表(wip)Entity
 * @author popo
 * @version 2018-03-17
 */
public class TsyPlannerFeed extends DataEntity<TsyPlannerFeed> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// 订单号
	private Date feedDate;		// 投料日期
	private String cusNo;		// 客户代码
	private String createModel;		// 生产型号
	private String lotNum;		// 生产批次
	private String leng;		// 长
	private String wide;		// 宽
	private String pcs;		// 拼版数量
	private String orderSum;		// 订单数量
	private String zuankSum;		// 钻孔已出货数量
	private String lineSum;		// 线路已出货数量
	private String tieheSum;		// 贴合已出货数量
	private String goldSum;		// 沉金已出货数量
	private String smtSum;		// smt已出货数量
	private String feedSum;		// 投料数量
	private String feedArea;		// 投料面积
	private String deliveredSum;		// 已交货数量
	private String undeliveredSum;		// 未交货数量
	private String onlineSum;		// 在线数量
	private String onlineArea;		// 在线面积
	private String isClear;		// 是否强制清单
	private String tagOperate;		// 操作码
	
	public TsyPlannerFeed() {
		super();
	}

	public TsyPlannerFeed(String id){
		super(id);
	}

	@Length(min=0, max=11, message="订单号长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFeedDate() {
		return feedDate;
	}

	public void setFeedDate(Date feedDate) {
		this.feedDate = feedDate;
	}
	
	@Length(min=0, max=255, message="客户代码长度必须介于 0 和 255 之间")
	public String getCusNo() {
		return cusNo;
	}

	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}
	
	@Length(min=0, max=255, message="生产型号长度必须介于 0 和 255 之间")
	public String getCreateModel() {
		return createModel;
	}

	public void setCreateModel(String createModel) {
		this.createModel = createModel;
	}
	
	@Length(min=0, max=3, message="生产批次长度必须介于 0 和 3 之间")
	public String getLotNum() {
		return lotNum;
	}

	public void setLotNum(String lotNum) {
		this.lotNum = lotNum;
	}
	
	@Length(min=0, max=8, message="长长度必须介于 0 和 8 之间")
	public String getLeng() {
		return leng;
	}

	public void setLeng(String leng) {
		this.leng = leng;
	}
	
	@Length(min=0, max=8, message="宽长度必须介于 0 和 8 之间")
	public String getWide() {
		return wide;
	}

	public void setWide(String wide) {
		this.wide = wide;
	}
	
	@Length(min=0, max=8, message="拼版数量长度必须介于 0 和 8 之间")
	public String getPcs() {
		return pcs;
	}

	public void setPcs(String pcs) {
		this.pcs = pcs;
	}
	
	@Length(min=0, max=8, message="订单数量长度必须介于 0 和 8 之间")
	public String getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(String orderSum) {
		this.orderSum = orderSum;
	}
	
	@Length(min=0, max=8, message="钻孔已出货数量长度必须介于 0 和 8 之间")
	public String getZuankSum() {
		return zuankSum;
	}

	public void setZuankSum(String zuankSum) {
		this.zuankSum = zuankSum;
	}
	
	@Length(min=0, max=8, message="线路已出货数量长度必须介于 0 和 8 之间")
	public String getLineSum() {
		return lineSum;
	}

	public void setLineSum(String lineSum) {
		this.lineSum = lineSum;
	}
	
	@Length(min=0, max=8, message="贴合已出货数量长度必须介于 0 和 8 之间")
	public String getTieheSum() {
		return tieheSum;
	}

	public void setTieheSum(String tieheSum) {
		this.tieheSum = tieheSum;
	}
	
	@Length(min=0, max=8, message="沉金已出货数量长度必须介于 0 和 8 之间")
	public String getGoldSum() {
		return goldSum;
	}

	public void setGoldSum(String goldSum) {
		this.goldSum = goldSum;
	}
	
	@Length(min=0, max=8, message="smt已出货数量长度必须介于 0 和 8 之间")
	public String getSmtSum() {
		return smtSum;
	}

	public void setSmtSum(String smtSum) {
		this.smtSum = smtSum;
	}
	
	@Length(min=0, max=8, message="投料数量长度必须介于 0 和 8 之间")
	public String getFeedSum() {
		return feedSum;
	}

	public void setFeedSum(String feedSum) {
		this.feedSum = feedSum;
	}
	
	public String getFeedArea() {
		return feedArea;
	}

	public void setFeedArea(String feedArea) {
		this.feedArea = feedArea;
	}
	
	@Length(min=0, max=8, message="已交货数量长度必须介于 0 和 8 之间")
	public String getDeliveredSum() {
		return deliveredSum;
	}

	public void setDeliveredSum(String deliveredSum) {
		this.deliveredSum = deliveredSum;
	}
	
	@Length(min=0, max=8, message="未交货数量长度必须介于 0 和 8 之间")
	public String getUndeliveredSum() {
		return undeliveredSum;
	}

	public void setUndeliveredSum(String undeliveredSum) {
		this.undeliveredSum = undeliveredSum;
	}
	
	@Length(min=0, max=8, message="在线数量长度必须介于 0 和 8 之间")
	public String getOnlineSum() {
		return onlineSum;
	}

	public void setOnlineSum(String onlineSum) {
		this.onlineSum = onlineSum;
	}
	
	public String getOnlineArea() {
		return onlineArea;
	}

	public void setOnlineArea(String onlineArea) {
		this.onlineArea = onlineArea;
	}
	
	@Length(min=0, max=2, message="是否强制清单长度必须介于 0 和 2 之间")
	public String getIsClear() {
		return isClear;
	}

	public void setIsClear(String isClear) {
		this.isClear = isClear;
	}
	
	@Length(min=0, max=255, message="操作码长度必须介于 0 和 255 之间")
	public String getTagOperate() {
		return tagOperate;
	}

	public void setTagOperate(String tagOperate) {
		this.tagOperate = tagOperate;
	}
	
}