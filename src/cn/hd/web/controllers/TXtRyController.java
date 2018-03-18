package cn.hd.web.controllers;

import cn.hd.common.constant.TreeNode;
import cn.hd.common.enumeration.GenderEnum;
import cn.hd.common.enumeration.ZtreeEnum;
import cn.hd.common.page.PageBean;
import cn.hd.entity.TXtDwEntity;
import cn.hd.entity.TXtRyEntity;
import cn.hd.module.repository.service.TXtDwService;
import cn.hd.module.repository.service.TXtRyService;
import cn.hd.module.repository.service.ZTreeService;
import cn.hd.utils.ResponseJSON;

import org.apache.log4j.Logger;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统人员控制器
 */
@Controller
@RequestMapping("tXtRyController")
public class TXtRyController extends BaseController {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    @Autowired
    private TXtRyService tXtRyService;
    @Autowired
    private ZTreeService zTreeService;
    @Autowired
    private TXtDwService dwService;

    /**
     * 新增业务
     *
     * @param tXtRy
     * @return
     */
    @RequestMapping(value = "savetXtRy")
    @ResponseBody
    public ResponseJSON savetXtRy(TXtRyEntity tXtRy,HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。新增业务。。。。。。。。。。。。。");
        String xb_form = request.getParameter("xb_form");
        if("1".equals(xb_form)){
            tXtRy.setXb(2);
        }else{
            tXtRy.setXb(1);
        }
        tXtRy.setCjSj(new Date());
        if(tXtRy.getId() == null || "".equals(tXtRy.getId())){
            tXtRy.setId(null);
        }
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            /* 保存人员：首先确认是否具有单位 */
            String dwId = request.getParameter("dwId");
            if(dwId != null && !"".equals(dwId)){
                TXtDwEntity xtDwEntity = new TXtDwEntity();
                xtDwEntity.setId(dwId);
                /*tXtRy.setXtDwEntity(xtDwEntity);*/
                responseJSON = tXtRyService.saveTXtRy(tXtRy);
                responseJSON.setId(tXtRy.getId());
            }else{
                /*单位不存在，请核查数据*/
                responseJSON.setSuccess(Boolean.TRUE);
                Map<String,Object> resMap = new HashMap<String,Object>();
                responseJSON.setIsExists(false);
                responseJSON.setMsg("单位不存在，请核查数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("保存失败!");
        } finally {
        /* 日志控制信息:日志控制不需要和业务事物同步 */
            ((BaseController) AopContext.currentProxy()).addLog(responseJSON.getT_xt_rz());
        };
        return responseJSON;
    }

    /**
     * 修改业务
     *
     * @param tXtRy
     * @return
     */
    @RequestMapping(value = "updateTXtRy")
    @ResponseBody
    public ResponseJSON updateTXtRy(TXtRyEntity tXtRy, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。修改业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tXtRyService.updateTXtRy(tXtRy);
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("修改失败!");
        } finally {
        /* 日志控制信息:日志控制不需要和业务事物同步 */
            ((BaseController) AopContext.currentProxy()).addLog(responseJSON.getT_xt_rz());
        }
        ;
        return responseJSON;
    }

