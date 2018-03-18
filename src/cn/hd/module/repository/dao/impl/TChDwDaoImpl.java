package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.ChDwEntity;
import cn.hd.entity.THyChdwEntity;
import cn.hd.module.repository.dao.TChDwDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  
*/
@Repository
@Qualifier("tChDwDao")
public class TChDwDaoImpl extends BasicImplDao<ChDwEntity> implements TChDwDao {

}