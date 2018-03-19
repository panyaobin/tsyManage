/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.tsy.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.tsy.entity.TsyCreater;
import com.thinkgem.jeesite.modules.tsy.entity.TsyProductInfo;
import com.thinkgem.jeesite.modules.tsy.service.TsyCreaterService;
import com.thinkgem.jeesite.modules.tsy.service.TsyProductInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 市场部订单明细表Controller
 * @author popo
 * @version 2018-01-27
 */
@Controller
@RequestMapping(value = "${adminPath}/tsy/tsyWorker")
public class TsyWorkerController extends BaseController {

	@Autowired
	private TsyProductInfoService tsyProductInfoService;

	@Autowired
	private TsyCreaterService tsyCreaterService;

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

	@RequiresPermissions("tsy:tsyWorker:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsyCreater tsyCreater, HttpServletRequest request, HttpServletResponse response, Model model) {
		tsyCreater.setDataFlag(1);
		tsyCreater.setLotNum(1);
		Page<TsyCreater> page = tsyCreaterService.findPage(new Page<TsyCreater>(request, response), tsyCreater);
		model.addAttribute("page", page);
		return "modules/tsy/worker/tsyWorkerList";
	}

	@RequiresPermissions("tsy:tsyWorker:view")
	@RequestMapping(value = "pass")
	public String pass(TsyCreater tsyCreater, HttpServletRequest request, HttpServletResponse response, Model model) {
		tsyCreater.setDataFlag(0);
		tsyCreater.setLotNum(1);
		Page<TsyCreater> page = tsyCreaterService.findPage(new Page<TsyCreater>(request, response), tsyCreater);
		model.addAttribute("page", page);
		return "modules/tsy/worker/tsyWorkerPassList";
	}

	@RequiresPermissions("tsy:tsyWorker:view")
	@RequestMapping(value = "form")
	public String form(TsyCreater tsyCreater, Model model) {
		model.addAttribute("tsyCreater", tsyCreater);
		return "modules/tsy/worker/tsyWorkerForm";
	}

	@RequiresPermissions("tsy:tsyWorker:edit")
	@Transactional
	@RequestMapping(value = "save")
	public String save(String error,TsyCreater tsyCreater,TsyProductInfo tsyProductInfo, Model model, RedirectAttributes
			redirectAttributes) {
		if (!beanValidator(model, tsyCreater)){
			return form(tsyCreater, model);
		}
		if(StringUtils.isNotBlank(error)){
			//如果有這個id,説明是工程部录入市场订单,样品或者量产都需要修改状态
			if (tsyCreater.getType()==1){
				//此处说明改订单是 量产，录入产品信息表
				tsyProductInfoService. save(tsyProductInfo);
			}
			tsyCreater=tsyCreaterService.get(error);
			tsyCreater.setDataFlag(0);
			tsyCreaterService.save(tsyCreater);
		}else {
			//如果沒有這個id,説明是工程部录入新单，手动录入新单只会有 量产，直接存入产品信息表
			tsyProductInfoService. save(tsyProductInfo);
		}
		addMessage(redirectAttributes, "工程部订单录入成功");
		model.addAttribute("tsyProductInfo",tsyProductInfo);
		//return "redirect:"+Global.getAdminPath()+"/tsy/tsyWorker/?repage";
		return "modules/tsy/worker/tsyWorkerPassList";
	}

	@RequiresPermissions("tsy:tsyWorker:edit")
	@RequestMapping(value = "delete")
	public String delete(TsyProductInfo tsyProductInfo, RedirectAttributes redirectAttributes) {
		tsyProductInfoService.delete(tsyProductInfo);
		addMessage(redirectAttributes, "删除市场部订单明细表成功");
		return "redirect:"+Global.getAdminPath()+"/tsy/tsyProductInfo/?repage";
	}

}