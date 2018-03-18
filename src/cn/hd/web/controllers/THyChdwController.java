package cn.hd.web.controllers;

import cn.hd.common.page.PageBean;
import cn.hd.entity.THyChdwEntity;
import cn.hd.module.repository.service.THyChdwService;
import cn.hd.utils.ResponseJSON;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("tHyChdwController")
public class THyChdwController extends BaseController {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    @Autowired
    private THyChdwService tHyChdwService;

    /**
     * 新增业务
     *
     * @param chdwEntity
     * @return
     */
    @RequestMapping(value = "saveChdwEntity")
    @ResponseBody
    public ResponseJSON savetHyChdw(THyChdwEntity chdwEntity, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。新增业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tHyChdwService.saveTHyChdw(chdwEntity);
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("保存失败!");
        } finally {
/* 日志控制信息:日志控制不需要和业务事物同步 */
            ((BaseController) AopContext.currentProxy()).addLog(responseJSON.getT_xt_rz());
        }
        ;
        return responseJSON;
    }

    /**
     * 修改业务
     *
     * @param chdwEntity
     * @return
     */
    @RequestMapping(value = "updateTHyChdw")
    @ResponseBody
    public ResponseJSON updateTHyChdw(THyChdwEntity chdwEntity, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。修改业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tHyChdwService.updateTHyChdw(chdwEntity);
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
     * @param chdwEntity
     * @return
     */
    @RequestMapping(value = "delTHyChdw")
    @ResponseBody
    public ResponseJSON delLocalIspCustomer(THyChdwEntity chdwEntity, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。删除业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tHyChdwService.delTHyChdw(chdwEntity);
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
     * @param chdwEntity
     * @return
     */
    @RequestMapping(value = "queryPageList")
    @ResponseBody
    public Map<String, Object> queryPageList(THyChdwEntity chdwEntity, HttpServletRequest request, PageBean<THyChdwEntity> pageBean) throws Exception {
        log.debug("。。。。。。。。。。。。。分页查询列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            responseJSON = tHyChdwService.queryPageList(paramMap, pageBean.getPageNo(), pageBean.getPageSize());
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("查询失败!");
        } finally {
    /* 日志控制信息:日志控制不需要和业务事物同步 */
            //((BaseController) AopContext.currentProxy()).addLog(responseJSON.getT_xt_rz());
        }
        ;
        return responseJSON.getResult();
    }

    /**
     * 查询所有数据列表
     *
     * @param chdwEntity
     * @return
     */
    @RequestMapping(value = "queryList")
    @ResponseBody
    public Map<String, Object> queryList(THyChdwEntity chdwEntity, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。查询所有数据列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            responseJSON = tHyChdwService.queryList(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("查询失败!");
        } finally {
    /* 日志控制信息:日志控制不需要和业务事物同步 */
            //((BaseController) AopContext.currentProxy()).addLog(responseJSON.getT_xt_rz());
        }
        ;
        return responseJSON.getResult();
    }




}
