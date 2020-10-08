package cn.action.modules.equip.web;

import java.util.Date;
import java.util.List;

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
import cn.action.common.utils.DateUtils;
import cn.action.common.utils.StringUtils;
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
	
	@ModelAttribute("equipFaultReport")
	public EquipFaultReport get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return equipFaultReportService.get(id);
		}
		return new EquipFaultReport();
	}
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
	
	//��������ҳ��ѯ
	@RequestMapping(value= {"list",""})
	public String list(EquipFaultReport equipFaultReport,HttpServletRequest request,HttpServletResponse response,Model model) {
		//������в�����Ϣ
		List<Line> lines=lineService.findAllList(new Line());
		model.addAttribute("lineList",lines);
		//��÷�ҳ����
		Page<EquipFaultReport> page=equipFaultReportService.findPage(new Page<EquipFaultReport>(request, response), equipFaultReport);
		model.addAttribute("page",page);
		return "modules/equip/equipFaultReportList";	
	}
	//�ɹ�
	@RequestMapping(value="assign")
	public String assign(EquipFaultReport equipFaultReport,Model model,RedirectAttributes redirectAttributes) {
		//�ж��Ƿ��Ѿ��ɹ�
		boolean flag=equipFaultReportService.isAssign(equipFaultReport);
		if(flag) {
			//������в�����Ϣ
			List<Line> lines=lineService.findAllList(new Line());
			model.addAttribute("lineList",lines);
			return "modules/equip/equipMaintenanceWorkerForm";
		}
		this.addMessage(redirectAttributes, "�ñ����Ѿ��õ����������ظ��ɹ���");
		return "redirect:"+adminPath+"/equip/report";
	}
	//�ɹ�����
	@RequestMapping(value="maintenance")
	public String maintenance(EquipFaultReport equipFaultReport,Model model,RedirectAttributes redirectAttributes) {
		equipFaultReport.setStatus("0002");
		equipFaultReport.setAssignTime(DateUtils.formatDateTime(new Date()));
		equipFaultReportService.save(equipFaultReport);
		this.addMessage(redirectAttributes, "�ɹ��ɹ���");
		return "redirect:"+adminPath+"/equip/report";
	}
	//��������ҳ��ѯ,��ת���豸ά�޼�¼ҳ��
	@RequestMapping(value="repairlist")
	public String repairList(EquipFaultReport equipFaultReport,HttpServletRequest request,HttpServletResponse response,Model model) {
		//������в�����Ϣ
		List<Line> lines=lineService.findAllList(new Line());
		model.addAttribute("lineList",lines);
		//��÷�ҳ����
		Page<EquipFaultReport> page=equipFaultReportService.findPage(new Page<EquipFaultReport>(request, response), equipFaultReport);
		model.addAttribute("page",page);
		return "modules/equip/equipRepairList";
	}
		@RequestMapping(value="start")
	public String startRepair(EquipFaultReport equipFaultReport,Model model,RedirectAttributes redirectAttributes){
	String message=equipFaultReportService.saveStartRepair(equipFaultReport);
	this.addMessage(redirectAttributes,message);
	return "redirect:"+adminPath+"/equip/report/repairlist";
	}
}
