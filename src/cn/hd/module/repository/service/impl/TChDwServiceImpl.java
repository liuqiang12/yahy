package cn.hd.module.repository.service.impl;

import cn.hd.common.enumeration.ModuleEnum;
import cn.hd.common.enumeration.OpTypeEnum;
import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.ChDwEntity;
import cn.hd.entity.THyHyEntity;
import cn.hd.entity.TXtDwEntity;
import cn.hd.entity.T_xt_rz;
import cn.hd.module.repository.dao.TChDwDao;
import cn.hd.module.repository.dao.THyHyDao;
import cn.hd.module.repository.service.TChDwService;
import cn.hd.utils.DateHelper;
import cn.hd.utils.GsonUtil;
import cn.hd.utils.ResponseJSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 动态生成
 */
@Service("tChDwService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class TChDwServiceImpl extends BasicImplDao<ChDwEntity> implements TChDwService {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    /**
     * 注入持久层
     **/
    @Autowired
    private TChDwDao tChDwDao;
    @Autowired
    private THyHyDao hyHyDao;//会议

    /**
     * 保存
     *
     * @param tChDw
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON saveTChDw(ChDwEntity tChDw) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tChDwDao.save(tChDw);
        try {
            T_xt_rz rz = new T_xt_rz();
/** 日志记录信息* */
/********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tChDw));//操作详情
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
     * @param tChDw
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON updateTChDw(ChDwEntity tChDw) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tChDwDao.update(tChDw);
        try {
            T_xt_rz rz = new T_xt_rz();
/** 日志记录信息* */
/********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tChDw));//操作详情
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
     * @param tChDw
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON delTChDw(ChDwEntity tChDw) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tChDwDao.delete(tChDw);
        try {
            T_xt_rz rz = new T_xt_rz();
/** 日志记录信息* */
/********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tChDw));//操作详情
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
        result.put("total", tChDwDao.count(countHql.toString(), paramMap));
        result.put("rows", tChDwDao.find(rowsHql.toString(), paramMap, page, rows));
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
// 不存在参会单位中的数据
        hql.append("from ChDwEntity t1 where t1.hyId = :hyId");
        List<ChDwEntity> list = tChDwDao.find(hql.toString(), paramMap);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", list);
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
    public List<ChDwEntity> findByHyId(String hyId) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("hyId",hyId);
        List<ChDwEntity> yqjsList =  tChDwDao.find("from ChDwEntity t where t.hyId = :hyId",params);//已经签收


        Iterator<ChDwEntity> chit = yqjsList.iterator();
        while(chit.hasNext()){
            ChDwEntity dwtmp = chit.next();
            dwtmp.setQssj(DateHelper.getFormatsdfLongTimePlus(dwtmp.getQsSj().getTime()));
            dwtmp.setChdw(dwtmp.getChdwName());
        }
        //未签收
        THyHyEntity hyHyEntity = hyHyDao.getByHql("from THyHyEntity t where t.id = :hyId",params);
        Set<TXtDwEntity> xtdwList = hyHyEntity.getChdwEntities();
        Iterator<TXtDwEntity> it = xtdwList.iterator();
        while(it.hasNext()){
            TXtDwEntity dwtmp = it.next();
            if(!isExists(yqjsList,dwtmp)){
                ChDwEntity chDwEntity = new ChDwEntity();
                chDwEntity.setChdw(dwtmp.getJgMc());
                chDwEntity.setChdw(dwtmp.getJgMc());
                chDwEntity.setQssj("未签收");
                yqjsList.add(chDwEntity);
            }
        }
        return yqjsList;
    }

    @Override
    public List<ChDwEntity> findByHyId4Total(String hyId) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("hyId",hyId);
        List<ChDwEntity> yqjsList =  tChDwDao.find("from ChDwEntity t where t.hyId = :hyId",params);//已经签收
        return yqjsList;

    }

    @Override
    public List queryListWord(String hyId) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("hyId",hyId);
        return tChDwDao.findBySql(" select " +
                " T2.JG_MC JGMC," +
                " CASE WHEN T1.CH_BZ = 1 THEN TO_CHAR(T1.QD_SJ,'YYYY-MM-DD') else '未签' end REMARK " +
                " from T_XT_HY_CHDW t1 " +
                " left join T_XT_DW t2 on t1.DW_ID = t2.ID " +
                " where T1.HY_ID = :hyId " +
                " ORDER BY T1.CH_BZ ASC,T1.QD_SJ ASC ",paramMap);
    }


    public Boolean isExists( List<ChDwEntity> yqjsList, TXtDwEntity dwEntity){
        Boolean isEXIST = Boolean.FALSE;
        Iterator<ChDwEntity> it = yqjsList.iterator();
        while(it.hasNext()){
            ChDwEntity chDwTmp = it.next();
            if(chDwTmp.getChdwName().equals(dwEntity.getJgMc()) || dwEntity.getId().equals(chDwTmp.getDwId())){
                isEXIST = Boolean.TRUE;
                break;
            }
        }
        return isEXIST;
    }
}