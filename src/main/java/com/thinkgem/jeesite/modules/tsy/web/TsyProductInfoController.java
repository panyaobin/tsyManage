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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.tsy.entity.TsyProductInfo;
import com.thinkgem.jeesite.modules.tsy.service.TsyProductInfoService;

/**
 * 产品信息表Controller
 * @author popo
 * @version 2018-03-03
 */
@Controller
@RequestMapping(value = "${adminPath}/tsy/tsyProductInfo")
public class TsyProductInfoController extends BaseController {

	@Autowired
	private TsyProductInfoService tsyProductInfoService;
	
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
	
	@RequiresPermissions("tsy:tsyProductInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(TsyProductInfo tsyProductInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TsyProductInfo> page = tsyProductInfoService.findPage(new Page<TsyProductInfo>(request, response), tsyProductInfo); 
		model.addAttribute("page", page);
		return "modules/tsy/product/tsyProductInfoList";
	}

	@RequiresPermissions("tsy:tsyProductInfo:view")
	@RequestMapping(value = "form")
	public String form(TsyProductInfo tsyProductInfo, Model model) {
		model.addAttribute("tsyProductInfo", tsyProductInfo);
		return "modules/tsy/product/tsyProductInfoForm";
	}

	@RequiresPermissions("tsy:tsyProductInfo:edit")
	@RequestMapping(value = "save")
	public String save(TsyProductInfo tsyProductInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tsyProductInfo)){
			return form(tsyProductInfo, model);
		}
		if (StringUtils.isBlank(tsyProductInfo.getIsHot())){tsyProductInfo.setIsHot("1");}
		if (StringUtils.isBlank(tsyProductInfo.getIsMask())){tsyProductInfo.setIsMask("1");}
		if (StringUtils.isBlank(tsyProductInfo.getCtFilm())){tsyProductInfo.setCtFilm("1");}
		if (StringUtils.isBlank(tsyProductInfo.getCbFilm())){tsyProductInfo.setCbFilm("1");}
		tsyProductInfoService.save(tsyProductInfo);
		addMessage(redirectAttributes, "保存产品信息表成功");
		return "redirect:"+Global.getAdminPath()+"/tsy/tsyProductInfo/?repage";
	}
	
	@RequiresPermissions("tsy:tsyProductInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(TsyProductInfo tsyProductInfo, RedirectAttributes redirectAttributes) {
		tsyProductInfoService.delete(tsyProductInfo);
		addMessage(redirectAttributes, "删除产品信息表成功");
		return "redirect:"+Global.getAdminPath()+"/tsy/tsyProductInfo/?repage";
	}

}