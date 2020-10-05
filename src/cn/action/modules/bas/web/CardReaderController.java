package cn.action.modules.bas.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.action.modules.bas.entity.AndroidPAD;
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
import cn.action.modules.bas.entity.CardReader;
import cn.action.modules.bas.entity.WorkCell;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.bas.service.CardReaderService;
import cn.action.modules.bas.service.WorkCellService;
import cn.action.modules.bas.service.WorkStationInfosService;

@Controller
@RequestMapping(value="${adminPath}/bas/cardReader")
public class CardReaderController extends BaseController{
	@Autowired
	private CardReaderService cardReaderService;
	@Autowired
	private WorkCellService workCellService;
	@Autowired
	private WorkStationInfosService workStationInfosService;
	
	@ModelAttribute("cardReader")
	public CardReader get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return cardReaderService.get(id);
		}
		return new CardReader();
	}
	//��ѯ
	@RequestMapping(value= {"list",""})
	public String list(CardReader cardReader,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<CardReader> page=cardReaderService.findPage(new Page<CardReader>(request, response), cardReader);
		model.addAttribute("page", page);
		return "modules/bas/cardReaderList";
	}
	//����
	@RequestMapping(value="save")
	public String save(CardReader cardReader,Model model,RedirectAttributes redirectAttributes) {
		cardReaderService.save(cardReader);
		this.addMessage(redirectAttributes, "����������豸�ɹ���");
		return "redirect:"+adminPath+"/bas/cardReader";
	}
	//ɾ��
	@RequestMapping(value="delete")
	public String delete(CardReader cardReader,Model model,RedirectAttributes redirectAttributes) {
		cardReaderService.delete(cardReader);
		this.addMessage(redirectAttributes, "ɾ���������豸�ɹ���");
		return "redirect:"+adminPath+"/bas/cardReader";
	}
	//����ɾ��
	@RequestMapping(value="deleteList")
	public String deleteList(String[] idAr, CardReader cardReader, Model model, RedirectAttributes redirectAttributes) {

		for (int i = 0; i < idAr.length; i++) {
			cardReader.setId(idAr[i]);
			cardReaderService.delete(cardReader);
		}
		this.addMessage(redirectAttributes, "ɾ���������豸�ɹ���");
		return "redirect:" + adminPath + "/bas/cardReader";
	}
	@RequestMapping(value="form")
	public String form(CardReader cardReader,Model model) {
		//������й�λ
		List<WorkCell> workCellList=workCellService.findAllList(new WorkCell());
		//������й�վ
		List<WorkStationInfos> workStationInfosList=workStationInfosService.findAllList(new WorkStationInfos());
		
		model.addAttribute("workCellList", workCellList);
		model.addAttribute("workStationInfosList",workStationInfosList);
		model.addAttribute("cardReader",cardReader);
		return "modules/bas/cardReaderForm";
		
	}
}
