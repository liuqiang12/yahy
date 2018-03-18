package cn.hd.module.repository.service.impl;

import cn.hd.common.constant.DevContext;
import cn.hd.common.enumeration.ModuleEnum;
import cn.hd.common.enumeration.OpTypeEnum;
import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.*;
import cn.hd.module.repository.dao.TAttachConfigDao;
import cn.hd.module.repository.dao.THyHyDao;
import cn.hd.module.repository.dao.TWjWjDao;
import cn.hd.module.repository.service.TWjWjService;
import cn.hd.utils.GsonUtil;
import cn.hd.utils.ResponseJSON;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

/**
 * 动态生成   物理文件表
 */
@Service("tWjWjService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class TWjWjServiceImpl extends BasicImplDao<TWjWjEntity> implements TWjWjService {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    /**
     * 注入持久层
     **/
    @Autowired
    private TWjWjDao tWjWjDao;
    @Autowired
    private THyHyDao hyHyDao;
    @Autowired
    private TAttachConfigDao attachConfigDao;

    /**
     * 保存
     *
     * @param tWjWj
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON saveTWjWj(TWjWjEntity tWjWj) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tWjWjDao.save(tWjWj);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tWjWj));//操作详情
            rz.setMk_bz(ModuleEnum.orgModule);//模块[需要手动修改]
            rz.setSj_id(String.valueOf(ModuleEnum.orgModule.getVal()));//模块ID[需要手动修改]
            rz.setLx_bz(OpTypeEnum.add);//操作类型
            rz.setBz("报错操作");//日志备注
            rz.setCj_sj(new Date());//创建时间
            /*********** 需要从前端传递start ************/
            rz.setCjr_fid("创建人单位ID");//创建人单位ID
            rz.setCjr_id("创建人ID");//创建人ID
            /*********** 需要从前端传递 end ************/
            responseJSON.setT_xt_rz(rz);
            responseJSON.setMsg("保存成功!");
        } catch (Exception e) {
            log.debug("日志报错...............:" + e.getMessage());
        }
        return responseJSON;
    }

    /**
     * 修改
     *
     * @param tWjWj
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON updateTWjWj(TWjWjEntity tWjWj) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tWjWjDao.update(tWjWj);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tWjWj));//操作详情
            rz.setMk_bz(ModuleEnum.orgModule);//模块[需要手动修改]
            rz.setSj_id(String.valueOf(ModuleEnum.orgModule.getVal()));//模块ID[需要手动修改]
            rz.setLx_bz(OpTypeEnum.update);//操作类型
            rz.setBz("修改操作");//日志备注
            rz.setCj_sj(new Date());//创建时间
            /*********** 需要从前端传递start ************/
            rz.setCjr_fid("创建人单位ID");//创建人单位ID
            rz.setCjr_id("创建人ID");//创建人ID
            /*********** 需要从前端传递 end ************/
            responseJSON.setMsg("修改成功");
            responseJSON.setT_xt_rz(rz);
        } catch (Exception e) {
            log.debug("日志报错...............:" + e.getMessage());
        }
        return responseJSON;
    }

    /**
     * 删除
     *
     * @param tWjWj
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON delTWjWj(TWjWjEntity tWjWj) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tWjWjDao.delete(tWjWj);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tWjWj));//操作详情
            rz.setMk_bz(ModuleEnum.orgModule);//模块[需要手动修改]
            rz.setSj_id(String.valueOf(ModuleEnum.orgModule.getVal()));//模块ID[需要手动修改]
            rz.setLx_bz(OpTypeEnum.delete);//操作类型
            rz.setBz("删除操作");//日志备注
            rz.setCj_sj(new Date());//创建时间
            /*********** 需要从前端传递start ************/
            rz.setCjr_fid("创建人单位ID");//创建人单位ID
            rz.setCjr_id("创建人ID");//创建人ID
            /*********** 需要从前端传递 end ************/
            responseJSON.setMsg("删除成功");
            responseJSON.setT_xt_rz(rz);
        } catch (Exception e) {
            log.debug("日志报错...............:" + e.getMessage());
        }
        return responseJSON;
    }

    /**
     * 分页查询
     *
     * @param paramMap 传递的参数
     * @param page     页号
     * @param rows     条数
     * @return
     */
    @Override
    public ResponseJSON queryPageList(Map<String, Object> paramMap, int page, int rows) {
        ResponseJSON responseJSON = new ResponseJSON();
        Map result = new HashMap();
        StringBuffer countHql = new StringBuffer();/*手动填写总数*/
        StringBuffer rowsHql = new StringBuffer();/*手动填写分页记录*/
        result.put("total", tWjWjDao.count(countHql.toString(), paramMap));
        result.put("rows", tWjWjDao.find(rowsHql.toString(), paramMap, page, rows));
        responseJSON.setResult(result);
        responseJSON.setMsg("查询成功");
        try {
            log.debug("日志记录...............");
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],查询所有的数据,查询条件:" + ((paramMap == null || paramMap.isEmpty()) ? "无" : GsonUtil.object2Json(paramMap)));//操作详情
            rz.setMk_bz(ModuleEnum.orgModule);//模块[需要手动修改]
            rz.setSj_id(String.valueOf(ModuleEnum.orgModule.getVal()));//模块ID[需要手动修改]
            rz.setLx_bz(OpTypeEnum.query);//操作类型
            rz.setBz("分页查询,当前的页数和条数分别是:[" + page + "," + rows + "]");//日志备注
            rz.setCj_sj(new Date());//创建时间
            /*********** 需要从前端传递start ************/
            rz.setCjr_fid("创建人单位ID");//创建人单位ID
            rz.setCjr_id("创建人ID");//创建人ID
            /*********** 需要从前端传递 end ************/
            responseJSON.setT_xt_rz(rz);
        } catch (Exception e) {
            log.debug("日志报错...............:" + e.getMessage());
        }

        return responseJSON;
    }

    /**
     * 查询所有的数据
     *
     * @param paramMap 传递的参数
     * @return
     */
    @Override
    public ResponseJSON queryList(Map<String, Object> paramMap) {
        ResponseJSON responseJSON = new ResponseJSON();
        StringBuffer hql = new StringBuffer();
        hql.append("from TWjWj t1");
        List<TWjWjEntity> list = tWjWjDao.find(hql.toString(), paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("rows", list);
        responseJSON.setMsg("查询成功");
        try {
            log.debug("日志记录...............");
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],查询所有的数据,查询条件:" + ((paramMap == null || paramMap.isEmpty()) ? "无" : GsonUtil.object2Json(paramMap)));//操作详情
            rz.setMk_bz(ModuleEnum.orgModule);//模块[需要手动修改]
            rz.setSj_id(String.valueOf(ModuleEnum.orgModule.getVal()));//模块ID[需要手动修改]
            rz.setLx_bz(OpTypeEnum.query);//操作类型
            rz.setBz("查询所有的数据");
            rz.setCj_sj(new Date());//创建时间
            /*********** 需要从前端传递start ************/
            rz.setCjr_fid("创建人单位ID");//创建人单位ID
            rz.setCjr_id("创建人ID");//创建人ID
            /*********** 需要从前端传递 end ************/
            responseJSON.setT_xt_rz(rz);
        } catch (Exception e) {
            log.debug("日志报错...............:" + e.getMessage());
        }
        return responseJSON;
    }

    @Override
    public ResponseJSON queryBsclList(Map<String, Object> paramMap) {
        StringBuffer sb = new StringBuffer();
        sb.append("from TWjWjEntity t where t.logicTablename = :logicTablename and t.ogicColumn = :ogicColumn and t.relationalValue = :relationalValue");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("logicTablename","T_HY_HY");
        params.put("ogicColumn","bscl");
        params.put("relationalValue",paramMap.get("hyId"));
        /* 获取了所有的报送材料:其实就一个 */
        List<TWjWjEntity> list = tWjWjDao.find(sb.toString(),params);
        ResponseJSON responseJSON = new ResponseJSON();
        responseJSON.setMsg("查询成功!");
        Map<String,Object> reMap = new HashMap<String,Object>();
        //会议信息：包含了上报材料单位。以分号隔开
        THyHyEntity hyEntity = hyHyDao.getByHql("from THyHyEntity t where t.id = :hyId",paramMap);
        Set<TXtDwEntity> sbclDw = hyEntity.getSbcldwEntities();
        Iterator<TXtDwEntity> it = sbclDw.iterator();
        String dwStrs = "";
        while(it.hasNext()){
            TXtDwEntity tXtDwEntityTmp = it.next();
            dwStrs += tXtDwEntityTmp.getJgMc()+";";
        }
        if (dwStrs != null && !"".equals(dwStrs)){
            dwStrs = dwStrs.substring(0,dwStrs.length() - 1);
        }
        responseJSON.setDwStrs(dwStrs);
        reMap.put("rows",list);
        reMap.put("dwStrs",dwStrs);
        responseJSON.setResult(reMap);
        return responseJSON;
    }

    @Override
    public void downLoadFile(HttpServletResponse response, TWjWjEntity wjEntity) throws Exception {
        /* 获取文件名称 */
        StringBuffer sb = new StringBuffer();
        sb.append("from TWjWjEntity t where t.logicTablename = :logicTablename and t.ogicColumn = :ogicColumn and t.relationalValue = :relationalValue");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("logicTablename",wjEntity.getLogicTablename());
        params.put("ogicColumn",wjEntity.getOgicColumn());
        params.put("relationalValue",wjEntity.getRelationalValue());
        wjEntity = tWjWjDao.getByHql(sb.toString(),params);
        
        if(wjEntity == null){
        	 //alert到界面提示框
            PrintWriter out = response.getWriter();
            response.setContentType("text/html; charset=utf-8");
            out.print("<script>" +
                    "alert('文件缺失');window.close();</script>");
            out.flush();
            out.close();
        }else{
        	 /* 获取文件路径 */
            AttachConfigEntity attachConfigEntity = attachConfigDao.getByHql("from AttachConfigEntity where 1 = 1");
            String localFilePath = DevContext.ATTACH_FILE;
            if(attachConfigEntity != null){
                localFilePath = attachConfigEntity.getFilePath();
            }
            /**
             * 读取文件流情况
             */
            System.out.println("然后获取本地的附件流[.......]");
            if (localFilePath == null || "".equalsIgnoreCase(localFilePath)) {
                /*文件缺失*/
                System.out.println("附件文件缺失”"+localFilePath+"“，请检查ERROR!ERROR!ERROR!");
                
              //alert到界面提示框
                PrintWriter out = response.getWriter();
                response.setContentType("text/html; charset=utf-8");
                out.print("<script>" +
                        "alert('文件缺失');window.close();</script>");
                out.flush();
                out.close();
                
                
            } else {
                String fileName = wjEntity.getUuidMc();
                File file = new File(localFilePath+File.separator+fileName);
                if (!file.exists()) {
                    System.out.println("---------------文件缺失---------------");
                    //alert到界面提示框
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html; charset=utf-8");
                    out.print("<script>" +
                            "alert('文件缺失');window.close();</script>");
                    out.flush();
                    out.close();
                } else {
                    System.out.println("开始下载相应的文件:[" + fileName + "]");
                    FileInputStream in = new FileInputStream(file);
                    response.reset(); // 非常重要
                    downAsmentFile(in, response, fileName);
                }
            }
        }
        
       
    }

    @Override
    public List<TWjWjEntity> findByJdxxId(String id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("relationalValue",id);
        params.put("logicTablename","T_JD_JDXX");
        return tWjWjDao.find("from TWjWjEntity t where t.logicTablename = :logicTablename and t.relationalValue = :relationalValue and (t.ogicColumn='jyfafj' or t.ogicColumn='fgldqpj')",params);
    }

    //下载附件
    public void downAsmentFile(InputStream in, HttpServletResponse response, String fileName) {
        if (fileName == null || "".equalsIgnoreCase(fileName)) {
            System.out.println("附件名称没有" + fileName);
            
          //alert到界面提示框
            PrintWriter out;
			try {
				out = response.getWriter();
				response.setContentType("text/html; charset=utf-8");
				 out.print("<script>" +
		                    "alert('文件缺失');window.close();</script>");
	            out.flush();
	            out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
            
        }

        if (in != null) {
            OutputStream out = null;
            try {
                response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
                BufferedInputStream br = new BufferedInputStream(in);
                byte[] buf = new byte[1024];
                int len = 0;
                out = response.getOutputStream();
                while ((len = br.read(buf)) > 0) {
                    out.write(buf, 0, len);
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}