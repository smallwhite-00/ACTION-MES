package cn.action.modules.tec.web;

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
import cn.action.modules.tec.entity.Process;
import cn.action.modules.tec.service.ProcessService;

@Controller
@RequestMapping(value="${adminPath}/tec/process")
public class ProcessController extends BaseController{
	@Autowired
	private ProcessService processService;
	
	@ModelAttribute("process")
	public Process get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return processService.get(id);
		}
		return new Process();
	}
	//��������ҳ��ѯ
	@RequestMapping(value= {"list",""})
	public String list(Process process,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<Process> page=processService.findPage(new Page<Process>(request, response), process);
		model.addAttribute("page", page);
		return "modules/tec/processList";
	}
	//����
	@RequestMapping(value="save")
	public String save(Process process,Model model,RedirectAttributes redirectAttributes) {
		processService.save(process);
		this.addMessage(redirectAttributes, "���湤����Ϣ�ɹ���");
		return "redirect:"+adminPath+"/tec/process";
	}
	//����
	@RequestMapping(value="delete")
	public String delete(Process process,Model model,RedirectAttributes redirectAttributes) {
		processService.delete(process);
		this.addMessage(redirectAttributes, "ɾ��������Ϣ�ɹ���");
		return "redirect:"+adminPath+"/tec/process";
	}
	//��תҳ��
	@RequestMapping(value="form")
	public String form(Process process,Model model) {
		model.addAttribute("process", process);
		return "modules/tec/processForm";
	}
}
