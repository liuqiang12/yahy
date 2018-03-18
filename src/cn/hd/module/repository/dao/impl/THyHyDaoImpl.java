package cn.hd.module.repository.dao.impl;


import cn.hd.common.util.BasicImplDao;
import cn.hd.entity.THyHyEntity;
import cn.hd.module.repository.dao.THyHyDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* 动态生成持久层  会议表
*/
@Repository
@Qualifier("tHyHyDao")
public class THyHyDaoImpl extends BasicImplDao<THyHyEntity> implements THyHyDao {

}