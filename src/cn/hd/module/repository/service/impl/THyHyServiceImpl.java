package cn.hd.module.repository.service.impl;

import cn.hd.common.constant.DevContext;
import cn.hd.common.enumeration.BanjieEnum;
import cn.hd.common.enumeration.ModuleEnum;
import cn.hd.common.enumeration.OpTypeEnum;
import cn.hd.common.enumeration.ShangbaoEnum;
import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.*;
import cn.hd.module.repository.dao.*;
import cn.hd.module.repository.service.FileService;
import cn.hd.module.repository.service.THyHyService;
import cn.hd.utils.BasicEncodeUtils;
import cn.hd.utils.DateHelper;
import cn.hd.utils.GsonUtil;
import cn.hd.utils.ResponseJSON;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.*;

/**
 * 动态生成   会议表
 */
@Service("tHyHyService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class THyHyServiceImpl extends BasicImplDao<THyHyEntity> implements THyHyService {
    protected Logger log = Logger.getLogger(this.getClass().getName());
    /**
     * 注入持久层
     **/
    @Autowired
    private THyHyDao tHyHyDao;
    @Autowired
    private THyHyInstDao hyHyInstDao;
    @Autowired
    private TXtRyDao ryDao;
    @Autowired
    private TAttachConfigDao attachConfigDao;
    @Autowired
    private TWjWjDao tWjWjDao;
    @Autowired
    private TWjFjDao tWjFjDao;
    @Autowired
    private FileService fileService;
    @Autowired
    private THyChryDao chryDao;
    @Autowired
    private TChDwDao chDwDao;



    /**
     * 保存
     *
     * @param tHyHy
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON saveTHyHy(THyHyEntity tHyHy, HttpServletRequest request) throws Exception{
        ResponseJSON responseJSON = new ResponseJSON();;
        if(tHyHy.getId() != null && !"".equals(tHyHy.getId())){
            tHyHyDao.update(tHyHy);
        }else{
            //此处需要保存两方面的数据：发送方和接收方。后期过滤权限时，接收方就根据单位过滤
            tHyHyDao.save(tHyHy);
            //保存的时候  开启两个实例：发起方和接收方
            System.out.println("发起方");
            THyHyInstEntity fqfHyInstEntity = new THyHyInstEntity();
            fqfHyInstEntity.setFlagStatus(1);
            fqfHyInstEntity.setSbZt(ShangbaoEnum.notShangbao);
            fqfHyInstEntity.setBjBz(BanjieEnum.notBanjie);
            fqfHyInstEntity.setHyHyEntity(tHyHy);
            hyHyInstDao.save(fqfHyInstEntity);

            System.out.println("接收方");
            THyHyInstEntity jsfHyInstEntity = new THyHyInstEntity();
            jsfHyInstEntity.setFlagStatus(2);
            jsfHyInstEntity.setSbZt(ShangbaoEnum.notShangbao);
            jsfHyInstEntity.setBjBz(BanjieEnum.notBanjie);
            jsfHyInstEntity.setHyHyEntity(tHyHy);
            hyHyInstDao.save(jsfHyInstEntity);
        }
        MultipartHttpServletRequest multipartRequest = null;
        try {
            multipartRequest = (MultipartHttpServletRequest) request;
            // 获得文件：
            MultiValueMap<String,MultipartFile > mr = multipartRequest.getMultiFileMap();
            /* 获取附件的路径 */
            AttachConfigEntity attachConfigEntity = attachConfigDao.getByHql("from AttachConfigEntity where 1 = 1");
            String localFilePath = DevContext.ATTACH_FILE;
            if(attachConfigEntity != null){
                localFilePath = attachConfigEntity.getFilePath();
            }
            if( mr.keySet().size() == 1){
                 /* 通过主键获取文件名称。然后删除文件 */
                StringBuffer sb = new StringBuffer();
                sb.append("from TWjWjEntity t where t.logicTablename = :logicTablename and t.relationalValue = :relationalValue");
                Map<String,Object> wjParams = new HashMap<String,Object>();
                wjParams.put("relationalValue",tHyHy.getId());
                wjParams.put("logicTablename","T_HY_HY");
                List<TWjWjEntity> wjWjEntities = tWjWjDao.find(sb.toString(),wjParams);
                /* 这里需要先删除文件 */
                if(wjWjEntities != null && !wjWjEntities.isEmpty()){
                    for(TWjWjEntity wjEntity : wjWjEntities){
                        File file = new File(localFilePath);
                        if(file.exists()){
                            //fileName
                            //创建的文件名称是:文件名称任意
                            String outFilePath = file.getPath()+File.separator+wjEntity.getUuidMc();
                            System.out.println("删除旧文件："+outFilePath);
                            File deleteFile = new File(outFilePath);
                            if(deleteFile.exists()){
                                deleteFile.delete();
                            }
                        }

                    }
                }
                //还是需要删除旧的附件信息
                Map<String,Object> deleteWjParams = new HashMap<String,Object>();
                deleteWjParams.put("relationalValue",tHyHy.getId());
                //获取所有的数据然后先删除字表信息
                List<TWjWjEntity> wjEntities = tWjWjDao.find("from TWjWjEntity t where t.logicTablename='T_HY_HY' and t.relationalValue=:relationalValue and (t.ogicColumn='tzyj')",deleteWjParams);
                if(wjEntities != null && !wjEntities.isEmpty()){
                    for(int i = 0 ;i < wjEntities.size(); i++){
                        TWjWjEntity wjWjEntity = wjEntities.get(i);
                        //先删除字表
                        Map<String,Object> fjParams = new HashMap<String,Object>();
                        fjParams.put("wjId",wjWjEntity.getId());
                        tWjFjDao.executeSql("delete from T_WJ_FJ t where t.wj_id=:wjId",fjParams);
                        tWjWjDao.executeSql("delete from T_WJ_WJ t where t.id=:wjId",fjParams);
                    }
                }

            }
            for(String fileKey : mr.keySet()){
                MultipartFile file= mr.getFirst(fileKey);
                // 原始文件名
                String originalFileName = file.getOriginalFilename();
                if(originalFileName != null && !"".equals(originalFileName)){
                    //文件的二进制
                    byte[] fileBytes = file.getBytes();
                    /* 上传附件的路径 */
                    /**
                     *1：随机文件名称:年月日小时分毫秒
                     *2：
                     */
                    Long size = file.getSize();//文件大小
                    String extendName = originalFileName.substring(originalFileName.lastIndexOf(".")+1);//文件后缀名称
                    String md5 = BasicEncodeUtils.getMD5(fileBytes);/* MD5值 */
                    String SHA1 = BasicEncodeUtils.getSHA1(md5);//SHA1
                    /*********      文件     *************/
                    TWjWjEntity wjEntity = new TWjWjEntity();
                    wjEntity.setUuidMc(DateHelper.getNowPlusTimeMill()+"."+extendName);
                    wjEntity.setDx(size);
                    wjEntity.setMd5(md5);
                    wjEntity.setSha1(SHA1);
                    wjEntity.setCjSj(new Date());
                    wjEntity.setLogicTablename("T_HY_HY");//LOGIC_TABLENAME
                    wjEntity.setOgicColumn(fileKey);//逻辑列名称
                    wjEntity.setRelationalValue(tHyHy.getId());//RELATIONAL_VALUE
                    tWjWjDao.saveOrUpdate(wjEntity);
                    /*  先保存附件。 */
                    TWjFjEntity wjFjEntity = new TWjFjEntity();
                    wjFjEntity.setMc(originalFileName);
                    wjFjEntity.setMkBz(ModuleEnum.xzhyModule);
                    wjFjEntity.setMj(1);
                    //是不是报送材料
                    String bz = "hyzc".equals(fileKey)?"会议座次":"通知原件";
                    wjFjEntity.setBz(bz);
                    wjFjEntity.setCjSj(new Date());
                    wjFjEntity.setCjrId(tHyHy.getFqrEntity().getId());
                    wjFjEntity.setWjWjEntity(wjEntity);
                    tWjFjDao.saveOrUpdate(wjFjEntity);
                    /*创建相应的文件到指定的目录*/
                    //直接保存到本机服务器
                    String outFilePath = fileService.pushSystemUploadFile(file.getInputStream(), wjEntity.getUuidMc(),localFilePath);
                    if(outFilePath != null){
                        System.out.println("保存成功:!!!!!!!!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("保存一条会议数据");//操作详情//操作详情
            rz.setMk_bz(ModuleEnum.xzjdModule);//模块[需要手动修改]
            rz.setSj_id(String.valueOf(ModuleEnum.xzjdModule.getVal()));//模块ID[需要手动修改]
            rz.setLx_bz(OpTypeEnum.add);//操作类型
            rz.setBz("保存一条会议数据");//日志备注
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
     * @param tHyHy
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON updateTHyHy(THyHyEntity tHyHy) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        tHyHyDao.update(tHyHy);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息");//操作详情//操作详情
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
     * @param tHyHy
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJSON delTHyHy(THyHyEntity tHyHy) throws Exception {
        ResponseJSON responseJSON = new ResponseJSON();
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("id",tHyHy.getId());
        /* 删除 T_XT_HY_CHDW：hyId
        * T_XT_HY_SBDW:hyId
        * T_XT_HY_SJ_CHRY:hyId
        * */
        tHyHyDao.executeSql("delete from T_XT_HY_CHDW t where t.HY_ID=:id",paramMap);
        tHyHyDao.executeSql("delete from T_XT_HY_SBDW t where t.HY_ID=:id",paramMap);
        /*tHyHyDao.executeSql("delete from T_XT_HY_SJ_CHRY t where t.HY_ID=:id",paramMap);*/
        tHyHyDao.executeSql("delete from T_HY_HY t where t.id=:id",paramMap);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息");//操作详情//操作详情
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
    public ResponseJSON queryBjZtPageList(Map<String, Object> paramMap, int page, int rows) {
        ResponseJSON responseJSON = new ResponseJSON();
        Map result = new HashMap();
        StringBuffer countHql = new StringBuffer();/*手动填写总数*/
        StringBuffer rowsHql = new StringBuffer();/*手动填写分页记录*/
        /* 查询数据 */
        countHql.append("select count(*) from THyHyEntity where bjBz = :bjBz");
        rowsHql.append("from THyHyEntity where bjBz = :bjBz");

        result.put("total", tHyHyDao.count(countHql.toString(), paramMap));

        result.put("rows", tHyHyDao.find(rowsHql.toString(), paramMap, page, rows));
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
    public  Boolean isExistsBean(TXtDwEntity dwEntity,List<ChDwEntity> chDwEntities){
        Boolean isExists = Boolean.FALSE;
        String jg_name = dwEntity.getJgMc();
        String jg_id = dwEntity.getId();
        for(int i = 0 ; i< chDwEntities.size(); i++){
            ChDwEntity chDwEntity = chDwEntities.get(i);
            //单位名字相同或者id相同代表一个
            String chdw_name = chDwEntity.getChdwName();
            String chdw_id = chDwEntity.getDwId();
            if(jg_name.equals(chdw_id) || jg_name.equals(chdw_name)){
                isExists = Boolean.TRUE;
            }
        }
        return isExists;
    }

    /**
     * 未签到的单位
     * @param paramMap 传递的参数
     * @return
     */
    @Override
    public ResponseJSON loadNotQsGridData(Map<String, Object> paramMap) {
        ResponseJSON responseJSON = new ResponseJSON();
        Map result = new HashMap();
        THyHyEntity hyEntity = tHyHyDao.getByHql("from THyHyEntity where id = :id",paramMap);
        Set<TXtDwEntity> dwEntities = hyEntity.getChdwEntities();//邀请的单位
        List<ChDwEntity> chDwEntities = chDwDao.find("from ChDwEntity where hyId = :id",paramMap);//实际的单位
        //不存在的
        Iterator<TXtDwEntity> it = dwEntities.iterator();
        List<ChDwEntity> resEntity = new ArrayList<ChDwEntity>();
        while(it.hasNext()){
            TXtDwEntity dwEntity = it.next();
            if(!isExistsBean(dwEntity,chDwEntities)){
                ChDwEntity chDwEntityTmp=new ChDwEntity();
                chDwEntityTmp.setDwId(dwEntity.getId());
                chDwEntityTmp.setChdwName(dwEntity.getJgMc());
                chDwEntityTmp.setHyId(String.valueOf(paramMap.get("id")));
                //签收时间是空
                resEntity.add(chDwEntityTmp);
            }
        }
        if(resEntity.size() != 0 && resEntity.size() % 2 != 0){
            ChDwEntity chDwEntityTmp=new ChDwEntity();
            chDwEntityTmp.setDwId(null);
            chDwEntityTmp.setChdwName(null);
            chDwEntityTmp.setHyId(String.valueOf(paramMap.get("id")));
            resEntity.add(chDwEntityTmp);
        }

        result.put("rows",resEntity);
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
            rz.setBz("分页查询");//日志备注
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
     * 查询所有的参会信息相关数据
     * @param paramMap
     * @return
     */
    @Override
    public ResponseJSON loadChdwGridData(Map<String, Object> paramMap) {
        ResponseJSON responseJSON = new ResponseJSON();
        Map result = new HashMap();
        /*THyHyEntity hyEntity = tHyHyDao.getByHql("from THyHyEntity where id = :id",paramMap);*/
        //参会单位
        List list = chDwDao.findBySql(" select " +
                " T2.JG_MC JGMC," +
                " CASE WHEN T1.CH_BZ = 1 THEN TO_CHAR(T1.QD_SJ,'YYYY-MM-DD') else '未签' end REMARK " +
                " from T_XT_HY_CHDW t1 " +
                " left join T_XT_DW t2 on t1.DW_ID = t2.ID " +
                " where T1.HY_ID = :hyId " +
                " ORDER BY T1.CH_BZ ASC,T1.QD_SJ ASC ",paramMap);
        if(list != null && !list.isEmpty() && list.size() % 2 != 0){
            //自动增加一个空的
            String[] emptyStr = new String[2];
            emptyStr[0] = "";
            emptyStr[1] = "";
            list.add(emptyStr);
        }
        result.put("rows",list);
        responseJSON.setResult(result);
        responseJSON.setMsg("查询成功");
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
        /* 查询数据 */
        countHql.append("select count(*) from THyHyEntity where sbZt = :sbZt");
        result.put("total", tHyHyDao.count(countHql.toString(), paramMap));
        rowsHql.append("from THyHyEntity where sbZt = :sbZt");
        result.put("rows", tHyHyDao.find(rowsHql.toString(), paramMap, page, rows));
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
     *
     select
     p1.HYSJ,
     sum(case when p1.FLAGSTATUS = 2 then nvl(p1.hyFlagCount,0) else 0 end) zk_hyFlagCount,
     sum(case when p1.FLAGSTATUS = 2 then nvl(p2.chrycount,0) else 0 end) zk_chrycount,
     sum(case when p1.FLAGSTATUS = 2 then nvl(p2.qjrycount,0) else 0 end) zk_qjrycount,
     sum(case when p1.FLAGSTATUS = 1 then nvl(p1.hyFlagCount,0) else 0 end) cj_hyFlagCount,
     sum(case when p1.FLAGSTATUS = 1 then nvl(p2.chrycount,0) else 0 end) cj_chrycount,
     sum(case when p1.FLAGSTATUS = 1 then nvl(p2.qjrycount,0) else 0 end) cj_qjrycount
     from
     (
     select
     to_char(t2.HY_SJ,'yyyy') AS HYSJ,
     T1.FLAGSTATUS,
     COUNT('X') as hyFlagCount
     from T_HY_HY_INST t1
     left join T_HY_HY t2 on t1.INST_ID = t2.ID
     group by to_char(t2.HY_SJ,'yyyy'),
     T1.FLAGSTATUS
     )p1 left join
     (
     select
     tmp.hysj,
     sum(tmp.qjrycount) as qjrycount,
     sum(tmp.chrycount) as chrycount,
     tmp.FLAGSTATUS
     from
     (
     select
     to_char(t2.HY_SJ,'yyyy') AS HYSJ,
     case when T1.LB = 3 then count('X') else 0 end qjrycount,
     case when T1.LB <> 3 then count('X') else 0 end chrycount,
     T1.FLAGSTATUS
     from T_HY_CHRY t1
     left join T_HY_HY t2 on t1.HY_ID = t2.ID
     group by to_char(t2.HY_SJ,'yyyy'),
     T1.LB,
     T1.FLAGSTATUS
     ) tmp
     group by tmp.hysj,tmp.FLAGSTATUS
     )p2 on p1.hysj = p2.hysj and p1.FLAGSTATUS=p2.FLAGSTATUS
     group by p1.HYSJ
     * @return
     */
    @Override
    public ResponseJSON countList() {
        List list  = tHyHyDao.findBySql(" select \n" +
                "\tp1.HYSJ, \n" +
                "\tsum(case when p1.FLAGSTATUS = 2 then nvl(p1.hyFlagCount,0) else 0 end) zk_hyFlagCount, \n" +
                "\tsum(case when p1.FLAGSTATUS = 2 then nvl(p2.chrycount,0) else 0 end) zk_chrycount, \n" +
                "\tsum(case when p1.FLAGSTATUS = 2 then nvl(p2.qjrycount,0) else 0 end) zk_qjrycount, \n" +
                "\tsum(case when p1.FLAGSTATUS = 1 then nvl(p1.hyFlagCount,0) else 0 end) cj_hyFlagCount, \n" +
                "\tsum(case when p1.FLAGSTATUS = 1 then nvl(p2.chrycount,0) else 0 end) cj_chrycount, \n" +
                "\tsum(case when p1.FLAGSTATUS = 1 then nvl(p2.qjrycount,0) else 0 end) cj_qjrycount \n" +
                "from \n" +
                "(\n" +
                "select \n" +
                "\tto_char(t2.HY_SJ,'yyyy') AS HYSJ,\n" +
                "\tT1.FLAGSTATUS,\n" +
                "\tCOUNT('X') as hyFlagCount\n" +
                "from T_HY_HY_INST t1\n" +
                "left join T_HY_HY t2 on t1.INST_ID = t2.ID\n" +
                "group by to_char(t2.HY_SJ,'yyyy'),\n" +
                "T1.FLAGSTATUS\n" +
                ")p1 left join \n" +
                "( \n" +
                "select \n" +
                "\ttmp.hysj,\n" +
                "\tsum(tmp.qjrycount) as qjrycount,\n" +
                "\tsum(tmp.chrycount) as chrycount,\n" +
                "\ttmp.FLAGSTATUS\n" +
                " from \n" +
                "(\n" +
                "select \n" +
                "\tto_char(t2.HY_SJ,'yyyy') AS HYSJ, \n" +
                "\tcase when T1.LB = 3 then count('X') else 0 end qjrycount,\n" +
                "\tcase when T1.LB <> 3 then count('X') else 0 end chrycount,\n" +
                "\tT1.FLAGSTATUS\n" +
                "from T_HY_CHRY t1\n" +
                "left join T_HY_HY t2 on t1.HY_ID = t2.ID\n" +
                "group by to_char(t2.HY_SJ,'yyyy'),\n" +
                "T1.LB, \n" +
                "T1.FLAGSTATUS\n" +
                ") tmp \n" +
                "group by tmp.hysj,tmp.FLAGSTATUS\n" +
                ")p2 on p1.hysj = p2.hysj and p1.FLAGSTATUS=p2.FLAGSTATUS \n" +
                "group by p1.HYSJ");
        ResponseJSON json = new ResponseJSON();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("rows",list);
        json.setResult(map);
        return json;
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
        hql.append("from THyHy t1");
        List<THyHyEntity> list = tHyHyDao.find(hql.toString(), paramMap);
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
    public Boolean isExistsByMc(THyHyEntity tHyHy) {
        String id = tHyHy.getId();
        String mc = tHyHy.getMc();
        StringBuffer hql = new StringBuffer();
        Map<String,Object> param = new HashMap<String,Object>();

        hql.append("from THyHyEntity t where 1=1 ");
        if( id != null && !"".equals(id) ){
            hql.append("and t.id <> :id ");
            param.put("id", id);
        }
        if(mc != null && !"".equals(mc)){
            hql.append("and t.mc = :mc ");
            param.put("mc", mc);
            List<THyHyEntity> list = tHyHyDao.find(hql.toString(),param);
            if(list != null && !list.isEmpty()){
                return true;
            }
        }
        return false;
    }

    @Override
    public THyHyEntity findById(String id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        return tHyHyDao.getByHql("from THyHyEntity t where t.id = :id",params);
    }

    @Override
    public ResponseJSON refreshTjInfo(Map<String, Object> paramMap) {
        ResponseJSON responseJSON = new ResponseJSON();
        StringBuffer hql = new StringBuffer();
        hql.append("from THyHy t1");
        List<THyHyEntity> list = tHyHyDao.find(hql.toString(), paramMap);
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
    public Map<String, Object> isCHryExist(Map<String, Object> paramMap) {
        //参会人员信息

        //List<THyChryEntity> list = chryDao.find("from THyChryEntity t where t..hyId = :hyInstId",paramMap);
        THyHyInstEntity hyHyInstEntity = hyHyInstDao.getByHql("from THyHyInstEntity t where t.id = :hyInstId",paramMap);
        //是否具有参会人员信息
        Set<THyChryEntity> list = hyHyInstEntity.getHyHyEntity().getChryEntities();
        //参会单位 有
        Set<TXtDwEntity> dwEntities = hyHyInstEntity.getHyHyEntity().getChdwEntities();
        /* 实际参会单位：循环没有出现的单位名称 */
        String mycxdwList = getNotExists(list,dwEntities);
        
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("isExist",(list!=null&&!list.isEmpty())?true:false);
        params.put("mycxdwList",mycxdwList);
        params.put("hymcj",mycxdwList!=null && !"".equals(mycxdwList)?true:false);
        
        
        return params;
    }
    public String getNotExists(Set<THyChryEntity> list,Set<TXtDwEntity> dwEntities){
//    	List<String> tmpList = new ArrayList<String>();
    	StringBuffer tmpList = new StringBuffer();
    	
    	Iterator<TXtDwEntity> lrIt = dwEntities.iterator();
    	
    	while(lrIt.hasNext()){
    		TXtDwEntity lrdw = lrIt.next();
    		String lrdwTmp = lrdw.getJgMc();
    		Boolean isNothas = false;
    		
    		Iterator<THyChryEntity> sjIt = list.iterator();
        	while(sjIt.hasNext()){
        		THyChryEntity sjChdw = sjIt.next();
        		String sjdwNameTmp = sjChdw.getDwName();
        		if (sjdwNameTmp.equals(lrdwTmp)) {
        			isNothas = true;
				}
        	}
        	if(!isNothas){
        		tmpList.append(lrdwTmp).append("<br>");
        	}
    	}
    	return tmpList.toString();
    };
    @Override
    public ResponseJSON updateTHyHyForSbZtById(THyHyEntity tHyHy) throws Exception {

        ResponseJSON responseJSON = new ResponseJSON();
        THyHyEntity hyTmp = new THyHyEntity();
        hyTmp.setId(tHyHy.getId());
        //hyTmp.setSbZt(ShangbaoEnum.shangbao);
        Map<String,Object> updateParams = new HashMap<String,Object>();
        updateParams.put("id",tHyHy.getId());
        updateParams.put("sbZt",ShangbaoEnum.shangbao);
        tHyHyDao.executeHql("update THyHyEntity t set t.sbZt = :sbZt where t.id = :id",updateParams);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("模块[xxxx],保存基本信息");//操作详情//操作详情
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

    @Override
    public ResponseJSON updateTHyHyForBjZtById(THyHyEntity tHyHy) throws Exception {

        ResponseJSON responseJSON = new ResponseJSON();
        THyHyEntity hyTmp = new THyHyEntity();
        hyTmp.setId(tHyHy.getId());
        //hyTmp.setBjBz(BanjieEnum.banjied);
        Map<String,Object> updateParams = new HashMap<String,Object>();
        updateParams.put("id",tHyHy.getId());
        updateParams.put("bjBz",BanjieEnum.banjied);
        /*updateParams.put("bz","asdfasdf");*/
        tHyHyDao.executeHql("update THyHyEntity t set t.bjBz=:bjBz where id=:id",updateParams);
        //tHyHyDao.executeHql("update THyHyEntity t set t.bjZt = :bjZt",updateParams);
        try {
            T_xt_rz rz = new T_xt_rz();
            /** 日志记录信息* */
            /********** 根据业务不同设置相应的参数值 ***********/
            rz.setNr("办结成功");//操作详情//操作详情
            rz.setMk_bz(ModuleEnum.xzjdModule);//模块[需要手动修改]
            rz.setSj_id(String.valueOf(ModuleEnum.xzjdModule.getVal()));//模块ID[需要手动修改]
            rz.setLx_bz(OpTypeEnum.update);//操作类型
            rz.setBz("办结成功");//日志备注
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

}