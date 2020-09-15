package cn.action.modules.qc.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.groovy.runtime.m12n.MetaInfExtensionModule;
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
import cn.action.modules.qc.entity.AbnormalProcess;
import cn.action.modules.qc.service.AbnormalProcessService;
import cn.action.modules.sys.entity.User;
import cn.action.modules.sys.service.SystemService;
import cn.action.modules.tec.entity.Process;
import cn.action.modules.tec.service.ProcessService;

@Controller
@RequestMapping(value="${adminPath}/qc/abnormalProcess")
public class AbnormalProcessController extends BaseController{
	@Autowired
	private AbnormalProcessService abnormalProcessService;
	@Autowired
	private ProcessService processService;
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute("abnormalProcess")
	public AbnormalProcess get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return abnormalProcessService.get(id);
		}
		return new AbnormalProcess();
	}
	
	//��������ҳ��ѯ
	@RequestMapping(value= {"list",""})
	public String list(AbnormalProcess abnormalProcess,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<AbnormalProcess> page=abnormalProcessService.findPage(new Page<AbnormalProcess>(request, response), abnormalProcess);
		model.addAttribute("page",page);
		return "modules/qc/abnormalProcessList";
	}
	//����
	@RequestMapping(value="save")
	public String save(AbnormalProcess abnormalProcess,Model model,RedirectAttributes redirectAttributes) {
		abnormalProcessService.save(abnormalProcess);
		this.addMessage(redirectAttributes, "�����쳣������Ϣ�ɹ���");
		return "redirect:"+adminPath+"/qc/abnormalProcess";
	}
	//ɾ��
	@RequestMapping(value="delete")
	public String delete(AbnormalProcess abnormalProcess,Model model,RedirectAttributes redirectAttributes) {
		abnormalProcessService.delete(abnormalProcess);
		this.addMessage(redirectAttributes, "ɾ���쳣������Ϣ�ɹ���");
		return "redirect:"+adminPath+"/qc/abnormalProcess";
	}
	//��ת
	@RequestMapping(value="form")
	public String form(AbnormalProcess abnormalProcess,Model model) {
		//�������ݣ����򼯺ϡ��û����ϡ��쳣�������
		List<Process> processes=processService.findAllList(new Process());
		List<User> users=systemService.findUser(new User());
		
		model.addAttribute("processList",processes);
		model.addAttribute("userList",users);
		model.addAttribute("abnormalProcess",abnormalProcess);
		
		return "modules/qc/abnormalProcessForm";
	
	}
	
}
