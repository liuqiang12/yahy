package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.TXtCbryEntity;
import cn.hd.module.repository.dao.TXtCbryDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  承办人员表[从系统用户中选定而来]
*/
@Repository
@Qualifier("tXtCbryDao")
public class TXtCbryDaoImpl extends BasicImplDao<TXtCbryEntity> implements TXtCbryDao {

}