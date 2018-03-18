package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.TXtRyEntity;
import cn.hd.module.repository.dao.TXtRyDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  系统人员
*/
@Repository
@Qualifier("tXtRyDao")
public class TXtRyDaoImpl extends BasicImplDao<TXtRyEntity> implements TXtRyDao {

}