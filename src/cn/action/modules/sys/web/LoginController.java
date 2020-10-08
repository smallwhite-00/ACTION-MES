package cn.action.modules.sys.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.action.common.utils.*;
import cn.action.modules.sys.utils.RandomValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;

import cn.action.common.config.Global;
import cn.action.common.web.BaseController;
import cn.action.modules.sys.entity.Menu;
import cn.action.modules.sys.entity.User;
import cn.action.modules.sys.service.SystemService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController extends BaseController{


	@Autowired
	private SystemService systemService;
	String code = null;


	/**
	 * �����¼
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
	public String login(HttpSession session, HttpServletResponse response, Model model) {

		// ����ѵ�¼���ٴη�����ҳ�����˳�ԭ�˺š�
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))){
			CookieUtils.setCookie(response, "LOGINED", "false");
		}
		User user =  (User)session.getAttribute(UserUtils.CURRENT_USER);
		// ����Ѿ���¼������ת��������ҳ
		if(user != null){
			return "redirect:" + adminPath;
		}
		return "modules/sys/sysLogin";
	}

	/**
	 * ��ȡ������֤����ʾ�� UI ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="${adminPath}/checkCode")
	public void checkCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//������Ӧ����,������������������ΪͼƬ
		response.setContentType("image/jpeg");

		//������Ӧͷ��Ϣ�������������Ҫ���������
		response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);

		RandomValidateCode randomValidateCode = new RandomValidateCode();
		try {
			code = randomValidateCode.getRandcode(request, response);//���ͼƬ����
			System.out.println("code of right:"+code);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.POST)
	public String login(HttpSession session,String account,String pwd,Model model,HttpServletRequest request ,@RequestParam String inputcode){

		User user = systemService.login(account, pwd);

		System.out.println("code of user inputted:"+inputcode);
		if ((user!=null)&&(code.equalsIgnoreCase(inputcode))) {
			user.setRoleList(UserUtils.getRoleList());
			session.setAttribute(UserUtils.CURRENT_USER, user);
			//��ȡ�û��Ĳ˵�
			List<Menu> menus = systemService.getMenuByUserId(user.getId());
			session.setAttribute(UserUtils.MENU_LIST, menus);

			//if(code.equalsIgnoreCase(inputcode))
			//	{
			return "modules/sys/sysIndex";

			//addMessage(model,"��֤�����");
			//return "redirect:" + adminPath;
			//return "modules/sys/sysIndex";
			//	}

			//addMessage(model, "��֤�����");
			//return "modules/sys/sysIndex"
		}

		//addMessage(model, "�û�����������������������룡");
		System.out.println("�˻���Ϣ���������֤�����");
		return "redirect:" + adminPath;




	}


	/**
	 * ��¼�ɹ������������ҳ
	 */

	@RequestMapping(value = "${adminPath}")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		User principal = UserUtils.getUser();

		// ��¼�ɹ�����֤�����������
		//isValidateCodeLogin(principal.getLoginName(), false, true);
		if(principal!=null && principal.getId()==null) {
			return "redirect:" + adminPath + "/login";
		}
		//return "modules/sys/sysIndex";
		return null;
	}



	/**
	 * ��ȡ���ⷽ��
	 */
	@RequestMapping(value = "/theme/{theme}")
	public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request, HttpServletResponse response){
		if (StringUtils.isNotBlank(theme)){
			CookieUtils.setCookie(response, "theme", theme);
		}else{
			theme = CookieUtils.getCookie(request, "theme");
		}
		return "redirect:"+request.getParameter("url");
	}

	/**
	 * �Ƿ�����֤���¼
	 * @param useruame �û���
	 * @param isFail ������1
	 * @param clean ��������
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean){
		Map<String, Integer> loginFailMap = (Map<String, Integer>)EhCacheUtils.get("loginFailMap");
		if (loginFailMap==null){
			loginFailMap = Maps.newHashMap();
			EhCacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum==null){
			loginFailNum = 0;
		}
		if (isFail){
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean){
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}



	@RequestMapping(value = "${adminPath}/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute(UserUtils.CURRENT_USER);
		return "redirect:" + adminPath;
	}
}
