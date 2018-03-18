package cn.hd.web.controllers;

import cn.hd.common.constant.TreeNode;
import cn.hd.common.enumeration.ZtreeEnum;
import cn.hd.common.page.PageBean;
import cn.hd.entity.TXtDwEntity;
import cn.hd.module.repository.service.TXtDwService;
import cn.hd.module.repository.service.ZTreeService;
import cn.hd.utils.ResponseJSON;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("tXtDwController")
public class TXtDwController extends BaseController {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    @Autowired
    private TXtDwService tXtDwService;
    @Autowired
    private ZTreeService zTreeService;


    /**
     * 新增业务
     *
     * @param tXtDw
     * @return
     */
    @RequestMapping(value = "savetXtDw")
    @ResponseBody
    public ResponseJSON savetXtDw(TXtDwEntity tXtDw) throws Exception {
        log.debug("。。。。。。。。。。。。。新增业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            //****是否选择了父级节点
            String parentId = request.getParameter("parentId");
            if(parentId != null &&  !"".equals(parentId)){
                TXtDwEntity parent = new TXtDwEntity();
                parent.setId(parentId);
                tXtDw.setParent(parent);
            }
            responseJSON = tXtDwService.saveTXtDw(tXtDw);
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
     * @param tXtDw
     * @return
     */
    @RequestMapping(value = "updateTXtDw")
    @ResponseBody
    public ResponseJSON updateTXtDw(TXtDwEntity tXtDw, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。修改业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tXtDwService.updateTXtDw(tXtDw);
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
     * @param tXtDw
     * @return
     */
    @RequestMapping(value = "delTXtDw")
    @ResponseBody
    public ResponseJSON delLocalIspCustomer(TXtDwEntity tXtDw, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。删除业务。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            responseJSON = tXtDwService.delTXtDw(tXtDw);
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
     * @param tXtDw
     * @return
     */
    @RequestMapping(value = "queryPageList")
    @ResponseBody
    public Map<String, Object> queryPageList(TXtDwEntity tXtDw, HttpServletRequest request, PageBean<TXtDwEntity> pageBean) throws Exception {
        log.debug("。。。。。。。。。。。。。分页查询列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            responseJSON = tXtDwService.queryPageList(paramMap, pageBean.getPageNo(), pageBean.getPageSize());
        } catch (Exception e) {
            e.printStackTrace();
            responseJSON.setSuccess(Boolean.FALSE);
            responseJSON.setMsg("查询失败!");
        } finally {
        /* 日志控制信息:日志控制不需要和业务事物同步 */
           // ((BaseController) AopContext.currentProxy()).addLog(responseJSON.getT_xt_rz());
        }
        ;
        return (Map) responseJSON.getResult();
    }

    /**
     * 查询所有数据列表
     *
     * @param tXtDw
     * @return
     */
    @RequestMapping(value = "queryList")
    @ResponseBody
    public Map<String, Object> queryList(TXtDwEntity tXtDw, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。查询所有数据列表。。。。。。。。。。。。。");
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();/****分页参数*****/
            responseJSON = tXtDwService.queryList(paramMap);
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

    //-------------------------------------------------------------------------------------
    @RequestMapping(value = "queryZtreeRecord")
    @ResponseBody
    public TreeNode queryZtreeRecord(TreeNode treeNode, HttpServletRequest request) throws Exception {
        log.debug("。。。。。。。。。。。。。[构建相应的树形数据]。。。。。。。。。。。。。");
        treeNode.setName("单位信息");
        treeNode.setNodeType("root");
        treeNode.setIsParent("true");
        treeNode.setIsedit("true");
        treeNode.setOpen("true");
        treeNode.setTitle("单位信息");
        treeNode.setPid("---");
        try {
            List<TreeNode> treeNodes =  zTreeService.getTree(treeNode,ZtreeEnum.XTDW,null,"false");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return treeNode;
    }
}
