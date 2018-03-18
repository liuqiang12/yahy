package cn.hd.module.repository.service.impl;

import cn.hd.common.enumeration.ModuleEnum;
import cn.hd.common.enumeration.OpTypeEnum;
import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.THyChryEntity;
import cn.hd.entity.TXtDwEntity;
import cn.hd.entity.TXtRyEntity;
import cn.hd.entity.T_xt_rz;
import cn.hd.module.repository.dao.THyChryDao;
import cn.hd.module.repository.dao.TXtDwDao;
import cn.hd.module.repository.service.THyChryService;
import cn.hd.utils.GsonUtil;
import cn.hd.utils.ResponseJSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;

/**
 * 动态生成   参会人员
 */
@Service("tHyChryService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class THyChryServiceImpl extends BasicImplDao<THyChryEntity> implements THyChryService {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    /**
     * 注入持久层
     **/
    @Autowired
    private THyChryDao tHyChryDao;
    @Autowired
    private TXtDwDao tXtDwDao;

    /**
     * 保存
     *
     * @param tHyChry
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON saveTHyChry(THyChryEntity tHyChry) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        T_xt_rz rz = new T_xt_rz();
        if(tHyChry.getId() !=null && !"".equals(tHyChry.getId())){
            tHyChryDao.update(tHyChry);
            rz.setNr(tHyChry.getHyHyEntity().getMc()+"|修改了一条参会人员信息.");//操作详情
            rz.setLx_bz(OpTypeEnum.update);//操作类型
            responseJSON.setMsg("修改成功!");
        }else{
            tHyChryDao.save(tHyChry);
            rz.setNr(tHyChry.getHyHyEntity().getMc()+"|保存了一条参会人员信息.");//操作详情
            rz.setLx_bz(OpTypeEnum.add);//操作类型
            responseJSON.setMsg("保存成功!");
            //修改参会单位的标志是1 表示已经参会 同时增加参会时间
            BigInteger isHaveChBzAttr = tXtDwDao.countBySql("select count('X') from user_col_comments where TABLE_NAME='T_XT_HY_CHDW' and column_name=upper('CH_BZ')");
            if ( isHaveChBzAttr.bitCount() == 0 ){
                System.out.println("不存在列属性:参会标志-------CH_BZ  0未参加 1已经参加");
                tXtDwDao.executeSql("alter table T_XT_HY_CHDW add (CH_BZ varchar2(32) default 0)");
                tXtDwDao.executeSql("alter table T_XT_HY_CHDW add (QD_SJ DATE)");
            }
            //通过参会人员单位ID和会议ID   修改标志1 和  签到时间
            String dwId = tHyChry.getDwId();
            String hyId = tHyChry.getHyHyEntity().getId();
            Map<String,Object> xtdwMap = new HashMap<String,Object>();
            if(dwId != null && !"".equals(dwId)){
                xtdwMap.put("dwId",dwId);
                xtdwMap.put("hyId",hyId);
                List chDwList = tXtDwDao.findBySql("select * from T_XT_HY_CHDW t where t.DW_ID=:dwId and t.HY_ID=:hyId",xtdwMap);
                if(chDwList != null && !chDwList.isEmpty()){
                    tXtDwDao.executeSql("update T_XT_HY_CHDW set CH_BZ=1 , QD_SJ=sysdate where DW_ID=:dwId and HY_ID=:hyId and QD_SJ is null",xtdwMap);
                }
            }else{
                System.out.println("如果没有传递单位ID则需要匹配名字，再修改");
                Map<String,Object> xtdwParamMap = new HashMap<String,Object>();
                xtdwParamMap.put("jgMc",tHyChry.getDwName());
                List<TXtDwEntity> regDwlist = tXtDwDao.find("from TXtDwEntity t where t.jgMc = :jgMc",xtdwParamMap);
                /* 系统单位  然后匹配  用户人员 */
                TXtDwEntity xtDwEntity = isExistDwRy(regDwlist,tHyChry.getXm());
                if(xtDwEntity !=  null){
                    xtdwMap.put("dwId",xtDwEntity.getId());
                    xtdwMap.put("hyId",hyId);
                    List chDwList = tXtDwDao.findBySql("select * from T_XT_HY_CHDW t where t.DW_ID=:dwId and t.HY_ID=:hyId",xtdwMap);
                    if(chDwList != null && !chDwList.isEmpty()){
                        tXtDwDao.executeSql("update T_XT_HY_CHDW set CH_BZ=1 , QD_SJ=sysdate where DW_ID=:dwId and HY_ID=:hyId and QD_SJ is null",xtdwMap);
                    }
                }
            }
        }
        try {
            /** 日志记录信息*** 根据业务不同设置相应的参数值 ***********/
            rz.setMk_bz(ModuleEnum.chryModule);//模块[需要手动修改]
            rz.setSj_id(String.valueOf(ModuleEnum.chryModule.getVal()));//模块ID[需要手动修改]
            rz.setLx_bz(OpTypeEnum.add);//操作类型
            rz.setBz("");//日志备注
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
     * 单位存在的人员
     *
     * @param regDwlist
     * @return
     */
    public TXtDwEntity isExistDwRy(List<TXtDwEntity> regDwlist,String userName){
        Iterator<TXtDwEntity> it = regDwlist.iterator();
        while(it.hasNext()){
            TXtDwEntity dwEntity = it.next();
            Set<TXtRyEntity> ryEntitySet = dwEntity.getRyEntities();
            Iterator<TXtRyEntity> ryIt = ryEntitySet.iterator();
            while(ryIt.hasNext()){
                TXtRyEntity ryEntityTmp = ryIt.next();
                if(ryEntityTmp.getXm().equals(userName)){
                    return dwEntity;
                }
            }
        }
        return null;
    }

    /**
     * 修改
     *
     * @param tHyChry
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON updateTHyChry(THyChryEntity tHyChry) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tHyChryDao.update(tHyChry);
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
     * @param tHyChry
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON delTHyChry(THyChryEntity tHyChry) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tHyChryDao.delete(tHyChry);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("");//操作详情
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
        result.put("total", tHyChryDao.count(countHql.toString(), paramMap));
        result.put("rows", tHyChryDao.find(rowsHql.toString(), paramMap, page, rows));
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
        hql.append("from THyChryEntity tmp left join fetch tmp.hyHyEntity t where t.id = :hyId order by tmp.lb asc");

        List<THyChryEntity> list = tHyChryDao.find(hql.toString(), paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("rows", list);
        responseJSON.setResult(resMap);
        responseJSON.setMsg("查询成功");
        return responseJSON;
    }

    @Override
    public ResponseJSON queryWinList(Map<String, Object> paramMap) {
        ResponseJSON responseJSON = new ResponseJSON();
        StringBuffer hql = new StringBuffer();
        hql.append("from THyChryEntity t1 where 1=1");
        if(paramMap.get("hyId") != null){
            hql.append(" and t1.hyHyEntity.id = :hyId");
        }
        if(paramMap.get("lb") != null){
            hql.append(" and t1.lb = :lb");
        }
        List<THyChryEntity> list = tHyChryDao.find(hql.toString(), paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("rows", list);
        responseJSON.setResult(resMap);
        responseJSON.setMsg("查询成功");
        /*try {
            log.debug("日志记录...............");
            T_xt_rz rz = new T_xt_rz();
            *//** 日志记录信息* *//*
            *//********** 根据业务不同设置相应的参数值 ***********//*
            rz.setNr("模块[xxxx],查询所有的数据,查询条件:" + ((paramMap == null || paramMap.isEmpty()) ? "无" : GsonUtil.object2Json(paramMap)));//操作详情
            rz.setMk_bz(ModuleEnum.orgModule);//模块[需要手动修改]
            rz.setSj_id(String.valueOf(ModuleEnum.orgModule.getVal()));//模块ID[需要手动修改]
            rz.setLx_bz(OpTypeEnum.query);//操作类型
            rz.setBz("查询所有的数据");
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

    @Override
    public THyChryEntity findById(String id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        return tHyChryDao.getByHql("from THyChryEntity t where t.id = :id",params);
    }

    @Override
    public ResponseJSON delTHyChryById(THyChryEntity tHyChry) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",tHyChry.getId());
        tHyChryDao.executeHql("delete THyChryEntity t where id = :id",params);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("删除一条参会人员");//操作详情
            rz.setMk_bz(ModuleEnum.chryModule);//模块[需要手动修改]
            rz.setSj_id(String.valueOf(ModuleEnum.chryModule.getVal()));//模块ID[需要手动修改]
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

    @Override
    public List<THyChryEntity> queryForWordWinList(Map<String, Object> paramMap) {
        StringBuffer hql = new StringBuffer();
        hql.append("from THyChryEntity t1 where 1=1");
        if(paramMap.get("hyId") != null){
            hql.append(" and t1.hyId = :hyId");
        }
        if(paramMap.get("lb") != null){
            hql.append(" and t1.lb = :lb");
        }
        List<THyChryEntity> list = tHyChryDao.find(hql.toString(), paramMap);
        return list;
    }
}