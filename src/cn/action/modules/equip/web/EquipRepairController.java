package cn.action.modules.equip.web;

import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.equip.entity.EquipFaultReport;
import cn.action.modules.equip.entity.EquipRepair;
import cn.action.modules.equip.service.EquipRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 此处填写说明
 *
 * @author Administrator
 * @createDate 2020/9/18
 */
@Controller
@RequestMapping(value="${adminPath}/equip/repair")
public class EquipRepairController  extends BaseController {
  	@Autowired
	private EquipRepairService equipRepairService;
	
	@ModelAttribute("equipRepair")
	public EquipRepair get(@RequestParam(required=false) String id,String mid) {
		if(StringUtils.isNotBlank(id)) {
			return equipRepairService.get(id);
		}
		EquipRepair repair=new EquipRepair();
		repair.setMid(mid);
		return repair;
	}
	@RequestMapping(value="save")
	public String save(EquipRepair equipRepair, Model model, RedirectAttributes redirectAttributes) {
		equipRepairService.saveRepair(equipRepair);
		this.addMessage(redirectAttributes,"维修报告添加成功");
		return "redirect:"+adminPath+"/equip/report/repairList";
	}
	@RequestMapping(value="form")
	public String form(EquipRepair equipRepair, Model model){
	  model.addAttribute("equipRepair",equipRepair);
	 return "modules/equip/maintenanceReportForm";
  }
}
