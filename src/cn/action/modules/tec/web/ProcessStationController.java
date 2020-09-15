package cn.action.modules.tec.web;

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
import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.bas.service.WorkStationInfosService;
import cn.action.modules.tec.entity.Process;
import cn.action.modules.tec.entity.ProcessStation;
import cn.action.modules.tec.service.ProcessService;
import cn.action.modules.tec.service.ProcessStationService;

@Controller
@RequestMapping(value="${adminPath}/tec/processStation")
public class ProcessStationController extends BaseController{
	@Autowired
	private ProcessStationService processStationService;
	@Autowired
	private ProcessService processService;
	@Autowired
	private WorkStationInfosService workStationInfosService;
	
	@ModelAttribute("processStation")
	public ProcessStation get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return processStationService.get(id);
		}
		return new ProcessStation();
	}
	
	//����������ҳ��ѯ
	@RequestMapping(value= {"list",""})
	public String list(ProcessStation processStation,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<ProcessStation> page=processStationService.findPage(new Page<ProcessStation>(request, response), processStation);
		model.addAttribute("page", page);
		return "modules/tec/processStationList";
	}
	//����
	@RequestMapping(value="save")
	public String save(ProcessStation processStation,Model model,RedirectAttributes redirectAttributes) {
		processStationService.save(processStation);
		this.addMessage(redirectAttributes, "���湤���빤վ��ϵ�ɹ���");
		return "redirect:"+adminPath+"/tec/processStation";
	}
	//ɾ��
	@RequestMapping(value="delete")
	public String delete(ProcessStation processStation,Model model,RedirectAttributes redirectAttributes) {
		processStationService.delete(processStation);
		this.addMessage(redirectAttributes, "ɾ�������빤վ��ϵ�ɹ���");
		return "redirect:"+adminPath+"/tec/processStation";
	}
	//��תҳ��
	@RequestMapping(value="form")
	public String form(ProcessStation processStation,Model model) {
		//������й���͹�վ
		List<Process> processes=processService.findAllList(new Process());
		List<WorkStationInfos> workStationInfos=workStationInfosService.findAllList(new WorkStationInfos());
		
		model.addAttribute("processList", processes);
		model.addAttribute("stationList",workStationInfos);
		model.addAttribute("processStation",processStation);
		
		return "modules/tec/processStationForm";
	}
}
