package cn.action.modules.kpi.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import cn.action.common.persistence.Page;
import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.kpi.entity.CutPiece;
import cn.action.modules.kpi.service.CutPieceService;

@Controller
@RequestMapping(value="${adminPath}/kpi/cutPiece")
public class CutPieceController extends BaseController{
	@Autowired
	private CutPieceService cutPieceService;
	
	@RequestMapping(value= {"list",""})
	public String list(CutPiece cutPiece,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<CutPiece> page=cutPieceService.findPage(new Page<CutPiece>(request, response), cutPiece);
		model.addAttribute("page",page);
		return "modules/kpi/cutPieceList";
	}
}
