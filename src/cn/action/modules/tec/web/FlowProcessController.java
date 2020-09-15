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
import cn.action.modules.tec.entity.Flow;
import cn.action.modules.tec.entity.FlowProcess;
import cn.action.modules.tec.entity.Process;
import cn.action.modules.tec.service.FlowProcessService;
import cn.action.modules.tec.service.FlowService;
import cn.action.modules.tec.service.ProcessService;

@Controller
@RequestMapping(value="${adminPath}/tec/flowProcess")
public class FlowProcessController extends BaseController{
	@Autowired
	private FlowProcessService flowProcessService;
	@Autowired
	private ProcessService processService;
	@Autowired
	private FlowService flowService;
	
	@ModelAttribute("flowProcess")
	public FlowProcess get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return flowProcessService.get(id);
		}
		return new FlowProcess();
	}
	//����������ҳ��ѯ
	@RequestMapping(value= {"list",""})
	public String list(FlowProcess flowProcess,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<FlowProcess> page=flowProcessService.findPage(new Page<FlowProcess>(request, response), flowProcess);
		model.addAttribute("page", page);
		return "modules/tec/flowProcessList";
	}
	//����
	@RequestMapping(value="save")
	public String save(FlowProcess flowProcess,Model model,RedirectAttributes redirectAttributes) {
		flowProcessService.save(flowProcess);
		this.addMessage(redirectAttributes, "���湤�������빤���ϵ�ɹ���");
		return "redirect:"+adminPath+"/tec/flowProcess";
	}
	//ɾ��
	@RequestMapping(value="delete")
	public String delete(FlowProcess flowProcess,Model model,RedirectAttributes redirectAttributes) {
		flowProcessService.delete(flowProcess);
		this.addMessage(redirectAttributes, "ɾ�����������빤���ϵ�ɹ���");
		return "redirect:"+adminPath+"/tec/flowProcess";
	}
	//��ת
	@RequestMapping(value="form")
	public String form(FlowProcess flowProcess,Model model) {
		//������й���
		List<Process> processes=processService.findAllList(new Process());
		//�����������
		List<Flow> flows=flowService.findAllList(new Flow());
		
		model.addAttribute("processList", processes);
		model.addAttribute("flowList", flows);
		model.addAttribute("flowProcess", flowProcess);
		return "modules/tec/flowProcessForm";
	}
}