    /**
     * 删除业务
     *
     * @param tXtRy
     * @return
     */
    @RequestMapping(value = "delTXtRy")
    @ResponseBody
    public ResponseJSON delLocalIspCustomer(TXtRyEntity tXtRy, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。删除业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            tXtRy = tXtRyService.findById(tXtRy.getId());
            responseJSON = tXtRyService.delTXtRy(tXtRy);
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("删除失败!");
        } finally {
        /* 日志控制信息:日志控制不需要和业务事物同步 */
            ((BaseController) AopContext.currentProxy()).addLog(responseJSON.getT_xt_rz());
        }
        ;
        return responseJSON;
    }

    /**
     * 分页查询列表
     *
     * @param tXtRy
     * @return
     */
    @RequestMapping(value = "queryPageList")
    @ResponseBody
    public Map<String, Object> queryPageList(TXtRyEntity tXtRy, HttpServletRequest request, PageBean<TXtRyEntity> pageBean) throws Exception {
        log.debug("。。。。。。。。。。。。。分页查询列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            String dwId = request.getParameter("dwId");
            if(dwId != null && !"".equals(dwId) && !"null".equals(dwId)  ){
                paramMap.put("dwId",dwId);
            }

            responseJSON = tXtRyService.queryPageList(paramMap, pageBean.getPageNo(), pageBean.getPageSize());
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("查询失败!");
        } finally {
        /* 日志控制信息:日志控制不需要和业务事物同步 */
            //((BaseController) AopContext.currentProxy()).addLog(responseJSON.getT_xt_rz());
        }
        ;
        return (Map) responseJSON.getResult();
    }

    @RequestMapping(value = "queryPageListOhterHyId")
    @ResponseBody
    public Map<String, Object> queryPageListOhterHyId(TXtRyEntity tXtRy, HttpServletRequest request, PageBean<TXtRyEntity> pageBean) throws Exception {
        log.debug("。。。。。。。。。。。。。分页查询列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            String dwId = request.getParameter("dwId");
            String hyId = request.getParameter("hyId");
            if(dwId != null && !"".equals(dwId) && !"null".equals(dwId)  ){
                paramMap.put("dwId",dwId);
            }
            if(hyId != null && !"".equals(hyId)){
                paramMap.put("hyId",hyId);
            }
            responseJSON = tXtRyService.queryPageListOtherHyId(paramMap, pageBean.getPageNo(), pageBean.getPageSize());
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("查询失败!");
        } finally {
            /* 日志控制信息:日志控制不需要和业务事物同步 */
            //((BaseController) AopContext.currentProxy()).addLog(responseJSON.getT_xt_rz());
        }
        ;
        return (Map) responseJSON.getResult();
    }


    /**
     * 查询所有数据列表
     *
     * @param tXtRy
     * @return
     */
    @RequestMapping(value = "queryList")
    @ResponseBody
    public Map<String, Object> queryList(TXtRyEntity tXtRy, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。查询所有数据列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            responseJSON = tXtRyService.queryList(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("查询失败!");
        } finally {
        /* 日志控制信息:日志控制不需要和业务事物同步 */
            //((BaseController) AopContext.currentProxy()).addLog(responseJSON.getT_xt_rz());
        }
        ;
        return (Map) responseJSON.getResult();
    }

    /**
     * 用户的双向选择
     * @param tXtRy
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryMulitSelectList")
    @ResponseBody
    public Map<String, Object> queryMulitSelectList(TXtRyEntity tXtRy) throws Exception {
        log.debug("。。。。。。。。。。。。。查询所有数据列表。。。。。。。。。。。。。");
        String dwId = request.getParameter("dwId");

        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tXtRyService.queryMulitSelectList(dwId);
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("查询失败!");
        };
        return (Map) responseJSON.getResult();
    }
    /**
     * 加载默认的数据封装到界面中去
     * @return
     */
    @RequestMapping(value = "defaultInfo")
    @ResponseBody
    public ResponseJSON defaultInfo() throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tXtRyService.defaultInfo();
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("查询失败!");
        };
        return responseJSON;
    }
    
    @RequestMapping(value = "userSelectOpenPluginForHy")
    public String userSelectOpenPluginForHy() throws Exception {
        /*树形结构*/
        log.debug("。。。。。。。。。。。。。[构建相应的树形数据]。。。。。。。。。。。。。");
        TreeNode treeNode = new TreeNode();
        treeNode.setName("单位信息");
        treeNode.setNodeType("root");
        treeNode.setIsParent("true");
        treeNode.setIsedit("true");
        treeNode.setOpen("true");
        treeNode.setTitle("单位信息");
        treeNode.setPid("---");
        treeNode.setNocheck("true");
        try {
            zTreeService.getTree(treeNode, ZtreeEnum.XTDW,null,"true");
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("treeNode",net.sf.json.JSONObject.fromObject(treeNode));
        String hyId = request.getParameter("hyId");
        model.addAttribute("hyId",hyId);
        return jdglPrefix+"userSelectOpenPluginForHy";
    }
    
}
