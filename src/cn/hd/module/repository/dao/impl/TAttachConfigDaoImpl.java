package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.AttachConfigEntity;
import cn.hd.module.repository.dao.TAttachConfigDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  
*/
@Repository
@Qualifier("tAttachConfigDao")
public class TAttachConfigDaoImpl extends BasicImplDao<AttachConfigEntity> implements TAttachConfigDao {

}