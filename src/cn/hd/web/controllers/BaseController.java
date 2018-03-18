package cn.hd.web.controllers;

import cn.hd.entity.T_xt_rz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@Scope("prototype")
public class BaseController extends AbstractController {
	public final String hyglPrefix = "page/hygl/";
	public final String jdglPrefix = "page/jdgl/";
	public T_xt_rz addLog(T_xt_rz rz){
//		T_xt_rz rz = new T_xt_rz();

		return rz;
	}
	public T_xt_rz jlLog(Integer module,String url){
		/** 日志记录信息* */
		T_xt_rz rz = new T_xt_rz();
		/********** 根据业务不同设置相应的参数值 ***********/
		/*rz.setNr("点击菜单模块["+ MenuModuleEnum.getDescriptionFromModule(module)+"]链接,查询相关业务");//操作详情
		rz.setMk_bz(ModuleEnum.menuModule);//菜单链接模块
		rz.setSj_id(String.valueOf(ModuleEnum.menuModule.getVal()));//模块ID[需要手动修改]
		rz.setLx_bz(OpTypeEnum.linked);//操作类型
		rz.setBz("链接的地址是："+jdglPrefix+url);//日志备注
		rz.setCj_sj(new Date());//创建时间
		*//*********** 需要从前端传递start ************//*
		rz.setCjr_fid("雅安市委机要局");//创建人单位ID
		rz.setCjr_id("杨柳周");//创建人ID
		((BaseController) AopContext.currentProxy()).addLog(rz);*/

		return rz;
	}
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected ModelMap model;
	/*@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, null, true));
		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true));
		binder.registerCustomEditor(Float.class, new CustomNumberEditor(Float.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
		binder.registerCustomEditor(BigInteger.class, new CustomNumberEditor(BigInteger.class, true));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));


	}*/
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}
	protected ApplicationContext getAppContext(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
		return ctx;
	}

	/**
	 * 使用了ModelAttribute：作用
	 * 1)放置在方法的形参上：表示引用Model中的数据
	 * 2)放置在方法上面：表示请求该类的每个Action前都会首先执行它，也可以将一些准备数据的操作放置在该方法里面。
	 * */
	@ModelAttribute
	protected void setReqAndRes(HttpServletRequest request, HttpServletResponse response,
								ModelMap model,String[] ids,Integer viewFlag,Integer zgid,String viewQuery ){
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.model=model;
		this.model.addAttribute("viewFlag", viewFlag);
		this.model.addAttribute("zgid", zgid);
		this.model.addAttribute("viewQuery", viewQuery);
	}

	protected HttpServletRequest getRequest() {
		return request;
	}

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	protected HttpServletResponse getResponse() {
		return response;
	}

	protected void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	//List 去重
	public List removeDuplicate(List<String> list) {
		HashSet h = new HashSet(list);
		List newList = new ArrayList();
		newList.addAll(h);
		return newList;
	}
	/**
	 * 在request中设置值；内部转发必须使用setAttr才能在跳转的方法中获取到值
	 * @param attributeName
	 * @param attributeValues
	 */
	protected void setAttr(String attributeName,Object attributeValues){
		if(this.request!=null){
			this.request.setAttribute(attributeName, attributeValues);
		}
	}


	/**
	 * 获取request中的值：内部转发必须使用getAttr才能获取到值
	 * @param attributeName
	 * @return 如果没有值则返回null
	 */
	protected Object getAttr(String attributeName){
		if(this.request!=null){
			return this.request.getAttribute(attributeName);
		}
		return null;
	}

	/**
	 * 获取request中的值：内部转发必须使用getParameter才能获取到值
	 * @param attributeName
	 * @return 如果没有值则返回null
	 */
	protected String getParameter(String attributeName){
		if(this.request!=null){
			return this.request.getParameter(attributeName);
		}
		return null;
	}
	/**
	 * easyui固定的返回格式 rows 和total
	 * @param list
	 * @return
	 */
	protected Map<String, Object> responseList(List list){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", (list!=null&&!list.isEmpty())?list.size():0);
		return map;
	}
	protected String forward(String url){
		if(!url.startsWith("/")){
			url="/"+url;
		}
		return "forward:"+url;
	}

	protected String redirect(String url){
		if(!url.startsWith("/")){
			url="/"+url;
		}
		return "redirect:"+url;
	}
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
}
