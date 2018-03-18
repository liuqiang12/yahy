package cn.hd.module.repository.service.impl;

import cn.hd.common.enumeration.GenderEnum;
import cn.hd.common.enumeration.ModuleEnum;
import cn.hd.common.enumeration.OpTypeEnum;
import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.TXtDwEntity;
import cn.hd.entity.TXtJsEntity;
import cn.hd.entity.TXtRyEntity;
import cn.hd.entity.T_xt_rz;
import cn.hd.module.repository.dao.TXtDwDao;
import cn.hd.module.repository.dao.TXtJsDao;
import cn.hd.module.repository.dao.TXtRyDao;
import cn.hd.module.repository.service.TXtRyService;
import cn.hd.utils.GsonUtil;
import cn.hd.utils.ResponseJSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 动态生成   系统人员
 */
@Service("tXtRyService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class TXtRyServiceImpl extends BasicImplDao<TXtRyEntity> implements TXtRyService {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    /**
     * 注入持久层
     **/
    @Autowired
    private TXtRyDao tXtRyDao;
    @Autowired
    private TXtDwDao xtDwDao;
    @Autowired
    private TXtJsDao jsDao;

    /**
     * 保存
     *
     * @param tXtRy
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON saveTXtRy(TXtRyEntity tXtRy) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tXtRyDao.saveOrUpdate(tXtRy);
        Map<String,Object> resMap = new HashMap<String,Object>();
        resMap.put("resObject",tXtRy);
        responseJSON.setResult(resMap);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tXtRy));//操作详情
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
     * @param tXtRy
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON updateTXtRy(TXtRyEntity tXtRy) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tXtRyDao.update(tXtRy);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tXtRy));//操作详情
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
     * @param tXtRy
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON delTXtRy(TXtRyEntity tXtRy) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tXtRyDao.delete(tXtRy);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tXtRy));//操作详情
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
     * 分页查询:排除已经在会议的相关人员
     *
     * @param paramMap 传递的参数
     * @param page     页号
     * @param rows     条数
     * @return
     */
    @Override
    public ResponseJSON queryPageListOtherHyId(Map<String, Object> paramMap, int page, int rows) {
        ResponseJSON responseJSON = new ResponseJSON();
        Map result = new HashMap();
        StringBuffer countHql = new StringBuffer();/*手动填写总数*/
        /* 获取所有的参会人员中的人员id */
        countHql.append("select count(*) from TXtRyEntity t where not exists( select tmp.id from THyChryEntity tmp  where t.id = tmp.ryId and tmp.hyHyEntity.id = :hyId)");
        if(paramMap.get("dwId") != null){
            countHql.append(" and t.xtDwEntity.id = :dwId");
        }
        StringBuffer rowsHql = new StringBuffer();/*手动填写分页记录*/
        rowsHql.append("from TXtRyEntity t where not exists( select tmp.id from THyChryEntity tmp   where t.id = tmp.ryId and tmp.hyHyEntity.id = :hyId)");

        if(paramMap.get("dwId") != null){
            rowsHql.append(" and t.xtDwEntity.id = :dwId");
        }

        result.put("total", tXtRyDao.count(countHql.toString(), paramMap));
        result.put("rows", tXtRyDao.find(rowsHql.toString(), paramMap, page, rows));

        responseJSON.setResult(result);
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
            rz.setBz("分页查询,当前的页数和条数分别是:[" + page + "," + rows + "]");//日志备注
            rz.setCj_sj(new Date());//创建时间
            *//*********** 需要从前端传递start ************//*
            rz.setCjr_fid("创建人单位ID");//创建人单位ID
            rz.setCjr_id("创建人ID");//创建人ID
            *//*********** 需要从前端传递 end ************//*
            responseJSON.setT_xt_rz(rz);
        } catch (Exception e) {
            log.debug("日志报错...............:" + e.getMessage());
        }
*/
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
        countHql.append("select count(*) from TXtRyEntity t where 1 = 1");
        if(paramMap.get("dwId") != null){
            countHql.append(" and t.xtDwEntity.id = :dwId");
        }
        StringBuffer rowsHql = new StringBuffer();/*手动填写分页记录*/
        rowsHql.append("from TXtRyEntity t where 1 = 1");//人员信息

        if(paramMap.get("dwId") != null){
            rowsHql.append(" and t.xtDwEntity.id = :dwId");
        }

        result.put("total", tXtRyDao.count(countHql.toString(), paramMap));
        result.put("rows", tXtRyDao.find(rowsHql.toString(), paramMap, page, rows));

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
        hql.append("from TXtRy t1");
        List<TXtRyEntity> list = tXtRyDao.find(hql.toString(), paramMap);
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
    public TXtRyEntity findById(String id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        return tXtRyDao.getByHql("from TXtRyEntity t where t.id = :id",params);
    }
    @Override
    public List<TXtRyEntity> findByFpryStatus(Integer fpryStatus) {
        StringBuffer sb = new StringBuffer();
        sb.append("from TXtRyEntity t where t.fpryStatus = :fpryStatus");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("fpryStatus",fpryStatus);
        return tXtRyDao.find(sb.toString(),params);
    }

    @Override
    public void  contextInitData() throws Exception {
        /**
         * 初始化数据
         */
        List<TXtRyEntity> list = new ArrayList<TXtRyEntity>();

        TXtRyEntity ryEntity = new TXtRyEntity();
        ryEntity.setXm("杨柳周");//角色
        ryEntity.setXb(GenderEnum.male);
        ryEntity.setZw("无");
        ryEntity.setLxfs("13438571235");
        ryEntity.setFpryStatus(0);
        ryEntity.setCjSj(new Date());
        ryEntity.setBz("无");
        /* 所属单位：默认单位 */
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("flag",1);
        List<TXtDwEntity> dwEntities = xtDwDao.find("from TXtDwEntity t where t.flag = :flag",params);
        if(dwEntities != null && !dwEntities.isEmpty()){
            ryEntity.setXtDwEntity(dwEntities.get(0));
        }
        ryEntity.setFlag(1);
        Long count =  tXtRyDao.count("select count(*) from TXtRyEntity t where t.flag=1");

        Map<String,Object> jsMap = new HashMap<String,Object>();
        jsMap.put("jsBm","1001001");
        jsMap.put("flag",1);
        TXtJsEntity jsEntity = jsDao.getByHql("from TXtJsEntity t where t.jsBm = :jsBm and t.flag = :flag",jsMap);
        Set<TXtJsEntity> jsEntities = new HashSet<TXtJsEntity>();
        jsEntities.add(jsEntity);
        ryEntity.setJsEntities(jsEntities);
        if(count < 1){
            tXtRyDao.executeHql("delete from TXtRyEntity t where t.flag=1");
            tXtRyDao.save(ryEntity);
        }
        //设置分配人员:查看是否具有分配人员：
        Long fpryCount = tXtRyDao.count("select count(*) from TXtRyEntity t where t.fpryStatus=1 and t.flag=1");
        /**
         * <label><input type="radio" name="peo">关晓松</label>
         <label><input type="radio" name="peo">王茜</label>
         <label><input type="radio" name="peo">程访</label>
         <label><input type="radio" name="peo">胡婧</label>
         <label><input type="radio" name="peo">刘波</label>
         <label><input type="radio" name="peo">蒲秋威</label>
         <label><input type="radio" name="peo">宋东琴</label>
         */

        if(fpryCount < 7){
            tXtRyDao.executeHql("delete TXtRyEntity t where t.fpryStatus=1 and t.flag=1");
            for(int i = 0 ;i < 7 ; i++){
                String name = i==0?"关晓松":
                i==1?"王茜":
                i==2?"程访":
                i==3?"胡婧":
                i==4?"刘波":
                i==5?"蒲秋威":
                i==6?"宋东琴":
                "";
                TXtRyEntity fpryEntity = new TXtRyEntity();
                fpryEntity.setXm(name);//角色
                fpryEntity.setXb(GenderEnum.male);
                fpryEntity.setZw("无");
                fpryEntity.setLxfs("13438571235");
                fpryEntity.setFpryStatus(1);
                fpryEntity.setCjSj(new Date());
                fpryEntity.setBz("无");
                fpryEntity.setXtDwEntity(dwEntities.get(0));
                fpryEntity.setFlag(1);
                fpryEntity.setJsEntities(jsEntities);
                tXtRyDao.save(fpryEntity);
            }
        }






    }

    @Override
    public ResponseJSON defaultInfo() {
        ResponseJSON responseJSON = new ResponseJSON();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("flag",1);
        TXtRyEntity ryEntity = tXtRyDao.getByHql("from TXtRyEntity t where t.flag = :flag",params);
        if(ryEntity != null && ryEntity.getXtDwEntity() == null){
            TXtDwEntity dwEntity = xtDwDao.getByHql("from TXtDwEntity t where t.flag = 1");
            responseJSON.setXtDwEntity(dwEntity);
        }else{
            responseJSON.setXtDwEntity(ryEntity.getXtDwEntity());
        }
        responseJSON.setSuccess(true);
        responseJSON.setRyEntity(ryEntity);

       /* Map<String,Object> resMap = new HashMap<String,Object>();
         *//*获取默认的单位 *//*
        TXtDwEntity dwEntity = xtDwDao.getByHql("from TXtDwEntity t where t.flag = 1");
        ryEntity.setXtDwEntity(dwEntity);
        resMap.put("ryEntity",ryEntity);
        responseJSON.setResult(resMap);*/
        return responseJSON;
    }

    @Override
    public TXtRyEntity getAuthUserByFlag(String hql, Map<String, Object> params) {
        List<TXtRyEntity> ryEntities = tXtRyDao.find("from TXtRyEntity t1 left join fetch t1.xtDwEntity t2 where t1.flag=:flag and t2.id is not null",params);
        if(ryEntities != null && !ryEntities.isEmpty()){
            return ryEntities.get(0);
        }
        return null;
    }

    @Override
    public ResponseJSON queryMulitSelectList(String dwId) {
        ResponseJSON responseJSON = new ResponseJSON();
        StringBuffer rowsHql = new StringBuffer();/*手动填写分页记录*/
        rowsHql.append("from TXtRyEntity t where t.xtDwEntity.id = :dwId");//人员信息
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("dwId",dwId);
        List<TXtRyEntity> list = tXtRyDao.find(rowsHql.toString(), paramMap);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("rows",list);
        responseJSON.setResult(resultMap);
        return responseJSON;
    }
}