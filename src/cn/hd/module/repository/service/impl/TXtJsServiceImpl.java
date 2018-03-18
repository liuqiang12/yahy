package cn.hd.module.repository.service.impl;

import cn.hd.common.enumeration.ActiveEnum;
import cn.hd.common.enumeration.ModuleEnum;
import cn.hd.common.enumeration.OpTypeEnum;
import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.TXtJsEntity;
import cn.hd.entity.T_xt_rz;
import cn.hd.module.repository.dao.TXtJsDao;
import cn.hd.module.repository.service.TXtJsService;
import cn.hd.utils.GsonUtil;
import cn.hd.utils.ResponseJSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 动态生成   角色表
 */
@Service("tXtJsService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class TXtJsServiceImpl extends BasicImplDao<TXtJsEntity> implements TXtJsService {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    /**
     * 注入持久层
     **/
    @Autowired
    private TXtJsDao tXtJsDao;

    /**
     * 保存
     *
     * @param tXtJs
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON saveTXtJs(TXtJsEntity tXtJs) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tXtJsDao.save(tXtJs);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tXtJs));//操作详情
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
     * @param tXtJs
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON updateTXtJs(TXtJsEntity tXtJs) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tXtJsDao.update(tXtJs);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tXtJs));//操作详情
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
     * @param tXtJs
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON delTXtJs(TXtJsEntity tXtJs) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tXtJsDao.delete(tXtJs);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tXtJs));//操作详情
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
        result.put("total", tXtJsDao.count(countHql.toString(), paramMap));
        result.put("rows", tXtJsDao.find(rowsHql.toString(), paramMap, page, rows));
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
        hql.append("from TXtJsEntity t1");
        List<TXtJsEntity> list = tXtJsDao.find(hql.toString(), paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("rows", list);
        responseJSON.setMsg("查询成功");
        responseJSON.setResult(resMap);
        try {
            log.debug("日志记录...............");
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],查询所有的数据" );//操作详情
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
    public void contextInitData() throws Exception {
        /**
         * 初始化数据
         */
        List<TXtJsEntity> list = new ArrayList<TXtJsEntity>();

        TXtJsEntity jsEntity1 = new TXtJsEntity();
        jsEntity1.setFlag(1);
        jsEntity1.setActiveZt(ActiveEnum.active);
        jsEntity1.setJsBm("1001001");
        jsEntity1.setMc("系统管理员");
        TXtJsEntity jsEntity2 = new TXtJsEntity();
        jsEntity2.setFlag(1);
        jsEntity2.setActiveZt(ActiveEnum.active);
        jsEntity2.setJsBm("1001002");
        jsEntity2.setMc("接待办管理人员");

        TXtJsEntity jsEntity3 = new TXtJsEntity();
        jsEntity3.setFlag(1);
        jsEntity3.setActiveZt(ActiveEnum.active);
        jsEntity3.setJsBm("1001003");
        jsEntity3.setMc("接待办普通人员");

        TXtJsEntity jsEntity4 = new TXtJsEntity();
        jsEntity4.setFlag(1);
        jsEntity4.setActiveZt(ActiveEnum.active);
        jsEntity4.setJsBm("1001004");
        jsEntity4.setMc("其他单位人员");
        list.add(jsEntity1);
        list.add(jsEntity2);
        list.add(jsEntity3);
        list.add(jsEntity4);
        Long count =  tXtJsDao.count("select count(*) from TXtJsEntity t where t.flag=1");
        if(count < 4){
            tXtJsDao.executeHql("delete from TXtJsEntity t where t.flag=1");
            for(int i = 0 ; i < list.size(); i++){
                tXtJsDao.save(list.get(i));
            }
        }
    }
}