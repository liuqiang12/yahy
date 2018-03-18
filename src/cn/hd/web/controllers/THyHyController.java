package cn.hd.web.controllers;

import cn.hd.common.page.PageBean;
import cn.hd.entity.*;
import cn.hd.module.repository.service.*;
import cn.hd.utils.ResponseJSON;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("tHyHyController")
public class THyHyController extends BaseController {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    @Autowired
    private THyHyService tHyHyService;
    @Autowired
    private THyHyInstService tHyHyInstService;
    @Autowired
    private THyChdwService chdwService;//参会单位业务层
    @Autowired
    private TXtDwService dwService;//单位服务
    @Autowired
    private TXtRyService fqrRySevice;//发起人服务
    @Autowired
    private TXtHyddService hyddService;//会议地点
    @Autowired
    private TWjWjService tWjWjService;
    @Autowired
    private TWjFjService fjService;//附件主表结构

    /**
     * 新增业务
     *
     * @param tHyHy
     * @return
     */
    @RequestMapping(value = "savetHyHy_other")
    @ResponseBody
    public ResponseJSON savetHyHy_other(THyHyEntityVo tHyHy, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。新增业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        if (tHyHy.getId() == "" || "".equals(tHyHy.getId())){
            tHyHy.setId(null);
        }
        try {
            /* 后台验证。是否应存在了相应的标题:避免重复提交 */

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
     * 新增业务
     *
     * @param tHyHy
     * @return
     */
    @RequestMapping(value = "savetHyHy")
    @ResponseBody
    public ResponseJSON savetHyHy(THyHyEntity tHyHy, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。新增业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        if (tHyHy.getId() == "" || "".equals(tHyHy.getId())){
            tHyHy.setId(null);
        }
        try {
            Boolean isExistsByBt = tHyHyService.isExistsByMc(tHyHy);/* 后台验证。是否应存在了相应的标题:避免重复提交 */
            if(isExistsByBt){
                /* 已经存在了数据。不能再次保存 */
                responseJSON.setSuccess(Boolean.TRUE);
                responseJSON.setIsExists(true);
                responseJSON.setMsg("存在相同数据:"+tHyHy.getMc());
                responseJSON.setId(tHyHy.getId());
            }else{
                String fqr_id = request.getParameter("fqr_id");//发起人id
                String hhyd_id = request.getParameter("hhyd_id");//会议地点Id
                String chdw_ids = request.getParameter("chdw_ids");//参会单位IDS
                /* 参会单位IDS */
                if(chdw_ids != null && !"".equals(chdw_ids)){
                    String[] chdw_ids_tmp = chdw_ids.split(";");
                    Set<TXtDwEntity> chdwEntities = new HashSet<TXtDwEntity>();
                    for(int i = 0 ;i < chdw_ids_tmp.length ; i++){
                        String chdw_id = chdw_ids_tmp[i];
                        TXtDwEntity chdwEntity = dwService.findById(chdw_id);
                        chdwEntities.add(chdwEntity);
                    }
                    tHyHy.setChdwEntities(chdwEntities);
                }
                String sbcldw_ids = request.getParameter("sbcldw_ids");//上报材料单位ID

                if(sbcldw_ids != null && !"".equals(sbcldw_ids)){
                    String[] sbcldw_ids_tmp = sbcldw_ids.split(";");
                    Set<TXtDwEntity> dwEntities = new HashSet<TXtDwEntity>();
                    for(int i = 0 ;i < sbcldw_ids_tmp.length ; i++){
                         /*设置参会单位信息*/
                        String dw_id = sbcldw_ids_tmp[i];
                        TXtDwEntity dwEntity = dwService.findById(dw_id);
                        dwEntities.add(dwEntity);
                    }
                    tHyHy.setSbcldwEntities(dwEntities);
                }
                //发起人
                TXtRyEntity fqrEntity = fqrRySevice.findById(fqr_id);
                tHyHy.setFqrEntity(fqrEntity);
                TXtHyddEntity hyddEntity = hyddService.findById(hhyd_id);//设置会议地点信息 hhyd_id
                tHyHy.setHyddEntity(hyddEntity);
                responseJSON = tHyHyService.saveTHyHy(tHyHy,request);
                
                responseJSON.setMsg("保存成功!");
                responseJSON.setId(tHyHy.getId());
            }
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
     * @param tHyHy
     * @return
     */
    @RequestMapping(value = "updateTHyHy")
    @ResponseBody
    public ResponseJSON updateTHyHy(THyHyEntity tHyHy, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。修改业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tHyHyService.updateTHyHy(tHyHy);
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
    @RequestMapping(value = "updateSbZt")
    @ResponseBody
    public ResponseJSON updateSbZt(THyHyEntity tHyHy, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。修改业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            /* 已经上报 */
            responseJSON = tHyHyService.updateTHyHyForSbZtById(tHyHy);
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
     * 办结
     * @param tHyHy
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateBanjieZt")
    @ResponseBody
    public ResponseJSON updateBanjieZt(THyHyEntity tHyHy, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。修改业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            /* 提交办结 */
            responseJSON = tHyHyService.updateTHyHyForBjZtById(tHyHy);
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
     * @param tHyHy
     * @return
     */
    @RequestMapping(value = "delTHyHy")
    @ResponseBody
    public ResponseJSON delLocalIspCustomer(THyHyEntity tHyHy, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。删除业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tHyHyService.delTHyHy(tHyHy);
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
     * @param tHyHy
     * @return
     */
    @RequestMapping(value = "queryPageListBjZT")
    @ResponseBody
    public Map<String, Object> queryPageListBjZT(THyHyEntity tHyHy, HttpServletRequest request, PageBean<THyHyEntity> pageBean) throws Exception {
        log.debug("。。。。。。。。。。。。。分页查询列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
           /* if(tHyHy.getJbBzint() == 0){
                *//* 未办结 *//*
                tHyHy.setBjBz(BanjieEnum.notBanjie);
            }else if(tHyHy.getJbBzint() == 1){
                *//* 已办结 *//*
                tHyHy.setBjBz(BanjieEnum.banjied);
            }
            *//*//**//*//*
            paramMap.put("bjBz",tHyHy.getBjBz());*/
            /* 然后查询数据 */
            responseJSON = tHyHyService.queryBjZtPageList(paramMap, pageBean.getPageNo(), pageBean.getPageSize());
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
     * 分页查询列表
     *
     * @param tHyHy
     * @return
     */
    @RequestMapping(value = "queryPageList")
    @ResponseBody
    public Map<String, Object> queryPageList(THyHyEntity tHyHy, HttpServletRequest request, PageBean<THyHyEntity> pageBean) throws Exception {
        log.debug("。。。。。。。。。。。。。分页查询列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            /*if(tHyHy.getSbZtInt() == 0){
                *//* 未上报 *//*
                tHyHy.setSbZt(ShangbaoEnum.notShangbao);
            }else if(tHyHy.getSbZtInt() == 1){
                *//* 已经上报 *//*
                tHyHy.setSbZt(ShangbaoEnum.shangbao);
            }else if(tHyHy.getJbBzint() == 0){
                *//* 未办结 *//*
                tHyHy.setBjBz(BanjieEnum.notBanjie);
            }else if(tHyHy.getJbBzint() == 1){
                *//* 已办结 *//*
                tHyHy.setBjBz(BanjieEnum.banjied);
            }
            paramMap.put("sbZt",tHyHy.getSbZt());*/
            /* 然后查询数据 */
            responseJSON = tHyHyService.queryPageList(paramMap, pageBean.getPageNo(), pageBean.getPageSize());
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
    @RequestMapping(value = "countList")
    @ResponseBody
    public Map<String, Object> countList(THyHyEntity tHyHy, HttpServletRequest request, PageBean<THyHyEntity> pageBean) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            responseJSON = tHyHyService.countList();
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
     * @param tHyHy
     * @return
     */
    @RequestMapping(value = "queryList")
    @ResponseBody
    public Map<String, Object> queryList(THyHyEntity tHyHy, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。查询所有数据列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            responseJSON = tHyHyService.queryList(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("查询失败!");
        } finally {
        /* 日志控制信息:日志控制不需要和业务事物同步 */
            //((BaseController) AopContext.currentProxy()).addLog(responseJSON.getT_xt_rz());
        };
        return (Map) responseJSON.getResult();
    }

    @RequestMapping(value = "loadNotQsGridData")
    @ResponseBody
    public Map<String, Object> loadNotQsGridData(THyHyEntity tHyHy, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。查询所有数据列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            String hyId = request.getParameter("hyId");
            paramMap.put("id",hyId);
            responseJSON = tHyHyService.loadNotQsGridData(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("查询失败!");
        } finally {
            /* 日志控制信息:日志控制不需要和业务事物同步 */
           // ((BaseController) AopContext.currentProxy()).addLog(responseJSON.getT_xt_rz());
        };
        return (Map) responseJSON.getResult();
    }

    /**
     * 参会单位列表信息
     * @param tHyHy
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "loadChdwGridData")
    @ResponseBody
    public Map<String, Object> loadChdwGridData(THyHyEntity tHyHy, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。查询所有数据列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            String hyId = request.getParameter("hyId");
            paramMap.put("hyId",hyId);
            responseJSON = tHyHyService.loadChdwGridData(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("查询失败!");
        } finally {
            /* 日志控制信息:日志控制不需要和业务事物同步 */
            // ((BaseController) AopContext.currentProxy()).addLog(responseJSON.getT_xt_rz());
        };
        return (Map) responseJSON.getResult();
    }


    /**
     * 报送材料信息:通过会议id
     * @param tHyHy
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryBsclList")
    @ResponseBody
    public Map<String,Object> queryBsclList(THyHyEntity tHyHy) throws Exception {
        log.debug("。。。。。。。。。。。。。报送材料。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();

        Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
        String hyId = request.getParameter("hyId");
        paramMap.put("hyId",hyId);
        responseJSON = tWjWjService.queryBsclList(paramMap);

        return (Map) responseJSON.getResult();
    }

    /**
     * 报送名单管理界面[可以和查询界面合并]
     * @return
     */
    @RequestMapping("bsmdWinPlugin")
    public String bsmdWinPlugin(){
        //这个是会议ID
        String hyInstId = request.getParameter("hyInstId");
        THyHyInstEntity hyHyInstEntity = tHyHyInstService.findById(hyInstId);
        model.addAttribute("hyHyInstEntity",hyHyInstEntity);
        model.addAttribute("hyHyEntity",hyHyInstEntity.getHyHyEntity());
        model.addAttribute("hyId",hyHyInstEntity.getHyHyEntity().getId());
        return hyglPrefix+"bsmdWinPlugin";
    }

    /**
     * 报送名单列表查询界面
     * @return
     */
    @RequestMapping("bsmdQueryWinPlugin")
    public String bsmdQueryWinPlugin(){
        String hyInstId = request.getParameter("hyInstId");
        THyHyInstEntity hyHyInstEntity = tHyHyInstService.findById(hyInstId);
        model.addAttribute("hyHyInstEntity",hyHyInstEntity);
        model.addAttribute("hyHyEntity",hyHyInstEntity.getHyHyEntity());
        model.addAttribute("hyId",hyHyInstEntity.getHyHyEntity().getId());

        return hyglPrefix+"bsmdQueryWinPlugin";
    }

    /**
     * 获取统计信息数据
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "refreshTjInfo")
    @ResponseBody
    public Map<String, Object> refreshTjInfo() throws Exception {
        log.debug("。。。。。。。。。。。。。获取统计信息数据。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            String id = request.getParameter("id");
            paramMap.put("id",id);
            /* 获取统计数据 */
            responseJSON = tHyHyService.refreshTjInfo(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("查询失败!");
        } finally {
            /* 日志控制信息:日志控制不需要和业务事物同步 */
            //((BaseController) AopContext.currentProxy()).addLog(responseJSON.getT_xt_rz());
        };
        return (Map) responseJSON.getResult();
    }

    /**
     * 判断是否具有参会相关人员
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "isCHryExist")
    @ResponseBody
    public Map<String, Object> isCHryExist() throws Exception {
        log.debug("。。。。。。。。。。。。。判断是否会否具有已选参会人员。。。。。。。。。。。。。");
        Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
        String hyInstId = request.getParameter("hyInstId");
        paramMap.put("hyInstId",hyInstId);
        return tHyHyService.isCHryExist(paramMap);
    }


    /**
     * 上报参会人员
     * @return
     */
    @RequestMapping("bscyryWin")
    public String bscyryWin(){
        String id = request.getParameter("id");
        model.addAttribute("hy_id",id);
        return hyglPrefix+"bscyryWin";
    }

    /**
     * 从单位中选择人员
     * @return
     */
    @RequestMapping("dwhyzxzWin")
    public String dwhyzxzWin(){
        String id = request.getParameter("id");
        model.addAttribute("hyId",id);
        return hyglPrefix+"dwhyzxzWin";
    }

    @RequestMapping("openWinInfo")
    public String openWinInfo(){
        String id = request.getParameter("id");
        //不是会议id  就是会议实例

        model.addAttribute("hyInstId",id);

        return hyglPrefix+"openWinInfo";
    }

    @RequestMapping("bsmdQueryEditWinPlugin")
    public String bsmdQueryEditWinPlugin(){
        String hyInstId = request.getParameter("hyInstId");

        THyHyInstEntity hyHyInstEntity = tHyHyInstService.findById(hyInstId);

        /* 会议id */
        THyHyEntity hyHyEntity = hyHyInstEntity.getHyHyEntity();
        /* 参会单位ids 参会单位names */
        Set<TXtDwEntity> chdwEntity = hyHyEntity.getChdwEntities();
        Set<TXtDwEntity> sbhdwEntity = hyHyEntity.getSbcldwEntities();
        /* 以分号隔开 */
        StringBuffer chdwIds = new StringBuffer();
        StringBuffer chdwNames = new StringBuffer();
        //上报单位
        StringBuffer sbdwIds = new StringBuffer();
        StringBuffer sbdwNames = new StringBuffer();
        Iterator<TXtDwEntity> dwIt = chdwEntity.iterator();
        while(dwIt.hasNext()){
            TXtDwEntity dwEntity = dwIt.next();
            String id_tmp = dwEntity.getId();
            String name_tmp = dwEntity.getJgMc();
            chdwIds.append(id_tmp).append(";");
            chdwNames.append(name_tmp).append(";");
        }
        chdwIds.deleteCharAt(chdwIds.lastIndexOf(";"));
        chdwNames.deleteCharAt(chdwNames.lastIndexOf(";"));
        model.addAttribute("chdwIds",chdwIds);
        model.addAttribute("chdwNames",chdwNames);
        //上报单位
        Iterator<TXtDwEntity> sbdwIt = sbhdwEntity.iterator();
        while(sbdwIt.hasNext()){
            TXtDwEntity dwEntity = sbdwIt.next();
            String id_tmp = dwEntity.getId();
            String name_tmp = dwEntity.getJgMc();
            sbdwIds.append(id_tmp).append(";");
            sbdwNames.append(name_tmp).append(";");
        }
        sbdwIds.deleteCharAt(sbdwIds.lastIndexOf(";"));
        sbdwNames.deleteCharAt(sbdwNames.lastIndexOf(";"));
        model.addAttribute("sbdwIds",sbdwIds);
        model.addAttribute("sbdwNames",sbdwNames);
        model.addAttribute("hyHyEntity",hyHyEntity);
        /*查询相关的附件信息：通知原件 和 报送材料 */
        List<TWjFjEntity> fjEntities = fjService.findFjAndZcByHyId(hyHyInstEntity.getHyHyEntity().getId());
        if(fjEntities != null && !fjEntities.isEmpty()){
            for(int i = 0 ;i < fjEntities.size() ; i++){
                TWjFjEntity fjEntity = fjEntities.get(i);
                TWjWjEntity wjEntity = fjEntity.getWjWjEntity()!=null?fjEntity.getWjWjEntity():null;
                if(wjEntity != null && wjEntity.getOgicColumn().equals("tzyj")){//通知原件
                    model.addAttribute("tzyj_fjEntity",fjEntity);
                    model.addAttribute("tzyj_wjEntity",wjEntity);
                }else if(wjEntity != null && wjEntity.getOgicColumn().equals("hyzc")){//报送材料
                    model.addAttribute("hyzc_fjEntity",fjEntity);
                    model.addAttribute("hyzc_wjEntity",wjEntity);
                }
            }
        }
        return hyglPrefix+"bsmdQueryEditWinPlugin";
    }





}
