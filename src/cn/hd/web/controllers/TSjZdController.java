package cn.hd.web.controllers;

import cn.hd.common.page.PageBean;
import cn.hd.entity.TSjZdEntity;
import cn.hd.module.repository.service.TSjZdService;
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
@RequestMapping("tSjZdController")
public class TSjZdController extends BaseController {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    @Autowired
    private TSjZdService tSjZdService;

    /**
     * 新增业务
     *
     * @param tSjZd
     * @return
     */
    @RequestMapping(value = "savetSjZd")
    @ResponseBody
    public ResponseJSON savetSjZd(TSjZdEntity tSjZd, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。新增业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tSjZdService.saveTSjZd(tSjZd);
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
     * @param tSjZd
     * @return
     */
    @RequestMapping(value = "updateTSjZd")
    @ResponseBody
    public ResponseJSON updateTSjZd(TSjZdEntity tSjZd, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。修改业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tSjZdService.updateTSjZd(tSjZd);
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
     * @param tSjZd
     * @return
     */
    @RequestMapping(value = "delTSjZd")
    @ResponseBody
    public ResponseJSON delLocalIspCustomer(TSjZdEntity tSjZd, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。删除业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tSjZdService.delTSjZd(tSjZd);
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
     * @param tSjZd
     * @return
     */
    @RequestMapping(value = "queryPageList")
    @ResponseBody
    public Map<String, Object> queryPageList(TSjZdEntity tSjZd, HttpServletRequest request, PageBean<TSjZdEntity> pageBean) throws Exception {
        log.debug("。。。。。。。。。。。。。分页查询列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            responseJSON = tSjZdService.queryPageList(paramMap, pageBean.getPageNo(), pageBean.getPageSize());
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
     * @param tSjZd
     * @return
     */
    @RequestMapping(value = "queryList")
    @ResponseBody
    public Map<String, Object> queryList(TSjZdEntity tSjZd, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。查询所有数据列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            responseJSON = tSjZdService.queryList(paramMap);
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
