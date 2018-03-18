package cn.hd.module.repository.service.impl;

import cn.hd.common.enumeration.ModuleEnum;
import cn.hd.common.enumeration.OpTypeEnum;
import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.TOperateEntity;
import cn.hd.entity.T_xt_rz;
import cn.hd.module.repository.dao.TXtOperateDao;
import cn.hd.module.repository.service.TXtOperateService;
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
@Service("tXtOperateService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class TXtOperateServiceImpl extends BasicImplDao<TOperateEntity> implements TXtOperateService {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    /**
     * 注入持久层
     **/
    @Autowired
    private TXtOperateDao tXtOperateDao;

    /**
     * 保存
     *
     * @param tXtOperate
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON saveTXtOperate(TOperateEntity tXtOperate) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tXtOperateDao.save(tXtOperate);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tXtOperate));//操作详情
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
     * @param tXtOperate
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON updateTXtOperate(TOperateEntity tXtOperate) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tXtOperateDao.update(tXtOperate);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tXtOperate));//操作详情
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
     * @param tXtOperate
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON delTXtOperate(TOperateEntity tXtOperate) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tXtOperateDao.delete(tXtOperate);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tXtOperate));//操作详情
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

        countHql.append("select count(*) from TOperateEntity");
        rowsHql.append("from TOperateEntity");

        result.put("total", tXtOperateDao.count(countHql.toString(), paramMap));
        result.put("rows", tXtOperateDao.find(rowsHql.toString(), paramMap, page, rows));
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
        hql.append("from TXtOperate t1");
        List<TOperateEntity> list = tXtOperateDao.find(hql.toString(), paramMap);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list",list);
        responseJSON.setResult(map);
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
    public void contextInitData() throws Exception {
        /**
         * 初始化数据
         */
        List<TOperateEntity> list = new ArrayList<TOperateEntity>();

        TOperateEntity operateEntity1 = new TOperateEntity();
        operateEntity1.setFlag(1);
        operateEntity1.setBz("");
        operateEntity1.setIsHold(0);//是否拥有
        operateEntity1.setMc("分配人员");
        TOperateEntity operateEntity2 = new TOperateEntity();
        operateEntity2.setFlag(1);
        operateEntity2.setBz("");
        operateEntity2.setIsHold(0);//是否拥有
        operateEntity2.setMc("初审");

        TOperateEntity operateEntity3 = new TOperateEntity();
        operateEntity3.setFlag(1);
        operateEntity3.setBz("");
        operateEntity3.setIsHold(0);//是否拥有
        operateEntity3.setMc("复审");

        TOperateEntity operateEntity4 = new TOperateEntity();
        operateEntity4.setFlag(1);
        operateEntity4.setBz("");
        operateEntity4.setIsHold(0);//是否拥有
        operateEntity4.setMc("编辑");

        TOperateEntity operateEntity5 = new TOperateEntity();
        operateEntity5.setFlag(1);
        operateEntity5.setBz("");
        operateEntity5.setIsHold(0);//是否拥有
        operateEntity5.setMc("删除");
        list.add(operateEntity1);
        list.add(operateEntity2);
        list.add(operateEntity3);
        list.add(operateEntity4);
        list.add(operateEntity5);

        Long count =  tXtOperateDao.count("select count(*) from TOperateEntity t where t.flag=1");
        if(count < 5){
            tXtOperateDao.executeHql("delete from TOperateEntity t where t.flag=1");
            for(int i = 0 ; i < list.size(); i++){
                tXtOperateDao.save(list.get(i));
            }
        }
    }

    @Override
    public ResponseJSON updateOperateIsHold(TOperateEntity tXtOperate) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",tXtOperate.getId());
        params.put("userIds",tXtOperate.getUserIds());
        params.put("userNames",tXtOperate.getUserNames());
        /* 填写中文信息 */

        tXtOperateDao.executeHql("update TOperateEntity t set t.userIds=:userIds,t.userNames=:userNames where t.id=:id",params);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("修改一条功能分配绑定人员");//操作详情
            rz.setMk_bz(ModuleEnum.gnfpModule);//模块[需要手动修改]
            rz.setSj_id(String.valueOf(ModuleEnum.gnfpModule.getVal()));//模块ID[需要手动修改]
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



}