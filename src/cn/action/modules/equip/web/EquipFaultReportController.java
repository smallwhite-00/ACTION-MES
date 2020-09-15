package cn.action.modules.equip.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.web.BaseController;
import cn.action.modules.bas.entity.Line;
import cn.action.modules.bas.service.LineService;
import cn.action.modules.equip.entity.EquipFaultReport;
import cn.action.modules.equip.service.EquipFaultReportService;

@Controller
@RequestMapping(value="${adminPath}/equip/report")
public class EquipFaultReportController extends BaseController{
	@Autowired
	private EquipFaultReportService equipFaultReportService;
	@Autowired
	private LineService lineService;
	//�豸�����ϱ�����
	@RequestMapping(value="save")
	public String save(EquipFaultReport equipFaultReport,Model model,RedirectAttributes redirectAttributes) {
		boolean flag=equipFaultReportService.saveFaultReport(equipFaultReport);
		String messages="�豸��������޴��豸��";
		if(flag) {
			messages="�����ϱ��ɹ���";
		}
		this.addMessage(redirectAttributes, messages);
		return "redirect:"+adminPath+"/equip/report/form";
	}
	
	//��ת�������ϱ�ҳ��
	@RequestMapping(value="form")
	public String form(EquipFaultReport equipFaultReport,Model model) {
		//������в�����Ϣ
		List<Line> lines=lineService.findAllList(new Line());
		model.addAttribute("lineList",lines);
		return "modules/equip/equipFaultReportForm";
	}
	
}
