package cn.hd.web.controllers;

import cn.hd.common.enumeration.BanjieEnum;
import cn.hd.common.enumeration.ShangbaoEnum;
import cn.hd.common.page.PageBean;
import cn.hd.entity.THyHyInstEntity;
import cn.hd.module.repository.service.THyHyInstService;
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
@RequestMapping("tHyHyInstController")
public class THyHyInstController extends BaseController {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    @Autowired
    private THyHyInstService tHyHyInstService;

    /**
     * 新增业务
     *
     * @param tHyHyInst
     * @return
     */
    @RequestMapping(value = "savetHyHyInst")
    @ResponseBody
    public ResponseJSON savetHyHyInst(THyHyInstEntity tHyHyInst, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。新增业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tHyHyInstService.saveTHyHyInst(tHyHyInst);
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
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateBanjieZt")
    @ResponseBody
    public ResponseJSON updateBanjieZt() throws Exception {
        log.debug("。。。。。。。。。。。。。修改业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            String hyInstId = request.getParameter("hyInstId");

            responseJSON = tHyHyInstService.updateForBjZtById(hyInstId);
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
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateSbZt")
    @ResponseBody
    public ResponseJSON updateSbZt() throws Exception {
        log.debug("。。。。。。。。。。。。。修改业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            String hyInstId = request.getParameter("hyInstId");
            //修改上报状态:

            responseJSON = tHyHyInstService.updateForSbZtyId(hyInstId);
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
     * 修改业务
     *
     * @param tHyHyInst
     * @return
     */
    @RequestMapping(value = "updateTHyHyInst")
    @ResponseBody
    public ResponseJSON updateTHyHyInst(THyHyInstEntity tHyHyInst, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。修改业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tHyHyInstService.updateTHyHyInst(tHyHyInst);
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
     * @param tHyHyInst
     * @return
     */
    @RequestMapping(value = "delTHyHyInst")
    @ResponseBody
    public ResponseJSON delLocalIspCustomer(THyHyInstEntity tHyHyInst, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。删除业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tHyHyInstService.delTHyHyInst(tHyHyInst);
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
     * @param tHyHyInst
     * @return
     */
    @RequestMapping(value = "queryPageList")
    @ResponseBody
    public Map<String, Object> queryPageList(THyHyInstEntity tHyHyInst, HttpServletRequest request, PageBean<THyHyInstEntity> pageBean) throws Exception {
        log.debug("。。。。。。。。。。。。。分页查询列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            Integer flagStatus = tHyHyInst.getFlagStatus();
            if(flagStatus == 1){
                //接收方:上报与未上报之分
                Integer sbZtInt=tHyHyInst.getSbZtInt();
                paramMap.put("sbZt", ShangbaoEnum.notShangbao);
                if(sbZtInt == 1){
                    paramMap.put("sbZt", ShangbaoEnum.shangbao);
                }
            }else{
                //发送方:办结与未办结之分
                Integer bjBzInt=tHyHyInst.getBjBzInt();
                paramMap.put("bjBz", BanjieEnum.notBanjie);
                if(bjBzInt == 1){
                    paramMap.put("bjBz", BanjieEnum.banjied);
                }
            }
            paramMap.put("flagStatus",flagStatus);
            responseJSON = tHyHyInstService.queryPageList(paramMap, pageBean.getPageNo(), pageBean.getPageSize());
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
     * @param tHyHyInst
     * @return
     */
    @RequestMapping(value = "queryList")
    @ResponseBody
    public Map<String, Object> queryList(THyHyInstEntity tHyHyInst, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。查询所有数据列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            responseJSON = tHyHyInstService.queryList(paramMap);
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
}
