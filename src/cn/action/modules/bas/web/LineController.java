package cn.action.modules.bas.web;

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
import cn.action.modules.bas.entity.Line;
import cn.action.modules.bas.entity.WorkShop;
import cn.action.modules.bas.service.LineService;
import cn.action.modules.bas.service.WorkShopService;

@Controller
@RequestMapping(value="${adminPath}/bas/line")
public class LineController extends BaseController {
	@Autowired
	private LineService lineService;
	@Autowired
	private WorkShopService workShopService;
	
	@ModelAttribute("line")
	public Line get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return lineService.get(id);
		}
		return new Line();
	}
	//��ҳ��ѯ
	@RequestMapping(value= {"list",""})
	public String list(Line line,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<Line> page=lineService.findPage(new Page<Line>(request,response), line);
		model.addAttribute("page", page);
		return "modules/bas/lineList";
	}
	//����
	@RequestMapping("save")
	public String save(Line line,Model model,RedirectAttributes redirectAttributes) {
		lineService.save(line);
		this.addMessage(redirectAttributes, "���������Ϣ�ɹ���");
		return "redirect:"+adminPath+"/bas/line";
	}
	//ɾ��
	@RequestMapping("delete")
	public String delete(Line line,RedirectAttributes redirectAttributes) {
		lineService.delete(line);
		this.addMessage(redirectAttributes, "ɾ��������Ϣ�ɹ���");
		return "redirect:"+adminPath+"/bas/line";
	}
	//��תҳ��
	@RequestMapping("form")
	public String form(Line line,Model model) {
		List<WorkShop> shopList=workShopService.findAllList(new WorkShop());
		model.addAttribute("shopList", shopList);
		model.addAttribute("line", line);
		return "modules/bas/lineForm";
	}
	
}
