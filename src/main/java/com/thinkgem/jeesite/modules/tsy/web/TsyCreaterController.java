/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tsy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.tsy.entity.TsyCreater;
import com.thinkgem.jeesite.modules.tsy.service.TsyCreaterService;

/**
 * 市场部订单明细表Controller
 * @author popo
 * @version 2018-01-27
 */
@Controller
@RequestMapping(value = "${adminPath}/tsy/tsyCreater")
public class TsyCreaterController extends BaseController {

	@Autowired
	private TsyCreaterService tsyCreaterService;
	
	@ModelAttribute
	public TsyCreater get(@RequestParam(required=false) String id) {
		TsyCreater entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tsyCreaterService.get(id);
		}
		if (entity == null){
			entity = new TsyCreater();
		}
		return entity;
	}
	
	@RequiresPermissions("tsy:tsyCreater:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsyCreater tsyCreater, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TsyCreater> page = tsyCreaterService.findPage(new Page<TsyCreater>(request, response), tsyCreater); 
		model.addAttribute("page", page);
		return "modules/tsy/creater/tsyCreaterList";
	}
	@RequiresPermissions("tsy:tsyCreater:view")
	@RequestMapping(value ="test")
	public String test() {
		return "modules/tsy/creater/tsyCreaterPrint";
	}

	@RequiresPermissions("tsy:tsyCreater:view")
	@RequestMapping(value = "form")
	public String form(TsyCreater tsyCreater, Model model) {
		model.addAttribute("tsyCreater", tsyCreater);
		return "modules/tsy/creater/tsyCreaterForm";
	}

	@RequiresPermissions("tsy:tsyCreater:edit")
	@RequestMapping(value = "save")
	public String save(TsyCreater tsyCreater, Model model, RedirectAttributes redirectAttributes) {
		// TODO: 2018/3/17 0017 这里是市场部下单新增操作
		String id=tsyCreater.getId();
		if (!beanValidator(model, tsyCreater)){
			return form(tsyCreater, model);
		}
		tsyCreater.setIsFeed(1);
		if(tsyCreater.getLotNum()!=1){
			// TODO: 2018/3/17 0017 市场部下单如果批次是大于1的，表示是返单，资料直接显示完成
			tsyCreater.setDataFlag(0);
		}else{
			tsyCreater.setDataFlag(1);
		}
		tsyCreaterService.save(tsyCreater);
		if (StringUtils.isNotBlank(id)){
			addMessage(redirectAttributes, "资料修改成功");
			return "redirect:"+Global.getAdminPath()+"/tsy/tsyCreater/?repage";
		}
        addMessage(redirectAttributes, "市场部下单成功");
		model.addAttribute("tsyCreater",tsyCreater);
		return "modules/tsy/creater/tsyCreaterPrint";
	}
	
	@RequiresPermissions("tsy:tsyCreater:edit")
	@RequestMapping(value = "delete")
	public String delete(TsyCreater tsyCreater, RedirectAttributes redirectAttributes) {
		tsyCreaterService.delete(tsyCreater);
		addMessage(redirectAttributes, "删除市场部订单明细表成功");
		return "redirect:"+Global.getAdminPath()+"/tsy/tsyCreater/?repage";
	}

	@RequiresPermissions("tsy:tsyCreater:view")
	@RequestMapping(value = "selId")
	@ResponseBody
	public String selId(TsyCreater tsyCreater, HttpServletRequest request, HttpServletResponse response) {
		Page<TsyCreater> page = tsyCreaterService.findPage(new Page<TsyCreater>(request, response), tsyCreater);
		String orderId="";
		if (null!=page&&null!=page.getList()&&page.getList().size()>0) {
			orderId = page.getList().get(0).getOrderId();
		}
		return orderId;
	}

}