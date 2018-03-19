/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tsy.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.tsy.entity.TsyCreater;
import com.thinkgem.jeesite.modules.tsy.entity.TsyPlannerFeed;
import com.thinkgem.jeesite.modules.tsy.entity.TsyProductInfo;
import com.thinkgem.jeesite.modules.tsy.service.TsyCreaterService;
import com.thinkgem.jeesite.modules.tsy.service.TsyPlannerFeedService;
import com.thinkgem.jeesite.modules.tsy.service.TsyProductInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 计划部下单Controller
 * @author popo
 * @version 2018-03-10
 */
@Controller
@RequestMapping(value = "${adminPath}/tsy/tsyPlanner")
public class TsyPlannerController extends BaseController {

	@Autowired
	private TsyProductInfoService tsyProductInfoService;

	@Autowired
    private TsyCreaterService tsyCreaterService;

	@Autowired
	private TsyPlannerFeedService tsyPlannerFeedService;

	@ModelAttribute
	public TsyProductInfo get(@RequestParam(required=false) String id) {
		TsyProductInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsyProductInfoService.get(id);
		}
		if (entity == null){
			entity = new TsyProductInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("tsy:tsyPlanner:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsyProductInfo tsyProductInfo, HttpServletRequest request, HttpServletResponse response, Model
			model) {
		Page<TsyProductInfo> page = tsyProductInfoService.findPage(new Page<TsyProductInfo>(request, response),
				tsyProductInfo);
		model.addAttribute("page", page);
		return "modules/tsy/planner/tsyPlannerList";
	}

	@RequiresPermissions("tsy:tsyPlanner:view")
	@RequestMapping(value = "noFeed")
	public String nofeed(TsyCreater tsyCreater, HttpServletRequest request, HttpServletResponse response, Model model) {
		// TODO: 2018/3/17 0017 此处是计划部待投料列表
		tsyCreater.setIsFeed(1);
		Page<TsyCreater> page = tsyCreaterService.findPage(new Page<TsyCreater>(request, response),tsyCreater);
		model.addAttribute("page", page);
		return "modules/tsy/planner/tsyPlannerNoFeed";
	}

	@RequiresPermissions("tsy:tsyPlanner:view")
	@RequestMapping(value = "feed")
	public String feed(TsyCreater tsyCreater,TsyProductInfo tsyProductInfo, HttpServletRequest request,HttpServletResponse response, Model model) {
		// TODO: 2018/3/17 0017 此处是计划部投料信息
		tsyCreater=tsyCreaterService.get(tsyCreater.getId());
		model.addAttribute("tsyCreater",tsyCreater);

		tsyProductInfo.setCreateModel(tsyCreater.getCreateModel());
		Page<TsyProductInfo> page2 = tsyProductInfoService.findPage(new Page<TsyProductInfo>(request, response),tsyProductInfo);
		model.addAttribute("tsyProductInfo", page2.getList().get(0));
		return "modules/tsy/planner/tsyPlannerFeed";
	}
	
	@RequiresPermissions("tsy:tsyPlanner:view")
	@RequestMapping(value = "feeding")
	public String feeding(TsyCreater tsyCreater,String feedSum,String feedArea,HttpServletRequest request,HttpServletResponse response, Model model) {
		// TODO: 2018/3/17 0017 这里是计划填写完良率之后投料操作有3步

		//第一步:首先根据订单号修改市场下单等级表的状态未已投料
		tsyCreater=tsyCreaterService.get(tsyCreater.getId());
		tsyCreater.setIsFeed(0);
		tsyCreaterService.save(tsyCreater);

		//第二步:录入wip,即计划的投料登记表
		// TODO: 2018/3/17 0017 先查出产品信息表信息
		TsyProductInfo info = new TsyProductInfo();
		info.setCreateModel(tsyCreater.getCreateModel());
		info=tsyProductInfoService.findPage(new Page<TsyProductInfo>(request, response),info).getList().get(0);

		TsyPlannerFeed tp = new TsyPlannerFeed();
		tp.setOrderId(tsyCreater.getOrderId());
		tp.setFeedDate(new Date());
		tp.setCusNo(tsyCreater.getCusNo());
		tp.setCreateModel(tsyCreater.getCreateModel());
		tp.setLotNum(String.valueOf(tsyCreater.getLotNum()));
		tp.setLeng(String.valueOf(info.getLeng()));
		tp.setWide(String.valueOf(info.getWide()));
		tp.setPcs(String.valueOf(info.getPnlSet()*info.getSetPcs()));
		tp.setOrderSum(tsyCreater.getOrderSum());
		tp.setFeedSum(feedSum);
		tp.setFeedArea(feedArea);
		tsyPlannerFeedService.save(tp);

		//第三步:录入仓库材料开料表


		//return "modules/tsy/planner/tsyPlannerHasFeed";
		return "redirect:noFeed";
	}

	@RequiresPermissions("tsy:tsyPlanner:view")
	@RequestMapping(value = "hasFeed")
	public String hasfeed(TsyCreater tsyCreater, HttpServletRequest request, HttpServletResponse response,Model model) {
		// TODO: 2018/3/17 0017 此处是计划部已投料信息
		tsyCreater.setIsFeed(0);
		Page<TsyCreater> page = tsyCreaterService.findPage(new Page<TsyCreater>(request, response),tsyCreater);
		model.addAttribute("page", page);
		return "modules/tsy/planner/tsyPlannerHasFeed";
	}

	@RequiresPermissions("tsy:tsyPlanner:view")
	@RequestMapping(value = "form")
	public String form(TsyProductInfo tsyProductInfo, Model model) {
		model.addAttribute("tsyProductInfo", tsyProductInfo);
		return "modules/tsy/planner/tsyPlannerForm";
	}

	@RequiresPermissions("tsy:tsyPlanner:edit")
	@RequestMapping(value = "save")
	public String save(TsyProductInfo tsyProductInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tsyProductInfo)){
			return form(tsyProductInfo, model);
		}

		model.addAttribute("tsyProductInfo",tsyProductInfo);
		/*return "redirect:"+Global.getAdminPath()+"/tsy/tsyCreater/?repage";*/
		return "modules/tsy/creater/tsyCreaterPrint";
	}
	
	@RequiresPermissions("tsy:tsyPlanner:edit")
	@RequestMapping(value = "delete")
	public String delete(TsyProductInfo tsyProductInfo, RedirectAttributes redirectAttributes) {
		tsyProductInfoService.delete(tsyProductInfo);
		addMessage(redirectAttributes, "删除计划部部订单明细表成功");
		return "redirect:"+Global.getAdminPath()+"/tsy/tsyCreater/?repage";
	}

}