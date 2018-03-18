package cn.hd.module.repository.service.impl;

import cn.hd.common.enumeration.BanjieEnum;
import cn.hd.common.enumeration.ModuleEnum;
import cn.hd.common.enumeration.OpTypeEnum;
import cn.hd.common.enumeration.ShangbaoEnum;
import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.THyHyInstEntity;
import cn.hd.entity.T_xt_rz;
import cn.hd.module.repository.dao.THyHyInstDao;
import cn.hd.module.repository.service.THyHyInstService;
import cn.hd.utils.GsonUtil;
import cn.hd.utils.ResponseJSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态生成
 */
@Service("tHyHyInstService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class THyHyInstServiceImpl extends BasicImplDao<THyHyInstEntity> implements THyHyInstService {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    /**
     * 注入持久层
     **/
    @Autowired
    private THyHyInstDao tHyHyInstDao;

    /**
     * 保存
     *
     * @param tHyHyInst
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON saveTHyHyInst(THyHyInstEntity tHyHyInst) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tHyHyInstDao.save(tHyHyInst);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("");//操作详情
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
     * @param tHyHyInst
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON updateTHyHyInst(THyHyInstEntity tHyHyInst) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tHyHyInstDao.update(tHyHyInst);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("");//操作详情
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
     * @param tHyHyInst
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON delTHyHyInst(THyHyInstEntity tHyHyInst) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",tHyHyInst.getId());
        tHyHyInst  = tHyHyInstDao.getByHql("from THyHyInstEntity t where t.id = :id",params);
        tHyHyInstDao.delete(tHyHyInst);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("删除未办结一条记录");//操作详情
            rz.setMk_bz(ModuleEnum.bajModule);//模块[需要手动修改]
            rz.setSj_id(String.valueOf(ModuleEnum.bajModule.getVal()));//模块ID[需要手动修改]
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

        countHql.append("select count(*) from THyHyInstEntity tmp where tmp.flagStatus = :flagStatus");
        if(Integer.valueOf(String.valueOf(paramMap.get("flagStatus"))) == 1){
            //接收方
            countHql.append(" and tmp.sbZt=:sbZt");
        }else{
            countHql.append(" and tmp.bjBz=:bjBz");
        }

        rowsHql.append("from THyHyInstEntity tmp left join fetch tmp.hyHyEntity t where tmp.flagStatus = :flagStatus");
        if(Integer.valueOf(String.valueOf(paramMap.get("flagStatus"))) == 1){
            //接收方
            rowsHql.append(" and tmp.sbZt=:sbZt");
        }else{
            rowsHql.append(" and tmp.bjBz=:bjBz");
        }
        result.put("total", tHyHyInstDao.count(countHql.toString(), paramMap));
        result.put("rows", tHyHyInstDao.find(rowsHql.toString(), paramMap, page, rows));
        responseJSON.setResult(result);
        responseJSON.setMsg("查询成功");
        /*try {
            log.debug("日志记录...............");
            T_xt_rz rz = new T_xt_rz();
            *//** 日志记录信息* *//*
            *//********** 根据业务不同设置相应的参数值 ***********//*
            rz.setNr("模块[xxxx],查询所有的数据");//操作详情
            rz.setMk_bz(ModuleEnum.orgModule);//模块[需要手动修改]
            rz.setSj_id(String.valueOf(ModuleEnum.orgModule.getVal()));//模块ID[需要手动修改]
            rz.setLx_bz(OpTypeEnum.query);//操作类型
            rz.setBz("分页查询,当前的页数和条数分别是:[" + page + "," + rows + "]");//日志备注
            rz.setCj_sj(new Date());//创建时间
            *//*********** 需要从前端传递start ************//*
            rz.setCjr_fid("创建人单位ID");//创建人单位ID
            rz.setCjr_id("创建人ID");//创建人ID
            *//*********** 需要从前端传递 end ************//*
            responseJSON.setT_xt_rz(rz);
        } catch (Exception e) {
            log.debug("日志报错...............:" + e.getMessage());
        }*/

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
        hql.append("from THyHyInst t1");
        List<THyHyInstEntity> list = tHyHyInstDao.find(hql.toString(), paramMap);
        /*responseJSON.setResult(list);*/
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
    public THyHyInstEntity findById(String hyInstId) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",hyInstId);
        return tHyHyInstDao.getByHql("from THyHyInstEntity t where t.id = :id",params);
    }

    @Override
    public ResponseJSON updateForBjZtById(String hyInstId) throws Exception{
        //修改状态是办结状态
        ResponseJSON responseJSON = new ResponseJSON();
        Map<String,Object> updateParams = new HashMap<String,Object>();
        updateParams.put("id",hyInstId);
        updateParams.put("bjBz", BanjieEnum.banjied);
        /*updateParams.put("bz","asdfasdf");*/
        tHyHyInstDao.executeHql("update THyHyInstEntity t set t.bjBz=:bjBz where id=:id",updateParams);
        try {
            log.debug("日志记录...............");
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("办结一条参加会议记录");//操作详情
            rz.setMk_bz(ModuleEnum.cjhywbModule);//模块[需要手动修改]
            rz.setSj_id(String.valueOf(ModuleEnum.cjhywbModule.getVal()));//模块ID[需要手动修改]
            rz.setLx_bz(OpTypeEnum.update);//操作类型
            rz.setBz("办结一条参加会议记录");
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
    public ResponseJSON updateForSbZtyId(String hyInstId) throws Exception{
        //修改状态是办结状态
        ResponseJSON responseJSON = new ResponseJSON();
        Map<String,Object> updateParams = new HashMap<String,Object>();
        updateParams.put("id",hyInstId);
        updateParams.put("sbZt", ShangbaoEnum.shangbao);
        tHyHyInstDao.executeHql("update THyHyInstEntity t set t.sbZt=:sbZt where id=:id",updateParams);
        try {
            log.debug("日志记录...............");
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("报送一条参加会议名单");//操作详情
            rz.setMk_bz(ModuleEnum.bsmdModule);//模块[需要手动修改]
            rz.setSj_id(String.valueOf(ModuleEnum.bsmdModule.getVal()));//模块ID[需要手动修改]
            rz.setLx_bz(OpTypeEnum.update);//操作类型
            rz.setBz("报送一条参加会议名单");
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
}