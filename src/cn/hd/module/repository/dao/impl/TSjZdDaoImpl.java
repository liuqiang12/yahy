package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.TSjZdEntity;
import cn.hd.module.repository.dao.TSjZdDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  数据字典
*/
@Repository
@Qualifier("tSjZdDao")
public class TSjZdDaoImpl extends BasicImplDao<TSjZdEntity> implements TSjZdDao {

}