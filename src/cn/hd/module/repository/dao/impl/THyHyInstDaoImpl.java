package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.THyHyInstEntity;
import cn.hd.module.repository.dao.THyHyInstDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * 动态生成持久层
 */
@Repository
@Qualifier("tHyHyInstDao")
public class THyHyInstDaoImpl extends BasicImplDao<THyHyInstEntity> implements THyHyInstDao {

}