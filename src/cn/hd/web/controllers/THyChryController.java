package cn.hd.web.controllers;

import cn.hd.common.enumeration.GenderCategoryEnum;
import cn.hd.common.enumeration.GenderEnum;
import cn.hd.common.enumeration.HotelEnum;
import cn.hd.common.page.PageBean;
import cn.hd.common.util.WordUtil;
import cn.hd.entity.THyChryEntity;
import cn.hd.entity.THyHyEntity;
import cn.hd.entity.TXtRyEntity;
import cn.hd.module.repository.service.THyChryService;
import cn.hd.module.repository.service.THyHyService;
import cn.hd.module.repository.service.TXtRyService;
import cn.hd.utils.ResponseJSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("tHyChryController")
public class THyChryController extends BaseController {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    @Autowired
    private THyChryService tHyChryService;
    @Autowired
    private THyHyService hyHyService;//会议服务
    @Autowired
    private TXtRyService ryService;//人员信息

    /**
     * 批量保存参会人员
     * @param resourceSettingData
     * @return
     */
    @RequestMapping(value = "savetHyChryBatch")
    @ResponseBody
    public ResponseJSON savetHyChryBatch(String resourceSettingData) throws Exception {
        log.debug("。。。。。。。。。。。。。新增业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            /**
             *json格式
             */
            if(resourceSettingData != null && !"".equals(resourceSettingData)){
                JSONArray arrayVoJson = JSONArray.fromObject(resourceSettingData);
                Collection collection=JSONArray.toCollection(arrayVoJson);
                if(collection!=null && !collection.isEmpty())
                {
                    Iterator it=collection.iterator();
                    while(it.hasNext())
                    {
                        JSONObject jsonObj=JSONObject.fromObject(it.next());
                        /* 通过用户id获取用户信息 */
                        String id = String.valueOf(jsonObj.get("id"));
                        String lb = String.valueOf(jsonObj.get("lb"));
                        String bz = String.valueOf(jsonObj.get("bz"));
                        String zsBz = String.valueOf(jsonObj.get("zsBz"));
                        String lxfs = String.valueOf(jsonObj.get("lxfs"));
                        String hyId = String.valueOf(jsonObj.get("hyId"));
                        TXtRyEntity ryEntity = ryService.findById(id);
                        THyChryEntity chryEntity = new THyChryEntity();
                        if(ryEntity.getXtDwEntity() != null ){
                            chryEntity.setDwId(ryEntity.getXtDwEntity().getId());
                            chryEntity.setDwName(ryEntity.getXtDwEntity().getJgMc());
                        }
                        chryEntity.setNbStatus(1);
                        chryEntity.setXm(ryEntity.getXm());
                        chryEntity.setXb(ryEntity.getXb());
                        chryEntity.setZw(ryEntity.getZw());
                        if(lb != null && "0".equals(lb)){
                            chryEntity.setLb(GenderCategoryEnum.Participant);
                        }else if(lb != null && "1".equals(lb)){
                            chryEntity.setLb(GenderCategoryEnum.Personnel);
                        }else if(lb != null && "2".equals(lb)){
                            chryEntity.setLb(GenderCategoryEnum.Driver);
                        }else if(lb != null && "3".equals(lb)){
                            chryEntity.setLb(GenderCategoryEnum.Leaved);
                        }
                        chryEntity.setBz(bz);
                        if(zsBz != null && "0".equals(zsBz)){
                            chryEntity.setZsBz(HotelEnum.notwant);
                        }else{
                            chryEntity.setZsBz(HotelEnum.want);
                        }
                        chryEntity.setLxfs(lxfs);
                        chryEntity.setRyId(id);
                        /**
                         * 保存
                         */
                        /*chryEntity.setHyId(hyId);*/
                        //获取会议实体
                        THyHyEntity hyEntity = hyHyService.findById(hyId);
                        chryEntity.setHyHyEntity(hyEntity);
                        tHyChryService.saveTHyChry(chryEntity);//保存相关人员
                    }
                }

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
     * 新增业务
     * @param tHyChry
     * @return
     */
    @RequestMapping(value = "savetHyChry")
    @ResponseBody
    public ResponseJSON savetHyChry(THyChryEntity tHyChry) throws Exception {
        log.debug("。。。。。。。。。。。。。新增业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            String hy_id = request.getParameter("hy_id");
            String sex = request.getParameter("sex");
            String lb_int = request.getParameter("lb_int");
            String zs = request.getParameter("zs");

            THyHyEntity hyHyEntity = hyHyService.findById(hy_id);
            if("1".equals(sex)){tHyChry.setXb(GenderEnum.male);}else{tHyChry.setXb(GenderEnum.female);}

            if("0".equals(lb_int)){tHyChry.setLb(GenderCategoryEnum.Participant);tHyChry.setBz(null);}
            else if("1".equals(lb_int)){tHyChry.setLb(GenderCategoryEnum.Personnel);tHyChry.setBz(null);}
            else if("2".equals(lb_int)){tHyChry.setLb(GenderCategoryEnum.Driver);tHyChry.setBz(null);}
            else if("3".equals(lb_int)){tHyChry.setLb(GenderCategoryEnum.Leaved);}

            if("1".equals(zs)){tHyChry.setZsBz(HotelEnum.want);}else{tHyChry.setZsBz(HotelEnum.notwant);}
            tHyChry.setHyHyEntity(hyHyEntity);
            responseJSON = tHyChryService.saveTHyChry(tHyChry);
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
     * @param tHyChry
     * @return
     */
    @RequestMapping(value = "updateTHyChry")
    @ResponseBody
    public ResponseJSON updateTHyChry(THyChryEntity tHyChry, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。修改业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tHyChryService.updateTHyChry(tHyChry);
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
     * updateById 状态数据
     * @param tHyChry
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateById")
    @ResponseBody
    public ResponseJSON updateById(THyChryEntity tHyChry, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。修改业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tHyChryService.updateTHyChry(tHyChry);
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
     * @param tHyChry
     * @return
     */
    @RequestMapping(value = "delTHyChry")
    @ResponseBody
    public ResponseJSON delLocalIspCustomer(THyChryEntity tHyChry, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。删除业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tHyChryService.delTHyChry(tHyChry);
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
    @RequestMapping(value = "deleteById")
    @ResponseBody
    public ResponseJSON deleteById(THyChryEntity tHyChry, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。删除业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tHyChryService.delTHyChryById(tHyChry);
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("删除失败!");
        } finally {
            ((BaseController) AopContext.currentProxy()).addLog(responseJSON.getT_xt_rz());
        }
        ;
        return responseJSON;
    }

    /**
     * 参会人员信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("transmitParticipants")
    public String transmitParticipants(HttpServletRequest request, org.springframework.ui.Model model){
        // 获取相应的数据
        String id = request.getParameter("id");
        THyChryEntity chryEntity = tHyChryService.findById(id);
        model.addAttribute("chryEntity",chryEntity);
        return hyglPrefix+"transmitParticipants";
    }

    /**
     * 分页查询列表
     *
     * @param tHyChry
     * @return
     */
    @RequestMapping(value = "queryPageList")
    @ResponseBody
    public Map<String, Object> queryPageList(THyChryEntity tHyChry, HttpServletRequest request, PageBean<THyChryEntity> pageBean) throws Exception {
        log.debug("。。。。。。。。。。。。。分页查询列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            responseJSON = tHyChryService.queryPageList(paramMap, pageBean.getPageNo(), pageBean.getPageSize());
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
     * @return
     */
    @RequestMapping(value = "queryList")
    @ResponseBody
    public Map<String, Object> queryList() throws Exception {
        log.debug("。。。。。。。。。。。。。查询所有数据列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            String hyId = request.getParameter("hyId");
            paramMap.put("hyId",hyId);//查询参会人员
            responseJSON = tHyChryService.queryList(paramMap);
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
     * 弹出界面过滤信息
     * @param tHyChry
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryWinList")
    @ResponseBody
    public Map<String, Object> queryWinList(THyChryEntity tHyChry) throws Exception {
        log.debug("。。。。。。。。。。。。。查询所有数据列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            String hyId = request.getParameter("hyId");
            //暂时不适用 paramMap.put("hyId",tHyChry.getHyId());
            paramMap.put("hyId",hyId);
            if(tHyChry.getLbZt() == 0){ paramMap.put("lb",GenderCategoryEnum.Participant) ;
            }else if(tHyChry.getLbZt() == 1){ paramMap.put("lb",GenderCategoryEnum.Personnel) ;
            }else if(tHyChry.getLbZt() == 2){ paramMap.put("lb",GenderCategoryEnum.Driver) ;
            }else if(tHyChry.getLbZt() == 3){ paramMap.put("lb",GenderCategoryEnum.Leaved) ;}
            responseJSON = tHyChryService.queryWinList(paramMap);
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
     *
     * @param tHyChry
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "queryListWord")
    public void queryListWord(THyChryEntity tHyChry, HttpServletResponse resp) throws Exception {
        log.debug("。。。。。。。。。。。。。查询所有数据列表。。。。。。。。。。。。。");
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            //暂时不适用 paramMap.put("hyId",tHyChry.getHyId());
            /*paramMap.put("hyId",tHyChry.getHyId());*/

            if(tHyChry.getLbZt() == 0){
                paramMap.put("lb",GenderCategoryEnum.Participant) ;
            }else if(tHyChry.getLbZt() == 1){
                paramMap.put("lb",GenderCategoryEnum.Personnel) ;
            }else if(tHyChry.getLbZt() == 2){
                paramMap.put("lb",GenderCategoryEnum.Driver) ;
            }else if(tHyChry.getLbZt() == 3){
                paramMap.put("lb",GenderCategoryEnum.Leaved) ;
            }
            String fileName = ((GenderCategoryEnum)(paramMap.get("lb"))).getDescription();
            Map<String,Object> map = new HashMap<String,Object>();
            List<THyChryEntity> chRyEntities = tHyChryService.queryForWordWinList(paramMap);
            /* 设置住宿  性别 */
            Iterator<THyChryEntity> it = chRyEntities.iterator();
            while(it.hasNext()){
                THyChryEntity chryEntityTmp = it.next();
                if(chryEntityTmp.getXb().getVal() == 1){
                    chryEntityTmp.setXbstr("男");
                }else{
                    chryEntityTmp.setXbstr("女");
                }
                if(chryEntityTmp.getZsBz().getVal() == 1){
                    chryEntityTmp.setZsstr("住宿");
                }else{
                    chryEntityTmp.setZsstr("不住宿");
                }

            }




            map.put("listRecord",chRyEntities);
            //List<ChDwEntity> yqjsList =  tChDwService.findByHyId4Total(hyId);//已经签收:只是已经签收的单位    后续处理

            map.put("total",(chRyEntities == null || chRyEntities.isEmpty()?0:chRyEntities.size()));
            File file = null;
            InputStream in = null;
            ServletOutputStream out = null;
            try {
                // 调用工具类WordGenerator的createDoc方法生成Word文档
                file = WordUtil.createDoc(map, "listGrid4Chry");
                in = new FileInputStream(file);

                resp.setCharacterEncoding("utf-8");
                resp.setContentType("application/msword");
                // 设置浏览器以下载的方式处理该文件默认名为resume.doc

                resp.addHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode(fileName, "UTF-8")+".doc");
                out = resp.getOutputStream();
                byte[] buffer = new byte[1024];  // 缓冲区
                int bytesToRead = -1;
                // 通过循环将读入的Word文件的内容输出到浏览器中
                while((bytesToRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesToRead);
                }
            } finally {
                if(in != null) in.close();
                if(out != null) out.close();
                if(file != null) file.delete(); // 删除临时文件
            }
    }



}
