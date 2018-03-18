package cn.hd.web.controllers;

import cn.hd.common.constant.TreeNode;
import cn.hd.common.enumeration.ZtreeEnum;
import cn.hd.entity.THyHyInstEntity;
import cn.hd.entity.TWjFjEntity;
import cn.hd.entity.TXtDwEntity;
import cn.hd.entity.TXtRyEntity;
import cn.hd.module.repository.service.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2017/12/17.
 * 刘强
 */
@Controller
@RequestMapping("hyGlController")
public class HyGlController extends BaseController{
    protected Logger log = Logger.getLogger(this.getClass().getName());
    @Autowired
    private ZTreeService zTreeService;
    @Autowired
    private THyHyService hyService;
    @Autowired
    private THyHyInstService tHyHyInstService;
    @Autowired
    private TWjFjService fjService;
    @Autowired
    private TXtDwService dwService;//系统单位
    @Autowired
    private TXtRyService ryService;//人员信息
    /**
     * 菜单跳转方法，跳转规则是.XXXX.do?URL=xxx:
     */
    @RequestMapping("menuLink/{url}/{module}")
    public String menuLink_xzjd(@PathVariable String url,@PathVariable Integer module,HttpServletRequest request, org.springframework.ui.Model model){
          /*菜单链接:模块功能*/
        try{
            jlLog(module,url);
            if(module == 16){
                model.addAttribute("flagStatus",1);
                model.addAttribute("sbZtInt",0);
            }else if(module == 17){
                model.addAttribute("flagStatus",1);
                model.addAttribute("sbZtInt",1);
            }else if(module == 18){
                model.addAttribute("flagStatus",2);
                model.addAttribute("bjBzInt",0);
            }else if(module == 19){
                model.addAttribute("flagStatus",2);
                model.addAttribute("bjBzInt",1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
       return hyglPrefix+url;
    }
    
    @RequestMapping("logpage")
    public String logpage(HttpServletRequest request, org.springframework.ui.Model model){
        /*  菜单链接:模块功能  */ 
       return jdglPrefix+"log";
    }

    /**
     * 创建会议
     * @return
     */
    @RequestMapping("createHyModel")
    public String createHyModel(){
        System.out.println("获取门户session用户   模拟");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("flag",1);
        TXtRyEntity xtRyEntity = ryService.getAuthUserByFlag("from TXtRyEntity t where t.flag = :flag",params);
        /* 系统人员 */
        model.addAttribute("xtRyEntity",xtRyEntity);
        return hyglPrefix+"add_meet";
    }
    /**
     * 参加会议查询列表
     * @return
     */
    @RequestMapping("cjhyRecordPageList")
    public String cjhyRecordPageList(){
        String flagStatus = request.getParameter("flagStatus");
        String sbZtInt = request.getParameter("sbZtInt");
        model.addAttribute("flagStatus",flagStatus);
        model.addAttribute("sbZtInt",sbZtInt);//就拿url作为标志使用:需唯一
        return hyglPrefix+"cjhyRecordPageList";
    }

    /**
     * 发起会议查询列表
     * @return
     */
    @RequestMapping("fqhyRecordPageList")
    public String fqhyRecordPageList(){
        String flagStatus = request.getParameter("flagStatus");
        String bjBzInt = request.getParameter("bjBzInt");
        model.addAttribute("flagStatus",flagStatus);
        model.addAttribute("bjBzInt",bjBzInt);//就拿url作为标志使用:需唯一
        return hyglPrefix+"fqhyRecordPageList";
    }

    @RequestMapping("navLink/{url}")
    public String navLink(@PathVariable String url){
        String hyInstId = request.getParameter("hyInstId");
        model.addAttribute("hyInstId",hyInstId);
        //获取会议实例信息
        THyHyInstEntity hyHyInstEntity =  tHyHyInstService.findById(hyInstId);
        model.addAttribute("hyHyInstEntity",hyHyInstEntity);
        model.addAttribute("hyEntity",hyHyInstEntity.getHyHyEntity());
        //通知原件--------------信息
        TWjFjEntity wjFjEntity = fjService.findTzfjByHyId(hyHyInstEntity.getHyHyEntity().getId());
        model.addAttribute("wjFjEntity",wjFjEntity);

        TWjFjEntity wjFjEntityTmp = fjService.findBsclByHyId(hyHyInstEntity.getHyHyEntity().getId());
        model.addAttribute("wjFjEntityTmp",wjFjEntityTmp);
        //会议座次
        TWjFjEntity hyzcEntity = fjService.findHyzcByHyId(hyHyInstEntity.getHyHyEntity().getId());
        model.addAttribute("hyzcEntity",hyzcEntity);
        return hyglPrefix+"page_fbhy/"+url;
    }

    /**
     *
     */
    @RequestMapping("cbdwOpenPlugin")
    public String cbdwOpenPlugin(HttpServletRequest request, org.springframework.ui.Model model){
        /*  菜单链接:模块功能  */
        log.debug("。。。。。。。。。。。。。[构建相应的树形数据]。。。。。。。。。。。。。");
        TreeNode treeNode = new TreeNode();
        treeNode.setName("单位信息");
        treeNode.setNodeType("root");
        treeNode.setIsParent("true");
        treeNode.setIsedit("true");
        treeNode.setOpen("true");
        treeNode.setTitle("单位信息");
        treeNode.setPid("---");
        try {
            List<TreeNode> treeNodes =  zTreeService.getTree(treeNode,ZtreeEnum.XTDW,null,"false");
            /*treeNode.setChildren(treeNodes);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("treeNode",net.sf.json.JSONObject.fromObject(treeNode));
        return hyglPrefix+"cbdwOpenPlugin";
    }

    /**
     * 参会组
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("chdwOpenPlugin")
    public String chdwOpenPlugin(HttpServletRequest request, org.springframework.ui.Model model){
        /*  菜单链接:模块功能  */
        log.debug("。。。。。。。。。。。。。[构建相应的树形数据]。。。。。。。。。。。。。");
        TreeNode treeNode = new TreeNode();
        treeNode.setName("单位信息");
        treeNode.setNodeType("root");
        treeNode.setIsParent("true");
        treeNode.setIsedit("true");
        treeNode.setOpen("true");
        treeNode.setTitle("单位信息");
        treeNode.setPid("---");
        try {
            List<TreeNode> treeNodes =  zTreeService.getTree(treeNode,ZtreeEnum.XTDW,null,"false");
            /*treeNode.setChildren(treeNodes);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("treeNode",net.sf.json.JSONObject.fromObject(treeNode));
        return hyglPrefix+"chdwOpenPlugin";
    }

    /**
     * 上报材料单位(old)
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("sbcldwOpenPlugin")
    public String sbcldwOpenPlugin(HttpServletRequest request, org.springframework.ui.Model model){
        /*  菜单链接:模块功能  */
        log.debug("。。。。。。。。。。。。。[构建相应的树形数据]。。。。。。。。。。。。。");
        TreeNode treeNode = new TreeNode();
        treeNode.setName("单位信息");
        treeNode.setNodeType("root");
        treeNode.setIsParent("true");
        treeNode.setIsedit("true");
        treeNode.setOpen("true");
        treeNode.setTitle("单位信息");
        treeNode.setPid("---");
        try {
            List<TreeNode> treeNodes =  zTreeService.getTree(treeNode,ZtreeEnum.XTDW,null,"false");
            /*treeNode.setChildren(treeNodes);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("treeNode",net.sf.json.JSONObject.fromObject(treeNode));
        return hyglPrefix+"sbcldwOpenPlugin";
    }

    /**
     * 参会单位选择控件(最新)
     * @return
     */
    @RequestMapping("sbcldwOpenPluginForGrid")
    public String sbcldwOpenPluginForGrid(){
        String chzIds = request.getParameter("chzIds");
        List<TXtDwEntity> dwEntities = new ArrayList<TXtDwEntity>();

        if(chzIds != null && !"".equals(chzIds)){
            String[] chzIdstrs = chzIds.split(";");
            for(int i = 0 ;i < chzIdstrs.length; i++){
                String chzIdTmp = chzIdstrs[i];
                if(chzIdTmp!=null && !"".equals(chzIdTmp)){
                    TXtDwEntity dwEntity = dwService.findById(chzIdTmp);
                    dwEntities.add(dwEntity);
                }
            }
        }
        model.addAttribute("dwEntities",dwEntities);
        return hyglPrefix+"sbcldwOpenPluginForGrid";
    }

    /**
     * 地点查询
     * @return
     */
    @RequestMapping("hyddWinPlugin")
    public String hyddWinPlugin(){
        return hyglPrefix+"hyddWinPlugin";
    }

}
