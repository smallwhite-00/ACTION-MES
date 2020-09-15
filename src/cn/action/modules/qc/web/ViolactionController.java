package cn.action.modules.qc.web;

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
import cn.action.modules.qc.entity.Violaction;
import cn.action.modules.qc.service.ViolactionService;

@Controller
@RequestMapping(value="${adminPath}/qc/violaction")
public class ViolactionController extends BaseController{
	@Autowired
	private ViolactionService violactionService;
	@Autowired
	private WorkStationInfosService workStationInfosService;
	
	@ModelAttribute("violaction")
	public Violaction get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return violactionService.get(id);
		}
		return new Violaction();
	}
	//��������ҳ��ѯ
	@RequestMapping(value= {"list",""})
	public String list(Violaction violaction,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<Violaction> page=violactionService.findPage(new Page<Violaction>(request, response), violaction);
		model.addAttribute("page",page);
		return "modules/qc/violactionList";
	}
	//����
	@RequestMapping(value="save")
	public String save(Violaction violaction,Model model,RedirectAttributes redirectAttributes) {
		String message="����Υ���¼�ɹ���";
		String url="redirect:"+adminPath+"/qc/violaction";
		
		boolean flag=violactionService.saveViolaction(violaction);
		if(!flag) {
			message="�ù�վû�����ö�Ӧ�Ĺ��򣬲������Υ���¼��";
			url="redirect:"+adminPath+"/qc/violaction/form";
		}
		this.addMessage(redirectAttributes, message);
		return url;
	}
	//ɾ��
	@RequestMapping(value="delete")
	public String delete(Violaction violaction,Model model,RedirectAttributes redirectAttributes) {
		violactionService.delete(violaction);
		this.addMessage(redirectAttributes, "ɾ��Υ���¼�ɹ���");
		return "redirect:"+adminPath+"/qc/violaction";
	}
	//��ת
	@RequestMapping(value="form")
	public String form(Violaction violaction,Model model) {
		List<WorkStationInfos> stations=workStationInfosService.findAllList(new WorkStationInfos());
		model.addAttribute("stationList",stations);
		model.addAttribute("violaction",violaction);
		return "modules/qc/violactionForm";
	}
	
	
}
