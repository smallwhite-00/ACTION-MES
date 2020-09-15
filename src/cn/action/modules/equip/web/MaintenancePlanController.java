package cn.action.modules.equip.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.persistence.Page;
import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.equip.entity.MaintenancePlan;
import cn.action.modules.equip.service.MaintenancePlanService;

@Controller
@RequestMapping(value="${adminPath}/equip/maintenance")
public class MaintenancePlanController extends BaseController{
	@Autowired
	private MaintenancePlanService maintenancePlanService;
	
	@ModelAttribute("maintenancePlan")
	public MaintenancePlan get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return maintenancePlanService.get(id);
		}
		return new MaintenancePlan();
	}
	//��������ҳ��ѯ
	@RequestMapping(value= {"list",""})
	public String list(MaintenancePlan maintenancePlan,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<MaintenancePlan> page=maintenancePlanService.findPage(new Page<MaintenancePlan>(request, response), maintenancePlan);
		model.addAttribute("page",page);
		return "modules/equip/maintenancePlanList";
	}
	//����
	@RequestMapping(value="save")
	public String save(MaintenancePlan maintenancePlan,Model model,RedirectAttributes redirectAttributes) {
		maintenancePlanService.save(maintenancePlan);
		this.addMessage(redirectAttributes, "�����豸�����ƻ��ɹ���");
		return "redirect:"+adminPath+"/equip/maintenance";
	}
	//ɾ��
	@RequestMapping(value="delete")
	public String delete(MaintenancePlan maintenancePlan,Model model,RedirectAttributes redirectAttributes) {
		maintenancePlanService.delete(maintenancePlan);
		this.addMessage(redirectAttributes, "ɾ���豸�����ƻ��ɹ���");
		return "redirect:"+adminPath+"/equip/maintenance";
	}
	//��ת
	@RequestMapping(value="form")
	public String form(MaintenancePlan maintenancePlan,Model model) {
		model.addAttribute("maintenancePlan",maintenancePlan);
		return "modules/equip/maintenancePlanForm";
	}
}
