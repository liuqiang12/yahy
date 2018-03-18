package cn.hd.web.controllers;

import cn.hd.common.page.PageBean;
import cn.hd.common.util.WordUtil;
import cn.hd.entity.ChDwEntity;
import cn.hd.module.repository.service.TChDwService;
import cn.hd.utils.ResponseJSON;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("tChDwController")
public class TChDwController extends BaseController {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    @Autowired
    private TChDwService tChDwService;

    /**
     * 新增业务
     *
     * @return
     */
    @RequestMapping(value = "savetChDw")
    @ResponseBody
    public ResponseJSON savetChDw(ChDwEntity tChDw, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。新增业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tChDwService.saveTChDw(tChDw);
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
     * @param tChDw
     * @return
     */
    @RequestMapping(value = "updateTChDw")
    @ResponseBody
    public ResponseJSON updateTChDw(ChDwEntity tChDw, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。修改业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tChDwService.updateTChDw(tChDw);
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
     * @param tChDw
     * @return
     */
    @RequestMapping(value = "delTChDw")
    @ResponseBody
    public ResponseJSON delLocalIspCustomer(ChDwEntity tChDw, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。删除业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tChDwService.delTChDw(tChDw);
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
     * @param tChDw
     * @return
     */
    @RequestMapping(value = "queryPageList")
    @ResponseBody
    public Map<String, Object> queryPageList(ChDwEntity tChDw, HttpServletRequest request, PageBean<ChDwEntity> pageBean) throws Exception {
        log.debug("。。。。。。。。。。。。。分页查询列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            responseJSON = tChDwService.queryPageList(paramMap, pageBean.getPageNo(), pageBean.getPageSize());
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
     * @param tChDw
     * @return
     */
    @RequestMapping(value = "queryList")
    @ResponseBody
    public Map<String, Object> queryList(ChDwEntity tChDw, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。查询所有数据列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            paramMap.put("hyId", tChDw.getHyId());
            responseJSON = tChDwService.queryList(paramMap);
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
     * 参会单位的word导出功能
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "queryListWord")
    public void queryListWord(HttpServletResponse resp)  throws ServletException, IOException {
        String hyId = request.getParameter("hyId");

        Map<String,Object> map = new HashMap<String,Object>();
        /**
         * 参会单位的所有原始数据
         *
         */
        List<ChDwEntity> chDwEntityList = new ArrayList<ChDwEntity>();
        List<Object[]> chDwEntities = tChDwService.queryListWord(hyId);
        if(chDwEntities != null && !chDwEntities.isEmpty()){
            for(int i = 0 ; i < chDwEntities.size(); i++){
                ChDwEntity chDwEntity = new ChDwEntity();
                String chdwName = String.valueOf(chDwEntities.get(i)[0]);
                String remark = String.valueOf(chDwEntities.get(i)[1]);
                chDwEntity.setChdw(chdwName);
                chDwEntity.setQssj(remark);

                chDwEntityList.add(chDwEntity);
            }
        }
        map.put("listRecord",chDwEntityList);
        map.put("total",(chDwEntities == null || chDwEntities.isEmpty()?0:chDwEntities.size()));
        File file = null;
        InputStream in = null;
        ServletOutputStream out = null;
        try {
            // 调用工具类WordGenerator的createDoc方法生成Word文档
            file = WordUtil.createDoc(map, "listGrid");
            in = new FileInputStream(file);

            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/msword");
            // 设置浏览器以下载的方式处理该文件默认名为resume.doc
            resp.addHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode("参会单位", "UTF-8")+".doc");

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
