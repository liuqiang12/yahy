package cn.hd.module.repository.service.impl;

import cn.hd.common.enumeration.ModuleEnum;
import cn.hd.common.enumeration.OpTypeEnum;
import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.*;
import cn.hd.module.repository.dao.TXtHyddDao;
import cn.hd.module.repository.service.TXtHyddService;
import cn.hd.utils.GsonUtil;
import cn.hd.utils.ResponseJSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 动态生成   会议地点
 */
@Service("tXtHyddService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class TXtHyddServiceImpl extends BasicImplDao<TXtHyddEntity> implements TXtHyddService {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    /**
     * 注入持久层
     **/
    @Autowired
    private TXtHyddDao tXtHyddDao;

    /**
     * 保存
     *
     * @param tXtHydd
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON saveTXtHydd(TXtHyddEntity tXtHydd) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tXtHyddDao.save(tXtHydd);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tXtHydd));//操作详情
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
     * @param tXtHydd
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON updateTXtHydd(TXtHyddEntity tXtHydd) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tXtHyddDao.update(tXtHydd);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tXtHydd));//操作详情
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
     * @param tXtHydd
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON delTXtHydd(TXtHyddEntity tXtHydd) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tXtHyddDao.delete(tXtHydd);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息,基本信息内容:" + GsonUtil.object2Json(tXtHydd));//操作详情
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
        result.put("total", tXtHyddDao.count(countHql.toString(), paramMap));
        result.put("rows", tXtHyddDao.find(rowsHql.toString(), paramMap, page, rows));
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
        hql.append("from TXtHyddEntity t1");
        List<TXtHyddEntity> list = tXtHyddDao.find(hql.toString(), paramMap);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("rows", list);
        responseJSON.setResult(resMap);
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
        List<TXtHyddEntity> list = new ArrayList<TXtHyddEntity>();
        TXtHyddEntity hyddEntity1 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity1.setSort(1);
        hyddEntity1.setMc("市委党校一楼会议室");
        hyddEntity1.setFlag(1);
        TXtHyddEntity hyddEntity2 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity2.setSort(2);
        hyddEntity2.setMc("市委党校二楼会议室");
        hyddEntity2.setFlag(1);
        TXtHyddEntity hyddEntity3 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity3.setSort(3);
        hyddEntity3.setMc("市委党校三楼会议室");
        hyddEntity3.setFlag(1);
        TXtHyddEntity hyddEntity4 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity4.setSort(4);
        hyddEntity4.setMc("市委党校四楼会议室");
        hyddEntity4.setFlag(1);
        TXtHyddEntity hyddEntity5 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity5.setSort(5);
        hyddEntity5.setMc("市委党校五楼会议室");
        hyddEntity5.setFlag(1);
        TXtHyddEntity hyddEntity6 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity6.setSort(6);
        hyddEntity6.setMc("市委党校六楼会议室");
        hyddEntity6.setFlag(1);
        TXtHyddEntity hyddEntity7 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity7.setSort(7);
        hyddEntity7.setMc("市委党校七楼会议室");
        hyddEntity7.setFlag(1);
        TXtHyddEntity hyddEntity8 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity8.setSort(8);
        hyddEntity8.setMc("市委党校八楼会议室");
        hyddEntity8.setFlag(1);
        TXtHyddEntity hyddEntity9 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity9.setSort(9);
        hyddEntity9.setMc("市委党校九楼会议室");
        hyddEntity9.setFlag(1);
        TXtHyddEntity hyddEntity10 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity10.setSort(10);
        hyddEntity10.setMc("市委党校十楼会议室");
        hyddEntity10.setFlag(1);
        TXtHyddEntity hyddEntity11 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity11.setSort(11);
        hyddEntity11.setMc("市委党校十一楼会议室");
        hyddEntity11.setFlag(1);
        TXtHyddEntity hyddEntity12 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity12.setSort(12);
        hyddEntity12.setMc("市委党校十二楼会议室");
        hyddEntity12.setFlag(1);
        TXtHyddEntity hyddEntity13 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity13.setSort(13);
        hyddEntity13.setMc("市委党校十三楼会议室");
        hyddEntity13.setFlag(1);
        TXtHyddEntity hyddEntity14 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity14.setSort(14);
        hyddEntity14.setMc("市委党校十四楼会议室");
        hyddEntity14.setFlag(1);
        TXtHyddEntity hyddEntity15 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity15.setSort(15);
        hyddEntity15.setMc("市委党校十五楼会议室");
        hyddEntity15.setFlag(1);
        TXtHyddEntity hyddEntity16 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity16.setSort(16);
        hyddEntity16.setMc("市委党校十六楼会议室");
        hyddEntity16.setFlag(1);
        TXtHyddEntity hyddEntity17 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity17.setSort(17);
        hyddEntity17.setMc("市委党校十七楼会议室");
        hyddEntity17.setFlag(1);
        TXtHyddEntity hyddEntity18 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity18.setSort(18);
        hyddEntity18.setMc("市委党校十八楼会议室");
        hyddEntity18.setFlag(1);
        TXtHyddEntity hyddEntity19 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity19.setSort(19);
        hyddEntity19.setMc("市委党校十九楼会议室");
        hyddEntity19.setFlag(1);
        TXtHyddEntity hyddEntity20 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity20.setSort(20);
        hyddEntity20.setMc("市委党校二十楼会议室");
        hyddEntity20.setFlag(1);
        TXtHyddEntity hyddEntity21 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity21.setSort(21);
        hyddEntity21.setMc("市委党校二十一楼会议室");
        hyddEntity21.setFlag(1);
        TXtHyddEntity hyddEntity22 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity22.setSort(22);
        hyddEntity22.setMc("市委党校二十二楼会议室");
        hyddEntity22.setFlag(1);
        TXtHyddEntity hyddEntity23 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity23.setSort(23);
        hyddEntity23.setMc("市委党校二十三楼会议室");
        hyddEntity23.setFlag(1);
        TXtHyddEntity hyddEntity24 = new TXtHyddEntity();/* 会议地点信息 */
        hyddEntity24.setSort(24);
        hyddEntity24.setMc("市委党校二十四楼会议室");
        hyddEntity24.setFlag(1);
        list.add(hyddEntity1);
        list.add(hyddEntity2);
        list.add(hyddEntity3);
        list.add(hyddEntity4);
        list.add(hyddEntity5);
        list.add(hyddEntity6);
        list.add(hyddEntity7);
        list.add(hyddEntity8);
        list.add(hyddEntity9);
        list.add(hyddEntity10);
        list.add(hyddEntity11);
        list.add(hyddEntity12);
        list.add(hyddEntity13);
        list.add(hyddEntity14);
        list.add(hyddEntity15);
        list.add(hyddEntity16);
        list.add(hyddEntity17);
        list.add(hyddEntity18);
        list.add(hyddEntity19);
        list.add(hyddEntity20);
        list.add(hyddEntity21);
        list.add(hyddEntity22);
        list.add(hyddEntity23);
        list.add(hyddEntity24);

        Long count =  tXtHyddDao.count("select count(*) from TXtHyddEntity t where t.flag=1");
        if(count < 24){
            tXtHyddDao.executeHql("delete from TXtHyddEntity t where t.flag=1");
            for(int i = 0 ;i < list.size(); i++){
                tXtHyddDao.save(list.get(i));
            }
        }
    }

    @Override
    public TXtHyddEntity findById(String id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        return tXtHyddDao.getByHql("from TXtHyddEntity t where t.id = :id",params);
    }
}